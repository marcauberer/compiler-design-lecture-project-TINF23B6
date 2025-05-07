package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

import java.util.Set;

public class ASTIfBodyNode extends ASTNode {
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitIfBody(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return Set.of(TokenType.TOK_RBRACE);
  }

  public ASTStmtLstNode getStmtLst() {
    return getChild(ASTStmtLstNode.class, 0);
  }
}
