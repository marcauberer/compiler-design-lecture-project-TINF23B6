package com.auberer.compilerdesignlectureproject.parser;

import com.auberer.compilerdesignlectureproject.ast.*;
import com.auberer.compilerdesignlectureproject.lexer.ILexer;
import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Parser class for building the abstract syntax tree (AST).
 * Input: Token stream
 * Output: AST
 */
@Slf4j
public class Parser implements IParser {

  // Lexer interface that can be used to accept the given input
  ILexer lexer;
  // Stack to keep track of the parent nodes
  Stack<ASTNode> parentStack = new Stack<>();

  public Parser(ILexer lexer) {
    this.lexer = lexer;
  }

  /**
   * Entry point to the parser. This method should parse the input file and return the root node of the AST.
   *
   * @return AST root node
   */
  @Override
  public ASTEntryNode parse() {
    ASTEntryNode node = new ASTEntryNode();
    enterNode(node);

    do {
      parseFctDef();
    } while (!lexer.isEOF());

    exitNode(node);
    return node;
  }

  public void parseFctDef() {
    // ToDo(Team 4)
  }

  // stmtLst: stmt*;
  //stmt: varDeclStmt | assignStmt | returnStmt | ifStmt | whileLoop | doWhileLoop | forLoop | switchCaseStmt | anonymousBlockStmt;
  //varDeclStmt: type IDENTIFIER ASSIGN ternaryExpr SEMICOLON;
  //assignStmt: assignExpr SEMICOLON;
  //assignExpr: (IDENTIFIER ASSIGN)? ternaryExpr;
  //printBuiltinCall: PRINT LPAREN ternaryExpr RPAREN;
  //literal: INT_LIT | DOUBLE_LIT | STRING_LIT;
  //type: TYPE_INT | TYPE_DOUBLE | TYPE_STRING | TYPE_BOOL;

  public ASTStmtLstNode parseStmtLst() {
    ASTStmtLstNode node = new ASTStmtLstNode();
    enterNode(node);



    exitNode(node);
    return node;
  }

  public ASTStmtNode parseStmt() {
    ASTStmtNode node = new ASTStmtNode();
    enterNode(node);

    // ...

    exitNode(node);
    return node;
  }

  public ASTVarDeclNode parseVarDeclStmt() {
    ASTVarDeclNode node = new ASTVarDeclNode();
    enterNode(node);

    // ...

    exitNode(node);
    return node;
  }

  public ASTAssignStmtNode parseAssignStmt() {
    ASTAssignStmtNode node = new ASTAssignStmtNode();
    enterNode(node);

    // ...

    exitNode(node);
    return node;
  }

  public ASTAssignExprNode parseAssignExpr() {
    ASTAssignExprNode node = new ASTAssignExprNode();
    enterNode(node);

    // ...

    exitNode(node);
    return node;
  }

  public ASTPrintBuiltinCallNode parsePrintBuiltinCall() {
    ASTPrintBuiltinCallNode node = new ASTPrintBuiltinCallNode();
    enterNode(node);

    // ...

    exitNode(node);
    return node;
  }

  public ASTLiteralNode parseLiteral() {
    ASTLiteralNode node = new ASTLiteralNode();
    enterNode(node);

    // ...

    exitNode(node);
    return node;
  }

  public ASTTypeNode parseType() {
    ASTTypeNode node = new ASTTypeNode();
    enterNode(node);

    // ...

    exitNode(node);
    return node;
  }

    public ASTDoWhileLoopNode parseDoWhileLoop() {
        ASTDoWhileLoopNode node = new ASTDoWhileLoopNode();
        enterNode(node);

        lexer.expect(TokenType.TOK_DO);
        lexer.expect(TokenType.TOK_LBRACE);
        ASTStmtLstNode stmtLst = node.getBody();
        node.addChild(stmtLst);
        lexer.expect(TokenType.TOK_RBRACE);
        lexer.expect(TokenType.TOK_WHILE);
        lexer.expect(TokenType.TOK_LPAREN);
        ASTTernaryExprNode ternaryExprNode =  node.getCondition();
        node.addChild(ternaryExprNode);
        lexer.expect(TokenType.TOK_RPAREN);
        lexer.expect(TokenType.TOK_SEMICOLON);

        exitNode(node);
        return node;
    }

  public ASTAnonymousBlockStmtNode parseAnonymousBlockStmt() {
    ASTAnonymousBlockStmtNode node = new ASTAnonymousBlockStmtNode();
    enterNode(node);

    lexer.expect(TokenType.TOK_LBRACE);
    ASTStmtLstNode stmtLst = parseStmtLst();
    lexer.expect(TokenType.TOK_RBRACE);

    node.addChild(stmtLst);

    exitNode(node);
    return node;
  }

  private void enterNode(ASTNode node) {
    // Attach CodeLoc to AST node
    node.setCodeLoc(lexer.getToken().getCodeLoc());

    if (!parentStack.empty()) {
      // Make sure the node is not pushed twice
      assert parentStack.peek() != node;
      // Link parent and child nodes, so that we can traverse the tree
      ASTNode parent = parentStack.peek();
      parent.addChild(node);
      node.setParent(parent);
    }
    // Push the node onto the stack
    parentStack.push(node);
  }

  private void exitNode(ASTNode node) {
    // Make sure the node is the last one pushed
    assert !parentStack.empty();
    assert parentStack.peek() == node;
    // Remove the node from the stack
    parentStack.pop();
  }

}
