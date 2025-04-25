package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.reader.Reader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {

  @Test
  @DisplayName("Test all tokens in order")
  public void testAll() {
    String input = "int double string " +
                    "if else switch case default " +
                    "while do for " +
                    "func return " +
                    "call print " +
                    "3.14 123 \"sampleString\" identifier" +
                    "{ int i = 1 }";

    Reader reader = new Reader(input);
    Lexer lexer = new Lexer(reader, false);

    assert !lexer.isEOF();

    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_TYPE_INT));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_TYPE_DOUBLE));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_TYPE_STRING));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_IF));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_ELSE));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_SWITCH));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_CASE));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_DEFAULT));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_WHILE));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_DO));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_FOR));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_FUNC));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_RET));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_CALL));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_PRINT));

    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_DOUBLE_LIT));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_INT_LIT));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_STRING_LIT));

    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_IDENTIFIER));

    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_LBRACE));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_TYPE_INT));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_IDENTIFIER));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_ASSIGN));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_INT_LIT));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_RBRACE));

    assertTrue(lexer.isEOF());
  }
}
