package com.auberer.compilerdesignlectureproject.reader;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

@Slf4j
public class Reader implements IReader{
    private BufferedReader inputReader;
    private char currentChar;
    private CodeLoc codeLoc;
    private boolean eof;

    @Override
    public char getChar() {
        return currentChar;
    }

    public Reader(String input){
        try {
            StringReader stringReader = new StringReader(input);
            inputReader = new BufferedReader(stringReader);
            assert inputReader.ready();

            advance();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing reader", e);
        }
    }
    @Override
    public CodeLoc getCodeLoc() {
        return null;
    }


    // muss noch implementiert werden
    @Override
    public void advance() {
        assert !isEOF();
        try {
            currentChar = (char) inputReader.read();
            if (currentChar == '\n') {
                codeLoc.line++;
                codeLoc.column = 0;
            } else {
                codeLoc.column++;
            }
        } catch (IOException e) {
            eof = true;
            log.error("Error reading character", e.getMessage());
        }
    }

    @Override
    public void expect(char c) throws RuntimeException {
        if (currentChar != c) {
            throw new RuntimeException("Expected " + c + " but found " + currentChar);
        } else {
            advance();
        }
    }

    @Override
    public boolean isEOF() {
        return eof;
    }

    @Override
    public void close() {
        try {
            inputReader.close();
        } catch (IOException e) {
            log.error("Error closing reader", e.getMessage());
        }

    }
}
