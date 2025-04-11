package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class DoubleLiteralStateMachine extends StateMachine {
    @Override
    public void init() {
        // Start state
        State stateStart = new State("start");
        stateStart.setStartState(true);
        addState(stateStart);
        // Content state
        State contentState = new State("content");
        stateStart.setAcceptState(true);
        addState(contentState);
        // End state
        State stateEnd = new State("end");
        stateEnd.setAcceptState(true);
        addState(stateEnd);

        State failState = new State("fail");
        stateEnd.setAcceptState(false);
        addState(failState);

        // Transitions
        addRangeTransition(stateStart,contentState,new Range('0','9'));
        addRangeTransition(contentState,contentState,new Range('0','9'));
        addCharTransition(contentState, stateEnd, '.');
        addRangeTransition(stateEnd,stateEnd,new Range('0','9'));
        addElseTransition(stateStart, failState);
        addElseTransition(contentState, failState);
        addElseTransition(stateEnd, failState);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_DOUBLE_LIT;
    }
}
