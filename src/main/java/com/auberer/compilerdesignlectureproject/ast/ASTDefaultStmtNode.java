package com.auberer.compilerdesignlectureproject.ast;

import java.util.HashSet;
import java.util.Set;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

public class ASTDefaultStmtNode extends ASTNode {

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitDefaultStmt(this);
    }

    public static Set<TokenType> getSelectionSet() {
        Set<TokenType> selectionSet = new HashSet<>();
        selectionSet.add(TokenType.TOK_DEFAULT);
        return selectionSet;
    }

    public ASTDefaultStmtNode getStmtLst() {
        return getChild(ASTDefaultStmtNode.class, 0);
    }
}
