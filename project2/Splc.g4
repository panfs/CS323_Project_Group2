// test update
//lexer grammar Splc;
grammar Splc;

// Changes in Project 2: Splc.g4 contains both parser rules and lexer rules.
//  so there should be "grammar Splc;' instead of 'lexer grammer Splc;'

// IDEA Plugin Settings
// - Output Directory: src/main/java/
// - package name: generated.Splc

// =========================
// Parser Rules
// =========================

program: globalDef* EOF;

globalDef
    :specifier Identifier LPAREN funcArgs RPAREN LBRACE statement* RBRACE
    |specifier varDec SEMI
    |specifier SEMI
    ;

specifier
    :INT
    |CHAR
    |'struct' Identifier
    |'struct' Identifier LBRACE (specifier varDec SEMI)* RBRACE
    ;

varDec
    : Identifier
    | varDec LBRACK Number RBRACK
    | STAR varDec
    | LPAREN varDec RPAREN
    ;

funcArgs
    :(specifier varDec (COMMA specifier varDec)*)?
    ;

statement
    : LBRACE statement* RBRACE                    # BlockStatement
    | specifier varDec (ASSIGN expression)? SEMI  # VarDecStmt
    | IF LPAREN expression RPAREN statement (ELSE statement)? # IfStatement
    | WHILE LPAREN expression RPAREN statement    # WhileStatement
    | RETURN expression SEMI                      # ReturnStatement
    | expression SEMI                             # ExpressionStatement
    ;


expression
// precedence 1
    : Identifier                                   # IdentifierPrimary
    | Number                                       # NumberPrimary
    | Char                                         # CharPrimary
    | LPAREN expression RPAREN                     # ParenPrimary
    | expression LBRACK expression RBRACK          # ArrayAccessExpression
// precedence 2
    | expression INC                               # PostfixIncExpression
    | expression DEC                               # PostfixDecExpression
    | Identifier LPAREN (expression (COMMA expression)*)? RPAREN       # FunctionCallExpression
    | expression DOT Identifier                    # StructAccessExpression
    | expression ARROW Identifier                  # StructPtrAccessExpression
// precedence 3
    | <assoc=right> INC expression                 # PrefixIncExpression
    | <assoc=right> DEC expression                 # PrefixDecExpression
    | <assoc=right> PLUS expression                # UnaryPlusExpression
    | <assoc=right> MINUS expression               # UnaryMinusExpression
    | <assoc=right> NOT expression                 # LogicalNotExpression
    | <assoc=right> STAR expression                # DereferenceExpression
    | <assoc=right> AMP expression                 # AddressOfExpression
// precedence 4
    | expression (STAR | DIV | MOD) expression     # MultiplicativeExpression
// precedence 5
    | expression (PLUS | MINUS) expression         # AdditiveExpression
// precedence 6
    | expression (LT | LE | GT | GE) expression    # RelationalExpression
// precedence 7
    | expression (EQ | NEQ) expression             # EqualityExpression
// precedence 8
    | expression AND expression                    # LogicalAndExpression
// precedence 9
    | expression OR expression                     # LogicalOrExpression
// precedence 10
    | <assoc=right> expression ASSIGN expression   # AssignExpression
    ;

// =========================
// Lexer Rules
// =========================
// ---------- Keywords ----------
INT   : 'int'    ;
CHAR  : 'char'   ;
STRUCT: 'struct' ;
RETURN: 'return' ;
IF    : 'if'     ;
ELSE  : 'else'   ;
WHILE : 'while'  ;

// ---------- Operators ----------
ASSIGN  : '=' ;
PLUS    : '+' ;
MINUS   : '-' ;
STAR : '*' ;
DIV : '/' ;
MOD : '%' ;
LT : '<' ;
LE : '<=' ;
GT : '>' ;
GE : '>=' ;
EQ : '==' ;
NEQ : '!=' ;
AND : '&&' ;
OR : '||' ;
NOT : '!' ;
INC : '++' ;
DEC : '--' ;
AMP : '&' ;
DOT : '.' ;
ARROW: '->';
// ---------- Separators ----------
SEMI : ';' ;
COMMA : ',' ;
LPAREN : '(' ;
RPAREN : ')' ;
LBRACE : '{' ;
RBRACE : '}' ;
LBRACK : '[' ;
RBRACK : ']' ;
// ---------- Identifiers & Literals ----------
Identifier : [a-zA-Z_][a-zA-Z_0-9]* ;
Number     : '0' | [1-9][0-9]*      ;
Char       : '\'' (EscapeChar | ~['\\\r\n]) '\'' ;
fragment EscapeChar: '\\' ['"\\nrt0];  // 支持常见的转义字符
// ---------- Whitespace & Comments ----------
WS      : [ \t\r\n]+ -> skip;
LINE_COMMENT: '//' .*? ('\r'? '\n' | EOF) -> skip;
BLOCK_COMMENT: '/*' .*? '*/' -> skip;
