package com.auberer.compilerdesignlectureproject.interpreter;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.auberer.compilerdesignlectureproject.sema.SuperType;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Value {

  // Counter will be incremented for each new unnamed value
  private static int counter = 0;

  private final ASTNode node;

  private final String name;

  @Setter
  private int intValue = 0;
  @Setter
  private double doubleValue = 0.0;
  @Setter
  private String stringValue = "";
  @Setter
  private boolean boolValue = false;

  public Value(ASTNode node) {
    this.node = node;
    this.name = "%" + counter++;
  }

  public Value(ASTNode node, String name) {
    this.node = node;
    this.name = "%" + name;
  }

  public boolean isTrue() {
    assert node.getType().getSuperType() == SuperType.TYPE_BOOL;
    return boolValue;
  }

  public boolean isFalse() {
    assert node.getType().getSuperType() == SuperType.TYPE_BOOL;
    return !boolValue;
  }

  @Override
  public String toString() {
    SuperType superType = node.getType().getSuperType();
    return switch (superType) {
      case TYPE_INT -> Integer.toString(intValue);
      case TYPE_DOUBLE -> Double.toString(doubleValue);
      case TYPE_STRING -> stringValue;
      case TYPE_BOOL -> Boolean.toString(boolValue);
      default -> throw new RuntimeException("Type not supported for printing: " + superType);
    };
  }
}
