package com.auberer.compilerdesignlectureproject.sema;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.auberer.compilerdesignlectureproject.interpreter.Value;
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

  @Setter
  @JsonIgnore
  private Value value = null;

  public SymbolTableEntry(String name, ASTNode declNode, Scope scope) {
    this.name = name;
    this.declNode = declNode;
    this.scope = scope;
  }

  public Value getValue() {
    assert value != null : "Value not set for symbol table entry " + name + ". Missing alloca?";
    return value;
  }

}
