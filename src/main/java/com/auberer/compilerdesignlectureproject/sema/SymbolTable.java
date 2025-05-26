package com.auberer.compilerdesignlectureproject.sema;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

  @JsonIgnore
  Scope scope;

  @JsonProperty("symbols")
  Map<String, SymbolTableEntry> symbols = new HashMap<>(); // Mapping from symbol name to symbol entry

  public SymbolTable(Scope scope) {
    this.scope = scope;
  }

  public void insert(String name, ASTNode declNode) {
    symbols.put(name, new SymbolTableEntry(name, declNode, scope));
  }

  public SymbolTableEntry lookup(String name, ASTNode lookupNode) {
    SymbolTableEntry entry = lookupStrict(name, lookupNode);
    if (entry != null) {
      entry.setUsed(true);
      return entry;
    }
    if (scope.parent == null)
      return null;
    return scope.parent.lookupSymbol(name, lookupNode);
  }

  public SymbolTableEntry lookupStrict(String name, ASTNode lookupNode) {
    SymbolTableEntry entry = symbols.get(name);
    return entry != null && entry.getDeclNode().getCodeLoc().compareTo(lookupNode.getCodeLoc()) <= 0 ? entry : null;
  }

}
