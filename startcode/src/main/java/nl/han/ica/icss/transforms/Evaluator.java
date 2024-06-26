package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SmallerEqualOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;

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
            return applyOperation((Operation) expression);
        } else if (expression instanceof VariableReference) {
            return getVariableLiteral(((VariableReference) expression).name, variableValues);
        } else if (expression instanceof Literal) {
            return (Literal) expression;
        } else if (expression instanceof BoolLiteral) {
            return (BoolLiteral) expression;
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


    private void applyIfClause(IfClause ifClause, ArrayList<ASTNode> parent) {
        ifClause.conditionalExpression = applyExpression(ifClause.conditionalExpression);
        applyIfBoolExpression(ifClause);


        if (ifClause.conditionalExpression != null && ((BoolLiteral) ifClause.conditionalExpression).value) {
            if (ifClause.elseClause != null) {
                ifClause.elseClause.body = new ArrayList<>();
            } else {
                if (ifClause.elseClause == null) {
                    ifClause.body = new ArrayList<>();
                } else {
                    ifClause.body = ifClause.elseClause.body;
                    ifClause.elseClause.body = new ArrayList<>();
                }
            }
            for (ASTNode child : ifClause.getChildren()) {
                applyStyleruleBody(child, parent);
            }
        }
    }

    private void applyIfBoolExpression(IfClause ifClause) {
        if (ifClause.conditionalExpression instanceof BoolCheck) {
            ifClause.conditionalExpression = applyBoolCheck((BoolCheck) ifClause.conditionalExpression);
        }

    }

    private Expression applyBoolCheck(BoolCheck boolCheck) {
        if (boolCheck instanceof SmallerEqualOperation) {
        }

        if (boolCheck.lhs instanceof VariableReference) {
            boolCheck.lhs = getVariableLiteral(((VariableReference) boolCheck.lhs).name, variableValues);
        }
        if (boolCheck.rhs instanceof VariableReference) {
            boolCheck.rhs = getVariableLiteral(((VariableReference) boolCheck.rhs).name, variableValues);
        }
        return boolCheck;
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