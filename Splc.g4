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
    : // TODO
    ;

// =========================
// Lexer Rules
// =========================

channels { WHITESPACE, COMMENTS }

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
Identifier : [a-zA-Z_][a-zA-Z0-9_]* ;
Number     : '0' | [1-9][0-9]* | '-'[1-9][0-9]* ;
Char       : '\'' (EscapeChar | ~['\\\r\n]) '\'' ;
fragment EscapeChar: '\\' [nrt\\'"0];
// ---------- Whitespace & Comments ----------
WS      : [ \t\r\n]+ -> channel(WHITESPACE);
LINE_COMMENT: '//' .*? ('\r'? '\n' | EOF) -> channel(COMMENTS);
BLOCK_COMMENT: '/*' .*? '*/' -> channel(COMMENTS);