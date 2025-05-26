package com.auberer.compilerdesignlectureproject.ast;

import java.util.HashSet;
import java.util.Set;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import com.auberer.compilerdesignlectureproject.sema.ExprResult;
import com.auberer.compilerdesignlectureproject.sema.Scope;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ASTCaseStmtNode extends ASTNode {

  private ExprResult conditionResult;
  private Scope scope;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitCaseStmt(this);
  }

  public static Set<TokenType> getSelectionSet() {
    Set<TokenType> selectionSet = new HashSet<>();
    selectionSet.add(TokenType.TOK_CASE);
    return selectionSet;
  }

  public ASTLiteralNode getLiteral() {
    return getChild(ASTLiteralNode.class, 0);
  }

  public ASTStmtLstNode getStmtLst() {
    return getChild(ASTStmtLstNode.class, 0);
  }
}
