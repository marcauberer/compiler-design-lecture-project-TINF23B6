package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTAdditiveExprNode;
import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.auberer.compilerdesignlectureproject.interpreter.InterpreterEnvironment;
import com.auberer.compilerdesignlectureproject.interpreter.Value;

public class EqualInstruction extends Instruction {

  private final ASTAdditiveExprNode leftOperand;
  private final ASTAdditiveExprNode rightOperand;

  public EqualInstruction(ASTNode node, ASTAdditiveExprNode leftOperand, ASTAdditiveExprNode rightOperand) {
    super(node);
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append(node.getValue().getName()).append(" = icmp eq ")
        .append(node.getType().toLLVMIRTypeString()).append(" ")
        .append(leftOperand.getValue().getName()).append(", ")
        .append(rightOperand.getValue().getName());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": binary operation: equal expression");
  }

  @Override
  public void run(InterpreterEnvironment env) {
    Value lhs = leftOperand.getValue();
    Value rhs = rightOperand.getValue();
    Value result = new Value(node);
    boolean resultValue = switch (leftOperand.getType().getSuperType()) {
      case TYPE_INT -> lhs.getIntValue() == rhs.getIntValue();
      case TYPE_DOUBLE -> lhs.getDoubleValue() == rhs.getDoubleValue();
      case TYPE_STRING -> lhs.getStringValue().equals(rhs.getStringValue());
      case TYPE_BOOL -> lhs.isTrue() == rhs.isTrue();
      default -> false;
    };
    result.setBoolValue(resultValue);
    node.setValue(result);
  }
}
