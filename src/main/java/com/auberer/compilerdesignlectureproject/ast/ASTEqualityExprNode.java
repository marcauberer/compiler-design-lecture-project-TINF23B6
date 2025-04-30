package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ASTEqualityExprNode extends ASTNode {

  public enum EqualityOp {
    EQ,
    NEQ,
  }

  private EqualityOp op;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitEqualityExpr(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return ASTAdditiveExprNode.getSelectionSet();
  }
}
