package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AndOperation;
import nl.han.ica.icss.ast.operations.ComparisonExpression;
import nl.han.ica.icss.ast.operations.OrOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Checker {
    private LinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
        variableTypes = new LinkedList<>();
        System.out.println("Starting checker on AST root...");
        checkStyleSheet(ast.root);
        System.out.println("Checker finished.");
    }

    private void checkStyleSheet(Stylesheet stylesheet) {
        variableTypes.addFirst(new HashMap<>());
        System.out.println("Checking stylesheet...");
        for (ASTNode child : stylesheet.getChildren()) {
            if (child instanceof VariableAssignment) {
                checkVariableAssignment((VariableAssignment) child);
            } else if (child instanceof Stylerule) {
                System.out.println("Entering stylerule...");
                variableTypes.addFirst(new HashMap<>());
                checkStyleRule((Stylerule) child);
                variableTypes.removeFirst();
            }
        }
        variableTypes.clear();
    }

    private void checkStyleRule(Stylerule styleRule) {
        for (ASTNode child : styleRule.getChildren()) {
            if (child instanceof Declaration) {
                System.out.println("Checking declaration: " + child);
                checkDeclaration((Declaration) child);
            }
            if (child instanceof VariableAssignment) {
                System.out.println("Checking variable assignment: " + child);
                checkVariableAssignment((VariableAssignment) child);
            }
            if (child instanceof IfClause) {
                System.out.println("Checking if-clause: " + child);
                checkIfClause((IfClause) child);
            }
        }
    }

    private void checkDeclaration(Declaration declaration) {
        ExpressionType expressionType = checkExpression(declaration.expression);

        System.out.println("Property: " + declaration.property.name + ", Expression type: " + expressionType);

        switch (declaration.property.name) {
            case "width":
            case "height":
                if (expressionType != ExpressionType.PIXEL && expressionType != ExpressionType.PERCENTAGE) {
                    declaration.setError(declaration.property.name + " can only be of type Percentage- or PixelLiteral.");
                }
                break;
            case "color":
            case "background-color":
                if (expressionType != ExpressionType.COLOR) {
                    declaration.setError(declaration.property.name + " can only be of type ColorLiteral.");
                }
                break;
            default:
                declaration.setError("Unknown property: " + declaration.property.name + " is not a valid property.");
                break;
        }
    }

    private ExpressionType checkExpression(Expression expression) {
        if (expression instanceof VariableReference) {
            return checkVariableReference((VariableReference) expression);
        } else if (expression instanceof PercentageLiteral) {
            return ExpressionType.PERCENTAGE;
        } else if (expression instanceof PixelLiteral) {
            return ExpressionType.PIXEL;
        } else if (expression instanceof ColorLiteral) {
            return ExpressionType.COLOR;
        } else if (expression instanceof ScalarLiteral) {
            return ExpressionType.SCALAR;
        } else if (expression instanceof BoolLiteral) {
            return ExpressionType.BOOL;
        } else {
            System.out.println("Unsupported expression: " + expression);
            return ExpressionType.UNDEFINED;
        }
    }

    private void checkVariableAssignment(VariableAssignment variableAssignment) {
        ExpressionType type = checkExpression(variableAssignment.expression);
        System.out.println("Variable assignment: " + variableAssignment.name.name + " of type " + type);

        if (type != ExpressionType.UNDEFINED) {
            variableTypes.getFirst().put(variableAssignment.name.name, type);
        } else {
            variableAssignment.setError("Invalid value for variable assignment.");
        }
    }

    private ExpressionType checkVariableReference(VariableReference variableReference) {
        ExpressionType expressionType = getVariableType(variableReference.name);
        System.out.println("Variable reference: " + variableReference.name + ", Type: " + expressionType);

        if (expressionType == null) {
            variableReference.setError("Variable '" + variableReference.name + "' was never set.");
            return ExpressionType.UNDEFINED;
        }
        return expressionType;
    }

    private ExpressionType getVariableType(String name) {
        for (HashMap<String, ExpressionType> scope : variableTypes) {
            ExpressionType type = scope.get(name);
            if (type != null) {
                return type;
            }
        }
        return null;
    }

    private void checkIfClause(IfClause ifClause) {
        ExpressionType conditionType = checkExpression(ifClause.conditionalExpression);

        System.out.println("If-clause condition type: " + conditionType);

        if (conditionType != ExpressionType.BOOL) {
            ifClause.setError("The conditional expression in an if-clause must evaluate to BOOL.");
        }
    }
}



