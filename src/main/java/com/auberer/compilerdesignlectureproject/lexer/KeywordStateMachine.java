package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

import java.util.ArrayList;
import java.util.List;

public class KeywordStateMachine extends StateMachine {
    private final String keyword;

    public KeywordStateMachine(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void init() {
        List<State> states = new ArrayList<>();

        // Start State
        State startState = new State("start");
        startState.setStartState(true);
        states.add(startState);
        addState(startState);

        // New State for every char in keyword
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            State newState = new State("state_" + i);
            addState(newState);
            states.add(newState);
            addCharTransition(states.get(i), newState, c);
        }

        // Last State
        State stateEnd = states.get(states.size() - 1);
        stateEnd.setAcceptState(true);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_KEYWORD;
    }
}
