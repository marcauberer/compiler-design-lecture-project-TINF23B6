package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.State;
import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;

import java.util.ArrayList;
import java.util.List;

public class KeywordStateMachine extends StateMachine {
    private String keyword;
    private Token token;
    private boolean isKeyword = true;
    private List<State> states = new ArrayList<>();

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
            if (keywordChars[i] == tokenChars[i]) {
                if(i!=0&&i!=keywordChars.length-1){
                    State contentState = new State("content");
                    addState(contentState);
                    states.add(contentState);
                }
            }else{
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
            addCharTransition(stateStart, states.getFirst(),keywordChars[0]);
            for (int i = 1; i < keyword.length()-1; i++) {
                addCharTransition(states.get(i-1),states.get(i),keywordChars[i]);
            }
            addCharTransition(states.getLast(), stateEnd,keywordChars[keyword.length()-1]);
        }else{
            addElseTransition(stateStart, stateFail);
        }
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.TOK_KEYWORD_LIT;
    }
}
