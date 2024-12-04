package nl.han.ica.icss.parser;

import nl.han.ica.datastructures.HANStack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.*;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {
    private AST ast;

    private IHANStack<ASTNode> currentContainer;

    public ASTListener() {
        ast = new AST();
        currentContainer = new HANStack<>();
    }

    public AST getAST() {
        return ast;
    }

    @Override
    public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
        Stylesheet stylesheet = new Stylesheet();
        currentContainer.push(stylesheet);
    }

    @Override
    public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
        Stylesheet stylesheet = (Stylesheet) currentContainer.pop();
        ast.setRoot(stylesheet);
    }

    @Override
    public void enterStylerule(ICSSParser.StyleruleContext ctx) {
        Stylerule stylerule = new Stylerule();
        currentContainer.push(stylerule);
    }

    @Override
    public void exitStylerule(ICSSParser.StyleruleContext ctx) {
        Stylerule stylerule = (Stylerule) currentContainer.pop();
        currentContainer.peek().addChild(stylerule);
    }

    @Override
    public void enterStyleDeclaration(ICSSParser.StyleDeclarationContext ctx) {
        Declaration declaration = new Declaration();
        currentContainer.push(declaration);
    }

    @Override
    public void enterStyleTag(ICSSParser.StyleTagContext ctx) {
        PropertyName propertyName = new PropertyName(ctx.getText());
        currentContainer.push(propertyName);
    }

    @Override
    public void exitStyleTag(ICSSParser.StyleTagContext ctx) {
        PropertyName propertyName = (PropertyName) currentContainer.pop();
        currentContainer.peek().addChild(propertyName);
    }

    @Override
    public void exitStyleDeclaration(ICSSParser.StyleDeclarationContext ctx) {
        Declaration declaration = (Declaration) currentContainer.pop();
        currentContainer.peek().addChild(declaration);
    }

    @Override
    public void enterScalarLiteral(ICSSParser.ScalarLiteralContext ctx) {
        ScalarLiteral scalarLiteral = new ScalarLiteral(ctx.getText());
        currentContainer.push(scalarLiteral);
    }

    @Override
    public void exitScalarLiteral(ICSSParser.ScalarLiteralContext ctx) {
        ScalarLiteral scalarLiteral = (ScalarLiteral) currentContainer.pop();
        currentContainer.peek().addChild(scalarLiteral);
    }

    @Override
    public void enterPixelLiteral(ICSSParser.PixelLiteralContext ctx) {
        PixelLiteral pixelLiteral = new PixelLiteral(ctx.getText());
        currentContainer.push(pixelLiteral);
    }

    @Override
    public void exitPixelLiteral(ICSSParser.PixelLiteralContext ctx) {
        PixelLiteral pixelLiteral = (PixelLiteral) currentContainer.pop();
        currentContainer.peek().addChild(pixelLiteral);
    }

    @Override
    public void enterColorLiteral(ICSSParser.ColorLiteralContext ctx) {
        ColorLiteral colorLiteral = new ColorLiteral(ctx.getText());
        currentContainer.push(colorLiteral);
    }

    @Override
    public void exitColorLiteral(ICSSParser.ColorLiteralContext ctx) {
        ColorLiteral colorLiteral = (ColorLiteral) currentContainer.pop();
        currentContainer.peek().addChild(colorLiteral);
    }

    @Override
    public void enterPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) {
        PercentageLiteral percentageLiteral = new PercentageLiteral(ctx.getText());
        currentContainer.push(percentageLiteral);
    }

    @Override
    public void exitPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) {
        PercentageLiteral percentageLiteral = (PercentageLiteral) currentContainer.pop();
        currentContainer.peek().addChild(percentageLiteral);
    }

    @Override
    public void enterBoolLiteral(ICSSParser.BoolLiteralContext ctx) {
        BoolLiteral boolLiteral = new BoolLiteral(ctx.getText());
        currentContainer.push(boolLiteral);
    }

    @Override
    public void exitBoolLiteral(ICSSParser.BoolLiteralContext ctx) {
        BoolLiteral boolLiteral = (BoolLiteral) currentContainer.pop();
        currentContainer.peek().addChild(boolLiteral);
    }

    @Override
    public void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
        VariableAssignment variableAssignment = new VariableAssignment();
        currentContainer.push(variableAssignment);
    }

    @Override
    public void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
        VariableAssignment variableAssignment = (VariableAssignment) currentContainer.pop();
        currentContainer.peek().addChild(variableAssignment);
    }

    @Override
    public void enterVariableName(ICSSParser.VariableNameContext ctx) {
        VariableReference variableReference = new VariableReference(ctx.getText());
        currentContainer.push(variableReference);
    }

    @Override
    public void exitVariableName(ICSSParser.VariableNameContext ctx) {
        VariableReference variableReference = (VariableReference) currentContainer.pop();
        currentContainer.peek().addChild(variableReference);
    }

    @Override
    public void enterExpression(ICSSParser.ExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            ASTNode operation = null;
            if (ctx.MIN() != null) {
                operation = new SubtractOperation();
            } else if (ctx.PLUS() != null) {
                operation = new AddOperation();
            } else if (ctx.MUL() != null) {
                operation = new MultiplyOperation();
            }
            currentContainer.push(operation);
        }
    }

    @Override
    public void exitExpression(ICSSParser.ExpressionContext ctx) {
        if (ctx.PLUS() != null || ctx.MIN() != null || ctx.MUL() != null) {
            ASTNode operation = currentContainer.pop();
            currentContainer.peek().addChild(operation);
        }
    }

    @Override
    public void enterIfStatement(ICSSParser.IfStatementContext ctx) {
        ASTNode ifClause = new IfClause();
        currentContainer.push(ifClause);
    }

    @Override
    public void exitIfStatement(ICSSParser.IfStatementContext ctx) {
        ASTNode ifClause = currentContainer.pop();
        currentContainer.peek().addChild(ifClause);
    }

    @Override
    public void enterElseStatement(ICSSParser.ElseStatementContext ctx) {
        ASTNode elseClause = new ElseClause();
        currentContainer.push(elseClause);
    }

    @Override
    public void exitElseStatement(ICSSParser.ElseStatementContext ctx) {
        ASTNode elseClause = currentContainer.pop();
        currentContainer.peek().addChild(elseClause);
    }

    @Override
    public void enterTagSelector(ICSSParser.TagSelectorContext ctx) {
        TagSelector tagSelector = new TagSelector(ctx.getText());
        currentContainer.push(tagSelector);
    }

    @Override
    public void exitTagSelector(ICSSParser.TagSelectorContext ctx) {
        TagSelector tagSelector = (TagSelector) currentContainer.pop();
        currentContainer.peek().addChild(tagSelector);
    }

    @Override
    public void enterClassSelector(ICSSParser.ClassSelectorContext ctx) {
        ClassSelector classSelector = new ClassSelector(ctx.getText());
        currentContainer.push(classSelector);
    }

    @Override
    public void exitClassSelector(ICSSParser.ClassSelectorContext ctx) {
        ClassSelector classSelector = (ClassSelector) currentContainer.pop();
        currentContainer.peek().addChild(classSelector);
    }

    @Override
    public void enterIdSelector(ICSSParser.IdSelectorContext ctx) {
        IdSelector idSelector = new IdSelector(ctx.getText());
        currentContainer.push(idSelector);
    }

    @Override
    public void exitIdSelector(ICSSParser.IdSelectorContext ctx) {
        IdSelector idSelector = (IdSelector) currentContainer.pop();
        currentContainer.peek().addChild(idSelector);
    }

    @Override
    public void enterBooleanExpression(ICSSParser.BooleanExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            ASTNode operation = null;

            if (ctx.AND() != null) {
                operation = new AndOperation();
            } else if (ctx.OR() != null) {
                operation = new OrOperation();
            } else if (ctx.comparisonExpression() != null) {
                enterComparisonExpression(ctx.comparisonExpression());
                return;
            }
            currentContainer.push(operation);
        } else if (ctx.getChildCount() == 1) {
            if (ctx.booleanLiteral() != null) {
                BoolLiteral boolLiteral = new BoolLiteral(ctx.booleanLiteral().getText());
                currentContainer.push(boolLiteral);
            } else if (ctx.comparisonExpression() != null) {
                ASTNode operation = null;
                if (ctx.comparisonExpression().SMALLER() != null) {
                    operation = new SmallerThanOperation();
                } else if (ctx.comparisonExpression().SMALLER_EQUAL() != null) {
                    operation = new SmallerThanOrEqualOperation();
                } else if (ctx.comparisonExpression().GREATER() != null) {
                    operation = new GreaterThanOperation();
                } else if (ctx.comparisonExpression().GREATER_EQUAL() != null) {
                    operation = new GreaterThanOrEqualOperation();
                } else if (ctx.comparisonExpression().EQUAL() != null) {
                    operation = new EqualOperation();
                } else if (ctx.comparisonExpression().NOT_EQUAL() != null) {
                    operation = new NotEqualOperation();
                }
                currentContainer.push(operation);
            } else if (ctx.variableName() != null) {
                VariableReference variableReference = new VariableReference(ctx.variableName().getText());
                currentContainer.push(variableReference);
            }
        }
    }

    @Override
    public void exitBooleanExpression(ICSSParser.BooleanExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            ASTNode operation = currentContainer.pop();
            currentContainer.peek().addChild(operation);
        } else if (ctx.getChildCount() == 1) {
            ASTNode literalOrComparison = currentContainer.pop();
            currentContainer.peek().addChild(literalOrComparison);
        }
    }

//     Logica in verplaatst omdat ik via recusie dubble ComparisonExpresion node kreeg.

//    @Override
//    public void enterComparisonExpression(ICSSParser.ComparisonExpressionContext ctx) {
//        if (ctx.getChildCount() == 3) {
//            ASTNode operation = null;
//            if (ctx.SMALLER() != null) {
//                operation = new SmallerThanOperation();
//            } else if (ctx.SMALLER_EQUAL() != null) {
//                operation = new SmallerThanOrEqualOperation();
//            } else if (ctx.GREATER() != null) {
//                operation = new GreaterThanOperation();
//            } else if (ctx.GREATER_EQUAL() != null) {
//                operation = new GreaterThanOrEqualOperation();
//            } else if (ctx.EQUAL() != null) {
//                operation = new EqualOperation();
//            } else if (ctx.NOT_EQUAL() != null) {
//                operation = new NotEqualOperation();
//            }
//            currentContainer.push(operation);
//        }
//    }
//
//    @Override
//    public void exitComparisonExpression(ICSSParser.ComparisonExpressionContext ctx) {
//        if (ctx.getChildCount() == 3) {
//            ASTNode operation = currentContainer.pop();
//            currentContainer.peek().addChild(operation);
//        }
//    }
}