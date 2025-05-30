package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTMultiplicativeExprNode;
import com.auberer.compilerdesignlectureproject.ast.ASTNode;

public class PlusInstruction extends Instruction {

  private final ASTMultiplicativeExprNode leftOperand;
  private final ASTMultiplicativeExprNode rightOperand;

  public PlusInstruction(ASTNode node, ASTMultiplicativeExprNode leftOperand, ASTMultiplicativeExprNode rightOperand) {
    super(node);
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append("plus ");
    sb.append(leftOperand.getValue().getName());
    sb.append(", ");
    sb.append(rightOperand.getValue().getName());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": binary operation: add expression");
  }
}
