package impl;

import framework.AbstractCompiler;
import framework.AbstractGrader;
import framework.lang.Type;
import framework.project3.Project3SemanticError;
import generated.Splc.SplcBaseVisitor;
import generated.Splc.SplcLexer;
import generated.Splc.SplcParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;
import java.util.*;

public class Compiler extends AbstractCompiler {
    public Compiler(AbstractGrader grader) {
        super(grader);
    }

    @Override
    public void start() throws IOException {
        CharStream input = CharStreams.fromStream(this.grader.getSourceStream());
        SplcLexer lexer = new SplcLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SplcParser parser = new SplcParser(tokens);
        SplcParser.ProgramContext program = parser.program();

        SemanticChecker checker = new SemanticChecker(grader);
        checker.visit(program);

        if (!checker.hasError) {
            checker.printOutput();
        }
    }

    // ==================== 类型系统 ====================
    
    // 基础类型
    static class PrimitiveType implements Type {
        final String name;

        PrimitiveType(String name) {
            this.name = name;
        }

        @Override
        public String prettyPrint() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PrimitiveType)) return false;
            PrimitiveType that = (PrimitiveType) o;
            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    // 数组类型
    static class ArrayType implements Type {
        final Type elementType;
        final int size;

        ArrayType(Type elementType, int size) {
            this.elementType = elementType;
            this.size = size;
        }

        @Override
        public String prettyPrint() {
            return elementType.prettyPrint() + "[" + size + "]";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ArrayType)) return false;
            ArrayType arrayType = (ArrayType) o;
            return size == arrayType.size && elementType.equals(arrayType.elementType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(elementType, size);
        }
    }

    // 指针类型
    static class PointerType implements Type {
        final Type pointeeType;

        PointerType(Type pointeeType) {
            this.pointeeType = pointeeType;
        }

        @Override
        public String prettyPrint() {
            return pointeeType.prettyPrint() + "*";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PointerType)) return false;
            PointerType that = (PointerType) o;
            return pointeeType.equals(that.pointeeType);
        }

        @Override
        public int hashCode() {
            return pointeeType.hashCode();
        }
    }

    // 结构体类型
    static class StructType implements Type {
        final String tag;
        boolean isComplete;
        final Map<String, Type> members; // 成员名 -> 类型
        final List<String> memberOrder; // 保持成员顺序

        StructType(String tag) {
            this.tag = tag;
            this.isComplete = false;
            this.members = new LinkedHashMap<>();
            this.memberOrder = new ArrayList<>();
        }

        void addMember(String name, Type type) {
            members.put(name, type);
            memberOrder.add(name);
        }

        void setComplete() {
            this.isComplete = true;
        }

        @Override
        public String prettyPrint() {
            return "struct " + tag;
        }

        @Override
        public String fullPrint() {
            if (!isComplete) {
                return prettyPrint();
            }
            StringBuilder sb = new StringBuilder("struct " + tag + "{");
            for (String memberName : memberOrder) {
                Type memberType = members.get(memberName);
                sb.append(memberType.prettyPrint()).append(" ").append(memberName).append(";");
            }
            sb.append("}");
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof StructType)) return false;
            StructType that = (StructType) o;
            return this == that;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(this);
        }
    }

    // 函数类型
    static class FunctionType implements Type {
        final Type returnType;
        final List<Type> paramTypes;

        FunctionType(Type returnType, List<Type> paramTypes) {
            this.returnType = returnType;
            this.paramTypes = paramTypes;
        }

        @Override
        public String prettyPrint() {
            StringBuilder sb = new StringBuilder();
            sb.append(returnType.prettyPrint()).append("(");
            for (int i = 0; i < paramTypes.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(paramTypes.get(i).prettyPrint());
            }
            sb.append(")");
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FunctionType)) return false;
            FunctionType that = (FunctionType) o;
            return returnType.equals(that.returnType) && paramTypes.equals(that.paramTypes);
        }

        @Override
        public int hashCode() {
            return Objects.hash(returnType, paramTypes);
        }
    }

    // ==================== 符号表 ====================
    
    static class Symbol {
        final String name;
        final Type type;
        final TerminalNode node;

        Symbol(String name, Type type, TerminalNode node) {
            this.name = name;
            this.type = type;
            this.node = node;
        }
    }

    static class Scope {
        final Map<String, Symbol> symbols = new HashMap<>();
        final Map<String, StructType> structTags = new HashMap<>();
    }

    static class SymbolTable {
        final List<Scope> scopes = new ArrayList<>();
        final Scope globalScope;

        SymbolTable() {
            globalScope = new Scope();
            scopes.add(globalScope);
        }

        void enterScope() { //next layer
            scopes.add(new Scope());
        }

        void exitScope() { //front layer
            if (scopes.size() > 1) {
                scopes.remove(scopes.size() - 1);
            }
        }

        Scope currentScope() {
            return scopes.get(scopes.size() - 1);
        }

        boolean isGlobalScope() {
            return scopes.size() == 1;
        }

        // 在当前作用域查找符号
        Symbol lookupInCurrentScope(String name) {
            return currentScope().symbols.get(name);
        }

        // 在所有可见作用域查找符号
        Symbol lookup(String name) {
            for (int i = scopes.size() - 1; i >= 0; i--) {
                Symbol sym = scopes.get(i).symbols.get(name);
                if (sym != null) return sym;
            }
            return null;
        }

        // 定义符号
        void define(String name, Type type, TerminalNode node) {
            currentScope().symbols.put(name, new Symbol(name, type, node));
        }

        // 查找结构体标签
        StructType lookupStructTag(String tag) {
            for (int i = scopes.size() - 1; i >= 0; i--) {
                StructType type = scopes.get(i).structTags.get(tag);
                if (type != null) return type;
            }
            return null;
        }

        // 在当前作用域查找结构体标签
        StructType lookupStructTagInCurrentScope(String tag) {
            return currentScope().structTags.get(tag);
        }

        // 定义结构体标签
        void defineStructTag(String tag, StructType type) {
            currentScope().structTags.put(tag, type);
        }
    }

    // ==================== 语义检查器 ====================
    
    static class VarDecResult {
        Type type;
        String name;
        TerminalNode node;
        
        VarDecResult(Type type, String name, TerminalNode node) {
            this.type = type;
            this.name = name;
            this.node = node;
        }
    }

    static class FunctionSymbol {
        final String name;
        final FunctionType type;
        final TerminalNode node;
        final boolean isDefined; // true=definition，false=declaration
        
        FunctionSymbol(String name, FunctionType type, TerminalNode node, boolean isDefined) {
            this.name = name;
            this.type = type;
            this.node = node;
            this.isDefined = isDefined;
        }
    }

    static class SemanticChecker extends SplcBaseVisitor<Type> {
        final AbstractGrader grader;
        final SymbolTable symbolTable;
        final List<Symbol> globalVariables = new ArrayList<>();
        final Map<String, FunctionSymbol> globalFunctionsMap = new LinkedHashMap<>();
        boolean hasError = false;

        SemanticChecker(AbstractGrader grader) {
            this.grader = grader;
            this.symbolTable = new SymbolTable();
        }

        void reportError(Project3SemanticError error) {
            hasError = true;
            grader.reportSemanticError(error);
        }

        @Override
        public Type visitProgram(SplcParser.ProgramContext ctx) {
            for (SplcParser.GlobalDefContext globalDef : ctx.globalDef()) {
                visitGlobalDef(globalDef);
                if (hasError) return null;
            }
            return null;
        }


	@Override
	public Type visitGlobalDef(SplcParser.GlobalDefContext ctx) {
	    Type baseType = visitSpecifier(ctx.specifier());
	    if (hasError) return null;

	    // 情况1: 函数定义 - specifier Identifier LPAREN funcArgs RPAREN LBRACE statement* RBRACE
	    if (ctx.LBRACE() != null) {
		TerminalNode funcName = ctx.Identifier();
		List<Type> paramTypes = new ArrayList<>();
		
		if (ctx.funcArgs().specifier() != null) {
		    for (int i = 0; i < ctx.funcArgs().specifier().size(); i++) {
		        Type paramBaseType = visitSpecifier(ctx.funcArgs().specifier(i));
		        if (hasError) return null;
		        VarDecResult paramResult = parseVarDecWithInfo(ctx.funcArgs().varDec(i), paramBaseType);
		        if (hasError) return null;
		        paramTypes.add(paramResult.type);
		    }
		}
		
		FunctionType funcType = new FunctionType(baseType, paramTypes);
		// 首先检查是否与变量冲突
		Symbol existingSym = symbolTable.lookupInCurrentScope(funcName.getText());
		if (existingSym != null && !(existingSym.type instanceof FunctionType)) {
		    reportError(Project3SemanticError.redefinition(funcName));
		    return null;
		}
		
		// 检查函数是否已经存在
		FunctionSymbol existingFunc = globalFunctionsMap.get(funcName.getText());
		if (existingFunc != null) {
		    if (existingFunc.isDefined) {
		        // 已经有定义了，不能再定义
		        reportError(Project3SemanticError.redefinition(funcName));
		        return null;
		    }
		    // 如果只是声明，现在提供定义，这是合法的
		    // 更新为定义
		    symbolTable.define(funcName.getText(), funcType, funcName); // 更新符号表
		    globalFunctionsMap.put(funcName.getText(), 
		        new FunctionSymbol(funcName.getText(), funcType, funcName, true));
		} else {
		    // 第一次出现，直接添加定义
		    symbolTable.define(funcName.getText(), funcType, funcName);
		    globalFunctionsMap.put(funcName.getText(), 
		        new FunctionSymbol(funcName.getText(), funcType, funcName, true));
		}
		// 处理函数体（进入新作用域）
		symbolTable.enterScope();
		
		// 将参数加入作用域
		if (ctx.funcArgs().varDec() != null) {
		    for (int i = 0; i < ctx.funcArgs().varDec().size(); i++) {
		        VarDecResult paramResult = parseVarDecWithInfo(ctx.funcArgs().varDec(i), paramTypes.get(i));
		        symbolTable.define(paramResult.name, paramTypes.get(i), paramResult.node);
		    }
		}
		
		// 访问语句
		for (SplcParser.StatementContext stmt : ctx.statement()) {
		    visit(stmt);
		    if (hasError) return null;
		}
		
		symbolTable.exitScope();
	    }
	    // 情况2: 变量声明 - specifier varDec SEMI
	    else if (ctx.varDec() != null) {
		VarDecResult result = parseVarDecWithInfo(ctx.varDec(), baseType);
		if (hasError) return null;
		
		// 检查类型完整性
		if (!isCompleteType(result.type)) {
		    reportError(Project3SemanticError.definitionIncomplete(result.node));
		    return null;
		}
		
		// 检查是否与现有符号冲突（包括函数）
		Symbol existingSym = symbolTable.lookupInCurrentScope(result.name);
		if (existingSym != null) {
		    reportError(Project3SemanticError.redefinition(result.node));
		    return null;
		}
		
		// 检查是否与函数冲突
		FunctionSymbol existingFunc = globalFunctionsMap.get(result.name);
		if (existingFunc != null) {
		    reportError(Project3SemanticError.redefinition(result.node));
		    return null;
		}
		symbolTable.define(result.name, result.type, result.node);
		globalVariables.add(new Symbol(result.name, result.type, result.node));
	    }
	    // 情况3: 结构体声明 - specifier SEMI
	    else if (ctx.SEMI() != null && ctx.varDec() == null && ctx.Identifier() == null) {
		// 仅声明结构体，不需要额外处理
	    }
	    // 情况4: 函数声明 - specifier Identifier LPAREN funcArgs RPAREN SEMI
	    else if (ctx.Identifier() != null && ctx.LPAREN() != null && ctx.SEMI() != null) {
		TerminalNode funcName = ctx.Identifier();
		List<Type> paramTypes = new ArrayList<>();
		
		if (ctx.funcArgs().specifier() != null) {
		    for (int i = 0; i < ctx.funcArgs().specifier().size(); i++) {
		        Type paramBaseType = visitSpecifier(ctx.funcArgs().specifier(i));
		        if (hasError) return null;
		        VarDecResult paramResult = parseVarDecWithInfo(ctx.funcArgs().varDec(i), paramBaseType);
		        if (hasError) return null;
		        paramTypes.add(paramResult.type);
		    }
		}
		
		FunctionType funcType = new FunctionType(baseType, paramTypes);
		
		// 首先检查是否与变量冲突
		Symbol existingSym = symbolTable.lookupInCurrentScope(funcName.getText());
		if (existingSym != null && !(existingSym.type instanceof FunctionType)) {
		    // 已存在同名变量，报重定义错误
		    reportError(Project3SemanticError.redeclaration(funcName));
		    return null;
		}
		
		// 检查是否已经存在函数（声明或定义）
		FunctionSymbol existingFunc = globalFunctionsMap.get(funcName.getText());
		if (existingFunc != null) {
		    // 已经存在（可能是声明或定义）
		    // 重复声明是错误
		    reportError(Project3SemanticError.redeclaration(funcName));
		    return null;
		}
		
		// 第一次声明
		symbolTable.define(funcName.getText(), funcType, funcName);
		globalFunctionsMap.put(funcName.getText(), 
		    new FunctionSymbol(funcName.getText(), funcType, funcName, false));
	    }
	    
	    return null;
	}

        @Override
        public Type visitSpecifier(SplcParser.SpecifierContext ctx) {
            if (ctx.INT() != null) {
                return new PrimitiveType("int");
            } else if (ctx.CHAR() != null) {
                return new PrimitiveType("char");
            } else if (ctx.Identifier() != null) {
                String tag = ctx.Identifier().getText();
                
                // 情况1: 完整结构体声明
                if (ctx.LBRACE() != null) {
                    StructType existingType = symbolTable.lookupStructTagInCurrentScope(tag);
                    
                    // 检查重复完整声明
                    if (existingType != null && existingType.isComplete) {
                        reportError(Project3SemanticError.redeclaration(ctx.Identifier()));
                        return null;
                    }
                    
                    StructType structType = existingType != null ? existingType : new StructType(tag);
                    if (existingType == null) {
                        symbolTable.defineStructTag(tag, structType);
                    }
                    
                    Set<String> memberNames = new HashSet<>();
                    
                    // 处理成员
                    for (int i = 0; i < ctx.specifier().size(); i++) {
                        Type memberBaseType = visitSpecifier(ctx.specifier(i));
                        if (hasError) return null;
                        
                        VarDecResult memberResult = parseVarDecWithInfo(ctx.varDec(i), memberBaseType);
                        if (hasError) return null;
                        
                        // 检查成员名重复
                        if (memberNames.contains(memberResult.name)) {
                            reportError(Project3SemanticError.memberDuplicate(memberResult.node));
                            return null;
                        }
                        
                        // 检查成员类型完整性
                        if (!isCompleteType(memberResult.type)) {
                            reportError(Project3SemanticError.memberIncomplete(memberResult.node));
                            return null;
                        }
                        
                        memberNames.add(memberResult.name);
                        structType.addMember(memberResult.name, memberResult.type);
                    }
                    
                    structType.setComplete();
                    return structType;
                }
                // 情况2: 不完整结构体声明或引用
                else {
                    StructType structType = symbolTable.lookupStructTag(tag);
                    if (structType == null) {
                        structType = new StructType(tag);
                        symbolTable.defineStructTag(tag, structType);
                    }
                    return structType;
                }
            }
            return null;
        }

        VarDecResult parseVarDecWithInfo(SplcParser.VarDecContext ctx, Type baseType) {
            // 找到标识符
            IdentifierInfo idInfo = findIdentifier(ctx);
            if (idInfo == null) {
                return new VarDecResult(baseType, null, null);
            }
            
            // 从外向内收集路径
            List<SplcParser.VarDecContext> path = collectPath(ctx, idInfo.ctx);
            
            // 从标识符开始，从内向外应用修饰符
            Type currentType = baseType;
            
            for (int i = path.size() - 1; i >= 0; i--) {
                SplcParser.VarDecContext node = path.get(i);
                
                if (node.LBRACK() != null) {
                    // 数组修饰符
                    int size = Integer.parseInt(node.Number().getText());
                    currentType = new ArrayType(currentType, size);
                } else if (node.STAR() != null) {
                    // 指针修饰符
                    currentType = new PointerType(currentType);
                }
                // LPAREN 不产生类型修饰
            }
            
            return new VarDecResult(currentType, idInfo.name, idInfo.node);
        }

        // 辅助类
        static class IdentifierInfo {
            String name;
            TerminalNode node;
            SplcParser.VarDecContext ctx;
            
            IdentifierInfo(String name, TerminalNode node, SplcParser.VarDecContext ctx) {
                this.name = name;
                this.node = node;
                this.ctx = ctx;
            }
        }

        // 找到标识符
        IdentifierInfo findIdentifier(SplcParser.VarDecContext ctx) {
            if (ctx.Identifier() != null) {
                return new IdentifierInfo(ctx.Identifier().getText(), ctx.Identifier(), ctx);
            } else if (ctx.varDec() != null) {
                return findIdentifier(ctx.varDec());
            }
            return null;
        }

        // 收集从外到标识符的路径（不包括标识符本身）
        List<SplcParser.VarDecContext> collectPath(SplcParser.VarDecContext start, 
                                                    SplcParser.VarDecContext target) {
            List<SplcParser.VarDecContext> path = new ArrayList<>();
            collectPathHelper(start, target, path);
            return path;
        }

        boolean collectPathHelper(SplcParser.VarDecContext current, 
                                SplcParser.VarDecContext target,
                                List<SplcParser.VarDecContext> path) {
            if (current == target) {
                return true;
            }
            
            if (current.varDec() != null) {
                if (collectPathHelper(current.varDec(), target, path)) {
                    path.add(current);
                    return true;
                }
            }
            
            return false;
        }
	// complex situation with multiple pointers and arrays
        VarDecResult parseVarDecRecursive(SplcParser.VarDecContext ctx, Type currentType) {
            if (ctx.Identifier() != null) {
                // 基础情况：找到标识符
                return new VarDecResult(currentType, ctx.Identifier().getText(), ctx.Identifier());
            } else if (ctx.STAR() != null) {
                // 指针：*varDec
                // 先递归处理内层，然后在返回的类型外包装指针
                VarDecResult inner = parseVarDecRecursive(ctx.varDec(), currentType);
                if (hasError) return inner;
                return new VarDecResult(new PointerType(inner.type), inner.name, inner.node);
            } else if (ctx.LPAREN() != null && ctx.RBRACK() == null) {
                // 括号：(varDec)
                return parseVarDecRecursive(ctx.varDec(), currentType);
            } else if (ctx.LBRACK() != null) {
                // 数组：varDec[Number]
                VarDecResult inner = parseVarDecRecursive(ctx.varDec(), currentType);
                if (hasError) return inner;
                int size = Integer.parseInt(ctx.Number().getText());
                Type newType = new ArrayType(inner.type, size);
                return new VarDecResult(newType, inner.name, inner.node);
            }
            return new VarDecResult(currentType, null, null);
        }

        Type parseVarDec(SplcParser.VarDecContext ctx, Type baseType) {
            return parseVarDecWithInfo(ctx, baseType).type;
        }

        String extractIdentifier(SplcParser.VarDecContext ctx) {
            return parseVarDecWithInfo(ctx, new PrimitiveType("dummy")).name;
        }

        TerminalNode extractIdentifierNode(SplcParser.VarDecContext ctx) {
            return parseVarDecWithInfo(ctx, new PrimitiveType("dummy")).node;
        }

        // 检查类型是否完整
        boolean isCompleteType(Type type) {
            if (type instanceof StructType) {
                return ((StructType) type).isComplete;
            } else if (type instanceof ArrayType) {
                return isCompleteType(((ArrayType) type).elementType);
            } else if (type instanceof PointerType) {
                // 指针类型总是完整的
                return true;
            }
            return true;
        }

        @Override
        public Type visitVarDecStmt(SplcParser.VarDecStmtContext ctx) {
            Type baseType = visitSpecifier(ctx.specifier());
            if (hasError) return null;
            
            VarDecResult result = parseVarDecWithInfo(ctx.varDec(), baseType);
            if (hasError) return null;
            
            // 检查类型完整性
            if (!isCompleteType(result.type)) {
                reportError(Project3SemanticError.definitionIncomplete(result.node));
                return null;
            }
            
            // 检查重定义
            Symbol existingSym = symbolTable.lookupInCurrentScope(result.name);
            if (existingSym != null) {
                reportError(Project3SemanticError.redefinition(result.node));
                return null;
            }
            
            symbolTable.define(result.name, result.type, result.node);
            
            if (ctx.expression() != null) {
                visit(ctx.expression());
            }
            
            return null;
        }

        @Override
        public Type visitBlockStatement(SplcParser.BlockStatementContext ctx) {
            symbolTable.enterScope();
            for (SplcParser.StatementContext stmt : ctx.statement()) {
                visit(stmt);
                if (hasError) return null;
            }
            symbolTable.exitScope();
            return null;
        }

        @Override
        public Type visitIdentifierPrimary(SplcParser.IdentifierPrimaryContext ctx) {
            String name = ctx.Identifier().getText();
            Symbol sym = symbolTable.lookup(name);
            if (sym == null) {
                reportError(Project3SemanticError.undeclaredUse(ctx.Identifier()));
                return null;
            }
            return sym.type;
        }

        void printOutput() { //fullprint for struct, prettyprint for incomplete struct and other types.
            grader.print("Variables:\n");
            for (Symbol var : globalVariables) {
                Type type = var.type;
                // 如果是结构体类型且完整，使用 fullPrint
                if (type instanceof StructType && ((StructType) type).isComplete) {
                    grader.print(var.name + ": " + type.fullPrint() + "\n");
                } else {
                    grader.print(var.name + ": " + type.prettyPrint() + "\n");
                }
            }
            grader.print("\n");
            grader.print("Functions:\n");
            for (FunctionSymbol func : globalFunctionsMap.values()) {
                grader.print(func.name + ": " + func.type.prettyPrint() + "\n");
            }
        }
    }
}