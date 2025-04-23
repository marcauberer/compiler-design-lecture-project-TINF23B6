package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLiteralStateMachineTest {

  @Test
  @DisplayName("Happy path: Valid double literal")
  public void testHappyPath() {
    String input = "123.45";
    DoubleLiteralStateMachine stateMachine = new DoubleLiteralStateMachine();
    stateMachine.init();
    stateMachine.reset();
    for (char c : input.toCharArray()) {
      assertDoesNotThrow(() -> stateMachine.processInput(c));
    }
    assertTrue(stateMachine.isInAcceptState());
  }

  @Test
  @DisplayName("Test erroneous input: Invalid double literal (leading zero)")
  public void testErroneousInput() {
    String input = "0123.45";
    DoubleLiteralStateMachine stateMachine = new DoubleLiteralStateMachine();
    stateMachine.init();
    stateMachine.reset();

    assertThrows(IllegalStateException.class, () -> {
      for (char c : input.toCharArray()) {
        stateMachine.processInput(c);
      }
    });
  }

  @Test
  @DisplayName("Test erroneous input: Non-digit character in double literal")
  public void testNonDigitCharacter() {
    String input = "123.45a";
    DoubleLiteralStateMachine stateMachine = new DoubleLiteralStateMachine();
    stateMachine.init();
    stateMachine.reset();

    assertThrows(IllegalStateException.class, () -> {
      for (char c : input.toCharArray()) {
        stateMachine.processInput(c);
      }
    });
  }
}
