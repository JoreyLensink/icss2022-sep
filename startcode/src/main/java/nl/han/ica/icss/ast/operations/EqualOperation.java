package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.BoolCheck;
import nl.han.ica.icss.ast.Operation;

public class EqualOperation extends BoolCheck {

    @Override
    public String getNodeLabel() {
        return "EqualOperation";
    }
}
