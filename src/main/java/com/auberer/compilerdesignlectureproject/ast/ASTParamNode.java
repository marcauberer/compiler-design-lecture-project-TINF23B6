package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

import java.util.HashSet;
import java.util.Set;

public class ASTParamNode extends ASTNode {
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public static Set<TokenType> getSelectionSet() {
        Set<TokenType> selectionSet = new HashSet<TokenType>();
        selectionSet.add(TokenType.TOK_TYPE_INT);
        selectionSet.add(TokenType.TOK_TYPE_DOUBLE);
        selectionSet.add(TokenType.TOK_TYPE_STRING);
        selectionSet.add(TokenType.TOK_TYPE_BOOL);
        return selectionSet;
    }
}
