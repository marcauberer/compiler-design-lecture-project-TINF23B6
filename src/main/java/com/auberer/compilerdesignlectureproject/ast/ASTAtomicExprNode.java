package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import com.auberer.compilerdesignlectureproject.sema.SymbolTableEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ASTAtomicExprNode extends ASTNode {

  private String variableName; // Only set if TOK_IDENTIFIER is selected
  private SymbolTableEntry currentSymbol;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitAtomicExpr(this);
  }

  public static Set<TokenType> getSelectionSet() {
    Set<TokenType> selectionSet = new HashSet<>();
    selectionSet.addAll(ASTLiteralNode.getSelectionSet());
    selectionSet.addAll(ASTFunctionCallNode.getSelectionSet());
    selectionSet.addAll(ASTPrintBuiltinCallNode.getSelectionSet());
    selectionSet.add(TokenType.TOK_IDENTIFIER);
    selectionSet.add(TokenType.TOK_LPAREN);
    return selectionSet;
  }

  public ASTLiteralNode getLiteral() {
    return getChild(ASTLiteralNode.class, 0);
  }

  public ASTFunctionCallNode getFunctionCall() {
    return getChild(ASTFunctionCallNode.class, 0);
  }

  public ASTPrintBuiltinCallNode getPrintBuiltin() {
    return getChild(ASTPrintBuiltinCallNode.class, 0);
  }

  public ASTTernaryExprNode getTernaryExpr() {
    return getChild(ASTTernaryExprNode.class, 0);
  }
}
