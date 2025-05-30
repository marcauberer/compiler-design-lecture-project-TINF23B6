package com.auberer.compilerdesignlectureproject.sema;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Scope {

  @JsonIgnore
  Scope parent = null;

  @JsonProperty("level")
  int level = 0;

  @JsonProperty("children")
  List<Scope> children = new ArrayList<>();

  @JsonProperty("symbolTable")
  SymbolTable symbolTable = new SymbolTable(this);

  public Scope createChildScope() {
    Scope child = new Scope();
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
