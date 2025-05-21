package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ASTTernaryExprNode extends ASTNode {

  private boolean isExpanded = false;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitTernaryExpr(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return ASTEqualityExprNode.getSelectionSet();
  }

  public ASTEqualityExprNode getEqualityExpr() {
    return getChild(ASTEqualityExprNode.class, 0);
  }

  public ASTEqualityExprNode getCondition() {
    return getChild(ASTEqualityExprNode.class, 0);
  }

  public ASTEqualityExprNode getTrueBranch() {
    return getChild(ASTEqualityExprNode.class, 1);
  }

  public ASTEqualityExprNode getFalseBranch() {
    return getChild(ASTEqualityExprNode.class, 2);
  }
}
