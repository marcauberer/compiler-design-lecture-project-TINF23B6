package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTReturnStmtNode;
import com.auberer.compilerdesignlectureproject.ast.ASTTernaryExprNode;
import com.auberer.compilerdesignlectureproject.interpreter.InterpreterEnvironment;

public class ReturnInstruction extends Instruction {

  private final ASTTernaryExprNode returnExpr;

  public ReturnInstruction(ASTReturnStmtNode node) {
    super(node);
    this.returnExpr = node.getReturnExpr();
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append("ret ")
        .append(returnExpr.getType().toLLVMIRTypeString()).append(" ")
        .append(returnExpr.getValue().getName());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": return");
  }

  @Override
  public void run(InterpreterEnvironment env) {
    env.returnFromFunction();
  }

  @Override
  public boolean isTerminator() {
    return true;
  }
}
