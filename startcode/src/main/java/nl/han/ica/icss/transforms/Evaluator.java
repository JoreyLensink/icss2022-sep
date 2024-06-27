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
        variableValues.removeFirst();
        checkedNodes.forEach(styleSheet::removeChild);
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
            applyIfClause((IfClause) body, parent);
        }
    }

    private void applyVariableAssignment(VariableAssignment variableAssignment) {
        variableAssignment.expression = applyExpression(variableAssignment.expression);
        if (variableAssignment.expression != null) {
            variableValues.getFirst().put(variableAssignment.name.name, (Literal) variableAssignment.expression);
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
        }
        System.out.println("Expression not found: " + expression.getClass().getName());
        return null;
    }

    private Literal applyOperation(Operation operation) {
        if (operation.lhs == null || operation.rhs == null) {
            return null;
        }
        Literal left = applyExpression(operation.lhs);
        Literal right = applyExpression(operation.rhs);

        int leftValue = getLiteralValue(left);
        int rightValue = getLiteralValue(right);

        if (operation instanceof AddOperation) {
            return createLiteral(left, leftValue + rightValue);
        } else if (operation instanceof SubtractOperation) {
            return createLiteral(left, leftValue - rightValue);
        } else if (operation instanceof MultiplyOperation) {
            if (left instanceof ScalarLiteral) {
                return createLiteral(right, leftValue * rightValue);
            } else {
                return createLiteral(left, leftValue * rightValue);
            }
        }
        return null;
    }

    private Literal applyBoolExpression(BoolExpression expression) {
        if (expression.lhs == null || expression.rhs == null) {
            return null;
        }
        Literal left = applyExpression(expression.lhs);
        Literal right = applyExpression(expression.rhs);

        boolean result = false;
        if (expression instanceof AndOperation) {
            result = ((BoolLiteral) left).value && ((BoolLiteral) right).value;
        } else if (expression instanceof OrOperation) {
            result = ((BoolLiteral) left).value || ((BoolLiteral) right).value;
        }

        return new BoolLiteral(result);
    }

    private Literal applyBoolCheck(BoolCheck boolCheck) {
        if (boolCheck.lhs == null || boolCheck.rhs == null) {
            return null;
        }
        Literal left = applyExpression(boolCheck.lhs);
        Literal right = applyExpression(boolCheck.rhs);

        boolean result = false;
        if (boolCheck instanceof SmallerEqualOperation) {
            result = getLiteralValue(left) <= getLiteralValue(right);
        } else if (boolCheck instanceof EqualOperation) {
            result = getLiteralValue(left) == getLiteralValue(right);
        } else if (boolCheck instanceof SmallerOperation) {
            result = getLiteralValue(left) < getLiteralValue(right);
        } else if (boolCheck instanceof GreaterOperation) {
            result = getLiteralValue(left) > getLiteralValue(right);
        } else if (boolCheck instanceof GreaterEqualOperation) {
            result = getLiteralValue(left) >= getLiteralValue(right);
        } else if (boolCheck instanceof NotEqualOperation) {
            result = getLiteralValue(left) != getLiteralValue(right);
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
        return null;
    }

    private void applyIfClause(IfClause ifClause, ArrayList<ASTNode> parent) {
        if (ifClause.conditionalExpression == null) {
            return;
        }
        Literal condition = applyExpression(ifClause.conditionalExpression);
        if (condition instanceof BoolLiteral && ((BoolLiteral) condition).value) {
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
        if (declaration.expression == null) {
            return;
        }
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
        }
        return 0;
    }

    private Literal createLiteral(Literal literal, int value) {
        if (literal instanceof PixelLiteral) {
            return new PixelLiteral(value);
        } else if (literal instanceof ScalarLiteral) {
            return new ScalarLiteral(value);
        } else if (literal instanceof PercentageLiteral) {
            return new PercentageLiteral(value);
        }
        return null;
    }
}
