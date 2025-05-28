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
  private final BasicBlock defaultBlock;

  public SwitchInstruction(ASTSwitchCaseStmtNode node, Value value, List<BasicBlock> caseBlocks, List<ASTCaseStmtNode> cases, BasicBlock defaultBlock) {
    super(node);
    assert cases.size() == caseBlocks.size();
    this.value = value;
    this.caseBlocks = caseBlocks;
    this.cases = cases;
    this.defaultBlock = defaultBlock;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append("switch (").append(value.getName()).append(") ");
    List<String> blockNames = caseBlocks.stream().map(BasicBlock::getLabel).toList();
    sb.append(String.join(", ", blockNames));
    if (defaultBlock != null) {
      sb.append(", ").append(defaultBlock.getLabel());
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
