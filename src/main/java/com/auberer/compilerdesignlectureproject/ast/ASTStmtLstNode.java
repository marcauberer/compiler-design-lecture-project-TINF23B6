package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ASTStmtLstNode extends ASTNode {
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visistStmtLst(this);
  }

  public static Set<TokenType> getSelectionSet() {
    Set<TokenType> selectionSet = new HashSet<>();
    selectionSet.addAll(ASTStmtNode.getSelectionSet());
    selectionSet.add(TokenType.TOK_RBRACE);
    selectionSet.add(TokenType.TOK_CASE);
    selectionSet.add(TokenType.TOK_DEFAULT);
    return selectionSet;
  }

  public List<ASTStmtNode> getStmts() {
    return getChildren(ASTStmtNode.class);
  }
}
