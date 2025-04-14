package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class IntegerLiteralStateMachine extends StateMachine {
    @Override
    public void init() {
        // Start state
        State stateStart = new State("Start");
        stateStart.setStartState(true);
        addState(stateStart);
        // Integer State
        State stateInt = new State("Integer");
        stateInt.setAcceptState(true);
        addState(stateInt);
        // Zero State
        State stateZero = new State("Zero");
        stateZero.setAcceptState(true);
        addState(stateZero);

        // Transitions
        addRangeTransition(stateStart, stateInt, new Range('1', '9'));
        addRangeTransition(stateInt, stateInt, new Range('0', '9'));
        addCharTransition(stateStart, stateZero, '0');
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_INT_LIT;
    }
}
