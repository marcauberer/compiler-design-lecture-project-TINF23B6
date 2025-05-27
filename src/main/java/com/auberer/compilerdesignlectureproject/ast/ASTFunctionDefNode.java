package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import com.auberer.compilerdesignlectureproject.sema.SymbolTableEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ASTFunctionDefNode extends ASTNode {

  private String identifier;

  private SymbolTableEntry currentSymbol;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitFunctionDef(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return ASTParamLstNode.getSelectionSet();
  }

  public ASTTypeNode getReturnType() { return getChild(ASTTypeNode.class, 0);}
  public ASTParamLstNode getParams() { return getChild(ASTParamLstNode.class, 0) ;
  }

  public ASTStmtLstNode getBody() {
    return getChild(ASTStmtLstNode.class, 0);
  }
}
