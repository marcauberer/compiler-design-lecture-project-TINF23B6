package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerLiteralStateMachineTest {
    @Test
    @DisplayName("Happy path")
    public void testHappyPath() {
        String input = "42";
        IntegerLiteralStateMachine stateMachine = new IntegerLiteralStateMachine();
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
        String input = "187.1"; // Double
        IntegerLiteralStateMachine stateMachine = new IntegerLiteralStateMachine();
        stateMachine.init();
        stateMachine.reset();
        char[] chars = input.toCharArray();
        boolean illegalState = false;

        for (int i = 0; i < 3; i++) {
            char c = chars[i];
            assertDoesNotThrow(() -> stateMachine.processInput(c));
            assertTrue(stateMachine.isInAcceptState());
        }
        try {
            stateMachine.processInput(chars[3]);
        } catch (IllegalStateException e) {
            illegalState = true;
        }
        assertTrue(illegalState);
    }
}
