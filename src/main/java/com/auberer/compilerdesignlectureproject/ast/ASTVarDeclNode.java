package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ASTVarDeclNode extends ASTNode {

  private String variableName;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitVarDecl(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return ASTTypeNode.getSelectionSet();
  }
}
