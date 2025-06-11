package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.ast.ASTEqualityExprNode;
import com.auberer.compilerdesignlectureproject.ast.ASTTernaryExprNode;
import com.auberer.compilerdesignlectureproject.interpreter.InterpreterEnvironment;
import com.auberer.compilerdesignlectureproject.interpreter.Value;
import com.auberer.compilerdesignlectureproject.sema.SuperType;

public class SelectInstruction extends Instruction {

  private final ASTEqualityExprNode condition;
  private final ASTEqualityExprNode trueValue;
  private final ASTEqualityExprNode falseValue;

  public SelectInstruction(ASTTernaryExprNode node, ASTEqualityExprNode condition, ASTEqualityExprNode trueValue, ASTEqualityExprNode falseValue) {
    super(node);
    this.condition = condition;
    this.trueValue = trueValue;
    this.falseValue = falseValue;
  }

  @Override
  public void dumpIR(StringBuilder sb) {
    sb.append(node.getValue().getName()).append(" = select i1 ")
        .append(condition.getValue().getName()).append(", ")
        .append(trueValue.getType().toLLVMIRTypeString()).append(" ")
        .append(trueValue.getValue().getName()).append(", ")
        .append(falseValue.getType().toLLVMIRTypeString()).append(" ")
        .append(falseValue.getValue().getName());
  }

  @Override
  public void trace(StringBuilder sb) {
    sb.append(node.getCodeLoc().toString()).append(": select");
  }

  @Override
  public void run(InterpreterEnvironment env) {
    assert condition.getType().is(SuperType.TYPE_BOOL);
    Value result = new Value(node);
    switch(trueValue.getType().getSuperType()) {
      case TYPE_INT -> result.setIntValue(condition.getValue().isTrue() ? trueValue.getValue().getIntValue() : falseValue.getValue().getIntValue());
      case TYPE_DOUBLE -> result.setDoubleValue(condition.getValue().isTrue() ? trueValue.getValue().getDoubleValue() : falseValue.getValue().getDoubleValue());
      case TYPE_STRING -> result.setStringValue(condition.getValue().isTrue() ? trueValue.getValue().getStringValue() : falseValue.getValue().getStringValue());
      case TYPE_BOOL -> result.setBoolValue(condition.getValue().isTrue() ? trueValue.getValue().isTrue() : falseValue.getValue().isTrue());
    }
    node.setValue(result);
  }
}
