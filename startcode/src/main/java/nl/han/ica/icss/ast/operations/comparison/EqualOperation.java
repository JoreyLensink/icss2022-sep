package nl.han.ica.icss.ast.operations.comparison;

import nl.han.ica.icss.ast.operations.ComparisonExpression;

public class EqualOperation extends ComparisonExpression {

    @Override
    public String getNodeLabel() {
        return "Equal";
    }
}
