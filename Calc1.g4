grammar Calc1;

expr    : expr ADD term
        | expr SUB term
        | term
        ;

term    : term MUL factor
        | term DIV factor
        | factor
        ;

factor  : LPAREN expr RPAREN
        | INT
        ;

INT : [0-9]+ ;
ADD : '+' ;
SUB : '-' ;
MUL : '*' ;
DIV : '/' ;
LPAREN : '(' ;
RPAREN : ')' ;

WS  : [ \t\r\n]+ -> skip ;