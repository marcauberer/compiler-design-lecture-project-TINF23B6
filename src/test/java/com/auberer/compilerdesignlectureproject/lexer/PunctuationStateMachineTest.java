package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PunctuationStateMachineTest {

  @Test
  @DisplayName("Happy path: Valid punctuation")
  public void testPunctuation() {
    String input = "{";
    PunctuationStateMachine stateMachine = new PunctuationStateMachine("{", TokenType.TOK_LBRACE);
    stateMachine.init();
    stateMachine.reset();
    for (char c : input.toCharArray()) {
      assertFalse(stateMachine.isInAcceptState());
      assertDoesNotThrow(() -> stateMachine.processInput(c));
    }
    assertTrue(stateMachine.isInAcceptState());
  }

  @Test
  @DisplayName("Test non-punctuation input")
  public void testNonPunctuationInput() {
    String input = "123.123";
    PunctuationStateMachine stateMachine = new PunctuationStateMachine("{if}", TokenType.TOK_LBRACE);
    stateMachine.init();
    stateMachine.reset();

    assertThrows(IllegalStateException.class, () -> {
      for (char c : input.toCharArray()) {
        assertFalse(stateMachine.isInAcceptState());
        stateMachine.processInput(c);
      }
    });

    assertFalse(stateMachine.isInAcceptState());
  }
}
