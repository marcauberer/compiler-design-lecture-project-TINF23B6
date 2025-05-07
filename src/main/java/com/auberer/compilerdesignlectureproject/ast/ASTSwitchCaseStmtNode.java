package com.auberer.compilerdesignlectureproject.ast;

import java.util.HashSet;
import java.util.Set;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

public class ASTSwitchCaseStmtNode extends ASTNode {

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitSwitchCaseStmt(this);
    }

    public static Set<TokenType> getSelectionSet() {
        Set<TokenType> selectionSet = new HashSet<>();
        selectionSet.add(TokenType.TOK_SWITCH);
        return selectionSet;
    }

    public ASTSwitchCaseStmtNode getStmtLst() {
        return getChild(ASTSwitchCaseStmtNode.class, 0);
    }
}
