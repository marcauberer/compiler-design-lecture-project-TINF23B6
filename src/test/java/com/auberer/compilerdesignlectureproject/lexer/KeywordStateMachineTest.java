package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KeywordStateMachineTest {
    @Test
    @DisplayName("Happy path")
    public void testHappyPath() {
        String input = "if";
        KeywordStateMachine stateMachine = new KeywordStateMachine("if");
        stateMachine.init();
        stateMachine.reset();
        char[] chars = input.toCharArray();

        assertDoesNotThrow(() -> stateMachine.processInput(chars[0]));
        assertFalse(stateMachine.isInAcceptState());
        assertDoesNotThrow(() -> stateMachine.processInput(chars[1]));
        assertTrue(stateMachine.isInAcceptState());
    }

    @Test
    @DisplayName("Test erroneous input")
    public void testErroneousInput() {
        String input = "for"; // Incorrect identifier
        KeywordStateMachine stateMachine = new KeywordStateMachine("if");
        stateMachine.init();
        stateMachine.reset();

        for (char c : input.toCharArray()) {
            assertDoesNotThrow(() -> stateMachine.processInput(c));
            assertFalse(stateMachine.isInAcceptState());
            assertEquals("error", stateMachine.getCurrentState().getName());
        }
        assertFalse(stateMachine.isInAcceptState());
        assertEquals("error", stateMachine.getCurrentState().getName());
    }
}
