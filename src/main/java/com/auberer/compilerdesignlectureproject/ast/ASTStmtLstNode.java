package com.auberer.compilerdesignlectureproject.ast;

public class ASTStmtLstNode extends ASTNode {
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visistStmtLst(this);
  }

  public static Set<TokenType> getSelectionSet() {
    Set<TokenType> selectionSet = new HashSet<>();
    selectionSet.addAll(ASTStmtNode.getSelectionSet());
    selectionSet.add(TokenType.TOK_RBRACE);
    selectionSet.add(TokenType.TOK_CASE);
    selectionSet.add(TokenType.TOK_DEFAULT);
    return selectionSet;
  }
}
