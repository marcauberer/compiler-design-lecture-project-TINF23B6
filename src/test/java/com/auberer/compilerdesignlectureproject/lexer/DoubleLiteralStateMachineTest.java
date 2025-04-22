package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLiteralStateMachineTest {

    @Test
    @DisplayName("Test Double :)")
    public void testDoubleIf() {
        String input = "123.45d";
        DoubleLiteralStateMachine stateMachine = new DoubleLiteralStateMachine();
        stateMachine.init();
        stateMachine.reset();
        for (char c : input.toCharArray()) {
            assertFalse(stateMachine.isInAcceptState());
            assertDoesNotThrow(() -> stateMachine.processInput(c));
        }
        assertTrue(stateMachine.isInAcceptState());
    }

    @Test
    @DisplayName("Test Non-Double :(")
    public void testNonDoubleInput() {
        String input = "noDouble";
        DoubleLiteralStateMachine stateMachine = new DoubleLiteralStateMachine();
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