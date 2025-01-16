package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Expression;

import java.util.ArrayList;

public abstract class ComparisonExpression extends Expression {
    protected Expression lhs;
    protected Expression rhs;

    public ComparisonExpression() {
    }

    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        if (lhs != null) {
            children.add(lhs);
        }
        if (rhs != null) {
            children.add(rhs);
        }
        return children;
    }

    @Override
    public ASTNode addChild(ASTNode child) {
        if (lhs == null) {
            lhs = (Expression) child;
        } else if (rhs == null) {
            rhs = (Expression) child;
        }
        return this;
    }

    public Expression getLeft() {
        return lhs;
    }

    public Expression getRight() {
        return rhs;
    }

    public abstract String getNodeLabel();
}