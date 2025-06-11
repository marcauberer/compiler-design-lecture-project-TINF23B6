package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTPrintBuiltinCallNode;
import com.auberer.compilerdesignlectureproject.ast.ASTTernaryExprNode;
import com.auberer.compilerdesignlectureproject.interpreter.InterpreterEnvironment;

public class PrintInstruction extends Instruction {

  private final ASTTernaryExprNode exprToPrint;

  public PrintInstruction(ASTPrintBuiltinCallNode node) {
    super(node);
    this.exprToPrint = node.getExpr();
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append(node.getValue().getName())
        .append(" = call i32 (ptr, ...) @printf(ptr noundef @.str, ptr ")
        .append(exprToPrint.getValue().getName())
        .append(")");
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": builtin function: print");
  }

  @Override
  public void run(InterpreterEnvironment env) {
    System.out.println(exprToPrint.getValue());
  }
}
