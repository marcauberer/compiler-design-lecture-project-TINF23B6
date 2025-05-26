package com.auberer.compilerdesignlectureproject.sema;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SymbolTableEntry {

  @JsonProperty("name")
  private final String name;

  @JsonIgnore
  private final ASTNode declNode;

  @JsonIgnore
  private final Scope scope;

  @Setter
  @JsonProperty("isUsed")
  private boolean isUsed = false;

  @Setter
  @JsonProperty("isParam")
  private boolean isParam = false;

  @Setter
  @JsonProperty("type")
  private Type type = new Type(SuperType.TYPE_INVALID);

  public SymbolTableEntry(String name, ASTNode declNode, Scope scope) {
    this.name = name;
    this.declNode = declNode;
    this.scope = scope;
  }

}
