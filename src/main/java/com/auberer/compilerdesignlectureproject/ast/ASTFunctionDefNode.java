package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ASTFunctionDefNode extends ASTNode {

  private String identifier;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return ASTParamLstNode.getSelectionSet();
  }
}
