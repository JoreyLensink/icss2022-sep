package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.BoolExpression;
import nl.han.ica.icss.ast.Operation;

public class AndOperation extends BoolExpression {

    @Override
    public String getNodeLabel() {
        return "AndOperation";
    }
}
