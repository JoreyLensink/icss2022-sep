package nl.han.ica.icss.ast.operations.comparison;

import nl.han.ica.icss.ast.operations.ComparisonExpression;

public class NotEqualOperation extends ComparisonExpression {

    @Override
    public String getNodeLabel() {
        return "Not Equal";
    }
}
