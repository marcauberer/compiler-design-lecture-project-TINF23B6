package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class KeywordStateMachine extends StateMachine {
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


        // Transitions
        addRangeTransition(stateStart,contentState,lowLetters);
        addRangeTransition(contentState,stateEnd,lowLetters);
        addRangeTransition(stateEnd,stateEnd,lowLetters);
        addElseTransition(stateEnd,failState);
        addElseTransition(stateStart,failState);
        addElseTransition(contentState,failState);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_KEYWORD_LIT;
    }
}
