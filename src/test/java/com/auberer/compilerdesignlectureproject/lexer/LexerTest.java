package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.reader.Reader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {

  @Test
  @DisplayName("Full lexer test")
  public void testAll() {
    String input = "\"string\" 22 223.22 osem_34UI for";
    Reader reader = new Reader(input);
    assert !reader.isEOF();
    Lexer lexer = new Lexer(reader, false);
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_STRING_LIT));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_INTEGER_LIT));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_DOUBLE_LIT));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_IDENTIFIER));
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_FOR));
    // ToDo(Students): Please add your token types here and in the input
    assert lexer.isEOF();
  }

  @Test
  public void test_IntegerLiteralStateMachine_successful(){
    int input = 132523;
    Reader reader = new Reader(input+"");
    assert !reader.isEOF();
    Lexer lexer = new Lexer(reader, false);
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_INTEGER_LIT));
    assert lexer.isEOF();
  }

  @Test
  public void test_IntegerLiteralStateMachine_unsuccessful(){
    String input = "as1e23";
    Reader reader = new Reader(input);
    assert !reader.isEOF();
    Lexer lexer = new Lexer(reader, false);
    // Make sure that the lexer does NOT return an integer literal token
    Token token = lexer.getToken();
    assertNotEquals(TokenType.TOK_INTEGER_LIT, token.getType());
  }

  @Test
  public void test_DoubleLiteralStateMachine_successful(){
    double input = 132.523;
    Reader reader = new Reader(input+"");
    assert !reader.isEOF();
    Lexer lexer = new Lexer(reader, false);
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_DOUBLE_LIT));
    assert lexer.isEOF();
  }

  @Test
  public void test_DoubleLiteralStateMachine_unsuccessful(){
    int input = 7989;
    Reader reader = new Reader(input+"");
    assert !reader.isEOF();
    Lexer lexer = new Lexer(reader, false);
    // Make sure that the lexer does NOT return an integer literal token
    Token token = lexer.getToken();
    assertNotEquals(TokenType.TOK_DOUBLE_LIT, token.getType());
  }

  @Test
  public void test_IdentifierStateMachine_successful(){
    String input = "wjoSW98_3e";
    Reader reader = new Reader(input);
    assert !reader.isEOF();
    Lexer lexer = new Lexer(reader, false);
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_IDENTIFIER));
    assert lexer.isEOF();
  }

  @Test
  public void test_IdentifierStateMachine_unsuccessful(){
    String input = "09as1e23";
    Reader reader = new Reader(input);
    assert !reader.isEOF();
    Lexer lexer = new Lexer(reader, false);
    // Make sure that the lexer does NOT return an integer literal token
    Token token = lexer.getToken();
    assertNotEquals(TokenType.TOK_IDENTIFIER, token.getType());
  }

  @Test
  public void test_KeywordMachine_successful(){
    String input = "for";
    Reader reader = new Reader(input);
    assert !reader.isEOF();
    Lexer lexer = new Lexer(reader, false);
    assertDoesNotThrow(() -> lexer.expect(lexer.getToken().getType()));
    assert lexer.isEOF();
  }

  @Test
  public void test_KeywordMachine_unsuccessful(){
    String input = "wq";
    Reader reader = new Reader(input);
    assert !reader.isEOF();
    Lexer lexer = new Lexer(reader, false);
    // Make sure that the lexer does NOT return an integer literal token
    Token token = lexer.getToken();
    assertNotEquals(TokenType.TOK_FOR, token.getType());
  }

}
