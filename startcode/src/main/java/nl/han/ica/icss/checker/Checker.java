package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;
import java.util.LinkedList;


public class Checker {

    private LinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
        variableTypes = new LinkedList<>();
        checkStyleSheet(ast.root);
    }

    private void checkStyleSheet(Stylesheet stylesheet) {
        variableTypes.addFirst((new HashMap<>()));
        for (ASTNode child : stylesheet.getChildren()) {
            if (child instanceof Stylerule) {
                variableTypes.addFirst(new HashMap<>());
                checkStyleRule((Stylerule) child);
                variableTypes.removeFirst();
            } else if (child instanceof VariableAssignment) {
                checkVariableAssignment((VariableAssignment) child);
            }
        }
        variableTypes.clear();
    }

    private void checkStyleRule(Stylerule stylerule) {
        variableTypes.addFirst(new HashMap<>());
        for (ASTNode child : stylerule.getChildren()) {
            if (child instanceof Declaration) {
                checkDeclaration((Declaration) child);
            }
            if (child instanceof VariableAssignment) {
                checkVariableAssignment((VariableAssignment) child);
            }
            if (child instanceof IfClause) {
                checkIfClause((IfClause) child);
            }
        }
        variableTypes.removeFirst();
    }

    private void checkDeclaration(Declaration declaration) {
        ExpressionType expressionType = checkExpression(declaration.expression);

        switch (declaration.property.name) {
            case "width":
            case "height":
                if (expressionType != ExpressionType.PIXEL && expressionType != ExpressionType.PERCENTAGE) {
                    System.out.println("Error: " + declaration.property.name + " should be a pixel or percentage");
                }
                break;
            case "color":
            case "background-color":
                if (expressionType != ExpressionType.COLOR) {
                    System.out.println("Error: " + declaration.property.name + " should be a color");
                }
                break;
        }
    }

    private ExpressionType checkExpression(Expression expression) {
        if (expression instanceof Operation) {
            return checkOperation((Operation) expression);
        } else {
            return checkExpressionType(expression);
        }
    }

    private ExpressionType checkExpressionType(Expression expression) {
        if (expression instanceof VariableReference) {
            checkVariableReference((VariableReference) expression);
        } else if (expression instanceof ScalarLiteral) {
            return ExpressionType.SCALAR;
        } else if (expression instanceof ColorLiteral) {
            return ExpressionType.COLOR;
        } else if (expression instanceof PercentageLiteral) {
            return ExpressionType.PERCENTAGE;
        } else if (expression instanceof PixelLiteral) {
            return ExpressionType.PIXEL;
        } else if (expression instanceof BoolLiteral) {
            return ExpressionType.BOOL;
        }
        return ExpressionType.UNDEFINED;
    }

    private ExpressionType checkOperation(Operation operation) {
        ExpressionType left;
        ExpressionType right;

        if (operation.lhs instanceof Operation) {
            left = checkOperation((Operation) operation.lhs);
        } else {
            left = checkExpressionType(operation.lhs);
        }

        if (operation.rhs instanceof Operation) {
            right = checkOperation((Operation) operation.rhs);
        } else {
            right = checkExpressionType(operation.rhs);
        }

        if (left == ExpressionType.COLOR || right == ExpressionType.COLOR ||
                left == ExpressionType.BOOL || right == ExpressionType.BOOL) {
            operation.setError("Booleans and colors are not allowed in an operation.");
            return ExpressionType.UNDEFINED;
        }
        if (operation instanceof MultiplyOperation) {
            if (left != ExpressionType.SCALAR && right != ExpressionType.SCALAR) {
                operation.setError("Multiplying is only allowed with at least one scalar value.");
                return ExpressionType.UNDEFINED;
            } else {
                if (left != ExpressionType.SCALAR) {
                    return left;
                } else {
                    return right;
                }
            }
        } else if ((operation instanceof SubtractOperation || operation instanceof AddOperation) && left != right) {
            operation.setError("Add and subtract operations are only allowed with the same literal type.");
            return ExpressionType.UNDEFINED;
        }

        return left;
    }


}



