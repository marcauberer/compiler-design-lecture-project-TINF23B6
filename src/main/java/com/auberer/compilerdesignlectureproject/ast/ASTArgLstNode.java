package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ASTArgLstNode extends ASTNode {
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public static Set<TokenType> getSelectionSet() {
        return ASTAtomicExprNode.getSelectionSet();
    }

    public ArrayList<ASTAtomicExprNode> getAtomicExpBranches() { return getChildren(ASTAtomicExprNode.class);}
}
