package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import com.auberer.compilerdesignlectureproject.sema.Scope;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class ASTIfBodyNode extends ASTNode {

  private Scope scope;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitIfBody(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return Set.of(TokenType.TOK_LBRACE);
  }

  public ASTStmtLstNode getBody() {
    return getChild(ASTStmtLstNode.class, 0);
  }
}
