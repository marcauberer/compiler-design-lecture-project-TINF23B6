package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.Range;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

public class DoubleLiteralStateMachine extends StateMachine {
  @Override
  public void init() {
    // States
    State stateStart = new State("Start");
    stateStart.setStartState(true);
    addState(stateStart);

    State zeroBeforeDot = new State("Zero Before Dot");
    addState(zeroBeforeDot);

    State dot = new State("Dot");
    addState(dot);

    State numberBeforeDot = new State("Number Before Dot");
    addState(numberBeforeDot);

    State singleZeroAfterDot = new State("Single Zero After Dot");
    singleZeroAfterDot.setAcceptState(true);
    addState(singleZeroAfterDot);

    State unnecessarZeroAfterDot = new State("Unnecessar Zero After Dot");
    addState(unnecessarZeroAfterDot);

    State numberAfterDot = new State("Number After Dot");
    numberAfterDot.setAcceptState(true);
    addState(numberAfterDot);

    addCharTransition(stateStart, zeroBeforeDot, '0');
    addRangeTransition(stateStart, numberBeforeDot, new Range('1','9'));
    addRangeTransition(numberBeforeDot, numberBeforeDot, new Range('0','9'));

    addCharTransition(zeroBeforeDot, dot, '.');
    addCharTransition(numberBeforeDot, dot, '.');

    addCharTransition(dot, singleZeroAfterDot, '0');
    addCharTransition(singleZeroAfterDot, unnecessarZeroAfterDot, '0');
    addCharTransition(unnecessarZeroAfterDot, unnecessarZeroAfterDot, '0');
    addRangeTransition(singleZeroAfterDot, numberAfterDot, new Range('1','9'));
    addRangeTransition(unnecessarZeroAfterDot, numberAfterDot, new Range('1','9'));
    addRangeTransition(dot, numberAfterDot, new Range('1','9'));
    addRangeTransition(numberAfterDot, numberAfterDot, new Range('1','9'));
    addCharTransition(numberAfterDot, unnecessarZeroAfterDot, '0');
  }

  @Override
  public TokenType getTokenType() {
    return TokenType.TOK_DOUBLE_LIT;
  }
}
