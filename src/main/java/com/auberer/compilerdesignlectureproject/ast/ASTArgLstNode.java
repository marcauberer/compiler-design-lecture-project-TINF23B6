package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

import java.util.HashSet;
import java.util.Set;

public class ASTArgLstNode extends ASTNode {
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public static Set<TokenType> getSelectionSet() {
        Set<TokenType> selectionSet = new HashSet<TokenType>();
        for (TokenType s : ASTLiteralNode.getSelectionSet()) {
            selectionSet.add(s);
        }
        for (TokenType s : ASTFunctionCallNode.getSelectionSet()) {
            selectionSet.add(s);
        }
        for (TokenType s : ASTPrintBuiltinCallNode.getSelectionSet()) {
            selectionSet.add(s);
        }
        for (TokenType s : ASTLiteralNode.getSelectionSet()) {
            selectionSet.add(s);
        }
        selectionSet.add(TokenType.TOK_LPAREN);
        return selectionSet;
    }
}
