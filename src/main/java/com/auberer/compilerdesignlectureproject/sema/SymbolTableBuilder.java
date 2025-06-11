package com.auberer.compilerdesignlectureproject.sema;

import com.auberer.compilerdesignlectureproject.ast.*;

public class SymbolTableBuilder extends ASTSemaVisitor<Void> {

  ReturnStateMachine returnStatemachine = new ReturnStateMachine();

  // global root scope (enable fctCalls to lookupSymbolStrict independent of the Scope degree(depth))
  private Scope rootScope;

  @Override
  public Void visitEntry(ASTEntryNode node) {

    returnStatemachine.init();
    rootScope = new Scope();
    node.setRootScope(rootScope);

    assert currentScope.isEmpty();
    currentScope.push(rootScope);

    visitChildren(node);

    assert currentScope.peek() == rootScope;
    currentScope.pop();
    assert currentScope.isEmpty();

    return null;
  }

  @Override
  public Void visitVarDecl(ASTVarDeclNode node) {
    visitChildren(node);

    String variableName = node.getVariableName();
    SymbolTableEntry entry = currentScope.peek().lookupSymbolStrict(variableName, node);
    if (entry == null) {
      entry = currentScope.peek().insertSymbol(variableName, node);
      node.setCurrentSymbol(entry);
    } else {
      throw new SemaError(node, "Variable " + variableName + " already declared");
    }

    return null;
  }

  @Override
  public Void visitAssignExpr(ASTAssignExprNode node) {
    visitChildren(node);

    if (node.isAssignment()) {
      String variableName = node.getVariableName();
      SymbolTableEntry entry = currentScope.peek().lookupSymbol(variableName, node);
      if (entry == null)
        throw new SemaError(node, "Variable " + variableName + " not declared");
      node.setCurrentSymbol(entry);
    }

    return null;
  }

  // Team 1
  public Void visitIfBody(ASTIfBodyNode node) {
    Scope current = currentScope.peek();
    Scope ifScope = current.createChildScope();
    node.setScope(ifScope);

    currentScope.push(ifScope);
    visitChildren(node);
    assert currentScope.peek() == ifScope;

    currentScope.pop();
    if (!currentScope.peek().getIsInALoop()) returnStatemachine.processInput('b');

    return null;
  }

  @Override
  public Void visitIfStmt(ASTIfStmtNode node) {
    if (!currentScope.peek().getIsInALoop()) returnStatemachine.processInput('i');
    super.visitIfStmt(node);
    return null;
  }

  @Override
  public Void visitElseStmt(ASTElseStmtNode node) {
    if (!currentScope.peek().getIsInALoop()) returnStatemachine.processInput('e');
    visit(node.getBody());
    return null;
  }

  // Team 2
  @Override
  public Void visitWhileLoopStmt(ASTWhileLoopNode node) {

    Scope whileScope = currentScope.peek().createChildScope(true);
    node.setScope(whileScope);
    currentScope.push(whileScope);

    visitChildren(node);

    assert currentScope.peek() == whileScope;
    currentScope.pop();

    return null;
  }


  // Team 3
  @Override
  public Void visitDoWhileLoop(ASTDoWhileLoopNode node) {

    Scope doWhileScope = currentScope.peek().createChildScope(true);
    currentScope.push(doWhileScope);

    node.setScope(doWhileScope);
    visitChildren(node);

    assert currentScope.peek() == doWhileScope;
    currentScope.pop();

    return null; // Überschreibt T Rückgabewert
  }

  // Team 4
  @Override
  public Void visitFunctionDef(ASTFunctionDefNode node) {
    returnStatemachine.reset();
    String functionName = node.getIdentifier();
    SymbolTableEntry entry = currentScope.peek().lookupSymbolStrict(functionName, node);
    if (entry == null) {
      entry = currentScope.peek().insertSymbol(functionName, node);
      node.setCurrentSymbol(entry);
    } else {
      throw new SemaError(node, "Function " + functionName + " already declared");
    }
    // may create problems due to Type (?)
    Scope scopeFct = currentScope.peek().createChildScope();
    currentScope.push(scopeFct);
    visitChildren(node);
    assert currentScope.peek() == scopeFct;
    currentScope.pop();

    if (!returnStatemachine.isInAcceptState()) {
      throw new RuntimeException("Return is missing State:" + returnStatemachine.getCurrentState().getName());
    }
    return null;
  }


  @Override
  public Void visitParam(ASTParamNode node) {
    visitChildren(node);
    String paramName = node.getParamName();
    SymbolTableEntry entry = currentScope.peek().lookupSymbolStrict(paramName, node);
    if (entry == null) {
      entry = currentScope.peek().insertSymbol(paramName, node);
      entry.setParam(true);
      node.setCurrentSymbol(entry);
    } else {
      throw new SemaError(node, "Parameter identifier" + paramName + " already used");
    }
    return null;
  }

  @Override
  public Void visitFunctionCall(ASTFunctionCallNode node) {

    visitChildren(node);
    String functionName = node.getIdentifier();
    SymbolTableEntry entry = rootScope.lookupSymbol(functionName, node);
    if (entry == null) {
      throw new SemaError(node, "function " + functionName + " not declared");
    } else {
      node.setCorrespondingSymbol(entry);
    }

    return null;
  }

  @Override
  public Void visitReturnStmt(ASTReturnStmtNode node) {
    if (!currentScope.peek().getIsInALoop()) returnStatemachine.processInput('r');
    super.visitReturnStmt(node);
    return null;
  }

  // Team 5
  @Override
  public Void visitForLoop(ASTForLoopNode node) {
    Scope scope = currentScope.peek().createChildScope(true);
    currentScope.push(scope);
    node.setScope(scope);
    visitChildren(node);
    assert currentScope.peek() == scope;
    currentScope.pop();

    return null;
  }

  // Team 6

  @Override
  public Void visitSwitchCaseStmt(ASTSwitchCaseStmtNode node) {
    Boolean isInALoop = currentScope.peek().getIsInALoop();
    if (!isInALoop) {
      returnStatemachine.processInput('i');
      returnStatemachine.processInput('r');
      returnStatemachine.processInput('b');
    }
    super.visitSwitchCaseStmt(node);
    return null;
  }

  public Void visitCaseStmt(ASTCaseStmtNode node) {
    Boolean isInALoop = currentScope.peek().getIsInALoop();
    if (!isInALoop) {
      returnStatemachine.processInput('e');
      returnStatemachine.processInput('i');
    }
    Scope current = currentScope.peek();
    Scope newScope = current.createChildScope();
    currentScope.push(newScope);

    visitChildren(node);

    assert currentScope.peek() == newScope;
    currentScope.pop();
    if (!currentScope.peek().getIsInALoop()) returnStatemachine.processInput('b');
    return null;
  }

  public Void visitDefaultStmt(ASTDefaultStmtNode node) {
    if (!currentScope.peek().getIsInALoop()) returnStatemachine.processInput('e');

    Scope current = currentScope.peek();
    Scope newScope = current.createChildScope();
    currentScope.push(newScope);

    visitChildren(node);

    assert currentScope.peek() == newScope;
    currentScope.pop();

    if (!currentScope.peek().getIsInALoop()) returnStatemachine.processInput('b');
    return null;
  }

  // Team 7

  @Override
  public Void visitAnonymousBlockStmt(ASTAnonymousBlockStmtNode node) {
    Scope current = currentScope.peek();
    Scope newScope = current.createChildScope();
    currentScope.push(newScope);

    node.setScope(newScope);

    visitChildren(node);

    assert currentScope.peek() == newScope;
    currentScope.pop();

    return null;
  }

  @Override
  public Void visitAtomicExpr(ASTAtomicExprNode node) {
    visitChildren(node);

    String variableName = node.getVariableName();
    if (variableName != null) {
      SymbolTableEntry entry = currentScope.peek().lookupSymbol(variableName, node);
      if (entry == null)
        throw new SemaError(node, "Variable " + variableName + " not declared");
      node.setCurrentSymbol(entry);
    }

    return null;
  }

}
