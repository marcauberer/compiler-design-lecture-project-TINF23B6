package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTMultiplicativeExprNode;
import com.auberer.compilerdesignlectureproject.ast.ASTNode;

public class MinusInstruction extends Instruction {

  private final ASTMultiplicativeExprNode leftOperand;
  private final ASTMultiplicativeExprNode rightOperand;

  public MinusInstruction(ASTNode node, ASTMultiplicativeExprNode leftOperand, ASTMultiplicativeExprNode rightOperand) {
    super(node);
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append(node.getValue().getName()).append(" = sub ")
        .append(leftOperand.getType().toLLVMIRTypeString()).append(" ")
        .append(leftOperand.getValue().getName()).append(", ")
        .append(rightOperand.getValue().getName());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": binary operation: sub expression");
  }
}
