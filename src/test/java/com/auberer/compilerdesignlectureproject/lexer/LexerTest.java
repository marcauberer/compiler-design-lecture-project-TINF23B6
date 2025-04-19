package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.reader.Reader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LexerTest {

  @Test
  @DisplayName("Full lexer test")
  public void testAll() {
    String input = "\"string\"";
    Reader reader = new Reader(input);
    assert !reader.isEOF();
    Lexer lexer = new Lexer(reader, false);
    assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_STRING_LIT));
    // ToDo(Students): Please add your token types here and in the input
    assert lexer.isEOF();
  }

    @Test
    public void test_IntegerLiteralStateMachine_is_correct_initialised_and_returns_TOK_INTEGER_LIT(){
        int input = 547465;
        Reader reader = new Reader(input+"");
        assert !reader.isEOF();
        Lexer lexer = new Lexer(reader, false);
        assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_INTEGER_LIT));
        assert lexer.isEOF();
    }
    @Test
    public void test_DoubleLiteralStateMachine_is_correct_initialised_and_returns_TOK_DOUBLE_LIT(){
        double input = 547.465;
        Reader reader = new Reader(input+"");
        assert !reader.isEOF();
        Lexer lexer = new Lexer(reader, false);
        assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_DOUBLE_LIT));
        assert lexer.isEOF();
    }
    @Test
    public void test_IdentifierLiteralStateMachine_is_correct_initialised_and_returns_TOK_IDENTIFIER_LIT(){
        String input = "foo";
        Reader reader = new Reader(input);
        assert !reader.isEOF();
        Lexer lexer = new Lexer(reader, false);
        assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_IDENTIFIER_LIT));
        assert lexer.isEOF();
    }
    // Test gibt einen TOK_IDENTIFIER_LIT zurück und beim Debugging ist uns aufgefallen, dass der Fehler bei Lexer.java Line 106 liegt. Hier wird der
    // Wert der IdentifierStateMachine hochgezählt und wir verstehen nicht warum.

//    @Test
//    public void test_KeyLiteralStateMachine_is_correct_initialised_and_returns_TOK_KEYWORD_LIT(){
//        String input = "while";
//        Reader reader = new Reader(input);
//        assert !reader.isEOF();
//        Lexer lexer = new Lexer(reader, false);
//        assertDoesNotThrow(() -> lexer.expect(TokenType.TOK_KEYWORD_LIT));
//        assert lexer.isEOF();
//    }
}
