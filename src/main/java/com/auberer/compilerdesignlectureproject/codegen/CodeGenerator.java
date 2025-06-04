package com.auberer.compilerdesignlectureproject.codegen;

import com.auberer.compilerdesignlectureproject.ast.*;
import com.auberer.compilerdesignlectureproject.codegen.instructions.*;
import com.auberer.compilerdesignlectureproject.interpreter.Value;
import com.auberer.compilerdesignlectureproject.sema.Type;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
    AllocaInstruction allocaInstruction = new AllocaInstruction(node, node.getCurrentSymbol(), node.getVariableName());
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

  @Override
  public IRExprResult visitLiteral(ASTLiteralNode node) {
    Value result = new Value(node);

    switch (node.getLiteralType()) {
      case INT -> {
        int value = Integer.parseInt(node.getLiteralValue());
        result.setIntValue(value);
      }
      case DOUBLE -> {
        double value = Double.parseDouble(node.getLiteralValue());
        result.setDoubleValue(value);
      }
      case STRING -> {
        String value = node.getLiteralValue();
        result.setStringValue(value);
      }
      case BOOL -> {
        boolean value = Boolean.parseBoolean(node.getLiteralValue());
        result.setBoolValue(value);
      }
    }

    node.setValue(result);
    return new IRExprResult(result, node, null);
  }

  // Team 1

  public IRExprResult visitIfStmt(ASTIfStmtNode node) {
    BasicBlock ifBodyBlock = new BasicBlock("if_body");
    BasicBlock elseBlock = new BasicBlock("if_else");
    BasicBlock afterIfBlock = new BasicBlock("after_if");

    CondJumpInstruction ifJumpInstruction = new CondJumpInstruction(node, node.getCondition(), ifBodyBlock, elseBlock);
    pushToCurrentBlock(ifJumpInstruction);

    switchToBlock(ifBodyBlock);
    visit(node.getIfBody());
    pushToCurrentBlock(new JumpInstruction(node, afterIfBlock));

    switchToBlock(elseBlock);
    if (node.getElseBody() != null) {
      visit(node.getElseBody());
      pushToCurrentBlock(new JumpInstruction(node, afterIfBlock));
    }

    switchToBlock(afterIfBlock);

    return new IRExprResult(null, node, null);
  }

  // Team 2

  @Override
  public IRExprResult visitWhileLoopStmt(ASTWhileLoopNode node) {
    BasicBlock conditionBlock = new BasicBlock("while_cond");
    BasicBlock bodyBlock = new BasicBlock("while_body");
    BasicBlock endBlock = new BasicBlock("while_end");

    pushToCurrentBlock(new JumpInstruction(node, conditionBlock));

    switchToBlock(conditionBlock);
    IRExprResult condResult = visit(node.getCondition());
    pushToCurrentBlock(new CondJumpInstruction(node, condResult.getValue().getNode(), bodyBlock, endBlock));

    switchToBlock(bodyBlock);
    visit(node.getBody());
    pushToCurrentBlock(new JumpInstruction(node, conditionBlock));

    switchToBlock(endBlock);
    return new IRExprResult(null, node, null);
  }

  // Team 3

  @Override
  public IRExprResult visitDoWhileLoop(ASTDoWhileLoopNode node) {
    BasicBlock bodyBlock = new BasicBlock("do_while_body");
    BasicBlock endBlock = new BasicBlock("do_while_end");

    JumpInstruction entryJump = new JumpInstruction(node, bodyBlock);
    pushToCurrentBlock(entryJump);
    switchToBlock(bodyBlock);

    visit(node.getBody());
    visit(node.getCondition());

    CondJumpInstruction condJumpInstruction = new CondJumpInstruction(node, node.getCondition(), bodyBlock, endBlock);
    pushToCurrentBlock(condJumpInstruction);

    switchToBlock(endBlock);
    return new IRExprResult(null, node, null);
  }

  // Team 4

  @Override
  public IRExprResult visitFunctionCall(ASTFunctionCallNode node) {
    assert node.getCorrespondingSymbol().getDeclNode().getClass() == ASTFunctionDefNode.class;
    ASTFunctionDefNode functionDef = (ASTFunctionDefNode) node.getCorrespondingSymbol().getDeclNode();
    List<Type> paramTypes = new ArrayList<>();
    if (functionDef.getParams() != null) {
      paramTypes = functionDef.getParams().getParams().stream().map(p -> p.getDataType().getType()).toList();
    }

    Function function = module.getFunction(node.getIdentifier(), paramTypes);

    assert function != null;
    CallInstruction newCallInstruction = new CallInstruction(node, function, functionDef.getParams());

    currentBlock.pushInstruction(newCallInstruction);

    return new IRExprResult(new Value(node, node.getIdentifier()), node, node.getCorrespondingSymbol());
  }

  @Override
  public IRExprResult visitFunctionDef(ASTFunctionDefNode node) {
    List<Function.Parameter> paramList = new ArrayList<>();

    if (node.getParams() != null) {
      paramList = node.getParams().getParams().stream().map(param -> new Function.Parameter(param.getIdentifier(), param.getDataType().getType())).toList();
    }

    Function newFunction = new Function(node.getIdentifier(), node.getReturnType().getType(), paramList);
    BasicBlock entryBlock = new BasicBlock(newFunction.getName());
    module.addFunction(newFunction);
    currentBlock = entryBlock;
    newFunction.setEntryBlock(currentBlock);
    visit(node.getBody());
    currentBlock = null;

    return null;
  }

  @Override
  public IRExprResult visitReturnStmt(ASTReturnStmtNode node) {
    visit(node.getReturnExpr());
    ReturnInstruction newReturnInstruction = new ReturnInstruction(node);
    pushToCurrentBlock(newReturnInstruction);
    return null;
  }

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
  @Override
  public IRExprResult visitSwitchCaseStmt(ASTSwitchCaseStmtNode node) {
    Value value = node.getCondition().getValue();
    List<BasicBlock> caseBlocks = new ArrayList<>();
    List<ASTCaseStmtNode> cases = node.getCaseBlocks();
    BasicBlock defaultBlock = node.getDefaultBlock() != null ?
            new BasicBlock("default_block_" + node.getDefaultBlock().getCodeLoc().getLine()) :
            null;
    BasicBlock endBlock = new BasicBlock("end_switch_" + node.getCodeLoc().getLine());

    for (int i = 0; i < cases.size(); i++) {
      BasicBlock caseBlock = new BasicBlock("case_block_" + i + "_" + cases.get(i).getCodeLoc().getLine());
      caseBlocks.add(caseBlock);
    }

    SwitchInstruction switchInstruction = new SwitchInstruction(node, value, caseBlocks, cases, defaultBlock);

    pushToCurrentBlock(switchInstruction);

    for (int i = 0; i < cases.size(); i++) {
      switchToBlock(caseBlocks.get(i));
      visitChildren(cases.get(i));
      pushToCurrentBlock(new JumpInstruction(node, endBlock));
    }

    if (node.getDefaultBlock() != null) {
      switchToBlock(defaultBlock);      
      visitChildren(node.getDefaultBlock());
      pushToCurrentBlock(new JumpInstruction(node, endBlock));
    }

    pushToCurrentBlock(new JumpInstruction(node, endBlock));
    switchToBlock(endBlock);

    return new IRExprResult(null, node, null);
  }

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
    BasicBlock trueBlock = new BasicBlock("ternary_true");
    BasicBlock falseBlock = new BasicBlock("ternary_false");
    BasicBlock exitBlock = new BasicBlock("ternary_exit");

    ASTEqualityExprNode condition = node.getCondition();
    visit(condition);

    if (node.isExpanded()) {
      CondJumpInstruction condJump = new CondJumpInstruction(node, condition, trueBlock, falseBlock);
      pushToCurrentBlock(condJump);

      switchToBlock(trueBlock);
      ASTEqualityExprNode trueBranch = node.getTrueBranch();
      visit(trueBranch);
      pushToCurrentBlock(new JumpInstruction(node, exitBlock));

      switchToBlock(falseBlock);
      ASTEqualityExprNode falseBranch = node.getFalseBranch();
      visit(falseBranch);
      pushToCurrentBlock(new JumpInstruction(node, exitBlock));

      switchToBlock(exitBlock);
      SelectInstruction selectInstruction = new SelectInstruction(node, condition, trueBranch, falseBranch);
      pushToCurrentBlock(selectInstruction);
    }

    return new IRExprResult(node.getValue(), node, null);
  }

  @Override
  public IRExprResult visitEqualityExpr(ASTEqualityExprNode node) {
    List<ASTAdditiveExprNode> operands = node.getOperands();
    visit(operands.getFirst());
    if (operands.size() == 2) {
      visit(operands.getLast());
      if (node.getOp() == ASTEqualityExprNode.EqualityOp.EQ) {
        EqualInstruction eqInstruction = new EqualInstruction(node, operands.getFirst(), operands.getLast());
        pushToCurrentBlock(eqInstruction);
      } else if (node.getOp() == ASTEqualityExprNode.EqualityOp.NEQ) {
        NotEqualInstruction neqInstruction = new NotEqualInstruction(node, operands.getFirst(), operands.getLast());
        pushToCurrentBlock(neqInstruction);
      } else {
        assert false : "Unexpected equality operator";
      }
    }

    return new IRExprResult(node.getValue(), node, null);
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
      } else {
        assert false : "Unexpected additive operator";
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
      } else {
        assert false : "Unexpected multiplicative operator";
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
