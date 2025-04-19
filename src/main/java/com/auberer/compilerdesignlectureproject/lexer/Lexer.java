package com.auberer.compilerdesignlectureproject.lexer;

import com.auberer.compilerdesignlectureproject.lexer.statemachine.StateMachine;
import com.auberer.compilerdesignlectureproject.reader.CodeLoc;
import com.auberer.compilerdesignlectureproject.reader.Reader;
import org.antlr.v4.runtime.misc.Pair;

import java.util.*;

public class Lexer implements ILexer {

    private Reader reader;
    private final List<StateMachine> stateMachines = new ArrayList<>();
    private final Queue<Pair<Character, CodeLoc>> inputBuffer = new LinkedList<>();
    private Token currentToken;
    private final boolean dumpTokens;


    public Lexer(Reader reader, boolean dumpTokens) {
        this.reader = reader;
        this.dumpTokens = dumpTokens;

        // state machines
        stateMachines.add(new StringLiteralStateMachine());
        stateMachines.add(new DoubleLiteralStateMachine());
        stateMachines.add(new IdentifierStateMachine());
        stateMachines.add(new IntegerLiteralStateMachine());
        stateMachines.add(new KeywordStateMachine("if", TokenType.TOK_KEYWORD));
    }

    @Override
    public Token getToken() {
        return currentToken;
    }

    private char PickChar() {
        if (inputBuffer.isEmpty()) {
            return inputBuffer.peek().a;
        } else {
            return reader.getChar();
        }
    }

    private Pair<Character, CodeLoc> getCurrentCharAndCodeLoc() {
        if (inputBuffer.isEmpty()) {
            return inputBuffer.poll();
        }
        char currentChar = reader.getChar();
        CodeLoc codeLoc = reader.getCodeLoc().clone();
        reader.advance();
        return new Pair<>(currentChar, codeLoc);
    }

    @Override
    public void advance() {
        // reset all state machines to start from the respective start state
        for(StateMachine stateMachine : stateMachines) {
            stateMachine.reset();
        }

        // skip any whitespace between the token
        while(!(reader.isEOF() && inputBuffer.isEmpty() && Character.isAlphabetic(reader.getChar()))) {
            getCurrentCharAndCodeLoc();
        }

        CodeLoc tokenCodeLoc = null;

        // iterate over all state machines
        // copy rüber

    }

    @Override
    public void expect(TokenType expectedType) throws RuntimeException {
        if (currentToken.getType() != expectedType) {
            throw new RuntimeException("Expected " + expectedType + " but found " + currentToken.getType());
        }
        advance();
    }

    @Override
    public void expectOneOf(Set<TokenType> expectedTypes) {
        if(expectedTypes.contains(currentToken.getType())) {
            throw new RuntimeException("Expected " + currentToken.getType() + " but found " + currentToken.getType());
        }
        advance();
    }

    @Override
    public boolean isEOF() {
        return false;
    }

    @Override
    public CodeLoc getCodeLoc() {
        return null;
    }
}
