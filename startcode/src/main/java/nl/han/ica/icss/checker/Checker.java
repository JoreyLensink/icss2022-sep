package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;
import java.util.LinkedList;


public class Checker {

    private LinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
        // variableTypes = new HANLinkedList<>();
        checkStyleSheet(ast.root);

    }

    private void checkStyleSheet(Stylesheet sheet) {
        checkStylerule((Stylerule) sheet.getChildren().get(0));
    }

    private void checkStylerule(Stylerule rule) {
        for (ASTNode node : rule.getChildren()) {
            if (node instanceof Declaration) {
                checkDeclaration((Declaration)node);
            }
        }

    }

    private void checkDeclaration(Declaration declaration) {
        if (declaration.property.name.equals("width")){
            if (declaration.expression instanceof PixelLiteral) {
                declaration.setError("Width should not be in pixels");

            }
        }
    }

}
