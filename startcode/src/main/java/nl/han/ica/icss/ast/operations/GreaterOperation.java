package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.BoolCheck;
import nl.han.ica.icss.ast.Operation;

public class GreaterOperation extends BoolCheck {

    @Override
    public String getNodeLabel() {
        return "GreaterOperation";
    }
}
