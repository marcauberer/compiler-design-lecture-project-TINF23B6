package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class IdentifierStateMachine extends StateMachine {
    @Override
    public void init() {
        State q0 = new State("q0");
        q0.setStartState(true);
        addState(q0);

        State q1 = new State("q1");
        addState(q1);

        State q2 = new State("q2");
        q2.setAcceptState(true);
        addState(q2);

        addRangeTransition(q0, q1, new Range('a', 'z'));
        addRangeTransition(q0, q1, new Range('A', 'Z'));
        addCharTransition(q0, q1, '_');

        addRangeTransition(q1, q1, new Range('a', 'z'));
        addRangeTransition(q1, q1, new Range('A', 'Z'));
        addRangeTransition(q1, q1, new Range('0', '9'));
        addCharTransition(q1, q1, '_');

        addCharTransition(q1, q2, ':');
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_IDENTIFIER;
    }
}