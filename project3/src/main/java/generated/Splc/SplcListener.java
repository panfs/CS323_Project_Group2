// Generated from D:/0大学/大学3年级/编译原理/projects/CS323-Compilers-2025F-Projects/Splc.g4 by ANTLR 4.13.2
package generated.Splc;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SplcParser}.
 */
public interface SplcListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SplcParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(SplcParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SplcParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(SplcParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link SplcParser#globalDef}.
	 * @param ctx the parse tree
	 */
	void enterGlobalDef(SplcParser.GlobalDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SplcParser#globalDef}.
	 * @param ctx the parse tree
	 */
	void exitGlobalDef(SplcParser.GlobalDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SplcParser#specifier}.
	 * @param ctx the parse tree
	 */
	void enterSpecifier(SplcParser.SpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SplcParser#specifier}.
	 * @param ctx the parse tree
	 */
	void exitSpecifier(SplcParser.SpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link SplcParser#varDec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(SplcParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SplcParser#varDec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(SplcParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SplcParser#funcArgs}.
	 * @param ctx the parse tree
	 */
	void enterFuncArgs(SplcParser.FuncArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SplcParser#funcArgs}.
	 * @param ctx the parse tree
	 */
	void exitFuncArgs(SplcParser.FuncArgsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(SplcParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(SplcParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarDecStmt}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVarDecStmt(SplcParser.VarDecStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarDecStmt}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVarDecStmt(SplcParser.VarDecStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(SplcParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(SplcParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(SplcParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(SplcParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(SplcParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(SplcParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(SplcParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(SplcParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(SplcParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(SplcParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(SplcParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(SplcParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalAndExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(SplcParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalAndExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(SplcParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpression(SplcParser.AssignExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpression(SplcParser.AssignExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DereferenceExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDereferenceExpression(SplcParser.DereferenceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DereferenceExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDereferenceExpression(SplcParser.DereferenceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentifierPrimary}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierPrimary(SplcParser.IdentifierPrimaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentifierPrimary}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierPrimary(SplcParser.IdentifierPrimaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixDecExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrefixDecExpression(SplcParser.PrefixDecExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixDecExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrefixDecExpression(SplcParser.PrefixDecExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalOrExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(SplcParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalOrExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(SplcParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StructPtrAccessExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStructPtrAccessExpression(SplcParser.StructPtrAccessExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StructPtrAccessExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStructPtrAccessExpression(SplcParser.StructPtrAccessExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalNotExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalNotExpression(SplcParser.LogicalNotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalNotExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalNotExpression(SplcParser.LogicalNotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberPrimary}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumberPrimary(SplcParser.NumberPrimaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberPrimary}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumberPrimary(SplcParser.NumberPrimaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenPrimary}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenPrimary(SplcParser.ParenPrimaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenPrimary}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenPrimary(SplcParser.ParenPrimaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCallExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpression(SplcParser.FunctionCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCallExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpression(SplcParser.FunctionCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixDecExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixDecExpression(SplcParser.PostfixDecExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixDecExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixDecExpression(SplcParser.PostfixDecExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryMinusExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinusExpression(SplcParser.UnaryMinusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryMinusExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinusExpression(SplcParser.UnaryMinusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CharPrimary}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCharPrimary(SplcParser.CharPrimaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CharPrimary}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCharPrimary(SplcParser.CharPrimaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddressOfExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddressOfExpression(SplcParser.AddressOfExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddressOfExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddressOfExpression(SplcParser.AddressOfExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixIncExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrefixIncExpression(SplcParser.PrefixIncExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixIncExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrefixIncExpression(SplcParser.PrefixIncExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryPlusExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPlusExpression(SplcParser.UnaryPlusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryPlusExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPlusExpression(SplcParser.UnaryPlusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixIncExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixIncExpression(SplcParser.PostfixIncExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixIncExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixIncExpression(SplcParser.PostfixIncExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualityExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(SplcParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualityExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(SplcParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(SplcParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(SplcParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayAccessExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccessExpression(SplcParser.ArrayAccessExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayAccessExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccessExpression(SplcParser.ArrayAccessExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StructAccessExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStructAccessExpression(SplcParser.StructAccessExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StructAccessExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStructAccessExpression(SplcParser.StructAccessExpressionContext ctx);
}