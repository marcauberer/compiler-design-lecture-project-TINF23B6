package com.auberer.compilerdesignlectureproject.sema;

import java.util.Stack;

import com.auberer.compilerdesignlectureproject.ast.*;

public class SymbolTableBuilder extends ASTVisitor<Void> {

  private Stack<Scope> currentScope = new Stack<>();

  @Override
  public Void visitEntry(ASTEntryNode node) {
    Scope rootScope = new Scope();
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
    }

    return null;
  }

  // Team 1
  public Void visitIfBody(ASTIfBodyNode node) {
    Scope current = currentScope.peek();
    Scope ifScope = current.createChildScope();

    currentScope.push(ifScope);
    visitChildren(node);

    assert currentScope.peek() == ifScope;
    currentScope.pop();

    return null;
  }

  // Team 2
  @Override
  public Void visitWhileLoopStmt(ASTWhileLoopNode node) {

    Scope whileScope = currentScope.peek().createChildScope();
    currentScope.push(whileScope);

    visitChildren(node);

    assert currentScope.peek() == whileScope;
    currentScope.pop();

    return null;
  }


  // Team 3
  @Override
  public Void visitDoWhileLoop(ASTDoWhileLoopNode node) {

    Scope doWhileScope = currentScope.peek().createChildScope();
    currentScope.push(doWhileScope);

    visitChildren(node);

    assert currentScope.peek() == doWhileScope;
    currentScope.pop();

    return null; // Überschreibt T Rückgabewert
  }

  // Team 4
  @Override
  public Void visitFunctionDef(ASTFunctionDefNode node) {
    // may create problems due to Type (?)
    Scope scopeFct = currentScope.peek().createChildScope();
    currentScope.push(scopeFct);
    visitChildren(node);
    assert currentScope.peek() == scopeFct;
    currentScope.pop();
    String functionName = node.getIdentifier();
    SymbolTableEntry entry = currentScope.peek().lookupSymbolStrict(functionName,node);
    if(entry == null) {
      entry = currentScope.peek().insertSymbol(functionName, node);
      node.setCurrentSymbol(entry);
    }else{
      throw new SemaError(node, "Function " + functionName + " already declared");
    }
    return null;
  }

  @Override
  public Void visitParam(ASTParamNode node) {
    visitChildren(node);
    String paramName = node.getIdentifier();
    SymbolTableEntry entry = currentScope.peek().lookupSymbolStrict(paramName,node);
    if(entry == null) {
      entry = currentScope.peek().insertSymbol(paramName, node);
      node.setCurrentSymbol(entry);
    }else{
      throw new SemaError(node, "Parameter identifier" + paramName + " already used");
    }
    return null;
  }

  @Override
  public Void visitFunctionCall(ASTFunctionCallNode node) {
    visitChildren(node);
    String functionName = node.getIdentifier();
    SymbolTableEntry entry = currentScope.peek().lookupSymbol(functionName,node);
    if(entry == null) {
      throw new SemaError(node, "Function " + functionName + " not declared");
    }
    return null;
  }




  // Team 5
  @Override
  public Void visitForLoop(ASTForLoopNode node) {
    Scope scope = currentScope.peek().createChildScope();
    currentScope.push(scope);
    visitChildren(node);
    assert currentScope.peek() == scope;
    currentScope.pop();

    return null;
  }

  // Team 6
  public Void visitCaseStmt(ASTCaseStmtNode node) {
    Scope current = currentScope.peek();
    Scope newScope = current.createChildScope();
    currentScope.push(newScope);

    visitChildren(node);

    assert currentScope.peek() == newScope;
    currentScope.pop();

    return null;
  }

  public Void visitDefaultStmt(ASTDefaultStmtNode node) {
    Scope current = currentScope.peek();
    Scope newScope = current.createChildScope();
    currentScope.push(newScope);

    visitChildren(node);

    assert currentScope.peek() == newScope;
    currentScope.pop();

    return null;
  }

  // Team 7

  @Override
  public Void visitAnonymousBlockStmt(ASTAnonymousBlockStmtNode node) {
    Scope current = currentScope.peek();
    Scope newScope = current.createChildScope();
    currentScope.push(newScope);

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
