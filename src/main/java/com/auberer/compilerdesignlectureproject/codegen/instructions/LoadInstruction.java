package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.auberer.compilerdesignlectureproject.sema.SymbolTableEntry;

public class LoadInstruction extends Instruction {

  private final SymbolTableEntry entry;

  public LoadInstruction(ASTNode node, SymbolTableEntry entry) {
    super(node);
    this.entry = entry;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append("load ").append(entry.getName());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": load");
  }
}
