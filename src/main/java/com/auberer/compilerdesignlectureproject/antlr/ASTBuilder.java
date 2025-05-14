package com.auberer.compilerdesignlectureproject.antlr;

import com.auberer.compilerdesignlectureproject.antlr.gen.TInfBaseVisitor;
import com.auberer.compilerdesignlectureproject.antlr.gen.TInfParser;
import com.auberer.compilerdesignlectureproject.ast.*;
import com.auberer.compilerdesignlectureproject.reader.CodeLoc;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Stack;

public class ASTBuilder extends TInfBaseVisitor<ASTNode> {

  // Stack to keep track of the parent nodes
  Stack<ASTNode> parentStack = new Stack<>();

  @Override
  public ASTNode visitEntry(TInfParser.EntryContext ctx) {
    ASTEntryNode node = new ASTEntryNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitStmtLst(TInfParser.StmtLstContext ctx) {
    ASTStmtLstNode node = new ASTStmtLstNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitStmt(TInfParser.StmtContext ctx) {
    ASTStmtNode node = new ASTStmtNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitVarDeclStmt(TInfParser.VarDeclStmtContext ctx) {
    ASTVarDeclNode node = new ASTVarDeclNode();
    enterNode(node, ctx);

    visitType(ctx.type());

    String identifierName = ctx.IDENTIFIER().getText();
    node.setVariableName(identifierName);

    visitTernaryExpr(ctx.ternaryExpr());

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitAssignStmt(TInfParser.AssignStmtContext ctx) {
    ASTAssignStmtNode node = new ASTAssignStmtNode();
    enterNode(node, ctx);

    visitAssignExpr(ctx.assignExpr());

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitAssignExpr(TInfParser.AssignExprContext ctx) {
    ASTAssignExprNode node = new ASTAssignExprNode();
    enterNode(node, ctx);

    if (ctx.IDENTIFIER() != null) {
      String identifierName = ctx.IDENTIFIER().getText();
      node.setVariableName(identifierName);
    }
    visitTernaryExpr(ctx.ternaryExpr());

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitPrintBuiltinCall(TInfParser.PrintBuiltinCallContext ctx) {
    ASTPrintBuiltinCallNode node = new ASTPrintBuiltinCallNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitLiteral(TInfParser.LiteralContext ctx) {
    ASTLiteralNode node = new ASTLiteralNode();
    enterNode(node, ctx);

    if (ctx.INT_LIT() != null) {
      node.setType(ASTLiteralNode.LiteralType.INT);
      node.setValue(ctx.INT_LIT().getText());
    } else if (ctx.DOUBLE_LIT() != null) {
      node.setType(ASTLiteralNode.LiteralType.DOUBLE);
      node.setValue(ctx.DOUBLE_LIT().getText());
    } else if (ctx.STRING_LIT() != null) {
      node.setType(ASTLiteralNode.LiteralType.STRING);
      node.setValue(ctx.STRING_LIT().getText().substring(1, ctx.STRING_LIT().getText().length() - 1));
    } else if (ctx.TRUE() != null || ctx.FALSE() != null) {
      node.setType(ASTLiteralNode.LiteralType.BOOL);
      node.setValue(ctx.getText().toLowerCase());
    } else {
      throw new RuntimeException("Unexpected token type");
    }

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitType(TInfParser.TypeContext ctx) {
    ASTTypeNode node = new ASTTypeNode();
    enterNode(node, ctx);

    if (ctx.TYPE_INT() != null) {
      node.setType(ASTTypeNode.Type.INT);
    } else if (ctx.TYPE_DOUBLE() != null) {
      node.setType(ASTTypeNode.Type.DOUBLE);
    } else if (ctx.TYPE_STRING() != null) {
      node.setType(ASTTypeNode.Type.STRING);
    } else if (ctx.TYPE_BOOL() != null) {
      node.setType(ASTTypeNode.Type.BOOL);
    } else {
      throw new RuntimeException("Unexpected token type");
    }

    exitNode(node);
    return node;
  }

  // Team 1

  public ASTNode visitIfStmt(TInfParser.IfStmtContext ctx) {
    ASTIfStmtNode node = new ASTIfStmtNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  public ASTNode visitIfBody(TInfParser.IfBodyContext ctx) {
    ASTIfBodyNode node = new ASTIfBodyNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  public ASTNode visitElseStmt(TInfParser.ElseStmtContext ctx) {
    ASTElseStmtNode node = new ASTElseStmtNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  // Team 2
  @Override
  public ASTNode visitWhileLoop(TInfParser.WhileLoopContext ctx) {
    ASTWhileLoopNode node = new ASTWhileLoopNode();
    enterNode(node, ctx);
    visitChildren(ctx);
    exitNode(node);
    return node;
  }

  // Team 3

  @Override
  public ASTNode visitDoWhileLoop(TInfParser.DoWhileLoopContext ctx) {
    ASTDoWhileLoopNode node = new ASTDoWhileLoopNode();

    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  // Team 4

  @Override
  public ASTNode visitArgLst(TInfParser.ArgLstContext ctx) {
    ASTArgLstNode node = new ASTArgLstNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitFctCall(TInfParser.FctCallContext ctx) {
    ASTFunctionCallNode node = new ASTFunctionCallNode();
    enterNode(node, ctx);

    node.setIdentifier(ctx.IDENTIFIER().getText());
    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitFctDef(TInfParser.FctDefContext ctx) {
    ASTFunctionDefNode node = new ASTFunctionDefNode();
    enterNode(node, ctx);

    node.setIdentifier(ctx.IDENTIFIER().getText());
    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitParamLst(TInfParser.ParamLstContext ctx) {
    ASTParamLstNode node = new ASTParamLstNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitParam(TInfParser.ParamContext ctx) {
    ASTParamNode node = new ASTParamNode();
    enterNode(node, ctx);

    node.setIdentifier(ctx.IDENTIFIER().getText());
    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitReturnStmt(TInfParser.ReturnStmtContext ctx) {
    ASTParamNode node = new ASTParamNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  // Team 5
  @Override
  public ASTNode visitForLoop(TInfParser.ForLoopContext ctx) {
    ASTForLoopNode node = new ASTForLoopNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  // Team 6

  @Override
  public ASTNode visitSwitchCaseStmt(TInfParser.SwitchCaseStmtContext ctx) {
    ASTSwitchCaseStmtNode node = new ASTSwitchCaseStmtNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitCaseBlockLst(TInfParser.CaseBlockLstContext ctx) {
    ASTCaseStmtNode node = new ASTCaseStmtNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitCaseBlock(TInfParser.CaseBlockContext ctx) {
    ASTCaseStmtNode node = new ASTCaseStmtNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitDefaultBlock(TInfParser.DefaultBlockContext ctx) {
    ASTDefaultStmtNode node = new ASTDefaultStmtNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  // Team 7

  @Override
  public ASTNode visitAnonymousBlockStmt(TInfParser.AnonymousBlockStmtContext ctx) {
    ASTAnonymousBlockStmtNode node = new ASTAnonymousBlockStmtNode();
    enterNode(node, ctx);

    visitChildren(ctx);

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitTernaryExpr(TInfParser.TernaryExprContext ctx) {
    ASTTernaryExprNode node = new ASTTernaryExprNode();
    enterNode(node, ctx);

    visitEqualityExpr(ctx.equalityExpr(0));
    if (ctx.equalityExpr().size() > 1) {
      visitEqualityExpr(ctx.equalityExpr(1));
      visitEqualityExpr(ctx.equalityExpr(2));
    }

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitEqualityExpr(TInfParser.EqualityExprContext ctx) {
    ASTEqualityExprNode node = new ASTEqualityExprNode();
    enterNode(node, ctx);

    visitAdditiveExpr(ctx.additiveExpr(0));
    if (ctx.additiveExpr().size() > 1) {
      node.setOp(ctx.EQUALS() != null ? ASTEqualityExprNode.EqualityOp.EQ : ASTEqualityExprNode.EqualityOp.NEQ);
      visitAdditiveExpr(ctx.additiveExpr(1));
    }

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitAdditiveExpr(TInfParser.AdditiveExprContext ctx) {
    ASTAdditiveExprNode node = new ASTAdditiveExprNode();
    enterNode(node, ctx);

    for (int i = 0; i < ctx.getChildCount(); i++) {
      ParseTree child = ctx.getChild(i);
      if (child instanceof TerminalNode terminalNode) {
        Token token = terminalNode.getSymbol();
        if (token.getType() == TInfParser.PLUS) {
          node.addOp(ASTAdditiveExprNode.AdditiveOp.PLUS);
        } else if (token.getType() == TInfParser.MINUS) {
          node.addOp(ASTAdditiveExprNode.AdditiveOp.MINUS);
        }
      } else if (child instanceof ParserRuleContext) {
        visit(child);
      }
    }

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitMultiplicativeExpr(TInfParser.MultiplicativeExprContext ctx) {
    ASTMultiplicativeExprNode node = new ASTMultiplicativeExprNode();
    enterNode(node, ctx);

    for (int i = 0; i < ctx.getChildCount(); i++) {
      ParseTree child = ctx.getChild(i);
      if (child instanceof TerminalNode terminalNode) {
        Token token = terminalNode.getSymbol();
        if (token.getType() == TInfParser.MUL) {
          node.addOp(ASTMultiplicativeExprNode.MultiplicativeOp.MUL);
        } else if (token.getType() == TInfParser.DIV) {
          node.addOp(ASTMultiplicativeExprNode.MultiplicativeOp.DIV);
        }
      } else if (child instanceof ParserRuleContext) {
        visit(child);
      }
    }

    exitNode(node);
    return node;
  }

  @Override
  public ASTNode visitAtomicExpr(TInfParser.AtomicExprContext ctx) {
    ASTAtomicExprNode node = new ASTAtomicExprNode();
    enterNode(node, ctx);

    if (ctx.literal() != null) {
      visitLiteral(ctx.literal());
    } else if (ctx.fctCall() != null) {
      visitFctCall(ctx.fctCall());
    } else if (ctx.printBuiltinCall() != null) {
      visitPrintBuiltinCall(ctx.printBuiltinCall());
    } else if (ctx.IDENTIFIER() != null) {
      String identifierName = ctx.IDENTIFIER().getText();
      node.setVariableName(identifierName);
    } else if (ctx.ternaryExpr() != null) {
      visitTernaryExpr(ctx.ternaryExpr());
    } else {
      throw new RuntimeException("Unexpected token type");
    }

    exitNode(node);
    return node;
  }

  private void enterNode(ASTNode node, ParserRuleContext ctx) {
    // Attach CodeLoc to AST node
    CodeLoc codeLoc = new CodeLoc(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    node.setCodeLoc(codeLoc);

    if (!parentStack.isEmpty()) {
      // Make sure the node is not pushed twice
      assert parentStack.peek() != node;
      // Link parent and child nodes, so we can traverse the tree
      ASTNode parent = parentStack.peek();
      parent.addChild(node);
      node.setParent(parent);
    }
    // Push the node to the stack
    parentStack.push(node);
  }

  private void exitNode(ASTNode node) {
    // Make sure the node is the last one pushed
    assert parentStack.peek() == node;
    // Remove the node from the stack
    parentStack.pop();
  }

}
