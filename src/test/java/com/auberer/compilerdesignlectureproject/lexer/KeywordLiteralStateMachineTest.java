package com.auberer.compilerdesignlectureproject.lexer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KeywordLiteralStateMachineTest {

    @Test
    @DisplayName("Happy path: Valid keywords")
    public void testValidKeywords() {
        String[] validKeywords = {
                "if",
                "else",
                "while",
                "for",
                "return",
                "int",
                "float",
                "char",
                "boolean",
                "switch"
        };

        for (String keyword : validKeywords) {
            KeywordStateMachine stateMachine = new KeywordStateMachine(keyword, KeywordStateMachine.getTokenType(keyword));
            stateMachine.init();
            stateMachine.reset();

            for (char c : keyword.toCharArray()) {
                assertDoesNotThrow(() -> stateMachine.processInput(c), "Exception for keyword: " + keyword);
            }

            assertTrue(stateMachine.isInAcceptState(), "Should be in accept state for keyword: " + keyword);
        }
    }

    @Test
    @DisplayName("Erroneous input: Invalid keywords")
    public void testInvalidKeywords() {
        String[] invalidKeywords = {
                "",           // empty
                "iff",        // typo
                "els",        // incomplete keyword
                "whileloop",  // valid keyword but extra characters
                "forEach",    // case-sensitive mismatch
                "return1",    // valid keyword but invalid suffix
                "int32",      // valid keyword but invalid suffix
                "Float",      // case-sensitive mismatch
                "booleanValue", // valid keyword but invalid suffix
                "switcheroo"  // valid keyword but invalid suffix
        };

        for (String keyword : invalidKeywords) {
            KeywordStateMachine stateMachine = new KeywordStateMachine("if", KeywordStateMachine.getTokenType("if"));
            stateMachine.init();
            stateMachine.reset();

            boolean threwException = false;

            try {
                for (char c : keyword.toCharArray()) {
                    stateMachine.processInput(c);
                }
            } catch (IllegalStateException e) {
                threwException = true;
            }

            assertTrue(threwException || !stateMachine.isInAcceptState(),
                    "Should not be in accept state for keyword: " + keyword);
        }
    }
}