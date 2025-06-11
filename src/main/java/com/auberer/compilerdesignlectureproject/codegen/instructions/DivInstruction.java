package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTAtomicExprNode;
import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.auberer.compilerdesignlectureproject.interpreter.InterpreterEnvironment;
import com.auberer.compilerdesignlectureproject.interpreter.Value;

public class DivInstruction extends Instruction {

  private final ASTAtomicExprNode leftOperand;
  private final ASTAtomicExprNode rightOperand;

  public DivInstruction(ASTNode node, ASTAtomicExprNode leftOperand, ASTAtomicExprNode rightOperand) {
    super(node);
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append(node.getValue().getName()).append(" = div ")
        .append(leftOperand.getType().toLLVMIRTypeString()).append(" ")
        .append(leftOperand.getValue().getName()).append(", ")
        .append(rightOperand.getValue().getName());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": binary operation: div expression");
  }

  @Override
  public void run(InterpreterEnvironment env) {
    Value lhs = leftOperand.getValue();
    Value rhs = rightOperand.getValue();
    Value result = new Value(node);
    switch (leftOperand.getType().getSuperType()) {
      case TYPE_INT: {
        result.setIntValue(lhs.getIntValue() / rhs.getIntValue());
        break;
      }
      case TYPE_DOUBLE: {
        result.setDoubleValue(lhs.getDoubleValue() / rhs.getDoubleValue());
        break;
      }
      default:
        throw new RuntimeException("Div with illegal types");
    }
    node.setValue(result);
  }
}
