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

    private void checkStyleSheet(ASTNode astNode) {
        Stylesheet stylesheet = (Stylesheet) astNode;
        variableTypes.addFirst((new HashMap<>()));

        for (ASTNode child : stylesheet.getChildren()) {
            if (child instanceof Stylerule) {
                variableTypes.addFirst(new HashMap<>());
                checkStyleRule(child);
                variableTypes.removeFirst();
            } else if (child instanceof VariableAssignment) {
                checkVariableAssignment(child);
            }
        }
        variableTypes.clear();
    }

    private void checkStyleRule(ASTNode astNode) {
        Stylerule stylerule = (Stylerule) astNode;
        checkRuleBody(stylerule.body);
    }

    private void checkRuleBody(ArrayList<ASTNode> astNodes) {
        for (ASTNode astNode : astNodes) {
            if (astNode instanceof Declaration) {
                checkDeclaration(astNode);
            } else if (astNode instanceof IfClause) {
                checkIfClause(astNode);
            } else if (astNode instanceof VariableAssignment) {
                checkVariableAssignment(astNode);
            }
        }
    }


    private void checkVariableAssignment(ASTNode astNode) {
        VariableAssignment variableAssignment = (VariableAssignment) astNode;
        VariableReference variableReference = variableAssignment.name;
        ExpressionType expressionType = checkExpression(variableAssignment.expression);

        if (expressionType == null || expressionType == ExpressionType.UNDEFINED) {
            astNode.setError("Variabele assignment lukt niet omdat de expression type undefined is.");
            return;
        }

        ExpressionType previousExpressionType = getVariableType(variableReference.name);
        if (variableTypeChanged(expressionType, previousExpressionType)) {
            astNode.setError("Een variabele kan niet veranderen van type: " + previousExpressionType + " ,naar type: " + expressionType);
        }

        putVariableType(variableReference.name, expressionType);
    }

    private void checkIfClause(ASTNode astNode) {
        IfClause ifClause = (IfClause) astNode;
        variableTypes.addFirst(new HashMap<>());

        Expression conditionalExpression = ifClause.getConditionalExpression();
        ExpressionType expressionType = checkExpressionType(conditionalExpression);

        if (expressionType != ExpressionType.BOOL) {
            ifClause.setError("Conditional expression moet een boolean literal type hebben.");
        }

        checkRuleBody(ifClause.body);

        variableTypes.removeFirst();

        if (ifClause.getElseClause() != null) {
            variableTypes.addFirst(new HashMap<>());
            checkElseClause(ifClause.getElseClause());
            variableTypes.removeFirst();
        }
    }


    private void checkElseClause(ElseClause elseClause) {
        checkRuleBody(elseClause.body);
    }

    private void checkDeclaration(ASTNode astNode) {
        Declaration declaration = (Declaration) astNode;
        ExpressionType expressionType = checkExpression(declaration.expression);

        switch (declaration.property.name) {
            case "color":
                if (expressionType != ExpressionType.COLOR) {
                    astNode.setError("Color waarde kan alleen van type color literal zijn.");
                }
                break;
            case "background-color":
                if (expressionType != ExpressionType.COLOR) {
                    astNode.setError("Background-color waarde kan alleen van color literal type zijn.");
                }
                break;
            case "width":
                if (expressionType != ExpressionType.PIXEL && expressionType != ExpressionType.PERCENTAGE) {
                    astNode.setError("Width waarde kan alleen van type pixel, of percentage literal zijn.");
                }
                break;
            case "height":
                if (expressionType != ExpressionType.PIXEL && expressionType != ExpressionType.PERCENTAGE) {
                    astNode.setError("Height waarde kan alleen van percentage of pixel literal type zijn.");
                }
                break;
            default:
                astNode.setError("De enige toegestane properties zijn: height, weight, color of background-color.");
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

    private ExpressionType checkVariableReference(VariableReference variableReference) {
        ExpressionType expressionType = getVariableType(variableReference.name);
        if (expressionType == null) {
            variableReference.setError("Variable has not yet been declared or is not in the same scope.");
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


        if (left == ExpressionType.COLOR || right == ExpressionType.COLOR || left == ExpressionType.BOOL || right == ExpressionType.BOOL) {
            operation.setError("Booleans en colors zijn niet toegestaan in een operation.");
            return ExpressionType.UNDEFINED;
        }

        if (operation instanceof MultiplyOperation) {
            if (left != ExpressionType.SCALAR && right != ExpressionType.SCALAR) {
                operation.setError("Multiply uitvoeren is alleen toegestaan met minimaal één scalar waarde.");
                return ExpressionType.UNDEFINED;
            }
            return right != ExpressionType.SCALAR ? right : left;
        } else if ((operation instanceof SubtractOperation || operation instanceof AddOperation) && left != right) {
            operation.setError("Add en subtract operations mogen alleen uitgevoerd worden met dezelfde type literal.");
            return ExpressionType.UNDEFINED;
        }

        return left;
    }

    private boolean variableTypeChanged(ExpressionType currentType, ExpressionType previousType) {
        return (previousType != null) && currentType != previousType;
    }

    private void putVariableType(String name, ExpressionType expressionType) {
        variableTypes.getFirst().put(name, expressionType);
    }
}



