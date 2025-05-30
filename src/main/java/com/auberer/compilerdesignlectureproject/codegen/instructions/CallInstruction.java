package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.auberer.compilerdesignlectureproject.ast.ASTParamLstNode;
import com.auberer.compilerdesignlectureproject.codegen.Function;

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
    // call <functionName>(<params>)
    sb.append("call ").append(function.getName()).append("(");
    String params = paramListNode.getParams().stream().map(astLogicalExprNode -> astLogicalExprNode.getValue().getName()).collect(Collectors.joining(","));
    sb.append(params).append(")");
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": call ");
  }
}
