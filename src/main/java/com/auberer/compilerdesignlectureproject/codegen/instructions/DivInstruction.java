package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTAtomicExprNode;
import com.auberer.compilerdesignlectureproject.ast.ASTNode;

public class DivInstruction extends Instruction {

  private final ASTAtomicExprNode leftOperand;
  private final ASTAtomicExprNode rightOperand;

  public DivInstruction(ASTNode node, ASTAtomicExprNode leftOperand, ASTAtomicExprNode rightOperand) {
    super(node);
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append("div ");
    sb.append(leftOperand.getValue().getName());
    sb.append(", ");
    sb.append(rightOperand.getValue().getName());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": binary operation: div expression");
  }
}
