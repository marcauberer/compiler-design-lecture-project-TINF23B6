package com.auberer.compilerdesignlectureproject.sema;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

// Abuse lexer statemachine

/*
INFO:
i = ifStmT
e = elseStmT
r = returnStmT
b = IfBodyStmt
 */
public class ReturnStateMachine extends StateMachine {
  @Override
  public void init() {
    State stateStart = new State("S1");
    stateStart.setStartState(true);
    addState(stateStart);

    State ifState = new State("S2");
    addState(ifState);

    State retState = new State("S3");
    addState(retState);

    State deadIfElseState = new State("S4");
    addState(deadIfElseState);

    State deadIfElseIfState = new State("S7");
    addState(deadIfElseIfState);

    State deadIfState = new State("S8");
    addState(deadIfState);

    State returnState = new State("S5");
    returnState.setAcceptState(true);
    addState(returnState);

    State returnIfState = new State("S6");
    returnIfState.setAcceptState(true);
    addState(returnIfState);

    State returnIfBodyState = new State("S8");
    returnIfBodyState.setAcceptState(true);
    addState(returnIfBodyState);

    addCharTransition(stateStart, ifState, 'i'); //if
    addCharTransition(stateStart, returnState, 'r'); //return
    addElseTransition(stateStart, stateStart);

    addCharTransition(ifState, retState, 'r'); // return in if
    addCharTransition(ifState, deadIfElseState, 'e'); // else without ret
    addElseTransition(ifState, ifState);

    addCharTransition(deadIfElseState, stateStart, 'b'); // ifbody end => terminating dead if else statement
    addCharTransition(deadIfElseState, deadIfElseIfState, 'i'); // if statement instead of end
    addElseTransition(deadIfElseState, deadIfElseIfState);

    addCharTransition(deadIfElseIfState, deadIfState, 'b'); // bodyend (chance to end dead if else)
    addElseTransition(deadIfElseIfState, deadIfElseIfState);

    addCharTransition(deadIfState, deadIfElseState, 'e');
    addCharTransition(deadIfState, ifState, 'i');
    addCharTransition(deadIfState, returnState, 'r');
    addElseTransition(deadIfState, deadIfState);

    addCharTransition(retState, returnIfState, 'r');
    addCharTransition(retState, ifState, 'i');
    addElseTransition(retState, retState);

    addCharTransition(returnState, retState, 'e'); // after succesfull finishing the elseif there is an else => inside an if clause
    addCharTransition(returnState, returnIfState, 'i'); // unnecessary if
    addElseTransition(returnState, returnState);

    addCharTransition(returnIfState, returnIfBodyState, 'b');
    addElseTransition(returnIfState, returnIfState);

    addCharTransition(returnIfBodyState, returnIfState, 'i');
    addCharTransition(returnIfBodyState, returnIfState, 'e');
    addCharTransition(returnIfBodyState, returnState, 'b'); // end of inner else if
    addElseTransition(returnIfBodyState, returnIfBodyState);
  }

  @Override
  public TokenType getTokenType() {
    return null;
  }
}
