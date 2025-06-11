package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.sema.Scope;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ASTEntryNode extends ASTNode implements IVisitable {

  private Scope rootScope;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visitEntry(this);
  }
}
