package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;

import java.util.HashSet;
import java.util.Set;

public class ASTStmtNode extends ASTNode {
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visistStmt(this);
  }

  public static Set<TokenType> getSelectionSet() {
    Set<TokenType> selectionSet = new HashSet<>();
    selectionSet.add(TokenType.TOK_TYPE_INT);
    selectionSet.add(TokenType.TOK_TYPE_DOUBLE);
    selectionSet.add(TokenType.TOK_TYPE_STRING);
    selectionSet.add(TokenType.TOK_TYPE_BOOL);
    selectionSet.add(TokenType.TOK_RET);
    selectionSet.add(TokenType.TOK_IF);
    selectionSet.add(TokenType.TOK_WHILE);
    selectionSet.add(TokenType.TOK_DO);
    selectionSet.add(TokenType.TOK_FOR);
    selectionSet.add(TokenType.TOK_SWITCH);
    selectionSet.add(TokenType.TOK_LBRACE);
    return selectionSet;
  }
}
