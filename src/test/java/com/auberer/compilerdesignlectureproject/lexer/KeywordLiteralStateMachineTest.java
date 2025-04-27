package com.auberer.compilerdesignlectureproject.lexer;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KeywordLiteralStateMachineTest {

  @Data
  @AllArgsConstructor
  static class KeywordTokenTypePair {
    private String keyword;
    private TokenType tokenType;
  }

  @Test
  @DisplayName("Happy path: Valid keywords")
  public void testValidKeywords() {
    KeywordTokenTypePair[] validKeywordTokenPairs = {
        new KeywordTokenTypePair("if", TokenType.TOK_IF),
        new KeywordTokenTypePair("else", TokenType.TOK_ELSE),
        new KeywordTokenTypePair("while", TokenType.TOK_WHILE),
        new KeywordTokenTypePair("for", TokenType.TOK_FOR),
        new KeywordTokenTypePair("switch", TokenType.TOK_SWITCH),
        new KeywordTokenTypePair("ret", TokenType.TOK_RET),
        new KeywordTokenTypePair("int", TokenType.TOK_TYPE_INT),
        new KeywordTokenTypePair("double", TokenType.TOK_TYPE_DOUBLE),
        new KeywordTokenTypePair("string", TokenType.TOK_TYPE_STRING),
        new KeywordTokenTypePair("bool", TokenType.TOK_TYPE_BOOL),
    };

    for (KeywordTokenTypePair keywordTokenTypePair : validKeywordTokenPairs) {
      String keyword = keywordTokenTypePair.getKeyword();
      TokenType tokenType = keywordTokenTypePair.getTokenType();

      KeywordStateMachine stateMachine = new KeywordStateMachine(keyword, tokenType);
      stateMachine.init();
      stateMachine.reset();

      for (char c : keyword.toCharArray()) {
        assertDoesNotThrow(() -> stateMachine.processInput(c), "Exception for keyword: " + keywordTokenTypePair);
      }

      assertTrue(stateMachine.isInAcceptState(), "Should be in accept state for keyword: " + keywordTokenTypePair);
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
      KeywordStateMachine stateMachine = new KeywordStateMachine("if", TokenType.TOK_IF);
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