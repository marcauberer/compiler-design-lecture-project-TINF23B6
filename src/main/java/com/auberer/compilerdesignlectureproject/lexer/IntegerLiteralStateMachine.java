package com.auberer.compilerdesignlectureproject.lexer;


import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class IntegerLiteralStateMachine extends StateMachine {
    @Override
    public void init() {

        // start state
        State startState = new State("start");
        startState.setStartState(true);
        addState(startState);

        State signState = new State("sign");
        addState(signState);

        State contentState = new State("integer");
        contentState.setAcceptState(true);
        addState(contentState);


        // transitions
        addCharTransition(startState, signState, '+');
        addCharTransition(startState, signState, '-');

        addRangeTransition(startState, contentState, new Range('0', '9'));
        addRangeTransition(signState, contentState, new Range('0', '9'));
        addRangeTransition(contentState, contentState, new Range('0', '9'));

        //addElseTransition(contentState, contentState);

    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_INTEGER_LIT;
    }
}
