package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTCaseStmtNode;
import com.auberer.compilerdesignlectureproject.ast.ASTSwitchCaseStmtNode;
import com.auberer.compilerdesignlectureproject.codegen.BasicBlock;
import com.auberer.compilerdesignlectureproject.interpreter.Value;

import java.util.List;

public class SwitchInstruction extends Instruction {

  private final Value value;
  private final List<BasicBlock> caseBlocks;
  private final List<ASTCaseStmtNode> cases;
  private final BasicBlock defaultOrEndBlock;

  public SwitchInstruction(ASTSwitchCaseStmtNode node, Value value, List<BasicBlock> caseBlocks, List<ASTCaseStmtNode> cases, BasicBlock defaultOrEndBlock) {
    super(node);
    assert cases.size() == caseBlocks.size();
    this.value = value;
    this.caseBlocks = caseBlocks;
    this.cases = cases;
    this.defaultOrEndBlock = defaultOrEndBlock;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append("switch ").append(node.getType().toLLVMIRTypeString()).append(" ")
        .append(value.getName()).append(", label %").append(defaultOrEndBlock.getLabel());
    for (int i = 0; i < cases.size(); i++) {
      ASTCaseStmtNode caseStmt = cases.get(i);
      BasicBlock caseBlock = caseBlocks.get(i);
      sb.append(" ").append(caseStmt.getType().toLLVMIRTypeString()).append(" ")
          .append(caseStmt.getValue().getName())
          .append(", label %").append(caseBlock.getLabel());
    }
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": switch ");
  }

  @Override
  public boolean isTerminator() {
    return true;
  }
}
