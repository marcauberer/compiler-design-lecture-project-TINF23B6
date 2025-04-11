package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class IdentifierStateMachine extends StateMachine {
    @Override
    public void init() {
        // Start state
        State stateStart = new State("start");
        stateStart.setStartState(true);
        addState(stateStart);

        // Error state
        State stateError = new State("error");
        addState(stateError);

        // End state
        State stateEnd = new State("end");
        stateEnd.setAcceptState(true);
        addState(stateEnd);

        // Transitions
        addRangeTransition(stateStart, stateEnd, new Range('a','z'));
        addRangeTransition(stateStart, stateEnd, new Range('A','Z'));
        addCharTransition(stateStart, stateEnd, '_');
        addElseTransition(stateEnd, stateEnd);
        addElseTransition(stateStart, stateError);
        addElseTransition(stateError, stateError);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_IDENTIFIER;
    }
}
