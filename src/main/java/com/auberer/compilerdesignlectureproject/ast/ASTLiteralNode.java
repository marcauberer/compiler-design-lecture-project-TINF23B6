package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ASTLiteralNode extends ASTNode {

  public enum LiteralType {
    INT,
    DOUBLE,
    STRING,
    BOOL
  }

  private LiteralType literalType;
  private String literalValue;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitLiteral(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return Set.of(
        TokenType.TOK_INT_LIT,
        TokenType.TOK_DOUBLE_LIT,
        TokenType.TOK_STRING_LIT,
        TokenType.TOK_TRUE,
        TokenType.TOK_FALSE
    );
  }

  int getValueAsInt() {
    assert literalType == LiteralType.INT;
    return Integer.parseInt(literalValue);
  }

  double getValueAsDouble() {
    assert literalType == LiteralType.DOUBLE;
    return Double.parseDouble(literalValue);
  }

  String getValueAsString() {
    assert literalType == LiteralType.STRING;
    return literalValue;
  }

  boolean getValueAsBool() {
    assert literalType == LiteralType.BOOL;
    return Boolean.parseBoolean(literalValue);
  }
}
