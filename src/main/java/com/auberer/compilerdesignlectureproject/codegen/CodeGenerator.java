package com.auberer.compilerdesignlectureproject.codegen;

import com.auberer.compilerdesignlectureproject.ast.*;
import com.auberer.compilerdesignlectureproject.codegen.instructions.Instruction;
import lombok.Getter;
import lombok.Setter;

public class CodeGenerator extends ASTVisitor<IRExprResult> {

  private final Module module; // IR module, which represents the whole program

  @Getter
  @Setter
  private BasicBlock currentBlock = null; // The basic block, which is currently the insert point for new instructions

  public CodeGenerator(String moduleName) {
    module = new Module(moduleName);
  }

  @Override
  public IRExprResult visitEntry(ASTEntryNode node) {
    // ToDo(Marc): Extend
    return null;
  }

  @Override
  public IRExprResult visitVarDecl(ASTVarDeclNode node) {
    // ToDo(Marc): Extend
    return null;
  }

  @Override
  public IRExprResult visitAssignStmt(ASTAssignStmtNode node) {
    // ToDo(Marc): Extend
    return null;
  }

  @Override
  public IRExprResult visitAssignExpr(ASTAssignExprNode node) {
    // ToDo(Marc): Extend
    return null;
  }

  @Override
  public IRExprResult visitPrintBuiltin(ASTPrintBuiltinCallNode node) {
    // ToDo(Marc): Extend
    return null;
  }

  // Team 1

  // Team 2

  // Team 3

  // Team 4

  // Team 5

  // Team 6

  // Team 7

  @Override
  public IRExprResult visitTernaryExpr(ASTTernaryExprNode node) {
    // ToDo(Marc): Extend
    return null;
  }

  @Override
  public IRExprResult visitEqualityExpr(ASTEqualityExprNode node) {
    // ToDo(Marc): Extend
    return null;
  }

  @Override
  public IRExprResult visitAdditiveExpr(ASTAdditiveExprNode node) {
    // ToDo(Marc): Extend
    return null;
  }

  @Override
  public IRExprResult visitMultiplicativeExpr(ASTMultiplicativeExprNode node) {
    // ToDo(Marc): Extend
    return null;
  }

  @Override
  public IRExprResult visitAtomicExpr(ASTAtomicExprNode node) {
    // ToDo(Marc): Extend
    return null;
  }

  /**
   * Can be used to set the instruction insert point to a specific block
   *
   * @param targetBlock Block to switch to
   */
  private void switchToBlock(BasicBlock targetBlock) {
    assert targetBlock != null;

    // Check if the old block was terminated
    assert currentBlock == null || isBlockTerminated(currentBlock);
    // Set the insert point to the new basic block
    currentBlock = targetBlock;
  }

  /**
   * Appends the given instruction to the current block
   *
   * @param instruction Instruction to append
   */
  private void pushToCurrentBlock(Instruction instruction) {
    assert instruction != null;
    assert currentBlock != null;
    assert !isBlockTerminated(currentBlock);

    // Push to the back to the current block
    currentBlock.pushInstruction(instruction);
  }

  /**
   * Checks if the given block is terminated
   *
   * @param block Block to check
   * @return True if the block is terminated
   */
  private boolean isBlockTerminated(BasicBlock block) {
    return !block.getInstructions().isEmpty() && block.getInstructions().getLast().isTerminator();
  }

  /**
   * Finalizes the IR of the current function by setting the current block to null
   */
  private void finalizeFunction() {
    assert currentBlock != null;
    assert isBlockTerminated(currentBlock);
    currentBlock = null;
  }

}
