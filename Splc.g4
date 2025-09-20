lexer grammar Splc;

// IDEA Plugin Settings
// - Output Directory: src/main/java/
// - package name: generated.Splc

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
DOT : '.' ;
ARROW : '->' ;
AMP : '&' ;
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
fragment EscapeChar: '\\' ['"\\nrt];  // 支持常见的转义字符
// ---------- Whitespace & Comments ----------
WS      : [ \t\r\n]+ -> skip;
LINE_COMMENT: '//' .*? ('\r'? '\n' | EOF) -> skip;
BLOCK_COMMENT: '/*' .*? '*/' -> skip;