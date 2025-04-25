package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

import java.util.HashSet;
import java.util.Set;

public class ASTAtomicExprNode extends ASTNode {
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitAtomicExpr(this);
  }

  public static Set<TokenType> getSelectionSet() {
    Set<TokenType> selectionSet = new HashSet<>();
    selectionSet.addAll(ASTLiteralNode.getSelectionSet());
    // ToDo(Marc): Add others
    selectionSet.addAll(ASTPrintBuiltinCallNode.getSelectionSet());
    selectionSet.add(TokenType.TOK_IDENTIFIER);
    selectionSet.add(TokenType.TOK_LPAREN);
    return selectionSet;
  }
}
