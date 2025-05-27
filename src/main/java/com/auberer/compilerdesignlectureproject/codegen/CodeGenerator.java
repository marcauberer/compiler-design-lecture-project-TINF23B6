package com.auberer.compilerdesignlectureproject.codegen;

import com.auberer.compilerdesignlectureproject.ast.*;
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

}
