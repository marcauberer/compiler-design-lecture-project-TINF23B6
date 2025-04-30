package com.auberer.compilerdesignlectureproject.antlr;

import com.auberer.compilerdesignlectureproject.antlr.gen.TInfBaseVisitor;
import com.auberer.compilerdesignlectureproject.antlr.gen.TInfParser;
import com.auberer.compilerdesignlectureproject.ast.*;
import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import com.auberer.compilerdesignlectureproject.reader.CodeLoc;
import org.antlr.v4.runtime.ParserRuleContext;

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
  public ASTNode visitPrintBuiltinCall(TInfParser.PrintBuiltinCallContext ctx) {
    ASTPrintBuiltinCallNode node = new ASTPrintBuiltinCallNode();
    enterNode(node, ctx);

    visitChildren(ctx);

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

  // Team 2

  // Team 3

//    lexer.expect(TokenType.TOK_DO);
//    lexer.expect(TokenType.TOK_LBRACE);
//  parseStmtLst();
//    lexer.expect(TokenType.TOK_RBRACE);
//    lexer.expect(TokenType.TOK_WHILE);
//    lexer.expect(TokenType.TOK_LPAREN);
//  parseTernaryExpr();
//    lexer.expect(TokenType.TOK_RPAREN);
//    lexer.expect(TokenType.TOK_SEMICOLON);
@Override
public ASTNode visitDoWhileLoop(TInfParser.DoWhileLoopContext ctx) {
  ASTDoWhileLoopNode node = new ASTDoWhileLoopNode();

  enterNode(node, ctx);

  visitChildren(ctx);

  exitNode(node);
  return node;
}


  // Team 4

  // Team 5

  // Team 6

  // Team 7

  private void enterNode(ASTNode node, ParserRuleContext ctx) {
    // Attach CodeLoc to AST node
    CodeLoc codeLoc = new CodeLoc(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    node.setCodeLoc(codeLoc);

    if (!parentStack.isEmpty()) {
      // Make sure the node is not pushed twice
      assert parentStack.peek() != node;
      // Link parent and chlid nodes, so we can traverse the tree
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
