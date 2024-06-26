// Generated from /Users/jorey/Documents/School/ASD/APP/Programmeertalen/Compiler Opdracht/icss2022-sep/startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ICSSParser}.
 */
public interface ICSSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 */
	void enterStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 */
	void exitStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#styleDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStyleDeclaration(ICSSParser.StyleDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#styleDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStyleDeclaration(ICSSParser.StyleDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code scalarLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterScalarLiteral(ICSSParser.ScalarLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code scalarLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitScalarLiteral(ICSSParser.ScalarLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pixelLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterPixelLiteral(ICSSParser.PixelLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pixelLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitPixelLiteral(ICSSParser.PixelLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code colorLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterColorLiteral(ICSSParser.ColorLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code colorLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitColorLiteral(ICSSParser.ColorLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code percentageLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterPercentageLiteral(ICSSParser.PercentageLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code percentageLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitPercentageLiteral(ICSSParser.PercentageLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(ICSSParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(ICSSParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#boolLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBoolLiteral(ICSSParser.BoolLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#boolLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBoolLiteral(ICSSParser.BoolLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#styleTag}.
	 * @param ctx the parse tree
	 */
	void enterStyleTag(ICSSParser.StyleTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#styleTag}.
	 * @param ctx the parse tree
	 */
	void exitStyleTag(ICSSParser.StyleTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#variableName}.
	 * @param ctx the parse tree
	 */
	void enterVariableName(ICSSParser.VariableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#variableName}.
	 * @param ctx the parse tree
	 */
	void exitVariableName(ICSSParser.VariableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#variableAssignment}.
	 * @param ctx the parse tree
	 */
	void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#variableAssignment}.
	 * @param ctx the parse tree
	 */
	void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#tagSelector}.
	 * @param ctx the parse tree
	 */
	void enterTagSelector(ICSSParser.TagSelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#tagSelector}.
	 * @param ctx the parse tree
	 */
	void exitTagSelector(ICSSParser.TagSelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#classSelector}.
	 * @param ctx the parse tree
	 */
	void enterClassSelector(ICSSParser.ClassSelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#classSelector}.
	 * @param ctx the parse tree
	 */
	void exitClassSelector(ICSSParser.ClassSelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#idSelector}.
	 * @param ctx the parse tree
	 */
	void enterIdSelector(ICSSParser.IdSelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#idSelector}.
	 * @param ctx the parse tree
	 */
	void exitIdSelector(ICSSParser.IdSelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#boolExpression}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpression(ICSSParser.BoolExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#boolExpression}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpression(ICSSParser.BoolExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#boolCheck}.
	 * @param ctx the parse tree
	 */
	void enterBoolCheck(ICSSParser.BoolCheckContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#boolCheck}.
	 * @param ctx the parse tree
	 */
	void exitBoolCheck(ICSSParser.BoolCheckContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(ICSSParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(ICSSParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseStatement(ICSSParser.ElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseStatement(ICSSParser.ElseStatementContext ctx);
}