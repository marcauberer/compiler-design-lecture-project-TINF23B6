package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

import java.util.regex.Pattern;

public class IntegerLiteralStateMachine extends StateMachine {
    @Override
    public void init() {
        // Start state
        State stateStart = new State("start");
        stateStart.setStartState(true);
        addState(stateStart);
        // End state
        State stateEnd = new State("end");
        stateEnd.setAcceptState(true);
        addState(stateEnd);

        //Fail State
        State failState = new State("fail");
        stateEnd.setAcceptState(false);
        addState(failState);

        // Transitions
        addRangeTransition(stateStart,stateEnd,new Range('0','9'));
        addRangeTransition(stateEnd,stateEnd,new Range('0','9'));
        addElseTransition(stateEnd, failState);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_INTEGER_LIT;
    }
}
