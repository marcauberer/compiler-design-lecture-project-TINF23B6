package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KeywordStateMachineTest {
    @Test
    @DisplayName("Happy path")
    public void testHappyPath() {
        String input = "if";
        KeywordStateMachine stateMachine = new KeywordStateMachine("if", TokenType.TOK_IF);
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
        String input = "for"; // Incorrect keyword
        KeywordStateMachine stateMachine = new KeywordStateMachine("if", TokenType.TOK_IF);
        stateMachine.init();
        stateMachine.reset();
        boolean illegalState = false;

        try {
            for (char c : input.toCharArray()) {
                stateMachine.processInput(c);
                assertFalse(stateMachine.isInAcceptState());
            }
        } catch (IllegalStateException e) {
            illegalState = true;
        }
        assertTrue(illegalState);
    }
}
