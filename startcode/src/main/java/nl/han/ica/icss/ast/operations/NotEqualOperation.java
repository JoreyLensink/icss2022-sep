package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.BoolCheck;

public class NotEqualOperation extends BoolCheck {

    @Override
    public String getNodeLabel() {
        return "NotEqualOperation";
    }
}
