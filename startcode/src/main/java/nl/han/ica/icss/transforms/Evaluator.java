package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.PixelLiteral;

import java.util.HashMap;

public class Evaluator implements Transform {

    private IHANLinkedList<HashMap<String, Literal>> variableValues;

    public Evaluator() {
        //variableValues = new HANLinkedList<>();
    }

    @Override
    public void apply(AST ast) {
//        variableValues = new HANLinkedList<>();
        applyStylesheet(ast.root);

    }

    private void applyStylesheet(Stylesheet stylesheet) {
        applyStyleRule((Stylerule)stylesheet.getChildren().get(0));
    }

    private void applyStyleRule(Stylerule rule) {
        for (ASTNode node : rule.getChildren()) {
            if (node instanceof Declaration) {
                applyDeclaration((Declaration) node);
            }
        }
    }

    private void applyDeclaration(Declaration declaration) {
        declaration.expression = calculateExpression(declaration.expression);

    }

    private Expression calculateExpression(Expression expression) {
        //check welke literal het is
        if (expression instanceof Literal) {
            return (Literal) expression;
        } else {
            return evaluateAddOperation((Operation) expression);
        }
    }

    private Expression evaluateExpression(Expression expression) {
        if (expression instanceof Operation) {
            return evaluateAddOperation((Operation) expression);
        } else {
            return expression;
        }
    }

    private Expression evaluateAddOperation(Operation expression) {
        // (waarschijnlijk) iets toevoegen over verschillende litterals
        PixelLiteral left = (PixelLiteral) evaluateExpression(expression.lhs);
        PixelLiteral right = (PixelLiteral) evaluateExpression(expression.rhs);
        return new PixelLiteral(left.value + right.value);
    }


}
