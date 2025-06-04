package com.auberer.compilerdesignlectureproject.codegen;

import com.auberer.compilerdesignlectureproject.sema.Type;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Function implements IDumpable {

  private final String name;
  private BasicBlock entryBlock;
  private final Type returnType;
  private final List<Parameter> parameters;

  public Function(String name, Type returnType, List<Parameter> parameters) {
    this.name = name;
    this.returnType = returnType;
    this.parameters = parameters;
  }

  public void setEntryBlock(BasicBlock entryBlock) {
    assert this.entryBlock == null;
    this.entryBlock = entryBlock;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append("define ").append(returnType.toLLVMIRTypeString()).append(" @").append(name).append("(");
    for (int i = 0; i < parameters.size(); i++) {
      Parameter param = parameters.get(i);
      sb.append(param.type().toLLVMIRTypeString()).append(" ").append(param.name());
      if (i < parameters.size() - 1)
        sb.append(", ");
    }
    sb.append(") {\n");
    List<BasicBlock> dumpedBlocks = new ArrayList<>();
    entryBlock.dumpIR(sb, dumpedBlocks);
    sb.append("}");
  }

  public void verify() {
    if (entryBlock == null)
      throw new IllegalStateException("Function " + name + " has no entry block");
    List<BasicBlock> verifiedBlocks = new ArrayList<>();
    entryBlock.verify(verifiedBlocks);
  }

  public record Parameter(String name, Type type) {}
}
