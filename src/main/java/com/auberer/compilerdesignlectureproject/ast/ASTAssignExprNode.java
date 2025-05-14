package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import com.auberer.compilerdesignlectureproject.sema.SymbolTableEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ASTAssignExprNode extends ASTNode {

  private String variableName;
  private SymbolTableEntry currentSymbol;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitAssignExpr(this);
  }

  public static Set<TokenType> getSelectionSet() {
    Set<TokenType> selectionSet = new HashSet<>();
    selectionSet.add(TokenType.TOK_IDENTIFIER);
    selectionSet.addAll(ASTTernaryExprNode.getSelectionSet());
    return selectionSet;
  }

  public ASTTernaryExprNode getRhs() {
    return getChild(ASTTernaryExprNode.class, 0);
  }
}
