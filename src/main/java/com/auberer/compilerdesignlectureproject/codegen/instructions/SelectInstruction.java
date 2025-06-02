package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTEqualityExprNode;
import com.auberer.compilerdesignlectureproject.ast.ASTTernaryExprNode;

public class SelectInstruction extends Instruction {

  private final ASTEqualityExprNode condition;
  private final ASTEqualityExprNode trueValue;
  private final ASTEqualityExprNode falseValue;

  public SelectInstruction(ASTTernaryExprNode node, ASTEqualityExprNode condition, ASTEqualityExprNode trueValue, ASTEqualityExprNode falseValue) {
    super(node);
    this.condition = condition;
    this.trueValue = trueValue;
    this.falseValue = falseValue;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append("select ")
        .append(condition.getValue().getName()).append(", ")
        .append(trueValue.getValue().getName()).append(", ")
        .append(falseValue.getValue().getName());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": select");
  }
}
