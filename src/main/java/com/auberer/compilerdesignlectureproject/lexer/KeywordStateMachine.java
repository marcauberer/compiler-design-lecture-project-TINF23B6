package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class KeywordStateMachine extends StateMachine {
    private final String keyword;

    public KeywordStateMachine(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void init() {
        if (keyword.equals("if")) {
            // Start state
            State stateStart = new State("start");
            stateStart.setStartState(true);
            addState(stateStart);

            // Error state
            State stateError = new State("error");
            addState(stateError);

            // State i
            State stateI = new State("one");
            addState(stateI);

            // End state
            State stateEnd = new State("end");
            stateEnd.setAcceptState(true);
            addState(stateEnd);

            // Transitions
            addCharTransition(stateStart, stateI, 'i');
            addCharTransition(stateI, stateEnd, 'f');
            addElseTransition(stateStart, stateError);
            addElseTransition(stateI, stateError);
            addElseTransition(stateEnd, stateError);
            addElseTransition(stateError, stateError);
        }
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_IF;
    }
}
