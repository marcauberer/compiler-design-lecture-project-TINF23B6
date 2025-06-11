package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.auberer.compilerdesignlectureproject.interpreter.InterpreterEnvironment;
import com.auberer.compilerdesignlectureproject.sema.SymbolTableEntry;

public class StoreInstruction extends Instruction {

  private final SymbolTableEntry entry;

  public StoreInstruction(ASTNode node, SymbolTableEntry entry) {
    super(node);
    this.entry = entry;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append("store ").append(entry.getType().toLLVMIRTypeString())
        .append(" ").append(node.getValue().getName())
        .append(" ptr ").append(entry.getValue().getName())
        .append(", align ").append(entry.getType().toAlignmentString());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": store");
  }

  @Override
  public void run(InterpreterEnvironment env) {
    entry.setValue(node.getValue());
  }
}
