package com.auberer.compilerdesignlectureproject.ast;

public class ASTVisitor<T> {

  // Generic visit methods

  public T visit(ASTNode node) {
    return node.accept(this);
  }

  public T visitChildren(ASTNode node) {
    for (ASTNode child : node.getChildren())
      child.accept(this);
    return null;
  }

  // Node-specific visit methods

  public T visitEntry(ASTEntryNode node) {
    return visitChildren(node);
  }

  public T visitPrintBuiltin(ASTPrintBuiltinCallNode node) {
    return visitChildren(node);
  }

  T visistStmtLst(ASTStmtLstNode node) {
    return visitChildren(node);
  }

  T visistStmt(ASTStmtNode node) {
    return visitChildren(node);
  }

  public T visitVarDecl(ASTVarDeclNode node) {
    return visitChildren(node);
  }

  public T visitAssignExpr(ASTAssignExprNode node) {
    return visitChildren(node);
  }

  public T visitAssignStmt(ASTAssignStmtNode node) {
    return visitChildren(node);
  }

  public T visitLiteral(ASTLiteralNode node) {
    return visitChildren(node);
  }

  public T visitType(ASTTypeNode node) {
    return visitChildren(node);
  }

  public T visitTernaryExpr(ASTTernaryExprNode node) {
    return visitChildren(node);
  }

  public T visitEqualityExpr(ASTEqualityExprNode node) {
    return visitChildren(node);
  }

  public T visitAdditiveExpr(ASTAdditiveExprNode node) {
    return visitChildren(node);
  }

  public T visitMultiplicativeExpr(ASTMultiplicativeExprNode node) {
    return visitChildren(node);
  }

  public T visitAtomicExpr(ASTAtomicExprNode node) {
    return visitChildren(node);
  }

  public T visitDoWhileLoop(ASTDoWhileLoopNode node) {
    return visitChildren(node);
  }

  public T visitAnonymousBlockStmt(ASTAnonymousBlockStmtNode node) {
    return visitChildren(node);
  }

  public T visitIfStmt(ASTIfStmtNode node) {
    return visitChildren(node);
  }

  public T visitIfBody(ASTIfBodyNode node) {
    return visitChildren(node);
  }

  public T visitElseStmt(ASTElseStmtNode node) {
    return visitChildren(node);
  }

  public T visitWhileLoopStmt(ASTWhileLoopNode node) {
    return visitChildren(node);
  }

  public T visitForLoop(ASTForLoopNode node) {
    return visitChildren(node);
  }

  public T visitSwitchCaseStmt(ASTSwitchCaseStmtNode node) {
    return visitChildren(node);
  }

  public T visitCaseStmt(ASTCaseStmtNode node) {
    return visitChildren(node);
  }

  public T visitDefaultStmt(ASTDefaultStmtNode node) {
    return visitChildren(node);
  }

  public T visitReturnStmt(ASTReturnStmtNode node) {
    return visitChildren(node);
  }

  public T visitFunctionDef(ASTFunctionDefNode node) {
    return visitChildren(node);
  }

  public T visitFunctionCall(ASTFunctionCallNode node) {
    return visitChildren(node);
  }

  public T visitParamLst(ASTParamLstNode node) {
    return visitChildren(node);
  }

  public T visitParam(ASTParamNode node) {
    return visitChildren(node);
  }

  public T visitArgLst(ASTArgLstNode node) {
    return visitChildren(node);
  }
}
