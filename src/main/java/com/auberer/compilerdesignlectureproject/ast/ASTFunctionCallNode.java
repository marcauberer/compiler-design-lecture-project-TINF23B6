package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import com.auberer.compilerdesignlectureproject.sema.SymbolTableEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ASTFunctionCallNode extends ASTNode {

  private SymbolTableEntry correspondingSymbol;
  private String identifier;
  private boolean hasArguments;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitFunctionCall(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return Set.of(TokenType.TOK_CALL);
  }

  public ASTArgLstNode getArguments() {
    return hasArguments ? getChild(ASTArgLstNode.class, 0) : null;
  }

}
