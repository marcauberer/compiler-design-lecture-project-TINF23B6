package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdentifierStateMachineTest {
    @Test
    @DisplayName("Happy path")
    public void testHappyPath() {
        String input = "test";
        IdentifierStateMachine stateMachine = new IdentifierStateMachine();
        stateMachine.init();
        stateMachine.reset();

        for (char c : input.toCharArray()) {
            assertDoesNotThrow(() -> stateMachine.processInput(c));
            assertTrue(stateMachine.isInAcceptState());
        }
        assertTrue(stateMachine.isInAcceptState());
    }

    @Test
    @DisplayName("Test erroneous input")
    public void testErroneousInput() {
        String input = "187"; // Illegal identifier name
        IdentifierStateMachine stateMachine = new IdentifierStateMachine();
        stateMachine.init();
        stateMachine.reset();
        for (char c : input.toCharArray()) {
            assertDoesNotThrow(() -> stateMachine.processInput(c));
            assertFalse(stateMachine.isInAcceptState());
        }
        assertFalse(stateMachine.isInAcceptState());
        assertEquals("error", stateMachine.getCurrentState().getName());
    }
}
