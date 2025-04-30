package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ASTMultiplicativeExprNode extends ASTNode {

  public enum MultiplicativeOp {
    MUL, DIV
  }

  private List<MultiplicativeOp> opList = new ArrayList<>();

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitMultiplicativeExpr(this);
  }

  public static Set<TokenType> getSelectionSet() {
    return ASTAtomicExprNode.getSelectionSet();
  }

  public void addOp(MultiplicativeOp op) {
    opList.add(op);
  }

  public List<MultiplicativeOp> getOpList() {
    return opList;
  }
}
