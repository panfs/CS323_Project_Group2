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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        SYMBOL_TO_TOKEN_MAP.put("}", "RBRACE");SYMBOL_TO_TOKEN_MAP.put("RBRACE", "RBRACE");
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


        if (recognizer instanceof Parser) {
            Parser parser = (Parser) recognizer;
            Token currentToken = parser.getCurrentToken();
        }

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
//        int missingIndex = msg.indexOf("missing");
//        if (missingIndex == -1) {
//            return "UNKNOWN";
//        }
//
//        int startQuote = msg.indexOf("'", missingIndex + 7); // +7 是 "missing" 的长度
//        if (startQuote == -1) {
//            return "UNKNOWN";
//        }
//
//        int endQuote = msg.indexOf("'", startQuote + 1);
//        if (endQuote == -1) {
//            return "UNKNOWN";
//        }
//
//        return msg.substring(startQuote + 1, endQuote);
        
        // 特别处理 "mismatched input" 情况
        if (msg.contains("mismatched input")) {
            return extractFromMismatchedInput(msg);
        }

        // 特别处理 "no viable alternative" 情况
        if (msg.contains("no viable alternative")) {
            return extractFromNoViableAlternative(msg);
        }

        // 特别处理 extraneous input '<EOF>' expecting { ... } 的情况
        if (msg.contains("extraneous input '<EOF>'") && msg.contains("expecting")) {
            
            // 在这种情况下，我们需要推断缺失的符号
            // 对于函数定义，最可能缺失的是右大括号
            if (msg.contains("'}") || msg.contains("RBRACE")) {
                return "}";
            }

            // 可以根据其他上下文推断其他符号
            // 比如如果包含 ';'，可能是缺失分号
            if (msg.contains("';") || msg.contains("SEMI")) {
                return ";";
            }
        }
        // 格式1: "missing X at Y"
        if (msg.contains("missing")) {
            Pattern pattern = Pattern.compile("missing\\s+([^\\s]+)\\s+at");
            Matcher matcher = pattern.matcher(msg);
            if (matcher.find()) {
                String symbol = matcher.group(1);
                return cleanSymbol(symbol);
            }

            // 另一种格式: "missing 'X'"
            pattern = Pattern.compile("missing\\s+'([^']*)'");
            matcher = pattern.matcher(msg);
            if (matcher.find()) {
                String symbol = matcher.group(1);
                return cleanSymbol(symbol);
            }
        }
        // 格式2: "extraneous input X expecting Y"
        if (msg.contains("expecting")) {
            // 处理单个期望符号的情况
            Pattern pattern = Pattern.compile("expecting\\s+([^\\s,;]+)");
            Matcher matcher = pattern.matcher(msg);
            if (matcher.find()) {
                String symbol = matcher.group(1);
                return cleanSymbol(symbol);
            }

            // 处理带引号的单个期望符号
            pattern = Pattern.compile("expecting\\s+'([^']*)'");
            matcher = pattern.matcher(msg);
            if (matcher.find()) {
                String symbol = matcher.group(1);
                return cleanSymbol(symbol);
            }

            // 处理期望符号集合的情况
            if (msg.contains("expecting {")) {
                // 从集合中提取最可能的符号
                return extractFromExpectingSet(msg);
            }
        }
        // 格式3: 直接查找单引号包围的内容
        Pattern pattern = Pattern.compile("'([^']*)'");
        Matcher matcher = pattern.matcher(msg);
        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group(1));
        }

        if (!matches.isEmpty()) {
            // 通常最后一个单引号内容是我们需要的
            String symbol = matches.get(matches.size() - 1);
            return cleanSymbol(symbol);
        }

        return "UNKNOWN";
    }
    private String cleanSymbol(String symbol) {
        if (symbol == null) return "UNKNOWN";

        // 移除可能的引号
        symbol = symbol.replace("'", "").replace("\"", "");

        // 处理特殊符号名称
        if (symbol.equals("{")) return "LBRACE";
        if (symbol.equals("}")) return "RBRACE";
        if (symbol.equals("(")) return "LPAREN";
        if (symbol.equals(")")) return "RPAREN";
        if (symbol.equals("[")) return "LBRACK";
        if (symbol.equals("]")) return "RBRACK";
        if (symbol.equals(";")) return "SEMI";

        return symbol;
    }
    private String extractFromExpectingSet(String msg) {
        
        // 根据上下文推断最可能缺失的符号
        // 这里可以根据具体的语法规则进行更精确的推断

        // 如果消息包含右大括号，推断缺失右大括号
        if (msg.contains("'}") || msg.contains("RBRACE")) {
            return "}";
        }

        // 如果消息包含右括号，推断缺失右括号
        if (msg.contains("')") || msg.contains("RPAREN")) {
            return ")";
        }

        // 如果消息包含右方括号，推断缺失右方括号
        if (msg.contains("']") || msg.contains("RBRACK")) {
            return "]";
        }

        // 如果消息包含分号，推断缺失分号
        if (msg.contains("';") || msg.contains("SEMI")) {
            return ";";
        }

        // 默认情况下，如果是在函数或代码块末尾，最可能缺失右大括号
        return "}";
    }
    private String extractFromNoViableAlternative(String msg) {
        
        // 分析输入内容来推断缺失的符号
        Pattern pattern = Pattern.compile("input '([^']*)'");
        Matcher matcher = pattern.matcher(msg);
        if (matcher.find()) {
            String input = matcher.group(1);
            
            // 根据常见的语法错误模式进行推断

            // 模式1: 函数定义中缺少左括号
            // 例如: "intmain)" -> 应该在 main 和 ) 之间缺少 (
            if (input.matches(".*[a-zA-Z_][a-zA-Z_0-9]*\\).*")) {
                return "(";
            }

            // 模式2: 缺少分号
            // 例如: "intx" 后面没有符号
            if (input.matches(".*[a-zA-Z_][a-zA-Z_0-9]*[^;\\s].*")) {
                return ";";
            }

            // 模式3: 结构体访问缺少点号
            if (input.matches(".*[a-zA-Z_][a-zA-Z_0-9]*\\s+[a-zA-Z_][a-zA-Z_0-9]*.*")) {
                return ".";
            }

            // 模式4: 数组访问缺少左方括号
            if (input.matches(".*[a-zA-Z_][a-zA-Z_0-9]*\\s*\\d+.*")) {
                return "[";
            }
        }

        // 默认推断为最常见的错误 - 缺少左括号
        return "(";
    }
    private String extractFromMismatchedInput(String msg) {
        
        // 分析错误消息来推断缺失的符号
        Pattern pattern = Pattern.compile("expecting \\{([^}]+)\\}");
        Matcher matcher = pattern.matcher(msg);
        if (matcher.find()) {
            String expectingSet = matcher.group(1);
            
            // 根据期望的符号集合推断最可能缺失的符号

            // 如果期望的是类型说明符，推断缺失类型说明符
            if (expectingSet.contains("'int'") || expectingSet.contains("'char'") ||
                    expectingSet.contains("'struct'") || expectingSet.contains("INT") ||
                    expectingSet.contains("CHAR") || expectingSet.contains("STRUCT")) {
                return "int";
            }

            // 如果期望的是语句开始符号，推断缺失分号或右大括号
            if (expectingSet.contains("';'") || expectingSet.contains("SEMI") ||
                    expectingSet.contains("'}'") || expectingSet.contains("RBRACE")) {
                return ";";
            }

            // 如果期望的是表达式开始符号，推断缺失标识符或常量
            if (expectingSet.contains("Identifier") || expectingSet.contains("Number") ||
                    expectingSet.contains("Char")) {
                return "Identifier";
            }
        }

        // 默认推断为类型说明符
        return "int";
    }
//    private String convertToTokenName(String symbol) {
//        if (symbol == null || symbol.isEmpty()) {
//            return "UNKNOWN";
//        }
//
//        if (SYMBOL_TO_TOKEN_MAP.containsKey(symbol)) {
//            return SYMBOL_TO_TOKEN_MAP.get(symbol);
//        }
//
//        if (symbol.matches("[A-Z_]+")) {
//            return symbol;
//        }
//
//        return symbol.toUpperCase();
//    }
    private String convertToTokenName(String symbol) {
        if (symbol == null || symbol.isEmpty()) {
            return "UNKNOWN";
        }

       
        // 特殊处理各种符号
        if (symbol.equals("}") || symbol.equals("RBRACE") || symbol.equals("'}'")) {
            return "RBRACE";
        }
        if (symbol.equals("(") || symbol.equals("LPAREN") || symbol.equals("'('")) {
            return "LPAREN";
        }
        if (symbol.equals(")") || symbol.equals("RPAREN") || symbol.equals("')'")) {
            return "RPAREN";
        }
        if (symbol.equals("int") || symbol.equals("INT")) {
            return "INT";
        }
        if (symbol.equals("char") || symbol.equals("CHAR")) {
            return "CHAR";
        }
        if (symbol.equals("struct") || symbol.equals("STRUCT")) {
            return "STRUCT";
        }

        // 首先检查映射表
        if (SYMBOL_TO_TOKEN_MAP.containsKey(symbol)) {
            String result = SYMBOL_TO_TOKEN_MAP.get(symbol);
            return result;
        }

        // 如果已经是全大写的token名称，直接返回
        if (symbol.matches("[A-Z][A-Z_]*")) {
            return symbol;
        }

        // 特殊处理其他常见符号
        switch (symbol) {
            case "{":
            case "LBRACE":
            case "'{'":
                return "LBRACE";
            case "]":
            case "RBRACK":
            case "']'":
                return "RBRACK";
            case "[":
            case "LBRACK":
            case "'['":
                return "LBRACK";
            case ";":
            case "SEMI":
            case "';'":
                return "SEMI";
            default:
                return symbol.toUpperCase();
        }
    }


//    private int getPreviousTokenLine(Recognizer<?, ?> recognizer, int currentLine) {
//        if (recognizer instanceof Parser) {
//            Parser parser = (Parser) recognizer;
//            TokenStream tokenStream = parser.getTokenStream();
//
//            Token currentToken = parser.getCurrentToken();
//            int currentIndex = currentToken.getTokenIndex();
//
//            for (int i = currentIndex - 1; i >= 0; i--) {
//                Token token = tokenStream.get(i);
//                if (token.getChannel() == Token.DEFAULT_CHANNEL) {
//                    return token.getLine() - 1;
//                }
//            }
//        }
//
//        return currentLine - 2;
//    }
    private int getPreviousTokenLine(Recognizer<?, ?> recognizer, int currentLine) {
        if (recognizer instanceof Parser) {
            Parser parser = (Parser) recognizer;
            TokenStream tokenStream = parser.getTokenStream();

            Token currentToken = parser.getCurrentToken();
            int currentIndex = currentToken.getTokenIndex();

            // 向前查找上一个在默认通道的token
            for (int i = currentIndex - 1; i >= 0; i--) {
                Token token = tokenStream.get(i);
                
                if (token.getChannel() == Token.DEFAULT_CHANNEL && !token.getText().isEmpty()) {
                    int line = token.getLine() - 1; // 转换为0-based
                    return line;
                }
            }

            // 如果没找到有效token，尝试找第一个token
            for (int i = 0; i < currentIndex; i++) {
                Token token = tokenStream.get(i);
                if (token.getChannel() == Token.DEFAULT_CHANNEL && !token.getText().isEmpty()) {
                    int line = token.getLine() - 1;
                    return line;
                }
            }
        }

        // 如果没有找到前一个token，使用当前行减1
        int fallbackLine = Math.max(0, currentLine - 1); // ANTLR行号是1-based，我们转换为0-based
        return fallbackLine;
    }
}