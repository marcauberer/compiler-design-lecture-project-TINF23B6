package com.auberer.compilerdesignlectureproject.lexer;

public enum TokenType {
  TOK_INVALID,
  TOK_STRING_LIT,
  TOK_DOUBLE_LIT,
  TOK_INT_LIT,
  TOK_IDENTIFIER,

  TOK_TYPE_INT,
  TOK_TYPE_DOUBLE,
  TOK_TYPE_STRING,

  TOK_IF,
  TOK_ELSE,
  TOK_SWITCH,
  TOK_CASE,
  TOK_DEFAULT,
  TOK_WHILE,
  TOK_DO,
  TOK_FOR,

  TOK_FUNC,
  TOK_RETURN,

  TOK_CALL,
  TOK_PRINT,

  TOK_EOF,

}
