package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class IdentifierStateMachine extends StateMachine {
  @Override
  public void init() {
    // States
    State stateStart = new State("start");
    stateStart.setStartState(true);
    addState(stateStart);

    State stateIdentifier = new State("identifier");
    stateIdentifier.setAcceptState(true);
    addState(stateIdentifier);

    // Transitions
    addRangeTransition(stateStart, stateIdentifier, new Range('a', 'z'));
    addRangeTransition(stateStart, stateIdentifier, new Range('A', 'Z'));
    addCharTransition(stateStart, stateIdentifier, '_');

    addRangeTransition(stateIdentifier, stateIdentifier, new Range('a', 'z'));
    addRangeTransition(stateIdentifier, stateIdentifier, new Range('A', 'Z'));
    addRangeTransition(stateIdentifier, stateIdentifier, new Range('0', '9'));
    addCharTransition(stateIdentifier, stateIdentifier, '_');
  }

  @Override
  public TokenType getTokenType() {
    return TokenType.TOK_IDENTIFIER;
  }
}
