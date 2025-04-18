package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class IntegerLiteralStateMachine extends StateMachine {
  @Override
  public void init() {
    // Start state
    State stateStart = new State("start");
    stateStart.setStartState(true);
    addState(stateStart);

    State digetsState = new State("digets");
    digetsState.setAcceptState(true);
    addState(digetsState);

    State stateZero = new State("zero");
    stateZero.setAcceptState(true);
    addState(stateZero);

    // Transitions
    addCharTransition(stateStart, stateZero, '0');
    addRangeTransition(stateStart, digetsState, new Range('1', '9'));
    addRangeTransition(digetsState, digetsState, new Range('0', '9'));
  }

  @Override
  public TokenType getTokenType() {
    return TokenType.TOK_INT_LIT;
  }
}
