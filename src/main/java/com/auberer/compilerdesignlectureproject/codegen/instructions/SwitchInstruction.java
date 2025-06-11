package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTCaseStmtNode;
import com.auberer.compilerdesignlectureproject.ast.ASTSwitchCaseStmtNode;
import com.auberer.compilerdesignlectureproject.ast.ASTTernaryExprNode;
import com.auberer.compilerdesignlectureproject.codegen.BasicBlock;
import com.auberer.compilerdesignlectureproject.interpreter.InterpreterEnvironment;
import com.auberer.compilerdesignlectureproject.interpreter.Value;

import java.util.ArrayList;
import java.util.List;

public class SwitchInstruction extends Instruction {

  private final ASTTernaryExprNode condition;
  private final List<BasicBlock> caseBlocks;
  private final List<ASTCaseStmtNode> cases;
  private final BasicBlock defaultOrEndBlock;

  public SwitchInstruction(ASTSwitchCaseStmtNode node, ASTTernaryExprNode condition, List<BasicBlock> caseBlocks, List<ASTCaseStmtNode> cases, BasicBlock defaultOrEndBlock) {
    super(node);
    assert cases.size() == caseBlocks.size();
    this.condition = condition;
    this.caseBlocks = caseBlocks;
    this.cases = cases;
    this.defaultOrEndBlock = defaultOrEndBlock;
  }

  public List<BasicBlock> getTargetBlocks() {
    List<BasicBlock> targetBlocks = new ArrayList<>(caseBlocks);
    targetBlocks.add(defaultOrEndBlock);
    return targetBlocks;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append("switch ").append(condition.getType().toLLVMIRTypeString()).append(" ")
        .append(condition.getValue().getName()).append(", label %").append(defaultOrEndBlock.getLabel());
    for (int i = 0; i < cases.size(); i++) {
      ASTCaseStmtNode caseStmt = cases.get(i);
      BasicBlock caseBlock = caseBlocks.get(i);
      sb.append(" ").append(caseStmt.getLiteral().getType().toLLVMIRTypeString()).append(" ")
          .append(caseStmt.getValue().getName())
          .append(", label %").append(caseBlock.getLabel());
    }
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": switch ");
  }

  @Override
  public void run(InterpreterEnvironment env) {
    Value conditionValue = condition.getValue();
    for (int i = 0; i < cases.size(); i++) {
      ASTCaseStmtNode caseNode = cases.get(i);
      BasicBlock caseBlock = caseBlocks.get(i);
      Value caseValue = caseNode.getValue();
      if (conditionValue.toString().equals(caseValue.toString())) {
        env.setInstructionIterator(caseBlock.getInstructions().listIterator());
        return;
      }
    }
    env.setInstructionIterator(defaultOrEndBlock.getInstructions().listIterator());
  }

  @Override
  public boolean isTerminator() {
    return true;
  }
}
