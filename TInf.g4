// Grammar for the TInf programming language
grammar TInf;

// Misc
entry: fctDef+;
stmtLst: stmt*;
stmt: varDeclStmt | assignStmt | returnStmt | ifStmt | whileLoop | doWhileLoop | forLoop | switchCaseStmt | anonymousBlockStmt;
varDeclStmt: type IDENTIFIER ASSIGN ternaryExpr SEMICOLON;
assignStmt: assignExpr SEMICOLON;
assignExpr: (IDENTIFIER ASSIGN)? ternaryExpr;
printBuiltinCall: PRINT LPAREN ternaryExpr RPAREN;
literal: INT_LIT | DOUBLE_LIT | STRING_LIT;
type: TYPE_INT | TYPE_DOUBLE | TYPE_STRING | TYPE_BOOL;

// Team 1: If/Else statement
ifStmt: IF LPAREN ternaryExpr RPAREN ifBody elseStmt?;
ifBody: LBRACE stmtLst RBRACE;
elseStmt: ELSE (ifBody | ifStmt);

// Team 2: While loop
whileLoop: WHILE LPAREN ternaryExpr RPAREN LBRACE stmtLst RBRACE;

// Team 3: Do-while loop
doWhileLoop: DO LBRACE stmtLst RBRACE WHILE LPAREN ternaryExpr RPAREN SEMICOLON;

// Team 4: Function Definition / Function Call
fctDef: type IDENTIFIER COLON ASSIGN LPAREN paramLst? RPAREN LBRACE stmtLst RBRACE;
paramLst: param (SEMICOLON param)*;
param: type IDENTIFIER (ASSIGN atomicExpr)?;
fctCall: IDENTIFIER LPAREN argLst RPAREN;
argLst: atomicExpr (SEMICOLON atomicExpr)*;
returnStmt: RET ternaryExpr SEMICOLON;

// Team 5: For loop
forLoop: FOR LPAREN varDeclStmt ternaryExpr SEMICOLON assignExpr RPAREN LBRACE stmtLst RBRACE;

// Team 6: Switch / Case / Default
switchCaseStmt: SWITCH LPAREN ternaryExpr RPAREN LBRACE caseBlockLst defaultBlock? RBRACE;
caseBlockLst: caseBlock+;
caseBlock: CASE literal COLON stmtLst;
defaultBlock: DEFAULT COLON stmtLst;

// Team 7: Anonymous Block Statement
anonymousBlockStmt: LBRACE stmtLst RBRACE;

// Expression loop
ternaryExpr: equalityExpr (QUESTION_MARK equalityExpr COLON equalityExpr)?;
equalityExpr: additiveExpr ((EQUALS | NOT_EQUALS) additiveExpr)?;
additiveExpr: multiplicativeExpr ((PLUS | MINUS) multiplicativeExpr)*;
multiplicativeExpr: atomicExpr ((MUL | DIV) atomicExpr)*;
atomicExpr: literal | fctCall | printBuiltinCall | IDENTIFIER | LPAREN ternaryExpr RPAREN;

QUESTION_MARK: '?';
COLON: ':';
ASSIGN: '=';
EQUALS: '==';
NOT_EQUALS: '!=';
PLUS: '+';
MINUS: '-';
MUL: '*';
DIV: '/';
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
SEMICOLON: ';';
IF: 'if';
ELSE: 'else';
WHILE: 'while';
DO: 'do';
FOR: 'for';
SWITCH: 'switch';
CASE: 'case';
DEFAULT: 'default';
RET: 'ret';
PRINT: 'print';
TYPE_INT: 'int';
TYPE_DOUBLE: 'double';
TYPE_STRING: 'string';
TYPE_BOOL: 'bool';

INT_LIT: [1-9][0-9]* | '0';
DOUBLE_LIT: ([1-9][0-9]* | '0')[.][0-9]+;
STRING_LIT: '"' (~["\\\r\n])* '"';
IDENTIFIER: [a-z_][a-zA-Z0-9_]*;

// Skipped tokens
LINE_COMMENT: '#' ~[\r\n]* -> skip;
WS: [ \t\r\n]+ -> skip;
