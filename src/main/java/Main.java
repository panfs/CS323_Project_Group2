import framework.project1.Grader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import generated.Calc1.Calc1Lexer;
import generated.Calc1.Calc1Parser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        FileInputStream fis = new FileInputStream("testcases/project1/case1.splc");
//        Grader grader1 = new Grader(fis);
//        grader1.run();
//
//        System.out.println("---");
//
//        Grader grader = new Grader("1 + 2");
//        grader.run();
        // 把要解析的字符串喂给 ANTLR
        CharStream input = CharStreams.fromString("(1 + 2 * 2 / 3)");
        // 词法分析器
        Calc1Lexer lexer = new Calc1Lexer(input);
        // Token 流
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // 语法分析器
        Calc1Parser parser = new Calc1Parser(tokens);
        // 从规则 'expression' 开始解析
        ParseTree tree = parser.expr();
        // 把树打印出来看看
        System.out.println(tree.toStringTree(parser));
    }
}
