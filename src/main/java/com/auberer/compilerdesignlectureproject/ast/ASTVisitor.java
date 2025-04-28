package com.auberer.compilerdesignlectureproject.ast;

public class ASTVisitor<T> {

  // Generic visit methods

  T visit(ASTNode node) {
    return node.accept(this);
  }

  T visitChildren(ASTNode node) {
    for (ASTNode child : node.getChildren())
      child.accept(this);
    return null;
  }

  // Node-specific visit methods

  T visitEntry(ASTEntryNode node) {
    return visitChildren(node);
  }

  T visitPrintBuiltin(ASTPrintBuiltinCallNode node) {
    return visitChildren(node);
  }

  T visistStmtLst(ASTStmtLstNode node) {
    return visitChildren(node);
  }

  T visistStmt(ASTStmtNode node) {
    return visitChildren(node);
  }

  T visitVarDecl(ASTVarDeclNode node) {
    return visitChildren(node);
  }

  T visitAssignExpr(ASTAssignExprNode node) {
    return visitChildren(node);
  }

  T visitAssignStmt(ASTAssignStmtNode node) {
    return visitChildren(node);
  }

  T visitLiteral(ASTLiteralNode node) {
    return visitChildren(node);
  }

  T visitType(ASTTypeNode node) {
    return visitChildren(node);
  }

  T visitTernaryExpr(ASTTernaryExprNode node) {
    return visitChildren(node);
  }

  T visitEqualityExpr(ASTEqualityExprNode node) {
    return visitChildren(node);
  }

  T visitAdditiveExpr(ASTAdditiveExprNode node) {
    return visitChildren(node);
  }

  T visitMultiplicativeExpr(ASTMultiplicativeExprNode node) {
    return visitChildren(node);
  }

  T visitAtomicExpr(ASTAtomicExprNode node) {
    return visitChildren(node);
  }
  T visitDoWhileLoop(ASTDoWhileLoopNode node) {
    return visitChildren(node);
  }

  // ToDo(Students): Insert your visit methods here

  T visitAnonymousBlockStmt(ASTAnonymousBlockStmtNode node) {
    return visitChildren(node);
  }

  T visitWhileLoopStmt(ASTWhileLoopStmtNode node) {
    return visitChildren(node);
  }

  T visitForLoop(ASTForLoopNode node) {
    return visitChildren(node);
  }

  T visitSwitchCaseStmt(ASTSwitchCaseStmtNode node) {
    return visitChildren(node);
  }

  T visitCaseStmt(ASTCaseStmtNode node) {
    return visitChildren(node);
  }

  T visitDefaultStmt(ASTDefaultStmtNode node) {
    return visitChildren(node);
  }
}
