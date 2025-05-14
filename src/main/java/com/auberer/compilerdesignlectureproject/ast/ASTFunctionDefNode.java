package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ASTFunctionDefNode extends ASTNode {

  private String identifier;
  private boolean haveParams;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitFunctionDef(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return ASTParamLstNode.getSelectionSet();
  }

  public ASTParamLstNode getParams() {
    return haveParams ? getChild(ASTParamLstNode.class, 0) : null;
  }

  public ASTStmtLstNode getBody() {
    return getChild(ASTStmtLstNode.class, 0);
  }
}
