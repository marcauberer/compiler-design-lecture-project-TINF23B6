package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.auberer.compilerdesignlectureproject.ast.ASTParamLstNode;
import com.auberer.compilerdesignlectureproject.codegen.Function;
import com.auberer.compilerdesignlectureproject.sema.Type;

import java.util.stream.Collectors;

public class CallInstruction extends Instruction {

  private final Function function;
  private final ASTParamLstNode paramListNode;

  public CallInstruction(ASTNode node, Function function, ASTParamLstNode paramListNode) {
    super(node);
    this.function = function;
    this.paramListNode = paramListNode;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    Type returnType = function.getReturnType();
    if (returnType != null)
      sb.append(node.getValue().getName()).append(" = call ").append(returnType.toLLVMIRTypeString()).append(" @");
    else
      sb.append("call void @");
    sb.append(function.getName());
    String params = paramListNode.getParams().stream()
        .map(astLogicalExprNode -> astLogicalExprNode.getValue().getName())
        .collect(Collectors.joining(","));
    sb.append("(").append(params).append(")");
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": call ");
  }
}
