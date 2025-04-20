package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

import java.util.ArrayList;

public class KeywordStateMachine extends StateMachine {
    private final String keyword;
    private final TokenType tokenType;

    public KeywordStateMachine(String keyword, TokenType tokenType) {
        this.keyword = keyword;
        this.tokenType = tokenType;
    }

    @Override
    public void init() {
        ArrayList<State> allStates = new ArrayList<>(keyword.length());

        // Start state
        State stateStart = new State("start");
        stateStart.setStartState(true);
        addState(stateStart);
        allStates.add(stateStart);
        // End state
        State stateEnd = new State("end");


        for (int i = 0; i < keyword.length(); i++) {
            char y = keyword.charAt(i);
            State currentState = new State("state"+i);
            addState(currentState);
            addCharTransition(allStates.get(i), currentState, y);
            allStates.add(currentState);
            stateEnd = currentState;
        }

        stateEnd.setAcceptState(true);
    }

    @Override
    public TokenType getTokenType() {
        return this.tokenType;
    }
}
