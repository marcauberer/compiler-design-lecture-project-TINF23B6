package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class KeywordStateMachine extends StateMachine {

  private final String keyword;
  private final TokenType tokenType;

  public KeywordStateMachine(String keyword, TokenType tokenType) {
    this.keyword = keyword;
    this.tokenType = tokenType;
  }

  @Override
  public void init() {
    State prevState = new State("start");
    prevState.setStartState(true);
    addState(prevState);

    for (int i = 0; i < keyword.length(); i++) {
      char c = keyword.charAt(i);
      State nextState = new State( c + " - state");

      if (i == keyword.length() - 1)
        nextState.setAcceptState(true);

      addState(nextState);
      addCharTransition(prevState, nextState, c);

      prevState = nextState;
    }
  }

  @Override
  public TokenType getTokenType() {
    return tokenType;
  }
}
