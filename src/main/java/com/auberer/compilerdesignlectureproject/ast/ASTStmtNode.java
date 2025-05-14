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
    selectionSet.addAll(ASTVarDeclNode.getSelectionSet());
    selectionSet.addAll(ASTAssignStmtNode.getSelectionSet());
    selectionSet.addAll(ASTReturnStmtNode.getSelectionSet());
    selectionSet.addAll(ASTIfStmtNode.getSelectionSet());
    selectionSet.addAll(ASTWhileLoopNode.getSelectionSet());
    selectionSet.addAll(ASTDoWhileLoopNode.getSelectionSet());
    selectionSet.addAll(ASTForLoopNode.getSelectionSet());
    selectionSet.addAll(ASTSwitchCaseStmtNode.getSelectionSet());
    selectionSet.addAll(ASTAnonymousBlockStmtNode.getSelectionSet());
    return selectionSet;
  }
}
