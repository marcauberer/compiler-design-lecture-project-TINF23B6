package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTReturnStmtNode;
import com.auberer.compilerdesignlectureproject.ast.ASTTernaryExprNode;

public class ReturnInstruction extends Instruction {

  private final ASTTernaryExprNode returnExpr;

  public ReturnInstruction(ASTReturnStmtNode node) {
    super(node);
    this.returnExpr = node.getReturnExpr();
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append("return ").append(returnExpr.getValue().getName());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": return");
  }

  @Override
  public boolean isTerminator() {
    return true;
  }
}
