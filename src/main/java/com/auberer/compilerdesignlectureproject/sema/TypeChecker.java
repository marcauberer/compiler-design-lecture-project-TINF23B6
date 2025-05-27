package com.auberer.compilerdesignlectureproject.sema;

import com.auberer.compilerdesignlectureproject.ast.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Typ-Kompatibilität prüfen
 * Overload resolution (Team 4)
 * AST-Knoten mit den Typen befüllen (setEvaluatedSymbolType)
 * SymbolTableEntry mit Typen ergänzen
 * Sinnvolle Fehlermeldungen erzeugen
 */
public class TypeChecker extends ASTSemaVisitor<ExprResult> {

  final ASTEntryNode entryNode;

  final FunctionTable functionTable = new FunctionTable();

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

  // Team 6
  @Override
  public ExprResult visitSwitchCaseStmt(ASTSwitchCaseStmtNode node) {
    ExprResult conditionResult = visit(node.getCondition());
    List<ASTCaseStmtNode> caseBlocks = node.getCaseBlocks();

    for (ASTCaseStmtNode caseBlock : caseBlocks) {
      caseBlock.setConditionResult(conditionResult);
      visitCaseStmt(caseBlock);
    }

    ASTDefaultStmtNode defaultBlock = node.getDefaultBlock();
    if (defaultBlock != null) {
      visit(defaultBlock);
    }

    Type resultType = new Type(SuperType.TYPE_INVALID);
    return new ExprResult(node.setEvaluatedSymbolType(resultType));
  }

  @Override
  public ExprResult visitCaseStmt(ASTCaseStmtNode caseBlock) {
    Scope scope = caseBlock.getScope();
    currentScope.push(scope);

    ASTLiteralNode literal = caseBlock.getLiteral();
    if (literal != null) {
      ExprResult caseResult = visit(literal);
      if (!caseResult.getType().is(caseBlock.getConditionResult().getType().getSuperType())) {
        throw new SemaError(literal, "Case value must be of type " + caseBlock.getConditionResult().getType().getSuperType());
      }
    }

    visit(caseBlock.getStmtLst());

    assert currentScope.peek() == scope;
    currentScope.pop();

    Type resultType = new Type(SuperType.TYPE_INVALID);
    return new ExprResult(caseBlock.setEvaluatedSymbolType(resultType));
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
      return new ExprResult(node.setEvaluatedSymbolType(new Type(SuperType.TYPE_BOOL)));
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
  public ExprResult visitAssignStmt(ASTAssignStmtNode node) {
    return new ExprResult(node.setEvaluatedSymbolType(new Type(SuperType.TYPE_INVALID)));
  }

  @Override
  public ExprResult visitAssignExpr(ASTAssignExprNode node) {
    Type resultType = new Type(SuperType.TYPE_INVALID);

    if (node.isAssignment()) {
      SymbolTableEntry entry = node.getCurrentSymbol();
      assert entry != null;

      ExprResult rhs = visit(node.getRhs());
      if (!entry.getType().is(rhs.getType().getSuperType()))
        throw new SemaError(node, "Type mismatch in assignment");

      resultType = rhs.getType();
    }

    return new ExprResult(node.setEvaluatedSymbolType(resultType));
  }

  @Override
  public ExprResult visitIfStmt(ASTIfStmtNode node) {
    visitChildren(node);

    ASTTernaryExprNode condition = node.getCondition();
      if (!condition.getType().is(SuperType.TYPE_BOOL)) {
        throw new SemaError(node, "If condition must be of type bool");
    }

      ASTIfBodyNode bodyNode = node.getIfBody();
      Scope bodyScope = bodyNode.getScope();
      currentScope.push(bodyScope);
      visit(bodyNode);

      assert currentScope.peek() == bodyScope;
      currentScope.pop();

      return null;
  }
  
  @Override
  public ExprResult visitForLoop(ASTForLoopNode node) {
    ASTVarDeclNode varDeclNode = node.getInitialization();
    ExprResult varDeclResult = visit(varDeclNode);
    if (!varDeclResult.getType().is(SuperType.TYPE_INT)) {
      throw new SemaError(node, "Please initialize an Integer.");
    }

    ASTTernaryExprNode ternaryExprNode = node.getCondition();
    ExprResult condResult = visit(ternaryExprNode);
    if (!condResult.getType().is(SuperType.TYPE_BOOL)) {
      throw new SemaError(ternaryExprNode, "The loop condition must be of type bool.");
    }

    ASTAssignExprNode assignExprNode = node.getIncrement();
    visit(assignExprNode);

    Scope bodyScope = node.getScope();
    currentScope.push(bodyScope);
    ASTStmtLstNode stmtLstNode = node.getBody();
    visit(stmtLstNode);
    currentScope.pop();

    return new ExprResult(new Type(SuperType.TYPE_INVALID));
  }

  @Override
  public ExprResult visitWhileLoopStmt(ASTWhileLoopNode node) {
    Scope whileLoopScope = node.getScope();
    currentScope.push(whileLoopScope);

    ASTTernaryExprNode conditionNode = node.getCondition();
    ExprResult exprResult = visit(conditionNode);
    if (!exprResult.getType().is(SuperType.TYPE_BOOL)) {
      throw new SemaError(node, "Wrong type: " + exprResult.getType().toString() + ". Type must be bool");
    }

    ASTStmtLstNode bodyNode = node.getBody();
    visit(bodyNode);

    assert currentScope.peek() == whileLoopScope;
    currentScope.pop();

    Type resultType = new Type(SuperType.TYPE_INVALID);
    return new ExprResult(node.setEvaluatedSymbolType(resultType));
  }

  @Override
  public ExprResult visitAnonymousBlockStmt(ASTAnonymousBlockStmtNode node) {
    Scope scope = node.getScope();
    currentScope.push(scope);

    visitChildren(node);

    assert currentScope.peek() == scope;
    currentScope.pop();
    return null;
  }

  @Override
  public ExprResult visitDoWhileLoop(ASTDoWhileLoopNode node) {
    Scope dowhileLoopScope = node.getScope();
    currentScope.push(dowhileLoopScope);

    ASTStmtLstNode stmtLstNode = node.getBody();
    visit(stmtLstNode);

    assert currentScope.peek() == dowhileLoopScope;
    currentScope.pop();

    ASTTernaryExprNode ternaryExprNode = node.getCondition();
    ExprResult exprResult = visit(ternaryExprNode);
    if (!exprResult.getType().is(SuperType.TYPE_BOOL)) {
      throw new SemaError(node, "Wrong type: " + exprResult.getType().toString() + ". Type must be bool");
    }

    Type resultType = new Type(SuperType.TYPE_INVALID);
    return new ExprResult(node.setEvaluatedSymbolType(resultType));
  }

  @Override
  public ExprResult visitParam(ASTParamNode node) {
    SymbolTableEntry entry = node.getCurrentSymbol();
    assert entry != null;

    ExprResult lhs = visit(node.getDataType());
    //ToDo Justus: ugly and doubled rewrite
    switch (lhs.getType().getSuperType()) {
      case SuperType.TYPE_BOOL:
        functionTable.incBooleanParamCount();
        break;
      case SuperType.TYPE_DOUBLE:
        functionTable.incDoubleParamCount();
        break;
      case SuperType.TYPE_INT:
        functionTable.incIntParamCount();
        break;
      case SuperType.TYPE_STRING:
        functionTable.incStringParamCount();
        break;
    }

    entry.setType(lhs.getType());

    if (node.getDefaultValue() != null) {
      ExprResult rhs = visit(node.getDefaultValue());
      if (!rhs.getType().is(rhs.getType().getSuperType()))
        throw new SemaError(node, "Type mismatch in default Type");
      else {
        //ToDo Justus: ugly and doubled rewrite
        switch (lhs.getType().getSuperType()) {
          case SuperType.TYPE_BOOL:
            functionTable.incrementAmountBooleanParamsDefaults();
            break;
          case SuperType.TYPE_DOUBLE:
            functionTable.incrementAmountDoubleParamsDefaults();
            break;
          case SuperType.TYPE_INT:
            functionTable.incrementAmountIntParamsDefaults();
            break;
          case SuperType.TYPE_STRING:
            functionTable.incrementAmountStringParamsDefaults();
            break;
        }
      }
    }

    return null;
  }

  @Override
  public ExprResult visitFunctionDef(ASTFunctionDefNode node) {
    functionTable.createEntry(node);
    Type retType = visitType(node.getReturnType()).getType();
    functionTable.setCurrentReturnType(retType);
    SymbolTableEntry entry = node.getCurrentSymbol();

    assert entry != null;
    entry.setType(retType);

    if (node.getParams() != null) {
      visitParamLst(node.getParams());
    }
    visit(node.getBody());
    return null;
  }

  //INFO: Importent need to be change the evaluation of fct calls without assign
  @Override
  public ExprResult visitFunctionCall(ASTFunctionCallNode node) {
    String identifier = node.getIdentifier();
    Type retType = functionTable.getTypeByIdentifier(identifier);
    functionTable.setPointer(functionTable.getPointerByIdentifier(identifier));
    visitChildren(node);
    functionTable.resetPointer();
    return new ExprResult(retType);
  }

  @Override
  public ExprResult visitArgLst(ASTArgLstNode node) {
    ArrayList<ASTAtomicExprNode> arguments = node.getArgs();
    //ToDo Justus: search for more effective method
    int intArgs = 0;
    int doubleArgs = 0;
    int stringArgs = 0;
    int boolArgs = 0;
    for (ASTAtomicExprNode arg : arguments) {
      ExprResult result = visit(arg);
      switch (result.getType().getSuperType()) {
        case SuperType.TYPE_INT:
          intArgs++;
          break;
        case SuperType.TYPE_DOUBLE:
          doubleArgs++;
          break;
        case SuperType.TYPE_STRING:
          stringArgs++;
          break;
        case SuperType.TYPE_BOOL:
          boolArgs++;
          break;
      }
    }
    functionTable.getActiveEntry().validateArgs(intArgs, doubleArgs, stringArgs, boolArgs);
    return null;
  }

  @Override
  public ExprResult visitReturnStmt(ASTReturnStmtNode node) {
    ExprResult result = visit(node.getReturnExpr());
    SuperType returnType = functionTable.getActiveEntry().getReturnType().getSuperType();
    if (!result.getType().getSuperType().equals(returnType)) {
      throw new RuntimeException("Return type mismatch");
    }
    return null;
  }

}
