package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;


public class KeywordStateMachine extends StateMachine {
    private final String keyword;
    private final Token token;
    private boolean isKeyword = true;

    public KeywordStateMachine(String keyword, Token token) {
        this.keyword = keyword;
        this.token = token;
    }

    @Override
    public void init() {
        // Start state
        State stateStart = new State("start");
        stateStart.setStartState(true);
        addState(stateStart);

        char[] keywordChars = keyword.toCharArray();
        char[] tokenChars = token.text.toCharArray();

        for (int i = 0; i < keyword.length(); i++) {
            if (!(keywordChars[i] == tokenChars[i])) {
                isKeyword = false;
                break;
            }
        }
        //Fail State
        State stateFail = new State("fail");
        stateFail.setAcceptState(false);
        addState(stateFail);

        // End state
        State stateEnd = new State("end");
        stateEnd.setAcceptState(true);
        addState(stateEnd);

        if (isKeyword) {
            addElseTransition(stateStart, stateEnd);
        }else{
            addElseTransition(stateStart, stateFail);
        }
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_KEYWORD_LIT;
    }
}
