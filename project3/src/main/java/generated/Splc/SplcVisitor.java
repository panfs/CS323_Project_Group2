// Generated from D:/0大学/大学3年级/编译原理/projects/CS323-Compilers-2025F-Projects/Splc.g4 by ANTLR 4.13.2
package generated.Splc;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SplcParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SplcVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SplcParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(SplcParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link SplcParser#globalDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalDef(SplcParser.GlobalDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SplcParser#specifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecifier(SplcParser.SpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link SplcParser#varDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(SplcParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link SplcParser#funcArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncArgs(SplcParser.FuncArgsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BlockStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(SplcParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarDecStmt}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecStmt(SplcParser.VarDecStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(SplcParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(SplcParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(SplcParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionStatement}
	 * labeled alternative in {@link SplcParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(SplcParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(SplcParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(SplcParser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalAndExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(SplcParser.LogicalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpression(SplcParser.AssignExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DereferenceExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDereferenceExpression(SplcParser.DereferenceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentifierPrimary}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierPrimary(SplcParser.IdentifierPrimaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixDecExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixDecExpression(SplcParser.PrefixDecExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalOrExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(SplcParser.LogicalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StructPtrAccessExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructPtrAccessExpression(SplcParser.StructPtrAccessExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalNotExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalNotExpression(SplcParser.LogicalNotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberPrimary}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberPrimary(SplcParser.NumberPrimaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenPrimary}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenPrimary(SplcParser.ParenPrimaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionCallExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallExpression(SplcParser.FunctionCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixDecExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixDecExpression(SplcParser.PostfixDecExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryMinusExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryMinusExpression(SplcParser.UnaryMinusExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CharPrimary}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharPrimary(SplcParser.CharPrimaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddressOfExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddressOfExpression(SplcParser.AddressOfExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixIncExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixIncExpression(SplcParser.PrefixIncExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryPlusExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryPlusExpression(SplcParser.UnaryPlusExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixIncExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixIncExpression(SplcParser.PostfixIncExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualityExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(SplcParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(SplcParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayAccessExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccessExpression(SplcParser.ArrayAccessExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StructAccessExpression}
	 * labeled alternative in {@link SplcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructAccessExpression(SplcParser.StructAccessExpressionContext ctx);
}