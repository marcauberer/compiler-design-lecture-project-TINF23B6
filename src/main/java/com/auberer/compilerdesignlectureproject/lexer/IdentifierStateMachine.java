package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class IdentifierStateMachine extends StateMachine {
    @Override
    public void init() {
        State stateStart = new State("start");
        stateStart.setStartState(true);
        addState(stateStart);
        // Content state
        State contentState = new State("content");
        addState(contentState);
        // End state
        State stateEnd = new State("end");
        stateEnd.setAcceptState(true);
        addState(stateEnd);
        //Fail
        State failState = new State("fail");
        addState(failState);
        stateEnd.setAcceptState(false);

        Range lowLetters = new Range('a','z');
        Range upperLetters = new Range('A','Z');
        Range numbers = new Range('0','9');

        // Transitions
        addRangeTransition(stateStart,stateEnd,lowLetters);
        addRangeTransition(stateStart,stateEnd,upperLetters);
        addCharTransition(stateStart, contentState, '_');
        addRangeTransition(stateEnd,stateEnd,lowLetters);
        addRangeTransition(stateEnd,stateEnd,upperLetters);
        addCharTransition(stateEnd, stateEnd, '_');
        addRangeTransition(contentState,stateEnd,lowLetters);
        addRangeTransition(contentState,stateEnd,upperLetters);
        addCharTransition(contentState, stateEnd, '_');
        addElseTransition(stateEnd,failState);
        addElseTransition(stateStart,failState);
        addElseTransition(contentState,failState);

    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_IDENTIFIER_LIT;
    }
}
