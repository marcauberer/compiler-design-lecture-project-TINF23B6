package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.auberer.compilerdesignlectureproject.interpreter.Value;
import com.auberer.compilerdesignlectureproject.sema.SymbolTableEntry;

public class AllocaInstruction extends Instruction {

  private final SymbolTableEntry entry;

  public AllocaInstruction(ASTNode node, SymbolTableEntry entry, String variableName) {
    super(node);
    this.entry = entry;
    entry.setValue(new Value(node, variableName));
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append(entry.getValue().getName()).append(" = alloca ")
        .append(entry.getType().toLLVMIRTypeString())
        .append(", align ").append(entry.getType().toAlignmentString());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": alloca");
  }
}
