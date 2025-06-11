package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import com.auberer.compilerdesignlectureproject.sema.Scope;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class ASTWhileLoopNode extends ASTNode {

  private Scope scope;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitWhileLoopStmt(this);
  }

  public static Set<TokenType> getSelectionSet() {
    Set<TokenType> selectionSet = new HashSet<>();
    selectionSet.add(TokenType.TOK_WHILE);
    return selectionSet;
  }

  public ASTStmtLstNode getBody() {
    return getChild(ASTStmtLstNode.class, 0);
  }

  public ASTTernaryExprNode getCondition() {
    return getChild(ASTTernaryExprNode.class, 0);
  }
}
