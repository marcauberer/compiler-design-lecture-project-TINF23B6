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
  private boolean haveParams;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return ASTParamLstNode.getSelectionSet();
  }

  public ASTParamLstNode getParamBranch() {return haveParams ? getChild(ASTParamLstNode.class,0) : null;}
  public ASTStmtLstNode getStmtBranch() {return getChild(ASTStmtLstNode.class,0);}
}
