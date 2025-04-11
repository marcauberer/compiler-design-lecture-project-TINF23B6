package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class DoubleLiteralStateMachine extends StateMachine {
    @Override
    public void init() {
        final Range zeroToNine = new Range('0', '9');
        // Start state
        State stateStart = new State("start");
        stateStart.setStartState(true);
        addState(stateStart);

        // Error state
        State stateError = new State("error");
        addState(stateError);

        // State 1
        State stateOne = new State("one");
        addState(stateOne);

        // State 2
        State stateTwo = new State("two");
        addState(stateTwo);

        // End state
        State stateEnd = new State("end");
        stateEnd.setAcceptState(true);
        addState(stateEnd);

        // Transitions
        addRangeTransition(stateStart, stateOne, zeroToNine);
        addRangeTransition(stateOne, stateOne, zeroToNine);
        addCharTransition(stateOne, stateTwo, '.');
        addRangeTransition(stateTwo, stateEnd, zeroToNine);
        addRangeTransition(stateEnd, stateEnd, zeroToNine);
        addElseTransition(stateStart, stateError);
        addElseTransition(stateOne, stateError);
        addElseTransition(stateTwo, stateError);
        addElseTransition(stateEnd, stateError);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_DOUBLE_LIT;
    }
}
