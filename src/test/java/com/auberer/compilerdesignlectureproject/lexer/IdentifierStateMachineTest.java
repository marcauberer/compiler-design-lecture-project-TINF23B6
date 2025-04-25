package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdentifierStateMachineTest {

  @Test
  @DisplayName("Happy path: Valid identifier")
  public void testHappyPath() {
    String input = "myVariable";
    IdentifierStateMachine stateMachine = new IdentifierStateMachine();
    stateMachine.init();
    stateMachine.reset();
    for (char c : input.toCharArray()) {
      assertDoesNotThrow(() -> stateMachine.processInput(c));
    }
    assertTrue(stateMachine.isInAcceptState());
  }

  @Test
  @DisplayName("Test erroneous input: Identifier with invalid character")
  public void testInvalidCharacter() {
    String input = "1myVariable$";
    IdentifierStateMachine stateMachine = new IdentifierStateMachine();
    stateMachine.init();
    stateMachine.reset();

    assertThrows(IllegalStateException.class, () -> {
      for (char c : input.toCharArray()) {
        stateMachine.processInput(c);
      }
    });
  }
}