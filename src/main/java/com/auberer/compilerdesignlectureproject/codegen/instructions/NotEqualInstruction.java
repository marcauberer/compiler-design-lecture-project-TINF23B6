package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTAdditiveExprNode;
import com.auberer.compilerdesignlectureproject.ast.ASTNode;

public class NotEqualInstruction extends Instruction {

  private final ASTAdditiveExprNode leftOperand;
  private final ASTAdditiveExprNode rightOperand;

  public NotEqualInstruction(ASTNode node, ASTAdditiveExprNode leftOperand, ASTAdditiveExprNode rightOperand) {
    super(node);
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append("not equal ");
    sb.append(leftOperand.getValue().getName());
    sb.append(", ");
    sb.append(rightOperand.getValue().getName());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": binary operation: not equal expression");
  }
}
