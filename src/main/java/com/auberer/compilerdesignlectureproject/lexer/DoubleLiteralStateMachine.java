package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class DoubleLiteralStateMachine extends StateMachine {
    @Override
    public void init() {
        // Start
        State startState = new State("start");
        startState.setStartState(true);
        addState(startState);

        // Optional sign
        State signState = new State("sign");
        addState(signState);

        // Digits before the dot
        State beforeCommaState = new State("beforeComma");
        addState(beforeCommaState);

        // Just saw the dot
        State afterCommaState = new State("afterComma");
        addState(afterCommaState);

        // At least one digit after the dot
        State afterCommaDigitsState = new State("afterCommaDigits");
        afterCommaDigitsState.setAcceptState(true);
        addState(afterCommaDigitsState);

        // Transitions
        addCharTransition(startState, signState, '-');
        addRangeTransition(startState, beforeCommaState, new Range('0', '9'));

        addRangeTransition(signState, beforeCommaState, new Range('0', '9'));

        addRangeTransition(beforeCommaState, beforeCommaState, new Range('0', '9'));
        addCharTransition(beforeCommaState, afterCommaState, '.');

        addRangeTransition(afterCommaState, afterCommaDigitsState, new Range('0', '9'));
        addRangeTransition(afterCommaDigitsState, afterCommaDigitsState, new Range('0', '9'));
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_DOUBLE_LIT;
    }
}
