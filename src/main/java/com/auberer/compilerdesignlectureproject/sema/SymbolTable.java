package com.auberer.compilerdesignlectureproject.sema;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

  Scope scope;
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

  public String serialize() {
    StringBuilder builder = new StringBuilder();
    builder.append("Scope: ").append(scope.level).append("\n");
    for (SymbolTableEntry entry : symbols.values()) {
      builder.append(entry.serialize()).append("\n");
    }
    return builder.toString();
  }
}
