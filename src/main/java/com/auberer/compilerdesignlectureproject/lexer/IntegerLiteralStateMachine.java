package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class IntegerLiteralStateMachine extends StateMachine {


    public void init() {
        State q0 = new State("q0");
        addState(q0);
        q0.setStartState(true);

        State q1 = new State("q1");
        addState(q1);

        State q2 = new State("q2");
        addState(q2);

        State q3 = new State("q3");
        addState(q3);

        State q4 = new State("q4");
        addState(q4);

        State q5 = new State("q5");
        q5.setAcceptState(true);
        addState(q5);


        // Transitions
        addCharTransition(q0, q1, '0');
        addElseTransition(q1, q3);
        addRangeTransition(q0, q2, new Range('1', '9'));
        addCharTransition(q0, q4, '-');
        addRangeTransition(q4, q2, new Range('1', '9'));
        addRangeTransition(q2, q2, new Range('0', '9'));
        addCharTransition(q1, q5, 'i');
        addCharTransition(q2, q5, 'i');

    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_INTEGER_LIT;
    }

}
