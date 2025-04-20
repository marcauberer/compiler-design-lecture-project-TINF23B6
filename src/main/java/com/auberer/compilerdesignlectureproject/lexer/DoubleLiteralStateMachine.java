package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class DoubleLiteralStateMachine extends StateMachine {
    @Override
    public void init() {
        Range digits = new Range('0','9');
        // Start state
        State stateStart = new State("start");
        stateStart.setStartState(true);
        addState(stateStart);
        // Content state one
        State state1 = new State("1");
        addState(state1);
        // Content state two
        State state2 = new State("2");
        addState(state2);
        // End state
        State stateEnd = new State("end");
        stateEnd.setAcceptState(true);
        addState(stateEnd);

        // Transitions
        addRangeTransition(stateStart, state1, digits);
        addRangeTransition(state1, state1, digits);
        addCharTransition(state1, state2, '.');
        addRangeTransition(state2, stateEnd, digits);
        addRangeTransition(stateEnd, stateEnd, digits);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_DOUBLE_LIT;
    }
}
