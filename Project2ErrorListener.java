//package impl.project2;
//
//import framework.project2.Grader;
//import framework.project2.MissingSymbolError;
//import org.antlr.v4.runtime.BaseErrorListener;
//import org.antlr.v4.runtime.RecognitionException;
//import org.antlr.v4.runtime.Recognizer;
//import org.antlr.v4.runtime.misc.ParseCancellationException;
//
//public class Project2ErrorListener extends BaseErrorListener {
//    private final Grader grader;
//    public Project2ErrorListener(Grader grader) {
//        this.grader = grader;
//    }
//
//    @Override
//    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
//        // TODO: extract information
//
//
//        MissingSymbolError missingSymbol = new MissingSymbolError("qwq", 1234);
//
//        this.grader.getWriter().println(missingSymbol);
//        throw new ParseCancellationException();
//    }
//}


package impl.project2;

import framework.project2.Grader;
import framework.project2.MissingSymbolError;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.antlr.v4.runtime.tree.ParseTree;

import generated.Splc.SplcBaseVisitor;
import generated.Splc.SplcLexer;
import generated.Splc.SplcParser;

public class Project2ErrorListener extends BaseErrorListener {
    private final Grader grader;
    // 常见符号到token名称的映射表
    private static final Map<String, String> SYMBOL_TO_TOKEN_MAP = new HashMap<>();

    static {
        // ---------- 关键字映射 ----------
        SYMBOL_TO_TOKEN_MAP.put("int", "INT");
        SYMBOL_TO_TOKEN_MAP.put("char", "CHAR");
        SYMBOL_TO_TOKEN_MAP.put("struct", "STRUCT");
        SYMBOL_TO_TOKEN_MAP.put("return", "RETURN");
        SYMBOL_TO_TOKEN_MAP.put("if", "IF");
        SYMBOL_TO_TOKEN_MAP.put("else", "ELSE");
        SYMBOL_TO_TOKEN_MAP.put("while", "WHILE");

        // ---------- 运算符映射 ----------
        SYMBOL_TO_TOKEN_MAP.put("=", "ASSIGN");
        SYMBOL_TO_TOKEN_MAP.put("+", "PLUS");
        SYMBOL_TO_TOKEN_MAP.put("-", "MINUS");
        SYMBOL_TO_TOKEN_MAP.put("*", "STAR");
        SYMBOL_TO_TOKEN_MAP.put("/", "DIV");
        SYMBOL_TO_TOKEN_MAP.put("%", "MOD");
        SYMBOL_TO_TOKEN_MAP.put("<", "LT");
        SYMBOL_TO_TOKEN_MAP.put("<=", "LE");
        SYMBOL_TO_TOKEN_MAP.put(">", "GT");
        SYMBOL_TO_TOKEN_MAP.put(">=", "GE");
        SYMBOL_TO_TOKEN_MAP.put("==", "EQ");
        SYMBOL_TO_TOKEN_MAP.put("!=", "NEQ");
        SYMBOL_TO_TOKEN_MAP.put("&&", "AND");
        SYMBOL_TO_TOKEN_MAP.put("||", "OR");
        SYMBOL_TO_TOKEN_MAP.put("!", "NOT");
        SYMBOL_TO_TOKEN_MAP.put("++", "INC");
        SYMBOL_TO_TOKEN_MAP.put("--", "DEC");
        SYMBOL_TO_TOKEN_MAP.put(".", "DOT");
        SYMBOL_TO_TOKEN_MAP.put("->", "ARROW");
        SYMBOL_TO_TOKEN_MAP.put("&", "AMP");

        // ---------- 分隔符映射 ----------
        SYMBOL_TO_TOKEN_MAP.put(";", "SEMI");
        SYMBOL_TO_TOKEN_MAP.put(",", "COMMA");
        SYMBOL_TO_TOKEN_MAP.put("(", "LPAREN");
        SYMBOL_TO_TOKEN_MAP.put(")", "RPAREN");
        SYMBOL_TO_TOKEN_MAP.put("{", "LBRACE");
        SYMBOL_TO_TOKEN_MAP.put("}", "RBRACE");
        SYMBOL_TO_TOKEN_MAP.put("[", "LBRACK");
        SYMBOL_TO_TOKEN_MAP.put("]", "RBRACK");

        // ---------- 标识符和字面量 ----------
        // 注意：Identifier、Number、Char通常不需要在映射表中，
        // 因为ANTLR的错误消息中通常不会直接说"missing Identifier"
        // 但如果需要，可以添加：
         SYMBOL_TO_TOKEN_MAP.put("identifier", "Identifier");
         SYMBOL_TO_TOKEN_MAP.put("number", "Number");
         SYMBOL_TO_TOKEN_MAP.put("char", "Char");
    }
    public Project2ErrorListener(Grader grader) {
        this.grader = grader;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine, String msg,
                            RecognitionException e) {

        String missingSymbol = extractMissingSymbol(msg);
        String tokenName = convertToTokenName(missingSymbol);
        int previousTokenLine = getPreviousTokenLine(recognizer, line);


        MissingSymbolError missingSymbolError = new MissingSymbolError(tokenName, previousTokenLine);

        this.grader.getWriter().println(missingSymbolError);
        throw new ParseCancellationException();
    }


    private String extractMissingSymbol(String msg) {
        if (msg == null || msg.isEmpty()) {
            return "UNKNOWN";
        }

        int missingIndex = msg.indexOf("missing");
        if (missingIndex == -1) {
            return "UNKNOWN";
        }

        int startQuote = msg.indexOf("'", missingIndex + 7); // +7 是 "missing" 的长度
        if (startQuote == -1) {
            return "UNKNOWN";
        }

        int endQuote = msg.indexOf("'", startQuote + 1);
        if (endQuote == -1) {
            return "UNKNOWN";
        }

        return msg.substring(startQuote + 1, endQuote);
    }

    private String convertToTokenName(String symbol) {
        if (symbol == null || symbol.isEmpty()) {
            return "UNKNOWN";
        }

        if (SYMBOL_TO_TOKEN_MAP.containsKey(symbol)) {
            return SYMBOL_TO_TOKEN_MAP.get(symbol);
        }

        if (symbol.matches("[A-Z_]+")) {
            return symbol;
        }

        return symbol.toUpperCase();
    }


    private int getPreviousTokenLine(Recognizer<?, ?> recognizer, int currentLine) {
        if (recognizer instanceof Parser) {
            Parser parser = (Parser) recognizer;
            TokenStream tokenStream = parser.getTokenStream();

            Token currentToken = parser.getCurrentToken();
            int currentIndex = currentToken.getTokenIndex();

            for (int i = currentIndex - 1; i >= 0; i--) {
                Token token = tokenStream.get(i);
                if (token.getChannel() == Token.DEFAULT_CHANNEL) {
                    return token.getLine() - 1;
                }
            }
        }

        return currentLine - 2;
    }
}