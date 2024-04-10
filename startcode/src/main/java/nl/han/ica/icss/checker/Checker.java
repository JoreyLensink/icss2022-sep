package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.ArrayList;
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

    private ExpressionType checkExpression(Expression expression) {
        if (expression instanceof Operation) {
            return checkOperation((Operation) expression);
        } else {
            return getExpressionType(expression);
        }
    }

    private ExpressionType getExpressionType(Expression expression) {
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
        }
        return ExpressionType.UNDEFINED;
    }

    private void checkVariableAssignment(VariableAssignment variableAssignment) {
        if (variableAssignment.expression instanceof ScalarLiteral) {
            variableTypes.getFirst().put(variableAssignment.name.name, ExpressionType.SCALAR);
        } else if (variableAssignment.expression instanceof PixelLiteral) {
            variableTypes.getFirst().put(variableAssignment.name.name, ExpressionType.PIXEL);
        } else if (variableAssignment.expression instanceof ColorLiteral) {
            variableTypes.getFirst().put(variableAssignment.name.name, ExpressionType.COLOR);
        } else if (variableAssignment.expression instanceof PercentageLiteral) {
            variableTypes.getFirst().put(variableAssignment.name.name, ExpressionType.PERCENTAGE);
        } else if (variableAssignment.expression instanceof BoolLiteral) {
            variableTypes.getFirst().put(variableAssignment.name.name, ExpressionType.BOOL);
        } else if (variableAssignment.expression instanceof VariableReference) {
            if (!variableTypes.getFirst().containsKey(((VariableReference) variableAssignment.expression).name)) {
                variableAssignment.setError("The variable '" + ((VariableReference) variableAssignment.expression).name + "' was never set.");
            } else {
                variableTypes.getFirst().put(variableAssignment.name.name, variableTypes.getFirst().get(((VariableReference) variableAssignment.expression).name));
            }
        } else if (variableAssignment.expression instanceof Operation) {
            ExpressionType type = checkOperation((Operation) variableAssignment.expression);
            variableTypes.getFirst().put(variableAssignment.name.name, type);
        }
    }

    private ExpressionType checkOperation(Operation operation) {
        ExpressionType left = operation.lhs instanceof Operation ? checkOperation((Operation) operation.lhs) : checkExpressionType(operation.lhs);
        ExpressionType right = operation.rhs instanceof Operation ? checkOperation((Operation) operation.rhs) : checkExpressionType(operation.rhs);

        if (left == ExpressionType.COLOR || right == ExpressionType.COLOR || left == ExpressionType.BOOL || right == ExpressionType.BOOL) {
            operation.setError("Booleans and Colors are not allowed in an operation.");
            return ExpressionType.UNDEFINED;
        }

        if (operation instanceof MultiplyOperation) {
            if (left != ExpressionType.SCALAR && right != ExpressionType.SCALAR) {
                operation.setError("Multiplication must have a scalar value.");
                return ExpressionType.UNDEFINED;
            }
            return right != ExpressionType.SCALAR ? right : left;
        } else if ((operation instanceof SubtractOperation || operation instanceof AddOperation) && left != right) {
            operation.setError("Add and subtract operations can only be done with the same literal type.");
            return ExpressionType.UNDEFINED;
        }

        return left;
    }

    private void checkRuleBody(ArrayList<ASTNode> body) {
        for (ASTNode astNode : body) {
            if (astNode instanceof Declaration) {
                checkDeclaration((Declaration) astNode);
            } else if (astNode instanceof IfClause) {
                checkIfClause((IfClause) astNode);
            } else if (astNode instanceof VariableAssignment) {
                checkVariableAssignment((VariableAssignment) astNode);
            }
        }
    }

    private ExpressionType checkExpressionType(Expression expression) {
        return getExpressionType(expression);
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
        variableTypes.addFirst(new HashMap<>());

        Expression conditionalExpression = ifClause.getConditionalExpression();
        ExpressionType expressionType = checkExpressionType(conditionalExpression);


        if (expressionType != ExpressionType.BOOL && !(conditionalExpression instanceof BoolExpression)) {
            ifClause.setError("Conditional must be a boolean or BooleanExpression.");
        }

        checkBoolExpression(ifClause.getConditionalExpression());

        checkRuleBody(ifClause.body);

        variableTypes.removeFirst();

        if (ifClause.getElseClause() != null) {
            checkElseClause(ifClause.getElseClause());
        }
    }

    private void checkBoolExpression(Expression conditionalExpression) {
        checkBoolCheck((BoolCheck) conditionalExpression.getChildren().get(0));
    }

    private void checkBoolCheck(BoolCheck boolCheck) {
        ExpressionType left = boolCheck.lhs instanceof Operation ? checkOperation((Operation) boolCheck.lhs) : checkExpressionType(boolCheck.lhs);
        ExpressionType right = boolCheck.rhs instanceof Operation ? checkOperation((Operation) boolCheck.rhs) : checkExpressionType(boolCheck.rhs);

        if (left != right) {
            boolCheck.setError("Both sides of the boolean check must be of the same type.");
        }


    }

    private void checkElseClause(ElseClause elseClause) {
        checkRuleBody(elseClause.body);
    }
}



