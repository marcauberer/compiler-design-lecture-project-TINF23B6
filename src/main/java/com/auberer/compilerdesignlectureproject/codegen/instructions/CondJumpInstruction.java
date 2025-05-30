package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.auberer.compilerdesignlectureproject.codegen.BasicBlock;

public class CondJumpInstruction extends Instruction {

  private final ASTNode condition;
  private final BasicBlock trueTargetBlock;
  private final BasicBlock falseTargetBlock;

  public CondJumpInstruction(ASTNode node, ASTNode condition, BasicBlock trueTargetBlock, BasicBlock falseTargetBlock) {
    super(node);
    this.condition = condition;
    this.trueTargetBlock = trueTargetBlock;
    this.falseTargetBlock = falseTargetBlock;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append("condjump ").append(condition.getValue().getName()).append(" ? ")
        .append(trueTargetBlock.getLabel()).append(" : ")
        .append(falseTargetBlock.getLabel());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": cond jump");
  }

  @Override
  public boolean isTerminator() {
    return true;
  }
}
