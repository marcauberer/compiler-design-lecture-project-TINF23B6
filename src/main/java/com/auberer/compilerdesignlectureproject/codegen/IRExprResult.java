package com.auberer.compilerdesignlectureproject.codegen;

import com.auberer.compilerdesignlectureproject.ast.ASTNode;
import com.auberer.compilerdesignlectureproject.sema.SymbolTableEntry;
import lombok.Data;

@Data
public class IRExprResult {
  private ASTNode node;
  private SymbolTableEntry entry;

  public IRExprResult(ASTNode node) {
    this.node = node;
  }

  public IRExprResult(ASTNode node, SymbolTableEntry entry) {
    this.node = node;
    this.entry = entry;
  }
}