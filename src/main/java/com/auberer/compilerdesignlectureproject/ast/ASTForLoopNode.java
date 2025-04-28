package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

import java.util.Set;

public class ASTForLoopNode extends ASTNode {

    public static Set<TokenType> getSelectionSet() {
        return Set.of(TokenType.TOK_FOR);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
