package com.auberer.compilerdesignlectureproject.sema;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Scope {

  // use a flag to ensure not testing ret in loops because they need to be calculating during run time;
  // There is only the possibility that they are not relevant
  @JsonIgnore
  @Getter
  Boolean isInALoop = false;

  @JsonIgnore
  Scope parent = null;

  @JsonProperty("level")
  int level = 0;

  @JsonProperty("children")
  List<Scope> children = new ArrayList<>();

  @JsonProperty("symbolTable")
  SymbolTable symbolTable = new SymbolTable(this);

  public Scope() {
  }

  public Scope(boolean isInALoop) {
    this.isInALoop = isInALoop;
  }

  public Scope createChildScope() {
    Scope child = new Scope(this.isInALoop);
    child.parent = this;
    children.add(child);
    child.level = level + 1;
    return child;
  }

  public Scope createChildScope(boolean isInALoop) {
    Scope child = new Scope(isInALoop);
    child.parent = this;
    children.add(child);
    child.level = level + 1;
    return child;
  }

  public SymbolTableEntry insertSymbol(String name, ASTNode declNode) {
    symbolTable.insert(name, declNode);
    return lookupSymbolStrict(name, declNode);
  }

  public SymbolTableEntry lookupSymbol(String name, ASTNode lookupNode) {
    return symbolTable.lookup(name, lookupNode);
  }

  public SymbolTableEntry lookupSymbolStrict(String name, ASTNode lookupNode) {
    return symbolTable.lookupStrict(name, lookupNode);
  }

}
