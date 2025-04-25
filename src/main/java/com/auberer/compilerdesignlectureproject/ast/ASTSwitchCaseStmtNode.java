package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

import java.util.HashSet;
import java.util.Set;

public class ASTSwitchCaseStmtNode extends ASTNode {

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitSwitchCaseStmt(this);
    }

    public static Set<TokenType> getSelectionSet() {
        Set<TokenType> selectionSet = new HashSet<>();
        selectionSet.add(TokenType.TOK_SWITCH);
        selectionSet.add(TokenType.TOK_LPAREN);
        selectionSet.add(TokenType.TOK_QUESTION);
        selectionSet.add(TokenType.TOK_RPAREN);
        selectionSet.add(TokenType.TOK_LBRACE);
        selectionSet.add(TokenType.TOK_CASE);
        selectionSet.add(TokenType.TOK_DEFAULT);
        selectionSet.add(TokenType.TOK_COLON);
        selectionSet.add(TokenType.TOK_RBRACE);
        return selectionSet;
    }
}
