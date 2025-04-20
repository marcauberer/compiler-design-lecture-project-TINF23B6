package com.auberer.compilerdesignlectureproject.lexer;

public class SymbolStateMachine extends KeywordStateMachine {

    public SymbolStateMachine(String keyword, TokenType tokenType) {
        super(keyword, tokenType);
    }
}
