package com.auberer.compilerdesignlectureproject.codegen;

import com.auberer.compilerdesignlectureproject.ast.*;
import com.auberer.compilerdesignlectureproject.codegen.instructions.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CodeGenerator extends ASTVisitor<IRExprResult> {

  @Getter
  private final Module module; // IR module, which represents the whole program

  @Getter
  @Setter
  private BasicBlock currentBlock = null; // The basic block, which is currently the insert point for new instructions

  public CodeGenerator(String moduleName) {
    module = new Module(moduleName);
  }

  @Override
  public IRExprResult visitEntry(ASTEntryNode node) {
    // We did not enter a function yet, so the current block has to be null
    assert currentBlock == null;

    // Visit children
    visitChildren(node);

    assert currentBlock == null;
    return null;
  }

  @Override
  public IRExprResult visitVarDecl(ASTVarDeclNode node) {
    AllocaInstruction allocaInstruction = new AllocaInstruction(node, node.getCurrentSymbol());
    pushToCurrentBlock(allocaInstruction);

    visit(node.getInitExpr());
    StoreInstruction storeInstruction = new StoreInstruction(node.getInitExpr(), node.getCurrentSymbol());
    pushToCurrentBlock(storeInstruction);

    return new IRExprResult(node.getValue(), node, node.getCurrentSymbol());
  }

  @Override
  public IRExprResult visitAssignExpr(ASTAssignExprNode node) {
    visit(node.getRhs());

    if (node.isAssignment()) {
      StoreInstruction storeInstruction = new StoreInstruction(node.getRhs(), node.getCurrentSymbol());
      pushToCurrentBlock(storeInstruction);
      return new IRExprResult(node.getCurrentSymbol().getValue(), node, node.getCurrentSymbol());
    }

    return new IRExprResult(node.getRhs().getValue(), node, null);
  }

  @Override
  public IRExprResult visitPrintBuiltin(ASTPrintBuiltinCallNode node) {
    // Visit expression to print
    visit(node.getExpr());

    // Create a print instruction and append it to the current BasicBlock
    PrintInstruction printInstruction = new PrintInstruction(node);
    pushToCurrentBlock(printInstruction);

    return new IRExprResult(null, node, null);
  }

  // Team 1

  // Team 2

  // Team 3

    @Override
    public IRExprResult visitDoWhileLoop(ASTDoWhileLoopNode node){
      BasicBlock bodyBlock = new BasicBlock("do_while_body");
      BasicBlock endBlock = new BasicBlock("do_while_end");

      JumpInstruction entryJump = new JumpInstruction(node, bodyBlock);
      pushToCurrentBlock(entryJump);
      switchToBlock(bodyBlock);

      visit(node.getBody());
      visit(node.getCondition());

      CondJumpInstruction condJumpInstruction = new CondJumpInstruction(node,node.getCondition(), bodyBlock, endBlock);
      pushToCurrentBlock(condJumpInstruction);

      switchToBlock(endBlock);
      return new IRExprResult(null, node, null);
    }

  // Team 4

  // Team 5
  @Override
  public IRExprResult visitForLoop(ASTForLoopNode node) {
    visit(node.getInitialization());
    BasicBlock condBlock = new BasicBlock("for_cond");
    BasicBlock bodyBlock = new BasicBlock("for_body");
    BasicBlock incrementBlock = new BasicBlock("for_increment");
    BasicBlock afterLoopBlock = new BasicBlock("after_for");


    pushToCurrentBlock(new JumpInstruction(node, condBlock));


    switchToBlock(condBlock);
    IRExprResult condResult = visit(node.getCondition());
    pushToCurrentBlock(new CondJumpInstruction(node, condResult.getValue().getNode(), bodyBlock, afterLoopBlock));


    switchToBlock(bodyBlock);
    visit(node.getBody());
    pushToCurrentBlock(new JumpInstruction(node, incrementBlock));


    switchToBlock(incrementBlock);
    visit(node.getIncrement());
    pushToCurrentBlock(new JumpInstruction(node, condBlock));

    switchToBlock(afterLoopBlock);

    return new IRExprResult(null, node, null);
  }
  // Team 6

  // Team 7

  @Override
  public IRExprResult visitAnonymousBlockStmt(ASTAnonymousBlockStmtNode node) {
    BasicBlock newBlock = new BasicBlock("anonymous_block_" + node.getCodeLoc().getLine());
    JumpInstruction jumpInAnonymousBlockInstruction = new JumpInstruction(node, newBlock);
    pushToCurrentBlock(jumpInAnonymousBlockInstruction);

    switchToBlock(newBlock);

    visitChildren(node);

    BasicBlock afterBlock = new BasicBlock("after_" + newBlock.getLabel());
    JumpInstruction jumpOutInstruction = new JumpInstruction(node, afterBlock);
    pushToCurrentBlock(jumpOutInstruction);

    switchToBlock(afterBlock);
    return new IRExprResult(null, node, null);
  }

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
    List<ASTMultiplicativeExprNode> operandsList = node.getOperands();
    if (operandsList.size() == 1)
      return visit(operandsList.getFirst());
    List<ASTAdditiveExprNode.AdditiveOp> operatorsList = node.getOpList();

    // Visit the first operand
    visit(operandsList.getFirst());

    for (int i = 1; i < operandsList.size(); i++) {
      visit(operandsList.get(i));
      if (operatorsList.get(i - 1) == ASTAdditiveExprNode.AdditiveOp.PLUS) {
        PlusInstruction plusInstruction = new PlusInstruction(node, operandsList.get(i - 1), operandsList.get(i));
        pushToCurrentBlock(plusInstruction);
      } else if (operatorsList.get(i - 1) == ASTAdditiveExprNode.AdditiveOp.MINUS) {
        MinusInstruction minusInstruction = new MinusInstruction(node, operandsList.get(i - 1), operandsList.get(i));
        pushToCurrentBlock(minusInstruction);
      }
    }

    return new IRExprResult(node.getValue(), node, null);
  }

  @Override
  public IRExprResult visitMultiplicativeExpr(ASTMultiplicativeExprNode node) {
    List<ASTAtomicExprNode> operandsList = node.getOperands();
    if (operandsList.size() == 1)
      return visit(operandsList.getFirst());
    List<ASTMultiplicativeExprNode.MultiplicativeOp> operatorsList = node.getOpList();

    // Visit the first operand
    visit(operandsList.getFirst());

    for (int i = 1; i < operandsList.size(); i++) {
      visit(operandsList.get(i));
      if (operatorsList.get(i - 1) == ASTMultiplicativeExprNode.MultiplicativeOp.MUL) {
        MulInstruction multiplicativeInstruction = new MulInstruction(node, operandsList.get(i - 1), operandsList.get(i));
        pushToCurrentBlock(multiplicativeInstruction);
      } else if (operatorsList.get(i - 1) == ASTMultiplicativeExprNode.MultiplicativeOp.DIV) {
        DivInstruction divisionInstruction = new DivInstruction(node, operandsList.get(i - 1), operandsList.get(i));
        pushToCurrentBlock(divisionInstruction);
      }
    }

    return new IRExprResult(node.getValue(), node, null);
  }

  @Override
  public IRExprResult visitAtomicExpr(ASTAtomicExprNode node) {
    if (node.getCurrentSymbol() != null) {
      LoadInstruction loadInstruction = new LoadInstruction(node, node.getCurrentSymbol());
      pushToCurrentBlock(loadInstruction);
      return new IRExprResult(node.getCurrentSymbol().getValue(), node, node.getCurrentSymbol());
    }

    if (node.getFunctionCall() != null) {
      IRExprResult result = visit(node.getFunctionCall());
      node.setValue(result.getValue());
      return new IRExprResult(node.getFunctionCall().getValue(), node, null);
    }

    if (node.getPrintBuiltin() != null) {
      IRExprResult result = visit(node.getPrintBuiltin());
      node.setValue(result.getValue());
      return new IRExprResult(node.getPrintBuiltin().getValue(), node, null);
    }

    if (node.getTernaryExpr() != null) {
      IRExprResult result = visit(node.getTernaryExpr());
      node.setValue(result.getValue());
      return new IRExprResult(node.getTernaryExpr().getValue(), node, null);
    }

    if (node.getLiteral() != null) {
      IRExprResult result = visit(node.getLiteral());
      node.setValue(result.getValue());
      return new IRExprResult(node.getLiteral().getValue(), node, null);
    }

    assert false : "Unexpected AST node type";
    return new IRExprResult(null, node, null);
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
