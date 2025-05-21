package com.auberer.compilerdesignlectureproject.sema;

import com.auberer.compilerdesignlectureproject.ast.ASTVisitor;

import java.util.Stack;

public abstract class ASTSemaVisitor<T> extends ASTVisitor<T> {
  Stack<Scope> currentScope = new Stack<>();
}
