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
  public IRExprResult visitStmtLst(ASTStmtLstNode node) {
    List<ASTStmtNode> statements = node.getStmts();
    for (ASTStmtNode stmt : statements) {
      // Visit the next statement
      visit(stmt);

      // If the block is already terminated, do not visit dead code
      if (isBlockTerminated(currentBlock))
        break;
    }
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
    BasicBlock afterIfBlock = new BasicBlock("after_if");

    if (node.getElseBody() == null) {
      insertCondJump(node, node.getCondition(), ifBodyBlock, afterIfBlock);

      switchToBlock(ifBodyBlock);
      visit(node.getIfBody());
      insertJump(node, afterIfBlock);
    } else {
      BasicBlock elseBlock = new BasicBlock("if_else");
      insertCondJump(node, node.getCondition(), ifBodyBlock, elseBlock);

      switchToBlock(ifBodyBlock);
      visit(node.getIfBody());
      insertJump(node, afterIfBlock);

      switchToBlock(elseBlock);
      visit(node.getElseBody());
      insertJump(node, afterIfBlock);
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

    insertJump(node, conditionBlock);

    switchToBlock(conditionBlock);
    IRExprResult condResult = visit(node.getCondition());
    insertCondJump(node, condResult.getNode(), bodyBlock, endBlock);

    switchToBlock(bodyBlock);
    visit(node.getBody());
    insertJump(node, conditionBlock);

    switchToBlock(endBlock);
    return new IRExprResult(null, node, null);
  }

  // Team 3

  @Override
  public IRExprResult visitDoWhileLoop(ASTDoWhileLoopNode node) {
    BasicBlock bodyBlock = new BasicBlock("do_while_body");
    BasicBlock condBlock = new BasicBlock("do_while_cond");
    BasicBlock endBlock = new BasicBlock("do_while_end");

    insertJump(node, bodyBlock);

    switchToBlock(bodyBlock);
    visit(node.getBody());
    insertJump(node, condBlock);

    switchToBlock(condBlock);
    visit(node.getCondition());
    insertCondJump(node, node.getCondition(), bodyBlock, endBlock);

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
    pushToCurrentBlock(newCallInstruction);

    return new IRExprResult(new Value(node, node.getIdentifier()), node, node.getCorrespondingSymbol());
  }

  @Override
  public IRExprResult visitFunctionDef(ASTFunctionDefNode node) {
    List<Function.Parameter> paramList = new ArrayList<>();
    if (node.getParams() != null) {
      paramList = node.getParams().getParams().stream().map(param -> new Function.Parameter(param.getParamName(), param.getDataType().getType())).toList();
    }

    Function newFunction = new Function(node.getIdentifier(), node.getReturnType().getType(), paramList);
    BasicBlock entryBlock = new BasicBlock(newFunction.getName());
    module.addFunction(newFunction);
    currentBlock = entryBlock;
    newFunction.setEntryBlock(currentBlock);

    if (node.getParams() != null) {
      visit(node.getParams());
    }

    visit(node.getBody());

    finalizeFunction();
    return null;
  }

  @Override
  public IRExprResult visitParam(ASTParamNode node) {
    AllocaInstruction allocaInstruction = new AllocaInstruction(node, node.getCurrentSymbol(), node.getParamName());
    pushToCurrentBlock(allocaInstruction);

    if (node.getDefaultValue() != null) {
      visit(node.getDefaultValue());
      StoreInstruction storeInstruction = new StoreInstruction(node.getDefaultValue(), node.getCurrentSymbol());
      pushToCurrentBlock(storeInstruction);
    }

    return new IRExprResult(node.getValue(), node, node.getCurrentSymbol());
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

    insertJump(node, condBlock);

    switchToBlock(condBlock);
    IRExprResult condResult = visit(node.getCondition());
    insertCondJump(node, condResult.getValue().getNode(), bodyBlock, afterLoopBlock);

    switchToBlock(bodyBlock);
    visit(node.getBody());
    insertJump(node, incrementBlock);

    switchToBlock(incrementBlock);
    visit(node.getIncrement());
    insertJump(node, condBlock);

    switchToBlock(afterLoopBlock);
    return new IRExprResult(null, node, null);
  }

  // Team 6
  @Override
  public IRExprResult visitSwitchCaseStmt(ASTSwitchCaseStmtNode node) {
    ASTTernaryExprNode condition = node.getCondition();
    List<BasicBlock> caseBlocks = new ArrayList<>();
    List<ASTCaseStmtNode> cases = node.getCaseBlocks();

    for (int i = 0; i < cases.size(); i++) {
      BasicBlock caseBlock = new BasicBlock("case_block_" + i + "_" + cases.get(i).getCodeLoc().getLine());
      caseBlocks.add(caseBlock);
    }
    BasicBlock defaultBlock = null;
    if (node.getDefaultBlock() != null)
      defaultBlock = new BasicBlock("default_block_" + node.getDefaultBlock().getCodeLoc().getLine());
    BasicBlock endBlock = new BasicBlock("end_switch_" + node.getCodeLoc().getLine());

    SwitchInstruction switchInstruction = new SwitchInstruction(node, condition, caseBlocks, cases, defaultBlock);
    pushToCurrentBlock(switchInstruction);

    for (int i = 0; i < cases.size(); i++) {
      switchToBlock(caseBlocks.get(i));
      visitChildren(cases.get(i));
      insertJump(node, endBlock);
    }

    if (node.getDefaultBlock() != null) {
      switchToBlock(defaultBlock);
      visitChildren(node.getDefaultBlock());
      insertJump(node, endBlock);
    }

    switchToBlock(endBlock);

    return new IRExprResult(null, node, null);
  }

  // Team 7

  @Override
  public IRExprResult visitAnonymousBlockStmt(ASTAnonymousBlockStmtNode node) {
    BasicBlock newBlock = new BasicBlock("anonymous_block_" + node.getCodeLoc().getLine());
    insertJump(node, newBlock);

    switchToBlock(newBlock);

    visitChildren(node);

    BasicBlock afterBlock = new BasicBlock("after_" + newBlock.getLabel());
    insertJump(node, afterBlock);

    switchToBlock(afterBlock);
    return new IRExprResult(null, node, null);
  }

  @Override
  public IRExprResult visitTernaryExpr(ASTTernaryExprNode node) {
    BasicBlock trueBlock = new BasicBlock("ternary_true");
    BasicBlock falseBlock = new BasicBlock("ternary_false");
    BasicBlock exitBlock = new BasicBlock("ternary_exit");

    ASTEqualityExprNode condition = node.getCondition();
    IRExprResult result = visit(condition);

    if (node.isExpanded()) {
      insertCondJump(node, condition, trueBlock, falseBlock);

      switchToBlock(trueBlock);
      ASTEqualityExprNode trueBranch = node.getTrueBranch();
      visit(trueBranch);
      insertJump(node, exitBlock);

      switchToBlock(falseBlock);
      ASTEqualityExprNode falseBranch = node.getFalseBranch();
      visit(falseBranch);
      insertJump(node, exitBlock);

      switchToBlock(exitBlock);
      SelectInstruction selectInstruction = new SelectInstruction(node, condition, trueBranch, falseBranch);
      pushToCurrentBlock(selectInstruction);
    } else {
      node.setValue(result.getValue());
    }

    return new IRExprResult(node.getValue(), node, null);
  }

  @Override
  public IRExprResult visitEqualityExpr(ASTEqualityExprNode node) {
    List<ASTAdditiveExprNode> operands = node.getOperands();
    IRExprResult result = visit(operands.getFirst());
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
    } else {
      node.setValue(result.getValue());
    }
    return new IRExprResult(node.getValue(), node, null);
  }

  @Override
  public IRExprResult visitAdditiveExpr(ASTAdditiveExprNode node) {
    List<ASTMultiplicativeExprNode> operandsList = node.getOperands();
    if (operandsList.size() == 1) {
      IRExprResult result = visit(operandsList.getFirst());
      node.setValue(result.getValue());
      return result;
    }
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
    if (operandsList.size() == 1) {
      IRExprResult result = visit(operandsList.getFirst());
      node.setValue(result.getValue());
      return result;
    }
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
   * Insert unconditional jump into the current block
   *
   * @param node Issuing AST node
   * @param targetBlock BasicBlock where to jump to
   */
  private void insertJump(ASTNode node, BasicBlock targetBlock) {
    if (isBlockTerminated(currentBlock))
      return;
    pushToCurrentBlock(new JumpInstruction(node, targetBlock));
  }

  /**
   * Insert conditional jump into the current block
   *
   * @param node Issuing AST node
   * @param condition AST node of the condition
   * @param targetBlockTrue BasicBlock where to jump to if the condition is true
   * @param targetBlockFalse BasicBlock where to jump to if the condition is false
   */
  private void insertCondJump(ASTNode node, ASTNode condition, BasicBlock targetBlockTrue, BasicBlock targetBlockFalse) {
    if (isBlockTerminated(currentBlock))
      return;
    pushToCurrentBlock(new CondJumpInstruction(node, condition, targetBlockTrue, targetBlockFalse));
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
    currentBlock = null;
  }

}
