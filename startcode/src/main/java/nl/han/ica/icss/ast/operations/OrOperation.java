package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.BoolExpression;


public class OrOperation extends BoolExpression {

    @Override
    public String getNodeLabel() {
        return "OrOperation";
    }
}
