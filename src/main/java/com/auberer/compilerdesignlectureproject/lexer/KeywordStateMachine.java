package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class KeywordStateMachine extends StateMachine {
    private final String keyword;
    private final TokenType tokenType;

    public KeywordStateMachine(String keyword, TokenType tokenType) {
        if (keyword == null || keyword.isEmpty()) {
            throw new IllegalArgumentException("No Keyword given");
        }

        this.keyword = keyword;
        this.tokenType = tokenType;
    }

    @Override
    public void init() {
        State startState = new State("startstate");
        startState.setStartState(true);
        addState(startState);
        State currentState = startState;

        for (int i = 0; i < keyword.length(); i++) {
            char ch = keyword.charAt(i);
            State nextState = new State("state" + i + " " + ch);

            if (i == keyword.length() - 1) {
                nextState.setAcceptState(true);
            }

            addState(nextState);
            addCharTransition(currentState, nextState, ch);
            currentState = nextState;
        }
    }

    @Override
    public TokenType getTokenType() {
        return tokenType;
    }
}