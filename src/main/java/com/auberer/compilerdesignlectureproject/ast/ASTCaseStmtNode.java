package com.auberer.compilerdesignlectureproject.ast;

import java.util.HashSet;
import java.util.Set;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

public class ASTCaseStmtNode extends ASTNode {

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitCaseStmt(this);
  }

  public static Set<TokenType> getSelectionSet() {
    Set<TokenType> selectionSet = new HashSet<>();
    selectionSet.add(TokenType.TOK_CASE);
    return selectionSet;
  }

  public ASTLiteralNode getLiteral() {
    return getChild(ASTLiteralNode.class, 0);
  }

  public ASTStmtLstNode getStmtLst() {
    return getChild(ASTStmtLstNode.class, 0);
  }
}
