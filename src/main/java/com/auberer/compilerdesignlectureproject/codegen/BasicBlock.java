package com.auberer.compilerdesignlectureproject.codegen;

import com.auberer.compilerdesignlectureproject.codegen.instructions.CondJumpInstruction;
import com.auberer.compilerdesignlectureproject.codegen.instructions.Instruction;
import com.auberer.compilerdesignlectureproject.codegen.instructions.JumpInstruction;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BasicBlock implements IDumpable {

  private final String label;
  private final List<Instruction> instructions = new ArrayList<>();

  public BasicBlock(String label) {
    this.label = label;
  }

  public void pushInstruction(Instruction instruction) {
    instructions.add(instruction);
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append(label).append(":\n");
    for (Instruction instruction : instructions) {
      sb.append("  ");
      instruction.dumpIR(sb);
      sb.append("\n");
    }
  }

  /**
   * Only dump the IR of this block if it has not been dumped yet.
   *
   * @param sb                  StringBuilder to append the IR to
   * @param alreadyDumpedBlocks List of blocks that have already been dumped
   */
  public void dumpIR(StringBuilder sb, List<BasicBlock> alreadyDumpedBlocks) {
    // Check if this block has already been dumped
    if (alreadyDumpedBlocks.contains(this) || instructions.isEmpty())
      return;
    alreadyDumpedBlocks.add(this);

    // Dump the IR of this block
    dumpIR(sb);
    
    // Dump the IR of the successor blocks
    for (BasicBlock successor : getSuccessors())
      successor.dumpIR(sb, alreadyDumpedBlocks);
  }

  public void verify(List<BasicBlock> alreadyVerifiedBlocks) {
    // Check if this block has already been verified
    if (alreadyVerifiedBlocks.contains(this))
      return;
    alreadyVerifiedBlocks.add(this);

    // BasicBlock may not be empty
    if (instructions.isEmpty())
      throw new IllegalStateException("BasicBlock " + label + " has no instructions");

    // Check for forbidden terminator instruction in the middle of the block
    for (int i = 0; i < instructions.size() - 1; i++)
      if (instructions.get(i).isTerminator())
        throw new IllegalStateException("BasicBlock " + label + " contains a terminator instruction in the middle");

    // The last instruction must be a terminator instruction
    if (!instructions.getLast().isTerminator())
      throw new IllegalStateException("BasicBlock " + label + " does not end with a terminator instruction");

    // Continue with successor blocks, if any
    for (BasicBlock successor : getSuccessors())
      successor.verify(alreadyVerifiedBlocks);
  }

  public List<BasicBlock> getSuccessors() {
    List<BasicBlock> successors = new ArrayList<>();
    Instruction lastInstruction = instructions.getLast();
    if (lastInstruction instanceof CondJumpInstruction condJumpInstruction) {
      successors.add(condJumpInstruction.getTrueTargetBlock());
      successors.add(condJumpInstruction.getFalseTargetBlock());
    } else if (lastInstruction instanceof JumpInstruction jumpInstruction) {
      successors.add(jumpInstruction.getTargetBlock());
    }
    return successors;
  }

}
