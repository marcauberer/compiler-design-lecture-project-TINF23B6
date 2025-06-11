package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.auberer.compilerdesignlectureproject.codegen.BasicBlock;
import com.auberer.compilerdesignlectureproject.interpreter.InterpreterEnvironment;
import com.auberer.compilerdesignlectureproject.sema.SuperType;
import lombok.Getter;

@Getter
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
    sb.append("br i1 ").append(condition.getValue().getName())
        .append(", label %").append(trueTargetBlock.getLabel())
        .append(", label %").append(falseTargetBlock.getLabel());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": cond jump");
  }

  @Override
  public void run(InterpreterEnvironment env) {
    assert condition.getType().is(SuperType.TYPE_BOOL);
    if (condition.getValue().isTrue()) {
      env.setInstructionIterator(trueTargetBlock.getInstructions().listIterator());
    } else {
      env.setInstructionIterator(falseTargetBlock.getInstructions().listIterator());
    }
  }

  @Override
  public boolean isTerminator() {
    return true;
  }
}
