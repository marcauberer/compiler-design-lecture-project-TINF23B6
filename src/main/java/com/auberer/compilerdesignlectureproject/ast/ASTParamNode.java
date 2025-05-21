package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import com.auberer.compilerdesignlectureproject.sema.SymbolTableEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ASTParamNode extends ASTNode {

  private Boolean hasAssignStmt;
  private String identifier;
  private SymbolTableEntry currentSymbol;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitParam(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return ASTTypeNode.getSelectionSet();
  }

  public ASTTypeNode getDataType() {
    return getChild(ASTTypeNode.class, 0);
  }

  public ASTAtomicExprNode getDefaultValue() {
    return hasAssignStmt ? getChild(ASTAtomicExprNode.class, 0) : null;
  }

}
