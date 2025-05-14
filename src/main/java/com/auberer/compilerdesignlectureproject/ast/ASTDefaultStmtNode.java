package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

import java.util.HashSet;
import java.util.Set;

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

  public ASTDefaultStmtNode getBody() {
    return getChild(ASTDefaultStmtNode.class, 0);
  }
}
