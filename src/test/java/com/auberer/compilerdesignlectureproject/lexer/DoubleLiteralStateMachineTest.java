package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLiteralStateMachineTest {

    @Test
    @DisplayName("Happy path: Valid double literals")
    public void testHappyPath() {
        String[] validInputs = {
                "0.0",
                "1.5",
                "-3.14",
                "123.456",
                "0.123456",
                "999999.000001"
        };

        for (String input : validInputs) {
            DoubleLiteralStateMachine stateMachine = new DoubleLiteralStateMachine();
            stateMachine.init();
            stateMachine.reset();

            for (char c : input.toCharArray()) {
                assertDoesNotThrow(() -> stateMachine.processInput(c), "Exception for input: " + input);
            }

            assertTrue(stateMachine.isInAcceptState(), "Should be in accept state for input: " + input);
        }
    }

    @Test
    @DisplayName("Erroneous input: Invalid double literals")
    public void testErroneousInput() {
        String[] invalidInputs = {
                ".",
                "12.",
                "-.5",
                "",
                "1.2.3",
                "abc",
                "--3.0",
                "4.5a",
                "-.123",
                "-"
        };

        for (String input : invalidInputs) {
            DoubleLiteralStateMachine stateMachine = new DoubleLiteralStateMachine();
            stateMachine.init();
            stateMachine.reset();

            boolean threwException = false;
            try {
                for (char c : input.toCharArray()) {
                    stateMachine.processInput(c);
                }
            } catch (IllegalStateException e) {
                threwException = true;
            }

            assertTrue(threwException || !stateMachine.isInAcceptState(),
                    "Should not be in accept state for input: " + input);
        }
    }
}
