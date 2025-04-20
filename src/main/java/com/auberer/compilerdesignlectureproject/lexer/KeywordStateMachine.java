package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class KeywordStateMachine extends StateMachine {
    String keyword;
    TokenType tokenType;
    public KeywordStateMachine(
            String keyword,
            TokenType tokenType
    ) {
        if (keyword == null || keyword.isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be null or empty");
        }
        this.keyword = keyword;
        this.tokenType = tokenType;
    }
    @Override
    public void init() {
        // Create the start state
        State currentState = new State("start");
        currentState.setStartState(true);
        addState(currentState);


        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            State nextState = new State("state_" + (i + 1));
            if (i == keyword.length() - 1) {
                nextState.setAcceptState(true);
            }
            addState(nextState);
            addCharTransition(currentState, nextState, c);
            currentState = nextState;
        }
    }

    @Override
    public TokenType getTokenType() {
        return tokenType;
    }

    static TokenType getTokenType(String keyword) {
        return switch (keyword) {
            case "if" -> TokenType.TOK_KEYWORD_IF;
            case "else" -> TokenType.TOK_KEYWORD_ELSE;
            case "while" -> TokenType.TOK_KEYWORD_WHILE;
            case "for" -> TokenType.TOK_KEYWORD_FOR;
            case "return" -> TokenType.TOK_KEYWORD_RETURN;
            case "int" -> TokenType.TOK_KEYWORD_INT;
            case "float" -> TokenType.TOK_KEYWORD_FLOAT;
            case "char" -> TokenType.TOK_KEYWORD_CHAR;
            case "boolean" -> TokenType.TOK_KEYWORD_BOOLEAN;
            case "switch" -> TokenType.TOK_KEYWORD_SWITCH;
            default -> throw new IllegalArgumentException("Unknown keyword: " + keyword);
        };
    }

}
