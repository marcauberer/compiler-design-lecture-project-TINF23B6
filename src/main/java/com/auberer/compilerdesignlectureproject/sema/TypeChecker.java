package com.auberer.compilerdesignlectureproject.sema;

import com.auberer.compilerdesignlectureproject.ast.*;

/**
 * Typ-Kompatibilität prüfen
 * Overload resolution (Team 4)
 * AST-Knoten mit den Typen befüllen (setEvaluatedSymbolType)
 * SymbolTableEntry mit Typen ergänzen
 * Sinnvolle Fehlermeldungen erzeugen
 */
public class TypeChecker extends ASTSemaVisitor<ExprResult> {

  final ASTEntryNode entryNode;

  public TypeChecker(ASTEntryNode entryNode) {
    this.entryNode = entryNode;
  }

  public TypeChecker() {
    this.entryNode = null;
  }

  @Override
  public ExprResult visitEntry(ASTEntryNode node) {
    assert currentScope.isEmpty();
    currentScope.push(node.getRootScope());

    visitChildren(node);

    assert currentScope.peek() == node.getRootScope();
    currentScope.pop();
    assert currentScope.isEmpty();

    return null;
  }

  @Override
  public ExprResult visitPrintBuiltin(ASTPrintBuiltinCallNode node) {
    ASTTernaryExprNode expr = node.getExpr();
    visit(expr);

    if (!expr.getType().isOneOf(SuperType.TYPE_INT, SuperType.TYPE_DOUBLE, SuperType.TYPE_STRING)) {
      throw new SemaError(node, "print() expects an integer, double or string as argument");
    }

    return new ExprResult(new Type(SuperType.TYPE_INVALID));
  }

  @Override
  public ExprResult visitLiteral(ASTLiteralNode node) {
    SuperType resultSuperType = switch (node.getLiteralType()) {
      case INT -> SuperType.TYPE_INT;
      case DOUBLE -> SuperType.TYPE_DOUBLE;
      case STRING -> SuperType.TYPE_STRING;
      case BOOL -> SuperType.TYPE_BOOL;
    };
    Type resultType = new Type(resultSuperType);
    return new ExprResult(node.setEvaluatedSymbolType(resultType));
  }

  @Override
  public ExprResult visitType(ASTTypeNode node) {
    SuperType resultSuperType = switch (node.getDataType()) {
      case INT -> SuperType.TYPE_INT;
      case DOUBLE -> SuperType.TYPE_DOUBLE;
      case STRING -> SuperType.TYPE_STRING;
      case BOOL -> SuperType.TYPE_BOOL;
    };
    Type resultType = new Type(resultSuperType);
    return new ExprResult(node.setEvaluatedSymbolType(resultType));
  }

  @Override
  public ExprResult visitTernaryExpr(ASTTernaryExprNode node) {
    if (node.getTrueBranch() != null) {
      assert node.getFalseBranch() != null;

      visitChildren(node);
      var condition = node.getCondition();
      var trueBranch = node.getTrueBranch();
      var falseBranch = node.getFalseBranch();
      if (!condition.getType().is(SuperType.TYPE_BOOL))
        throw new SemaError(node, "Ternary condition must be of type bool");

      if (!trueBranch.getType().is(falseBranch.getType().getSuperType()))
        throw new SemaError(node, "Ternary branches must have the same type");

      return new ExprResult(node.setEvaluatedSymbolType(trueBranch.getType()));
    } else {
      ExprResult result = visit(node.getEqualityExpr());
      node.setEvaluatedSymbolType(result.getType());
      return result;
    }
  }

  @Override
  public ExprResult visitEqualityExpr(ASTEqualityExprNode node) {
    var operands = node.getOperands();
    if (operands.size() == 1) {
      ExprResult result = visit(operands.getFirst());
      node.setEvaluatedSymbolType(result.getType());
      return result;
    } else {
      assert operands.size() == 2;
      visitChildren(node);
      var left = operands.get(0);
      var right = operands.get(1);
      if (!left.getType().is(right.getType().getSuperType()))
        throw new SemaError(node, "Equality expressions must have the same type");
      return new ExprResult(node.setEvaluatedSymbolType(left.getType()));
    }
  }

  @Override
  public ExprResult visitAdditiveExpr(ASTAdditiveExprNode node) {
    var operands = node.getOperands();
    if (operands.size() == 1) {
      ExprResult result = visit(operands.getFirst());
      node.setEvaluatedSymbolType(result.getType());
      return result;
    } else {
      assert operands.size() == 2;
      visitChildren(node);
      var left = operands.get(0);
      var right = operands.get(1);
      if (!left.getType().is(right.getType().getSuperType()))
        throw new SemaError(node, "Additive expressions must have the same type");
      return new ExprResult(node.setEvaluatedSymbolType(left.getType()));
    }
  }

  @Override
  public ExprResult visitMultiplicativeExpr(ASTMultiplicativeExprNode node) {
    var operands = node.getOperands();
    if (operands.size() == 1) {
      ExprResult result = visit(operands.getFirst());
      node.setEvaluatedSymbolType(result.getType());
      return result;
    } else {
      assert operands.size() == 2;
      visitChildren(node);
      var left = operands.get(0);
      var right = operands.get(1);
      if (!left.getType().is(right.getType().getSuperType()))
        throw new SemaError(node, "Additive expressions must have the same type");
      return new ExprResult(node.setEvaluatedSymbolType(left.getType()));
    }
  }

  @Override
  public ExprResult visitAtomicExpr(ASTAtomicExprNode node) {
    ASTLiteralNode literal = node.getLiteral();
    if (literal != null) {
      ExprResult result = visit(literal);
      node.setEvaluatedSymbolType(result.getType());
      return result;
    }

    ASTFunctionCallNode functionCall = node.getFunctionCall();
    if (functionCall != null) {
      ExprResult result = visit(functionCall);
      node.setEvaluatedSymbolType(result.getType());
      return result;
    }

    ASTPrintBuiltinCallNode printBuiltinCall = node.getPrintBuiltin();
    if (printBuiltinCall != null) {
      ExprResult result = visit(printBuiltinCall);
      node.setEvaluatedSymbolType(result.getType());
      return result;
    }

    ASTTernaryExprNode ternaryExpr = node.getTernaryExpr();
    if (ternaryExpr != null) {
      ExprResult result = visit(ternaryExpr);
      node.setEvaluatedSymbolType(result.getType());
      return result;
    }

    SymbolTableEntry entry = node.getCurrentSymbol();
    return new ExprResult(node.setEvaluatedSymbolType(entry.getType()));
  }

  @Override
  public ExprResult visitVarDecl(ASTVarDeclNode node) {
    ExprResult lhs = visit(node.getDataType());
    ExprResult rhs = visit(node.getInitExpr());

    if (!lhs.getType().is(rhs.getType().getSuperType()))
      throw new SemaError(node, "Type mismatch in variable declaration");

    SymbolTableEntry entry = node.getCurrentSymbol();
    assert entry != null;
    entry.setType(lhs.getType());

    return new ExprResult(node.setEvaluatedSymbolType(lhs.getType()));
  }

  @Override
  public ExprResult visitAssignExpr(ASTAssignExprNode node) {
    SymbolTableEntry entry = node.getCurrentSymbol();
    assert entry != null;

    ExprResult rhs = visit(node.getRhs());
    if (!entry.getType().is(rhs.getType().getSuperType()))
      throw new SemaError(node, "Type mismatch in assignment");

    return new ExprResult(node.setEvaluatedSymbolType(rhs.getType()));
  }
}
