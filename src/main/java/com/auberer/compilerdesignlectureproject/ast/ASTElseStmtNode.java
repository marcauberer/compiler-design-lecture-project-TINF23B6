package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ASTElseStmtNode extends ASTNode {

    private boolean containsIfStmt;

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitElseStmt(this);
    }

    public static Set<TokenType> getSelectionSet() {
        return Set.of(TokenType.TOK_ELSE);
    }

    public ASTNode getBody() {
        if (containsIfStmt) {
            return getChild(ASTIfBodyNode.class, 0);
        } else {
            return getChild(ASTElseStmtNode.class, 0);
        }
    }
}
