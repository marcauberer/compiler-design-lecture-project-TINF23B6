package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import com.auberer.compilerdesignlectureproject.sema.SymbolTableEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ASTVarDeclNode extends ASTNode {

  private String variableName;
  private SymbolTableEntry currentSymbol;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitVarDecl(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return ASTTypeNode.getSelectionSet();
  }

  public ASTTypeNode getType() {
    return getChild(ASTTypeNode.class, 0);
  }

  public ASTTernaryExprNode getInitExpr() {
    return getChild(ASTTernaryExprNode.class, 0);
  }
}
