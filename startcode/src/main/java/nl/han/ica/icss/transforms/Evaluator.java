package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.*;
import nl.han.ica.icss.ast.operations.comparison.*;

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
        variableValues.getFirst().put(variableAssignment.name.name, (Literal) variableAssignment.expression);
    }


    private Literal applyExpression(Expression expression) {
        if (expression instanceof Operation) {
            if (expression instanceof AndOperation || expression instanceof OrOperation) {
                return applyLogicalOperation((Operation) expression);
            } else {
                return applyArithmeticOperation((Operation) expression);
            }
        } else if (expression instanceof ComparisonExpression) {
            return applyComparisonExpression((ComparisonExpression) expression);
        } else if (expression instanceof VariableReference) {
            return getVariableLiteral(((VariableReference) expression).name, variableValues);
        } else if (expression instanceof Literal) {
            return (Literal) expression;
        }
        return null;
    }

    private Literal applyOperation(Operation operation) {
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

    private Literal getVariableLiteral(String variableReference, LinkedList<HashMap<String, Literal>> variableValues) {
        for (HashMap<String, Literal> variableValue : variableValues) {
            Literal variable = variableValue.get(variableReference);
            if (variable != null) {
                return variable;
            }
        }
        return null;
    }


    // Evaluates arithmetic operations (e.g., +, -, *)
    private Literal applyArithmeticOperation(Operation operation) {
        Literal left = applyExpression(operation.lhs);
        Literal right = applyExpression(operation.rhs);

        int leftValue = getLiteralValue(left);
        int rightValue = getLiteralValue(right);

        if (operation instanceof AddOperation) {
            return createLiteral(left, leftValue + rightValue);
        } else if (operation instanceof SubtractOperation) {
            return createLiteral(left, leftValue - rightValue);
        } else if (operation instanceof MultiplyOperation) {
            return createLiteral(left, leftValue * rightValue);
        }
        return null;
    }

    private Literal applyLogicalOperation(Operation operation) {
        // Evalueer de linker- en rechterzijde van de operatie
        Literal left = applyExpression(operation.lhs);
        Literal right = applyExpression(operation.rhs);

        // Controleer of beide linker- en rechterzijde van de operatie booleans zijn
        if (left instanceof BoolLiteral && right instanceof BoolLiteral) {
            BoolLiteral leftBool = (BoolLiteral) left;
            BoolLiteral rightBool = (BoolLiteral) right;

            // Voer de logische operatie uit afhankelijk van het type operatie
            if (operation instanceof AndOperation) {
                return new BoolLiteral(leftBool.value && rightBool.value);
            } else if (operation instanceof OrOperation) {
                return new BoolLiteral(leftBool.value || rightBool.value);
            }
        }

        return null;
    }


    // Evaluates comparison operations (e.g., <, ==)
    private Literal applyComparisonExpression(ComparisonExpression expression) {
        Literal left = applyExpression(expression.lhs);
        Literal right = applyExpression(expression.rhs);

        int leftValue = getLiteralValue(left);
        int rightValue = getLiteralValue(right);

        if (expression instanceof SmallerThanOperation) {
            return new BoolLiteral(leftValue < rightValue);
        } else if (expression instanceof GreaterThanOperation) {
            return new BoolLiteral(leftValue >= rightValue);
        } else if (expression instanceof EqualOperation) {
            return new BoolLiteral(leftValue == rightValue);
        } else if (expression instanceof NotEqualOperation) {
            return new BoolLiteral(leftValue != rightValue);
        } else if (expression instanceof SmallerThanOrEqualOperation) {
            return new BoolLiteral(leftValue <= rightValue);
        } else if (expression instanceof GreaterThanOrEqualOperation) {
            return new BoolLiteral(leftValue >= rightValue);
        }
        return null;
    }

    // Handles the IfClause logic
    private void applyIfClause(IfClause ifClause, ArrayList<ASTNode> parent) {
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
        declaration.expression = applyExpression(declaration.expression);
    }

    private int getLiteralValue(Literal literal) {
        if (literal instanceof PixelLiteral) {
            return ((PixelLiteral) literal).value;
        } else if (literal instanceof ScalarLiteral) {
            return ((ScalarLiteral) literal).value;
        } else {
            return ((PercentageLiteral) literal).value;
        }
    }

    private Literal createLiteral(Literal literal, int value) {
        if (literal instanceof PixelLiteral) {
            return new PixelLiteral(value);
        } else if (literal instanceof ScalarLiteral) {
            return new ScalarLiteral(value);
        } else {
            return new PercentageLiteral(value);
        }
    }
}