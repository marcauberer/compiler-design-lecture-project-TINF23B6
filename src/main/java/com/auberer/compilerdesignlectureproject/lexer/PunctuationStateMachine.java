package com.auberer.compilerdesignlectureproject.lexer;

public class PunctuationStateMachine extends KeywordStateMachine {
    public PunctuationStateMachine(String symbol, TokenType tokenType) {
        super(symbol, tokenType);
    }
}
