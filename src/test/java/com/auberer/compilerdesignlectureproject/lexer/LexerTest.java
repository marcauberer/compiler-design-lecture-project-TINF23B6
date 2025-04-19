package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.reader.Reader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LexerTest {

//  @Test
//  @DisplayName("Full lexer test")
//  public void testAll() {
//    String input = "\"string\"";
//    Reader reader = new Reader(input);
//    assert !reader.isEOF();
//    Lexer lexer = new Lexer(reader, false);
//    assert !lexer.isEOF();
//    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_STRING_LIT));
//      assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_INTEGER_LIT));
//      assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_DOUBLE_LIT));
//      assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_IDENTIFIER));
//      assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_IF));
//    assert lexer.isEOF();
//  }

}
