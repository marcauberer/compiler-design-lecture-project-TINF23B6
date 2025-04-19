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
}
