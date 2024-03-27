package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.*;
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
        for (var child : stylesheet.getChildren()) {
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
            return getExpressionType(expression);
        }
    }

    private ExpressionType checkOperation(Expression expression) {
        if (expression instanceof Operation) {
            return checkOperation((Operation) expression);
        } else {
            return getExpressionType(expression);
        }
    }


}



