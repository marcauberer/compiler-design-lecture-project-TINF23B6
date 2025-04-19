package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class IdentifierStateMachine extends StateMachine {

    @Override
    public void init() {
        State startState = new State("start");
        startState.setStartState(true);
        addState(startState);


        // content state
        State contentState = new State("content");
        addState(contentState);
        contentState.setAcceptState(true);


        // end state
        State endState = new State("end");
        endState.setAcceptState(true);
        addState(endState);

        // transitions
        addCharTransition(startState, contentState, '_');
        addRangeTransition(startState, contentState, new Range('a', 'z'));
        addRangeTransition(startState, contentState, new Range('A', 'Z'));

        addRangeTransition(contentState, contentState, new Range('a', 'z'));
        addRangeTransition(contentState, contentState, new Range('A', 'Z'));
        addRangeTransition(contentState, contentState, new Range('0', '9'));
        addCharTransition(contentState, contentState,  '_');

        addRangeTransition(contentState, endState, new Range('a', 'z'));
        addRangeTransition(contentState, endState, new Range('A', 'Z'));
        addRangeTransition(contentState, endState, new Range('0', '9'));
        //addCharTransition(contentState, endState,  '_');



    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_IDENTIFIER;
    }
}
