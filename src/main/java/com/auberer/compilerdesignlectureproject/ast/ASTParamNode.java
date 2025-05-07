package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ASTParamNode extends ASTNode {

  private Boolean hasAssignStmt;
  private String identifier;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return ASTTypeNode.getSelectionSet();
  }

  public ASTTypeNode getType() { return getChild(ASTTypeNode.class, 0); }

  public ASTAtomicExprNode getAtomicExpr() { return hasAssignStmt ? getChild(ASTAtomicExprNode.class, 0) : null; }

}
