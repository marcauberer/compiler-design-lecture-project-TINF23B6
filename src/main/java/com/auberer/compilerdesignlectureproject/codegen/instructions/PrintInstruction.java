package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTPrintBuiltinCallNode;
import com.auberer.compilerdesignlectureproject.ast.ASTTernaryExprNode;

public class PrintInstruction extends Instruction {

  private final ASTTernaryExprNode exprToPrint;

  public PrintInstruction(ASTPrintBuiltinCallNode node) {
    super(node);
    this.exprToPrint = node.getExpr();
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append("print ").append(exprToPrint.getValue().getName());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": builtin function: print");
  }
}
