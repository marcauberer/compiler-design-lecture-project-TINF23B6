package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerLiteralStateMachineTest {

  @Test
  @DisplayName("Happy path: Valid integer literal")
  public void testHappyPath() {
    String input = "12345";
    IntegerLiteralStateMachine stateMachine = new IntegerLiteralStateMachine();
    stateMachine.init();
    stateMachine.reset();
    for (char c : input.toCharArray()) {
      assertDoesNotThrow(() -> stateMachine.processInput(c));
    }
    assertTrue(stateMachine.isInAcceptState());
  }

  @Test
  @DisplayName("Test erroneous input: Invalid integer literal (leading zero)")
  public void testErroneousInput() {
    String input = "01234";
    IntegerLiteralStateMachine stateMachine = new IntegerLiteralStateMachine();
    stateMachine.init();
    stateMachine.reset();

    assertThrows(IllegalStateException.class, () -> {
      for (char c : input.toCharArray()) {
        stateMachine.processInput(c);
      }
    });
  }

  @Test
  @DisplayName("Test erroneous input: Non-digit character in integer literal")
  public void testNonDigitCharacter() {
    String input = "123a4";
    IntegerLiteralStateMachine stateMachine = new IntegerLiteralStateMachine();
    stateMachine.init();
    stateMachine.reset();

    assertThrows(IllegalStateException.class, () -> {
      for (char c : input.toCharArray()) {
        stateMachine.processInput(c);
      }
    });
  }
}
