package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class IdentifierStateMachine extends StateMachine {
    @Override
    public void init() {
        Range alphabet = new Range('a','z');
        Range alphabetUpper = new Range('A','Z');
        Range digits = new Range('0','9');
        // Start state
        State stateStart = new State("start");
        stateStart.setStartState(true);
        addState(stateStart);
        // End state
        State stateEnd = new State("end");
        stateEnd.setAcceptState(true);
        addState(stateEnd);

        // Transitions
        addRangeTransition(stateStart, stateEnd, alphabet);
        addRangeTransition(stateStart, stateEnd, alphabetUpper);
        addCharTransition(stateStart, stateEnd, '_');

        addRangeTransition(stateEnd, stateEnd, alphabet);
        addRangeTransition(stateEnd, stateEnd, alphabetUpper);
        addRangeTransition(stateEnd, stateEnd, digits);
        addCharTransition(stateEnd, stateEnd, '_');
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_IDENTIFIER;
    }
}
