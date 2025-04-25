package com.auberer.compilerdesignlectureproject.ast;

public class ASTStmtLstNode extends ASTNode {
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visistStmtLst(this);
  }
}
