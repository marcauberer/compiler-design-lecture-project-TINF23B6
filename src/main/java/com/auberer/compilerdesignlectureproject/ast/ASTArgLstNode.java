package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

import java.util.ArrayList;
import java.util.Set;

public class ASTArgLstNode extends ASTNode {
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitArgLst(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return ASTAtomicExprNode.getSelectionSet();
  }

  public ArrayList<ASTAtomicExprNode> getArgs() {
    return getChildren(ASTAtomicExprNode.class);
  }
}
