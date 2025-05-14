package com.auberer.compilerdesignlectureproject.sema;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;

import java.util.ArrayList;
import java.util.List;

public class Scope {

  Scope parent = null;
  List<Scope> children = new ArrayList<>();
  SymbolTable symbolTable = new SymbolTable(this);
  int level = 0;

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

  public String serialize() {
    StringBuilder builder = new StringBuilder();
    builder.append(symbolTable.serialize()).append("\n\n");
    for (Scope child : children) {
      builder.append(child.serialize()).append("\n\n");
    }
    return builder.toString();
  }
}
