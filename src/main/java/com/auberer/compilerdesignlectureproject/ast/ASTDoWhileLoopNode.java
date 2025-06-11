package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import com.auberer.compilerdesignlectureproject.sema.Scope;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ASTDoWhileLoopNode extends ASTNode {

  private Scope scope;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitDoWhileLoop(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return Set.of(TokenType.TOK_DO);
  }

  public ASTStmtLstNode getBody() {
    return getChild(ASTStmtLstNode.class, 0);
  }

  public ASTTernaryExprNode getCondition() {
    return getChild(ASTTernaryExprNode.class, 0);
  }
}
