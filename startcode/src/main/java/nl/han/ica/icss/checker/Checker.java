package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.ComparisonExpression;
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
        variableTypes.addFirst(new HashMap<>());
        for (ASTNode child : stylesheet.getChildren()) {
            if (child instanceof VariableAssignment) {
                checkVariableAssignment((VariableAssignment) child);
            } else if (child instanceof Stylerule) {
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

    private void checkVariableAssignment(VariableAssignment variableAssignment) {
        ExpressionType type = checkExpression(variableAssignment.expression);

        if (type != ExpressionType.UNDEFINED) {
            variableTypes.getFirst().put(variableAssignment.name.name, type);
        } else {
            variableAssignment.setError("Invalid value for variable assignment.");
        }
    }

    private ExpressionType checkVariableReference(VariableReference variableReference) {
        ExpressionType expressionType = getVariableType(variableReference.name);

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
        System.out.println("Checking if-clause");
        System.out.println(ifClause.conditionalExpression);
        // Controleer de conditie in de IF-clause.
        ExpressionType conditionType = checkBooleanExpression(ifClause.conditionalExpression);
        if (conditionType != ExpressionType.BOOL) {
            ifClause.setError("The conditional expression in an if-clause must evaluate to BOOL.");
        }
    }

    private ExpressionType checkBooleanExpression(Expression expression) {
        if (expression instanceof Literal) {
            // Controleer of het een geldig boolean-literal is (bijvoorbeeld geen PixelLiteral of PercentageLiteral)
            if (expression instanceof BoolLiteral || expression instanceof ScalarLiteral) {
                return ExpressionType.BOOL;
            } else {
                expression.setError("Boolean expressions can only be BoolLiteral or ScalarLiteral, not " + expression.getClass().getSimpleName());
                return ExpressionType.UNDEFINED;
            }
        } else if (expression instanceof VariableReference) {
            // Controleer of de variabele een BOOLEAN type is.
            return checkVariableReference((VariableReference) expression);
        } else if (expression instanceof ComparisonExpression) {
            // Comparisons:  <, >, == enz.
            return checkComparisonExpression((ComparisonExpression) expression);
        } else if (expression instanceof Operation) {
            // AND - OR
            return checkLogicalOperation((Operation) expression);
        } else {
            expression.setError("Invalid boolean expression.");
            return ExpressionType.UNDEFINED;
        }
    }


    private ExpressionType checkLogicalOperation(Operation operation) {
        ExpressionType lhsType = checkBooleanExpression(operation.lhs);
        ExpressionType rhsType = checkBooleanExpression(operation.rhs);

        if (lhsType != ExpressionType.BOOL || rhsType != ExpressionType.BOOL) {
            operation.setError("Both sides of a logical operation (AND, OR) must evaluate to BOOL.");
            return ExpressionType.UNDEFINED;
        }
        return ExpressionType.BOOL;
    }

    private ExpressionType checkComparisonExpression(ComparisonExpression comparison) {
        ExpressionType lhsType = checkExpression(comparison.lhs);
        ExpressionType rhsType = checkExpression(comparison.rhs);


        if (isComparableType(lhsType) || isComparableType(rhsType)) {
            comparison.setError("Both sides of a comparison must be Literal type.");
            return ExpressionType.UNDEFINED;
        }
        return ExpressionType.BOOL;
    }

    private boolean isComparableType(ExpressionType type) {
        return type != ExpressionType.SCALAR;
    }

    private ExpressionType checkExpression(Expression expression) {
        if (expression instanceof Literal) {
            // Literals worden op type gecontroleerd.
            return checkLiteral((Literal) expression);
        } else if (expression instanceof VariableReference) {
            return checkVariableReference((VariableReference) expression);
        } else if (expression instanceof Operation) {
            // Rekenkundige operaties zoals +, -, *, etc.
            return checkArithmeticOperation((Operation) expression);
        } else {
            expression.setError("Invalid expression.");
            return ExpressionType.UNDEFINED;
        }
    }

    private ExpressionType checkArithmeticOperation(Operation operation) {
        ExpressionType lhsType = checkExpression(operation.lhs);
        ExpressionType rhsType = checkExpression(operation.rhs);

        // Rekenoperaties mogen geen BOOLEAN of COLOR types bevatten.
        if (lhsType == ExpressionType.BOOL || lhsType == ExpressionType.COLOR ||
                rhsType == ExpressionType.BOOL || rhsType == ExpressionType.COLOR) {
            operation.setError("Arithmetic operations cannot involve BOOLEAN or COLOR types.");
            return ExpressionType.UNDEFINED;
        }
        return lhsType; // Type van de expressie wordt gebaseerd op het LHS-type.
    }

    private ExpressionType checkLiteral(Literal literal) {
        if (literal instanceof BoolLiteral) {
            return ExpressionType.BOOL;
        } else if (literal instanceof ScalarLiteral) {
            return ExpressionType.SCALAR;
        } else if (literal instanceof PixelLiteral) {
            return ExpressionType.PIXEL;
        } else if (literal instanceof PercentageLiteral) {
            return ExpressionType.PERCENTAGE;
        } else if (literal instanceof ColorLiteral) {
            return ExpressionType.COLOR;
        } else {
            literal.setError("Unknown literal type.");
            return ExpressionType.UNDEFINED;
        }
    }

}



