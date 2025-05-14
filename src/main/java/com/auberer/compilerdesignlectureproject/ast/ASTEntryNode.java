package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.sema.Scope;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ASTEntryNode extends ASTNode implements IVisitable {

  private Scope rootScope;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitEntry(this);
  }

  public List<ASTFunctionDefNode> getFunctions() {
    return getChildren(ASTFunctionDefNode.class);
  }
}
