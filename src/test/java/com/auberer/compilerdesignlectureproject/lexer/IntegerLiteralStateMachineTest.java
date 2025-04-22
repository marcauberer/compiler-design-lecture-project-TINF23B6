package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerLiteralStateMachineTest {

    @Test
    @DisplayName("Test Integer :)")
    public void testIntegerIf() {
        String input = "123i";
        IntegerLiteralStateMachine stateMachine = new IntegerLiteralStateMachine();
        stateMachine.init();
        stateMachine.reset();
        for (char c : input.toCharArray()) {
            assertFalse(stateMachine.isInAcceptState());
            assertDoesNotThrow(() -> stateMachine.processInput(c));
        }
        assertTrue(stateMachine.isInAcceptState());
    }

    @Test
    @DisplayName("Test Non-Integer :(")
    public void testNonIntegerInput() {
        String input = "noInteger";
        IntegerLiteralStateMachine stateMachine = new IntegerLiteralStateMachine();
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