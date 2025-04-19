package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdentifierLiteralStateMachineTest {

    @Test
    @DisplayName("Happy path: Valid identifiers")
    public void testValidIdentifiers() {
        String[] validInputs = {
                "abc",
                "a1b2c3",
                "_underscore",
                "CamelCase",
                "snake_case_123",
                "_123abc",
                "a",
                "_",
                "A9B_C"
        };

        for (String input : validInputs) {
            IdentifierStateMachine stateMachine = new IdentifierStateMachine();
            stateMachine.init();
            stateMachine.reset();

            for (char c : input.toCharArray()) {
                assertDoesNotThrow(() -> stateMachine.processInput(c), "Exception for input: " + input);
            }

            assertTrue(stateMachine.isInAcceptState(), "Should be in accept state for input: " + input);
        }
    }

    @Test
    @DisplayName("Erroneous input: Invalid identifiers")
    public void testInvalidIdentifiers() {
        String[] invalidInputs = {
                "",
                "123abc",
                "9to5",
                "!",
                "@name",
                "abc!",
                "first-name",
                "abc def",
                "1",
                "0_var"
        };

        for (String input : invalidInputs) {
            IdentifierStateMachine stateMachine = new IdentifierStateMachine();
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
