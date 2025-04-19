package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerLiteralStateMachineTest {

    @Test
    @DisplayName("Happy path: Valid integer literals")
    public void testHappyPath() {
        String[] validInputs = {
                "0",
                "123",
                "+42",
                "-7",
                "00123"
        };

        for (String input : validInputs) {
            IntegerLiteralStateMachine stateMachine = new IntegerLiteralStateMachine();
            stateMachine.init();
            stateMachine.reset();

            for (char c : input.toCharArray()) {
                assertDoesNotThrow(() -> stateMachine.processInput(c), "Input: " + input);
            }

            assertTrue(stateMachine.isInAcceptState(), "Should be in accept state for input: " + input);
        }
    }

    @Test
    @DisplayName("Erroneous input: Invalid integer literals")
    public void testErroneousInput() {
        String[] invalidInputs = {
                "",
                "+",
                "-",
                "12a3",
                "42!",
                "++10",
                "--5"
        };

        for (String input : invalidInputs) {
            IntegerLiteralStateMachine stateMachine = new IntegerLiteralStateMachine();
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
