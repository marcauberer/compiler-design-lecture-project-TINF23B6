package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

import java.util.HashSet;
import java.util.Set;

public class ASTParamLstNode extends ASTNode {
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public static Set<TokenType> getSelectionSet() {
        Set<TokenType> selectionSet = new HashSet<TokenType>();
        selectionSet.addAll(ASTTypeNode.getSelectionSet());
        return selectionSet;
    }
}
