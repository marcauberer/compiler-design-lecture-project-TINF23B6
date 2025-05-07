package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

import java.util.Set;

public class ASTForLoopNode extends ASTNode {


  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitForLoop(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return Set.of(TokenType.TOK_FOR);
  }

  public ASTVarDeclNode getInitialization() {
    return getChild(ASTVarDeclNode.class, 0);
  }

  public ASTTernaryExprNode getCondition() {
    return getChild(ASTTernaryExprNode.class, 0);
  }

  public ASTAssignExprNode getIncrement() {
    return getChild(ASTAssignExprNode.class, 0);
  }

  public ASTStmtLstNode getBody() {
    return getChild(ASTStmtLstNode.class, 0);
  }


}