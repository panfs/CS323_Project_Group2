package impl.project2;

import generated.Splc.SplcBaseVisitor;
import generated.Splc.SplcParser;

public class ConstExprVisitor extends SplcBaseVisitor<Integer> {
    @Override
    protected Integer defaultResult() {
        return null;
    }

    @Override
    protected Integer aggregateResult(Integer aggregate, Integer nextResult) {
        return nextResult != null ? nextResult : aggregate;
    }

    @Override
    public Integer visitPrimaryExpression(SplcParser.PrimaryExpressionContext ctx) {
        return visit(ctx.primary());
    }

    @Override
    public Integer visitNumberPrimary(SplcParser.NumberPrimaryContext ctx) {
        return Integer.parseInt(ctx.Number().getText());
    }

    @Override
    public Integer visitParenPrimary(SplcParser.ParenPrimaryContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Integer visitUnaryPlusExpression(SplcParser.UnaryPlusExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Integer visitUnaryMinusExpression(SplcParser.UnaryMinusExpressionContext ctx) {
        Integer v = visit(ctx.expression());
        return v == null ? null : -v;
    }

    @Override
    public Integer visitAdditiveExpression(SplcParser.AdditiveExpressionContext ctx) {
        Integer l = visit(ctx.expression(0));
        Integer r = visit(ctx.expression(1));
        if (l == null || r == null) return null;
        if (ctx.PLUS() != null) return l + r;
        return l - r;
    }

    @Override
    public Integer visitMultiplicativeExpression(SplcParser.MultiplicativeExpressionContext ctx) {
        Integer l = visit(ctx.expression(0));
        Integer r = visit(ctx.expression(1));
        if (l == null || r == null) return null;
        if (ctx.STAR() != null) return l * r;
        if (ctx.DIV() != null) {
            if (r == 0) return null;
            return l / r;
        }
        if (ctx.MOD() != null) {
            if (r == 0) return null;
            return l % r;
        }
        return null;
    }

    @Override public Integer visitIdentifierPrimary(SplcParser.IdentifierPrimaryContext ctx) { return null; }
    @Override public Integer visitCharPrimary(SplcParser.CharPrimaryContext ctx) { return null; }
    @Override public Integer visitArrayAccessExpression(SplcParser.ArrayAccessExpressionContext ctx) { return null; }
    @Override public Integer visitStructAccessExpression(SplcParser.StructAccessExpressionContext ctx) { return null; }
    @Override public Integer visitStructPtrAccessExpression(SplcParser.StructPtrAccessExpressionContext ctx) { return null; }
    @Override public Integer visitFunctionCallExpression(SplcParser.FunctionCallExpressionContext ctx) { return null; }
    @Override public Integer visitPostfixIncExpression(SplcParser.PostfixIncExpressionContext ctx) { return null; }
    @Override public Integer visitPostfixDecExpression(SplcParser.PostfixDecExpressionContext ctx) { return null; }
    @Override public Integer visitPrefixIncExpression(SplcParser.PrefixIncExpressionContext ctx) { return null; }
    @Override public Integer visitPrefixDecExpression(SplcParser.PrefixDecExpressionContext ctx) { return null; }
    @Override public Integer visitAddressOfExpression(SplcParser.AddressOfExpressionContext ctx) { return null; }
    @Override public Integer visitDereferenceExpression(SplcParser.DereferenceExpressionContext ctx) { return null; }
    @Override public Integer visitRelationalExpression(SplcParser.RelationalExpressionContext ctx) { return null; }
    @Override public Integer visitEqualityExpression(SplcParser.EqualityExpressionContext ctx) { return null; }
    @Override public Integer visitLogicalAndExpression(SplcParser.LogicalAndExpressionContext ctx) { return null; }
    @Override public Integer visitLogicalOrExpression(SplcParser.LogicalOrExpressionContext ctx) { return null; }
    @Override public Integer visitAssignExpression(SplcParser.AssignExpressionContext ctx) { return null; }

    @Override
    public Integer visitVarDecStmt(SplcParser.VarDecStmtContext ctx) {
        return null;
    }
}