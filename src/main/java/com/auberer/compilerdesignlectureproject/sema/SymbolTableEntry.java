package com.auberer.compilerdesignlectureproject.sema;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SymbolTableEntry {

  private String name;

  private ASTNode declNode;

  private Scope scope;

  @Setter
  private boolean isUsed = false;

  @Setter
  private boolean isParam = false;

  // ToDo(Marc): Insert type member

  public SymbolTableEntry(String name, ASTNode declNode, Scope scope) {
    this.name = name;
    this.declNode = declNode;
    this.scope = scope;
  }

  // ToDo(Marc): Insert setter for type

  public String serialize() {
    StringBuilder builder = new StringBuilder();
    builder.append("Symbol name: ").append(name).append(", ");
    builder.append("DeclNode: ").append(declNode.getCodeLoc().toString()).append(", ");
    builder.append("IsUsed: ").append(isUsed).append(", ");
    builder.append("IsParam: ").append(isParam);
    return builder.toString();
  }

}
