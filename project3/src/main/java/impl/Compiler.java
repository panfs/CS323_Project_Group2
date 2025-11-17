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
    /** 主要修改：引入record关键字替换final class<br>
     * Record（记录类）是一种特殊的类，它充当透明数据载体。当你定义一个 record时，你主要是在定义它的状态（即有哪些字段），Java 编译器会自动为你生成以下内容：<br>
     * 私有 final 字段：对应你声明的组件。<br>
     * 公共的读取方法（getter）：方法名就是字段名本身（例如 name()，而不是 getName()）。<br>
     * 一个全参构造函数：用于初始化所有字段。<br>
     * 自动生成的 equals()、hashCode()和 toString()方法：这些方法会基于所有声明的组件来生成逻辑。<br>
     * 它的核心思想是 “代码即数据”。你声明数据是什么，编译器负责生成所有繁琐的样板代码。<br>
     *
     *
    * */
    // ==================== 类型系统 ====================

    // 基础类型
    record PrimitiveType(String name) implements Type {
        @Override
        public String prettyPrint() {
            return name; // 可以直接使用 `name()` 方法，这里直接使用 `name` 也可以
        }
        @Override
        public String fullPrint() {
            return prettyPrint();
        }
    }
    // 数组类型
    record ArrayType(Type elementType, int size) implements Type {
        @Override
        public String prettyPrint() {
            return elementType.prettyPrint() + "[" + size + "]";
        }
        @Override
        public String fullPrint() {
            return prettyPrint();
        }
    }

    // 指针类型
    record PointerType(Type pointeeType) implements Type{
        @Override
        public String prettyPrint() {
            return pointeeType.prettyPrint() + "*";
        }
        @Override
        public String fullPrint() {
            return prettyPrint();
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
            if (o == null || getClass() != o.getClass()) return false;
            StructType that = (StructType) o;

            if (isComplete != that.isComplete) return false;
            if (!Objects.equals(tag, that.tag)) return false;

            if (!isComplete) return true; // 不完整类型只需要标签相同

            // 完整类型需要比较所有成员
            return memberOrder.equals(that.memberOrder) &&
                    members.equals(that.members);
        }

        @Override
        public int hashCode() {
            if (!isComplete) {
                // 不完整结构体：只基于标签
                return Objects.hash(tag, isComplete);
            } else {
                // 完整结构体：基于标签、完整性和所有成员信息
                return Objects.hash(tag, isComplete, memberOrder, members);
            }
        }
    }

    // 函数类型
    record FunctionType(Type returnType, List<Type> paramTypes) implements Type {
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
        public String fullPrint() {
            return prettyPrint(); // 对于简单类型可以相同
        }
    }

    // ==================== 符号表 ====================

    record Symbol(String name, Type type, TerminalNode node){}

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
                scopes.removeLast();
            }
        }

        Scope currentScope() {
            return scopes.getLast();
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

    record VarDecResult(Type type, String name, TerminalNode node){}

    record FunctionSymbol(String name, FunctionType type, TerminalNode node, boolean isDefined){}

    static class SemanticChecker extends SplcBaseVisitor<Type> {
        final AbstractGrader grader;
        final SymbolTable symbolTable;
        final List<Symbol> globalVariables = new ArrayList<>();
        final Map<String, FunctionSymbol> globalFunctionsMap = new LinkedHashMap<>();

        // 全局结构体状态注册表
        final Map<String, StructDefinitionState> globalStructStates = new HashMap<>();
        final Set<String> currentlyDefiningStructs = new HashSet<>();
        int structNestingDepth = 0;

        boolean hasError = false;

        // 结构体定义状态枚举
        enum StructDefinitionState {
            NOT_DEFINED,     // 未定义
            IN_PROGRESS,     // 正在定义中（处理到一半）
            COMPLETED        // 已完整定义
        }

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

            // 程序结束时检查所有全局变量的类型完整性
            if (!hasError) {
                checkGlobalVariablesCompleteness();
            }
            return null;
        }

        // 检查全局变量类型完整性
        private void checkGlobalVariablesCompleteness() {
            for (Symbol globalVar : globalVariables) {
                if (!isCompleteType(globalVar.type, false)) { // 使用严格检查
                    reportError(Project3SemanticError.definitionIncomplete(globalVar.node));
                    return;
                }
            }
        }

        @Override
        public Type visitGlobalDef(SplcParser.GlobalDefContext ctx) {
            Type baseType = visitSpecifier(ctx.specifier());
            if (hasError) return null;

            // 情况1: 函数定义 - specifier Identifier LPAREN funcArgs RPAREN LBRACE statement* RBRACE
            if (ctx.LBRACE() != null) {
                TerminalNode funcName = ctx.Identifier();
                List<Type> paramTypes = new ArrayList<>();
                Set<String> paramNames = new HashSet<>(); // 检查参数名重复

                if (ctx.funcArgs().specifier() != null) {
                    for (int i = 0; i < ctx.funcArgs().specifier().size(); i++) {
                        Type paramBaseType = visitSpecifier(ctx.funcArgs().specifier(i));
                        if (hasError) return null;
                        VarDecResult paramResult = parseVarDecWithInfo(ctx.funcArgs().varDec(i), paramBaseType);
                        if (hasError) return null;

                        if (paramNames.contains(paramResult.name)) {
                            reportError(Project3SemanticError.redefinition(paramResult.node));
                            return null;
                        }
                        paramNames.add(paramResult.name);

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

                // 新增：检查结构体重定义
                if (baseType instanceof StructType structType) {
                    String tag = structType.tag;
                    StructDefinitionState state = globalStructStates.getOrDefault(
                            tag, StructDefinitionState.NOT_DEFINED);

                    if (state == StructDefinitionState.COMPLETED) {
                        reportError(Project3SemanticError.redeclaration(ctx.specifier().Identifier()));
                        return null;
                    }
                }

                // 修改：区分全局和局部变量的类型检查
                boolean isGlobal = symbolTable.isGlobalScope();
                // 局部变量：立即检查完整性
                if (!isGlobal && !isCompleteType(result.type, false)) {
                    reportError(Project3SemanticError.definitionIncomplete(result.node));
                    return null;
                }
                // 全局变量：允许不完整类型（试探性定义）
                // 不立即检查，记录到全局变量列表
                if (isGlobal) {
                    // 记录试探性定义，在程序结束时统一检查
                    globalVariables.add(new Symbol(result.name, result.type, result.node));
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
            }
            // 情况3: 结构体声明 - specifier SEMI
            else if (ctx.SEMI() != null && ctx.varDec() == null && ctx.Identifier() == null) {
                // 仅声明结构体，不需要额外处理
            }
            // 情况4: 函数声明 - specifier Identifier LPAREN funcArgs RPAREN SEMI
            else if (ctx.Identifier() != null && ctx.LPAREN() != null && ctx.SEMI() != null) {
                TerminalNode funcName = ctx.Identifier();
                List<Type> paramTypes = new ArrayList<>();
                Set<String> paramNames = new HashSet<>();

                // 步骤1: 处理函数参数
                if (ctx.funcArgs().specifier() != null) {
                    for (int i = 0; i < ctx.funcArgs().specifier().size(); i++) {
                        Type paramBaseType = visitSpecifier(ctx.funcArgs().specifier(i));
                        if (hasError) return null;
                        VarDecResult paramResult = parseVarDecWithInfo(ctx.funcArgs().varDec(i), paramBaseType);
                        if (hasError) return null;

                        // 检查参数名重复
                        if (paramNames.contains(paramResult.name)) {
                            reportError(Project3SemanticError.redefinition(paramResult.node));
                            return null;
                        }
                        paramNames.add(paramResult.name);
                        paramTypes.add(paramResult.type);
                    }
                }

                // 步骤2: 创建函数类型
                FunctionType funcType = new FunctionType(baseType, paramTypes);

                // 步骤3: 检查符号冲突
                Symbol existingSym = symbolTable.lookupInCurrentScope(funcName.getText());

                // 情况3.1: 已存在同名符号
                if (existingSym != null) {
                    if (existingSym.type instanceof FunctionType) {
                        // 已存在函数：检查是否是重复声明
                        FunctionSymbol existingFunc = globalFunctionsMap.get(funcName.getText());
                        if (existingFunc != null) {
                            // 重复声明是错误
                            reportError(Project3SemanticError.redeclaration(funcName));
                            return null;
                        }
                    } else {
                        // 已存在变量：报重定义错误
                        reportError(Project3SemanticError.redefinition(funcName));
                        return null;
                    }
                }

                // 检查是否已经存在函数（声明或定义）
                FunctionSymbol existingFunc = globalFunctionsMap.get(funcName.getText());
                if (existingFunc != null) {
                    // 已经存在（可能是声明或定义）
                    reportError(Project3SemanticError.redeclaration(funcName));
                    return null;
                }

                // 步骤4: 第一次声明，添加到符号表和全局映射
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
                    // 新增：检查是否已定义
                    StructType existingType0 = symbolTable.lookupStructTag(tag);
                    if (existingType0 != null && existingType0.isComplete) {
                        reportError(Project3SemanticError.redeclaration(ctx.Identifier()));
                        return null;
                    }

                    // 检查是否正在定义中（防止递归重定义）
                    StructDefinitionState currentState = globalStructStates.getOrDefault(tag, StructDefinitionState.NOT_DEFINED);
                    if (currentState == StructDefinitionState.IN_PROGRESS) {
                        reportError(Project3SemanticError.redeclaration(ctx.Identifier()));
                        return null;
                    }

                    // 标记为正在定义中
                    globalStructStates.put(tag, StructDefinitionState.IN_PROGRESS);
                    currentlyDefiningStructs.add(tag);
                    structNestingDepth++;

                    try {
                        StructType existingType = symbolTable.lookupStructTag(tag);
                        StructType structType = existingType != null ? existingType : new StructType(tag);

                        if (existingType == null) {
                            symbolTable.defineStructTag(tag, structType);
                        }

                        Set<String> memberNames = new HashSet<>();
                        symbolTable.enterScope();

                        // 处理成员
                        for (int i = 0; i < ctx.specifier().size(); i++) {
                            Type memberBaseType = visitSpecifier(ctx.specifier(i));
                            if (hasError) {
                                symbolTable.exitScope();
                                return null;
                            }

                            VarDecResult memberResult = parseVarDecWithInfo(ctx.varDec(i), memberBaseType);
                            if (hasError) {
                                symbolTable.exitScope();
                                return null;
                            }

                            // 第一步：立即检查成员类型完整性
                            if (!isCompleteType(memberResult.type,false)) {
                                reportError(Project3SemanticError.memberIncomplete(memberResult.node));
                                symbolTable.exitScope();
                                return null;
                            }

                            // 第二步：检查嵌套重定义（只有在类型完整时才需要检查）
                            if (memberBaseType instanceof StructType memberStruct) {
                                String memberTag = memberStruct.tag;
                                // 只有当标签相同且不是自引用时才检查重定义
                                if (memberTag.equals(tag) && !currentlyDefiningStructs.contains(memberTag)) {
                                    TerminalNode structTagNode = ctx.specifier(i).Identifier();
                                    StructDefinitionState memberState = globalStructStates.getOrDefault(
                                            memberTag, StructDefinitionState.NOT_DEFINED);

                                    if (memberState == StructDefinitionState.COMPLETED) {
                                        reportError(Project3SemanticError.redeclaration(structTagNode));
                                        symbolTable.exitScope();
                                        return null;
                                    }
                                }
                            }

                            // 检查成员名重复
                            if (memberNames.contains(memberResult.name)) {
                                reportError(Project3SemanticError.memberDuplicate(memberResult.node));
                                symbolTable.exitScope();
                                return null;
                            }

                            memberNames.add(memberResult.name);
                            structType.addMember(memberResult.name, memberResult.type);
                        }

                        symbolTable.exitScope();

                        // 标记为已完成定义
                        globalStructStates.put(tag, StructDefinitionState.COMPLETED);
                        structType.setComplete();
                        return structType;

                    } finally {
                        // 确保清理状态
                        structNestingDepth--;
                        currentlyDefiningStructs.remove(tag);

                        // 如果是最外层结构体，确保状态正确
                        if (structNestingDepth == 0) {
                            globalStructStates.put(tag, StructDefinitionState.COMPLETED);
                        }
                    }
                }
                // 情况2: 不完整结构体声明或引用
                else {
                    StructType structType = symbolTable.lookupStructTag(tag);
                    if (structType == null) {
                        structType = new StructType(tag);
                        symbolTable.defineStructTag(tag, structType);
                        // 不完整结构体不改变全局状态
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
        record IdentifierInfo(String name, TerminalNode node, SplcParser.VarDecContext ctx) {}

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
        boolean isCompleteType(Type type, boolean isGlobalVariable) {
            if (type instanceof StructType structType) {
                StructDefinitionState state = globalStructStates.getOrDefault(
                        structType.tag, StructDefinitionState.NOT_DEFINED);

                // 全局变量允许不完整类型
                if (isGlobalVariable) {
                    return true;
                }
                return state == StructDefinitionState.COMPLETED;
            } else if (type instanceof ArrayType) {
                return isCompleteType(((ArrayType) type).elementType, isGlobalVariable);
            } else if (type instanceof PointerType) {
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
            if (!isCompleteType(result.type, false)) {
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

        void printOutput() { //fullPrint for struct, prettyPrint for incomplete struct and other types.
            grader.print("Variables:\n");
            for (Symbol var : globalVariables) {
                Type type = var.type;
                grader.print(var.name + ": " + type.fullPrint() + "\n");
            }
            grader.print("\n");
            grader.print("Functions:\n");
            for (FunctionSymbol func : globalFunctionsMap.values()) {
                grader.print(func.name + ": " + func.type.fullPrint() + "\n");
            }
        }
    }
}
