package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ASTAtomicExprNode extends ASTNode {

  private String variableName; // Only set if TOK_IDENTIFIER is selected

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitAtomicExpr(this);
  }

  public static Set<TokenType> getSelectionSet() {
    Set<TokenType> selectionSet = new HashSet<>();
    selectionSet.addAll(ASTLiteralNode.getSelectionSet());
    // ToDo(Marc): Add others
    selectionSet.addAll(ASTPrintBuiltinCallNode.getSelectionSet());
    selectionSet.add(TokenType.TOK_IDENTIFIER);
    selectionSet.add(TokenType.TOK_LPAREN);
    return selectionSet;
  }
}
