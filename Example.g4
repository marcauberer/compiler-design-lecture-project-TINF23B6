grammar Example;
entry: test;
// Parser rules
addition: const ('+' const)* EOF;
const: INT_LIT;

test: L_BRACKET addition R_BRACKET;
// Lexer rules
INT_LIT: [0-9]+;
L_BRACKET: '[';
R_BRACKET: ']';
// Skipped tokens
WS: [ \t\r\n]+ -> skip;