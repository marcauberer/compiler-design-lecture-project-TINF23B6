package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import com.auberer.compilerdesignlectureproject.sema.Scope;
import lombok.Data;

import java.util.Set;

@Data
public class ASTForLoopNode extends ASTNode {

  private Scope scope;
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