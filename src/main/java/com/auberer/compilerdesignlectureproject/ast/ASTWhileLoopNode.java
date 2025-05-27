package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import com.auberer.compilerdesignlectureproject.sema.Scope;

import java.util.HashSet;
import java.util.Set;

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

    public Scope getScope() {
        return scope;
    }
    public void setScope(Scope scope) {
        this.scope = scope;
    }
}
