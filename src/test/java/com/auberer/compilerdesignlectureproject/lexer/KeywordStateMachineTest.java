package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KeywordStateMachineTest {

  @Test
  @DisplayName("Happy path: Valid keyword 'if'")
  public void testKeywordIf() {
    String input = "if";
    KeywordStateMachine stateMachine = new KeywordStateMachine("if", TokenType.TOK_IF);
    stateMachine.init();
    stateMachine.reset();
    for (char c : input.toCharArray()) {
      assertFalse(stateMachine.isInAcceptState());
      assertDoesNotThrow(() -> stateMachine.processInput(c));
    }
    assertTrue(stateMachine.isInAcceptState());
  }

  @Test
  @DisplayName("Test non-keyword input as identifier")
  public void testNonKeywordInput() {
    String input = "myVariable";
    KeywordStateMachine stateMachine = new KeywordStateMachine("if", TokenType.TOK_IF);
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
