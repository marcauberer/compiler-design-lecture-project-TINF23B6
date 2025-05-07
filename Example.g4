grammar Example;

// Parser rules
addition: const ('+' const)* EOF;
const: INT_LIT;

// Lexer rules
INT_LIT: [0-9]+;

// Skipped tokens
WS: [ \t\r\n]+ -> skip;