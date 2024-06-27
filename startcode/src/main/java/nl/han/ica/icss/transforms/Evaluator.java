package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Evaluator implements Transform {

    private LinkedList<HashMap<String, Literal>> variableValues;

    public Evaluator() {
        variableValues = new LinkedList<>();
    }

    @Override
    public void apply(AST ast) {
        applyStylesheet(ast.root);
    }

    private void applyStylesheet(Stylesheet styleSheet) {
        variableValues.addFirst(new HashMap<>());

        List<ASTNode> checkedNodes = new ArrayList<>();
        for (ASTNode child : styleSheet.getChildren()) {
            if (child instanceof Stylerule) {
                applyStylerule((Stylerule) child);
            } else if (child instanceof VariableAssignment) {
                applyVariableAssignment((VariableAssignment) child);
                checkedNodes.add(child);
            }
        }
        // Only remove checkNodes that are VariableAssignments
        checkedNodes.forEach(styleSheet::removeChild);

        variableValues.removeFirst();
    }

    private void applyStylerule(Stylerule rule) {
        variableValues.addFirst(new HashMap<>());

        ArrayList<ASTNode> nodesToAdd = new ArrayList<>();
        for (ASTNode body : rule.body) {
            applyStyleruleBody(body, nodesToAdd);
        }
        variableValues.removeFirst();
        rule.body = nodesToAdd;
    }

    private void applyStyleruleBody(ASTNode body, ArrayList<ASTNode> parent) {
        if (body instanceof Declaration) {
            applyDeclaration((Declaration) body);
            parent.add(body);
        } else if (body instanceof VariableAssignment) {
            applyVariableAssignment((VariableAssignment) body);
        } else if (body instanceof IfClause) {
            System.out.println("Applying IfClause");
            applyIfClause((IfClause) body, parent);
        }
    }

    private void applyVariableAssignment(VariableAssignment variableAssignment) {
        Literal evaluatedExpression = applyExpression(variableAssignment.expression);
        if (evaluatedExpression != null) {
            variableValues.getFirst().put(variableAssignment.name.name, evaluatedExpression);
        }
    }

    private Literal applyExpression(Expression expression) {
        if (expression == null) {
            return null;
        }
        if (expression instanceof Operation) {
            return applyOperation((Operation) expression);
        } else if (expression instanceof VariableReference) {
            return getVariableLiteral(((VariableReference) expression).name);
        } else if (expression instanceof Literal) {
            return (Literal) expression;
        } else if (expression instanceof BoolExpression) {
            return applyBoolExpression((BoolExpression) expression);
        } else if (expression instanceof BoolCheck) {
            return applyBoolCheck((BoolCheck) expression);
        } else {
            System.out.println("Unsupported expression type: " + expression.getClass().getName());
            return null;
        }
    }

    private Literal applyOperation(Operation operation) {
        Literal left = applyExpression(operation.lhs);
        Literal right = applyExpression(operation.rhs);

        if (left == null || right == null) {
            return null;
        }

        int leftValue = getLiteralValue(left);
        int rightValue = getLiteralValue(right);

        if (operation instanceof AddOperation) {
            return createLiteral(left, leftValue + rightValue);
        } else if (operation instanceof SubtractOperation) {
            return createLiteral(left, leftValue - rightValue);
        } else if (operation instanceof MultiplyOperation) {
            return createLiteral(left, leftValue * rightValue);
        } else {
            System.out.println("Unsupported operation: " + operation.getClass().getName());
            return null;
        }
    }

    private Literal applyBoolExpression(BoolExpression expression) {
        Literal left = applyExpression(expression.lhs);
        Literal right = applyExpression(expression.rhs);

        if (left instanceof BoolLiteral && right instanceof BoolLiteral) {
            boolean leftValue = ((BoolLiteral) left).value;
            boolean rightValue = ((BoolLiteral) right).value;

            if (expression instanceof AndOperation) {
                return new BoolLiteral(leftValue && rightValue);
            } else if (expression instanceof OrOperation) {
                return new BoolLiteral(leftValue || rightValue);
            }
        }

        System.out.println("Unsupported boolean expression: " + expression.getClass().getName());
        return null;
    }

    private Literal applyBoolCheck(BoolCheck boolCheck) {
        Literal left = applyExpression(boolCheck.lhs);
        Literal right = applyExpression(boolCheck.rhs);

        if (left == null || right == null) {
            return null;
        }

        int leftValue = getLiteralValue(left);
        int rightValue = getLiteralValue(right);
        boolean result;

        if (boolCheck instanceof EqualOperation) {
            result = leftValue == rightValue;
        } else if (boolCheck instanceof NotEqualOperation) {
            result = leftValue != rightValue;
        } else if (boolCheck instanceof GreaterOperation) {
            result = leftValue > rightValue;
        } else if (boolCheck instanceof GreaterEqualOperation) {
            result = leftValue >= rightValue;
        } else if (boolCheck instanceof SmallerOperation) {
            result = leftValue < rightValue;
        } else if (boolCheck instanceof SmallerEqualOperation) {
            result = leftValue <= rightValue;
        } else {
            System.out.println("Unsupported boolean check: " + boolCheck.getClass().getName());
            return null;
        }

        return new BoolLiteral(result);
    }

    private Literal getVariableLiteral(String variableReference) {
        for (HashMap<String, Literal> variableValue : variableValues) {
            Literal variable = variableValue.get(variableReference);
            if (variable != null) {
                return variable;
            }
        }
        System.out.println("Variable '" + variableReference + "' not found in current scope.");
        return null;
    }

    private void applyIfClause(IfClause ifClause, ArrayList<ASTNode> parent) {
        Literal condition = applyExpression(ifClause.conditionalExpression);

        if (!(condition instanceof BoolLiteral)) {
            System.out.println("IfClause condition is not a boolean");
            return;
        }

        boolean conditionValue = ((BoolLiteral) condition).value;

        if (conditionValue) {
            for (ASTNode child : ifClause.body) {
                applyStyleruleBody(child, parent);
            }
        } else if (ifClause.elseClause != null) {
            for (ASTNode child : ifClause.elseClause.body) {
                applyStyleruleBody(child, parent);
            }
        }
    }

    private void applyDeclaration(Declaration declaration) {
        declaration.expression = applyExpression(declaration.expression);
    }

    private int getLiteralValue(Literal literal) {
        if (literal instanceof PixelLiteral) {
            return ((PixelLiteral) literal).value;
        } else if (literal instanceof ScalarLiteral) {
            return ((ScalarLiteral) literal).value;
        } else if (literal instanceof PercentageLiteral) {
            return ((PercentageLiteral) literal).value;
        } else if (literal instanceof BoolLiteral) {
            return ((BoolLiteral) literal).value ? 1 : 0;
        } else {
            System.out.println("Unsupported literal type: " + literal.getClass().getName());
            return 0;
        }
    }

    private Literal createLiteral(Literal literal, int value) {
        if (literal instanceof PixelLiteral) {
            return new PixelLiteral(value);
        } else if (literal instanceof ScalarLiteral) {
            return new ScalarLiteral(value);
        } else if (literal instanceof PercentageLiteral) {
            return new PercentageLiteral(value);
        } else {
            System.out.println("Unsupported literal type for creating: " + literal.getClass().getName());
            return null;
        }
    }
}
