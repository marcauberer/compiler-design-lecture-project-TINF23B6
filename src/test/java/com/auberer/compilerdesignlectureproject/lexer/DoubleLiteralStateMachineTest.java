package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLiteralStateMachineTest {
    @Test
    @DisplayName("Happy path")
    public void testHappyPath() {
        String input = "42.187";
        DoubleLiteralStateMachine stateMachine = new DoubleLiteralStateMachine();
        stateMachine.init();
        stateMachine.reset();
        char[] chars = input.toCharArray();

        for (int i = 0; i < 3; i++) {
            char c = chars[i];
            assertDoesNotThrow(() -> stateMachine.processInput(c));
            assertFalse(stateMachine.isInAcceptState());
        }
        assertDoesNotThrow(() -> stateMachine.processInput(chars[3]));
        assertTrue(stateMachine.isInAcceptState());
        assertDoesNotThrow(() -> stateMachine.processInput(chars[4]));
        assertTrue(stateMachine.isInAcceptState());
        assertDoesNotThrow(() -> stateMachine.processInput(chars[5]));
        assertTrue(stateMachine.isInAcceptState());
    }

    @Test
    @DisplayName("Test erroneous input")
    public void testErroneousInput() {
        String input = "187."; // Missing number after .
        DoubleLiteralStateMachine stateMachine = new DoubleLiteralStateMachine();
        stateMachine.init();
        stateMachine.reset();
        char[] chars = input.toCharArray();

        for (int i = 0; i < 3; i++) {
            char c = chars[i];
            assertDoesNotThrow(() -> stateMachine.processInput(c));
            assertFalse(stateMachine.isInAcceptState());
        }
        assertDoesNotThrow(() -> stateMachine.processInput(chars[3]));
        assertFalse(stateMachine.isInAcceptState());
    }
}
