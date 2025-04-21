package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class DoubleLiteralStateMachine extends StateMachine {
    @Override
    public void init() {
        // Start state
        State q0 = new State("q0");

        // Content state
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");
        State q4 = new State("q4");

        // End state
        State q5 = new State("q5");

        addState(q0);
        addState(q1);
        addState(q2);
        addState(q3);
        addState(q4);
        addState(q5);


        q0.setStartState(true);
        q5.setAcceptState(true);

        // Transitions
        addCharTransition(q0, q1, '-');
        addCharTransition(q0, q2, '0');
        addRangeTransition(q0, q3, new Range('1', '9'));

        addCharTransition(q1, q2, '0');
        addRangeTransition(q1, q3, new Range('1', '9'));

        addCharTransition(q2, q4, ',');

        addCharTransition(q3, q4, ',');
        addRangeTransition(q3, q3, new Range('0', '9'));

        addRangeTransition(q4, q4, new Range('0', '9'));

        addCharTransition(q4, q5, 'd');
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_DOUBLE_LIT;
    }
}
