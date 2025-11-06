// Generated from D:/0大学/大学3年级/编译原理/projects/CS323-Compilers-2025F-Projects/Calc1.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Calc1Parser}.
 */
public interface Calc1Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Calc1Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(Calc1Parser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Calc1Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(Calc1Parser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Calc1Parser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(Calc1Parser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Calc1Parser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(Calc1Parser.FactorContext ctx);
}