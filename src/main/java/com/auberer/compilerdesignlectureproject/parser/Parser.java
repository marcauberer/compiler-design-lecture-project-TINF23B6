package com.auberer.compilerdesignlectureproject.parser;

import com.auberer.compilerdesignlectureproject.ast.*;
import com.auberer.compilerdesignlectureproject.lexer.ILexer;
import com.auberer.compilerdesignlectureproject.lexer.Token;
import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
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

    Set<TokenType> selectionSet = ASTFunctionDefNode.getSelectionSet();
    if (selectionSet.contains(lexer.getToken().getType())) {
      do {
        parseFctDef();
      } while (!lexer.isEOF());
    } else {
      throw new RuntimeException("Need at least one function definition");
    }

    exitNode(node);
    assert parentStack.empty();
    return node;
  }

  public ASTFunctionDefNode parseFctDef() {
    ASTFunctionDefNode node = new ASTFunctionDefNode();
    enterNode(node);

    parseType();
    Token token = lexer.getToken();
    if (token.getType() == TokenType.TOK_IDENTIFIER) {
      node.setIdentifier(token.getText());
    }else{
      throw new RuntimeException("Unexpected token type: " + token.getType());
    }
    lexer.expect(TokenType.TOK_IDENTIFIER);
    lexer.expect(TokenType.TOK_COLON);
    lexer.expect(TokenType.TOK_ASSIGN);
    lexer.expect(TokenType.TOK_LPAREN);
    Set<TokenType> selectionSet = ASTParamLstNode.getSelectionSet();
    if (selectionSet.contains(lexer.getToken().getType())) {
      parseParamLst();
    }
    lexer.expect(TokenType.TOK_RPAREN);
    lexer.expect(TokenType.TOK_LBRACE);
    parseStmtLst();
    lexer.expect(TokenType.TOK_RBRACE);

    exitNode(node);
    return node;
  }

  public ASTFunctionCallNode parseFctCall() {
    ASTFunctionCallNode node = new ASTFunctionCallNode();
    enterNode(node);

    lexer.expect(TokenType.TOK_CALL);
    Token token = lexer.getToken();
    if (token.getType() == TokenType.TOK_IDENTIFIER) {
      node.setIdentifier(token.getText());
    }else{
      throw new RuntimeException("Unexpected token type: " + token.getType());
    }
    lexer.expect(TokenType.TOK_IDENTIFIER);
    lexer.expect(TokenType.TOK_LPAREN);
    Set<TokenType> selectionSet = ASTArgLstNode.getSelectionSet();
    if (selectionSet.contains(lexer.getToken().getType())) {
      parseArgLst();
    }
    lexer.expect(TokenType.TOK_RPAREN);
    exitNode(node);
    return node;
  }

  public ASTParamLstNode parseParamLst() {
    ASTParamLstNode node = new ASTParamLstNode();
    enterNode(node);

    parseParam();
    while (lexer.getToken().getType().equals(TokenType.TOK_SEMICOLON)) {
      lexer.expect(TokenType.TOK_SEMICOLON);
      parseParam();
    }

    exitNode(node);
    return node;
  }

  private ASTParamNode parseParam() {
    ASTParamNode node = new ASTParamNode();
    enterNode(node);

    parseType();
    lexer.expect(TokenType.TOK_IDENTIFIER);
    if (lexer.getToken().getType() == TokenType.TOK_ASSIGN) {
      lexer.expect(TokenType.TOK_ASSIGN);
      parseAtomicExpr();
    }

    exitNode(node);
    return node;
  }

  public ASTArgLstNode parseArgLst() {
    ASTArgLstNode node = new ASTArgLstNode();
    enterNode(node);

    parseAtomicExpr();
    while (lexer.getToken().getType().equals(TokenType.TOK_SEMICOLON)) {
      lexer.expect(TokenType.TOK_SEMICOLON);
      parseAtomicExpr();
    }

    exitNode(node);
    return node;
  }

  public ASTReturnStmtNode parseReturnStmt() {
    ASTReturnStmtNode node = new ASTReturnStmtNode();
    enterNode(node);

    lexer.expect(TokenType.TOK_RET);
    parseTernaryExpr();
    lexer.expect(TokenType.TOK_SEMICOLON);

    exitNode(node);
    return node;
  }

  public ASTStmtLstNode parseStmtLst() {
    ASTStmtLstNode node = new ASTStmtLstNode();
    enterNode(node);

    Set<TokenType> selectionSet = ASTStmtNode.getSelectionSet();
    while (selectionSet.contains(lexer.getToken().getType())) {
      parseStmt();
    }

    exitNode(node);
    return node;
  }

  public ASTStmtNode parseStmt() {
    ASTStmtNode node = new ASTStmtNode();
    enterNode(node);

    TokenType tokenType = lexer.getToken().getType();
    if (ASTVarDeclNode.getSelectionSet().contains(tokenType)) {
      parseVarDeclStmt();
    } else if (ASTAssignStmtNode.getSelectionSet().contains(tokenType)) {
      parseAssignStmt();
    } else if (ASTReturnStmtNode.getSelectionSet().contains(tokenType)) {
      parseReturnStmt();
    } else if (ASTIfStmtNode.getSelectionSet().contains(tokenType)) {
      parseIfStmt();
    } else if (ASTWhileLoopStmtNode.getSelectionSet().contains(tokenType)) {
      parseWhileLoopStmt();
    } else if (ASTDoWhileLoopNode.getSelectionSet().contains(tokenType)) {
      parseDoWhileLoop();
    } else if (ASTForLoopNode.getSelectionSet().contains(lexer.getToken().getType())) {
      parseForLoop();
    } else if (ASTSwitchCaseStmtNode.getSelectionSet().contains(tokenType)) {
      parseSwitchCaseStmt();
    } else if (ASTAnonymousBlockStmtNode.getSelectionSet().contains(tokenType)) {
      parseAnonymousBlockStmt();
    } else if (ASTFunctionCallNode.getSelectionSet().contains(tokenType)) {
      parseFctCall();
    }

    exitNode(node);
    return node;
  }

  public ASTVarDeclNode parseVarDeclStmt() {
    ASTVarDeclNode node = new ASTVarDeclNode();
    enterNode(node);

    parseType();
    lexer.expect(TokenType.TOK_IDENTIFIER);
    lexer.expect(TokenType.TOK_ASSIGN);
    parseTernaryExpr();
    lexer.expect(TokenType.TOK_SEMICOLON);

    exitNode(node);
    return node;
  }

  public ASTAssignStmtNode parseAssignStmt() {
    ASTAssignStmtNode node = new ASTAssignStmtNode();
    enterNode(node);

    parseAssignExpr();
    lexer.expect(TokenType.TOK_SEMICOLON);

    exitNode(node);
    return node;
  }

  public ASTAssignExprNode parseAssignExpr() {
    ASTAssignExprNode node = new ASTAssignExprNode();
    enterNode(node);

    if (lexer.getToken().getType() == TokenType.TOK_IDENTIFIER) {
      node.setVariableName(lexer.getToken().getText());
      lexer.expect(TokenType.TOK_IDENTIFIER);
      lexer.expect(TokenType.TOK_ASSIGN);
    }
    parseTernaryExpr();

    exitNode(node);
    return node;
  }

  public ASTPrintBuiltinCallNode parsePrintBuiltinCall() {
    ASTPrintBuiltinCallNode node = new ASTPrintBuiltinCallNode();
    enterNode(node);

    lexer.expect(TokenType.TOK_PRINT);
    lexer.expect(TokenType.TOK_LPAREN);
    parseTernaryExpr();
    lexer.expect(TokenType.TOK_RPAREN);

    exitNode(node);
    return node;
  }

  public ASTLiteralNode parseLiteral() {
    ASTLiteralNode node = new ASTLiteralNode();
    enterNode(node);

    lexer.expectOneOf(Set.of(TokenType.TOK_INT_LIT, TokenType.TOK_DOUBLE_LIT, TokenType.TOK_STRING_LIT, TokenType.TOK_TRUE, TokenType.TOK_FALSE));
    node.setValue(lexer.getToken().getText());

    exitNode(node);
    return node;
  }

  public ASTTypeNode parseType() {
    ASTTypeNode node = new ASTTypeNode();
    enterNode(node);

    TokenType tokenType = lexer.getToken().getType();
    if (tokenType == TokenType.TOK_TYPE_INT) {
      node.setType(ASTTypeNode.Type.INT);
    } else if (tokenType == TokenType.TOK_TYPE_DOUBLE) {
      node.setType(ASTTypeNode.Type.DOUBLE);
    } else if (tokenType == TokenType.TOK_TYPE_STRING) {
      node.setType(ASTTypeNode.Type.STRING);
    } else if (tokenType == TokenType.TOK_TYPE_BOOL) {
      node.setType(ASTTypeNode.Type.BOOL);
    } else {
      throw new RuntimeException("Unexpected token type: " + tokenType);
    }
    lexer.expect(tokenType);

    exitNode(node);
    return node;
  }

  public ASTIfStmtNode parseIfStmt() {
    ASTIfStmtNode node = new ASTIfStmtNode();
    enterNode(node);

    lexer.expect(TokenType.TOK_IF);
    lexer.expect(TokenType.TOK_LPAREN);
    parseTernaryExpr();
    lexer.expect(TokenType.TOK_RPAREN);
    parseIfBody();

    if (ASTElseStmtNode.getSelectionSet().contains(lexer.getToken().getType())) {
      parseElseStmt();
    }

    exitNode(node);
    return node;
  }

  public ASTIfBodyNode parseIfBody() {
    ASTIfBodyNode node = new ASTIfBodyNode();
    enterNode(node);

    lexer.expect(TokenType.TOK_LBRACE);
    parseStmtLst();
    lexer.expect(TokenType.TOK_RBRACE);

    exitNode(node);
    return node;
  }

  public ASTElseStmtNode parseElseStmt() {
    ASTElseStmtNode node = new ASTElseStmtNode();
    enterNode(node);

    lexer.expect(TokenType.TOK_ELSE);

    TokenType tokenType = lexer.getToken().getType();
    if (ASTIfStmtNode.getSelectionSet().contains(tokenType)) {
      node.setContainsIfStmt(true);
      parseIfStmt();
    } else if (ASTIfBodyNode.getSelectionSet().contains(tokenType)) {
      node.setContainsIfStmt(false);
      parseIfBody();
    }

    exitNode(node);
    return node;
  }

  /**
   * Parses a while loop
   *
   * @return the AST node representing the while loop statement
   * Rule: whileLoop: WHILE LPAREN ternaryExpr RPAREN LBRACE stmtLst RBRACE;
   */
  public ASTWhileLoopStmtNode parseWhileLoopStmt() {

    ASTWhileLoopStmtNode node = new ASTWhileLoopStmtNode();
    enterNode(node);
    lexer.expect(TokenType.TOK_WHILE);
    lexer.expect(TokenType.TOK_LPAREN);
    parseTernaryExpr();
    lexer.expect(TokenType.TOK_RPAREN);
    lexer.expect(TokenType.TOK_LBRACE);
    parseStmtLst();
    lexer.expect(TokenType.TOK_RBRACE);

    exitNode(node);
    return node;
  }


  public ASTDoWhileLoopNode parseDoWhileLoop() {
    ASTDoWhileLoopNode node = new ASTDoWhileLoopNode();
    enterNode(node);

    lexer.expect(TokenType.TOK_DO);
    lexer.expect(TokenType.TOK_LBRACE);
    parseStmtLst();
    lexer.expect(TokenType.TOK_RBRACE);
    lexer.expect(TokenType.TOK_WHILE);
    lexer.expect(TokenType.TOK_LPAREN);
    parseTernaryExpr();
    lexer.expect(TokenType.TOK_RPAREN);
    lexer.expect(TokenType.TOK_SEMICOLON);

    exitNode(node);
    return node;
  }

  public ASTForLoopNode parseForLoop() {
    ASTForLoopNode node = new ASTForLoopNode();
    enterNode(node);

    lexer.expect(TokenType.TOK_FOR);
    lexer.expect(TokenType.TOK_LPAREN);
    parseVarDeclStmt();
    parseTernaryExpr();
    lexer.expect(TokenType.TOK_SEMICOLON);
    parseAssignExpr();
    lexer.expect(TokenType.TOK_RPAREN);
    lexer.expect(TokenType.TOK_LBRACE);
    parseStmtLst();
    lexer.expect(TokenType.TOK_RBRACE);

    exitNode(node);
    return node;
  }

  public ASTSwitchCaseStmtNode parseSwitchCaseStmt() {
    ASTSwitchCaseStmtNode node = new ASTSwitchCaseStmtNode();
    enterNode(node);

    lexer.expect(TokenType.TOK_SWITCH);
    lexer.expect(TokenType.TOK_LPAREN);
    parseTernaryExpr();
    lexer.expect(TokenType.TOK_RPAREN);
    lexer.expect(TokenType.TOK_LBRACE);

    do {
      parseCaseStmt();
    } while (lexer.getToken().getType() == TokenType.TOK_CASE);

    if (lexer.getToken().getType() == TokenType.TOK_DEFAULT) {
      parseDefaultStmt();
    }

    lexer.expect(TokenType.TOK_RBRACE);

    exitNode(node);
    return node;
  }

  public ASTCaseStmtNode parseCaseStmt() {
    ASTCaseStmtNode node = new ASTCaseStmtNode();
    enterNode(node);

    lexer.expect(TokenType.TOK_CASE);
    parseLiteral();
    lexer.expect(TokenType.TOK_COLON);
    parseStmtLst();

    exitNode(node);
    return node;
  }

  public ASTDefaultStmtNode parseDefaultStmt() {
    ASTDefaultStmtNode node = new ASTDefaultStmtNode();
    enterNode(node);

    lexer.expect(TokenType.TOK_DEFAULT);
    lexer.expect(TokenType.TOK_COLON);
    parseStmtLst();

    exitNode(node);
    return node;
  }

  public ASTAnonymousBlockStmtNode parseAnonymousBlockStmt() {
    ASTAnonymousBlockStmtNode node = new ASTAnonymousBlockStmtNode();
    enterNode(node);

    lexer.expect(TokenType.TOK_LBRACE);
    parseStmtLst();
    lexer.expect(TokenType.TOK_RBRACE);

    exitNode(node);
    return node;
  }

  public ASTTernaryExprNode parseTernaryExpr() {
    ASTTernaryExprNode node = new ASTTernaryExprNode();
    enterNode(node);

    parseEqualityExpr();

    TokenType tokenType = lexer.getToken().getType();
    if (tokenType == TokenType.TOK_QUESTION_MARK) {
      node.setExpanded(true);

      lexer.expect(TokenType.TOK_QUESTION_MARK);
      parseEqualityExpr();

      lexer.expect(TokenType.TOK_COLON);
      parseEqualityExpr();
    }

    exitNode(node);
    return node;
  }

  public ASTEqualityExprNode parseEqualityExpr() {
    ASTEqualityExprNode node = new ASTEqualityExprNode();
    enterNode(node);

    parseAdditiveExpr();

    TokenType tokenType = lexer.getToken().getType();
    if (tokenType == TokenType.TOK_EQUAL || tokenType == TokenType.TOK_NOT_EQUAL) {
      lexer.expectOneOf(Set.of(TokenType.TOK_EQUAL, TokenType.TOK_NOT_EQUAL));
      parseAdditiveExpr();
    }

    exitNode(node);
    return node;
  }

  public ASTAdditiveExprNode parseAdditiveExpr() {
    ASTAdditiveExprNode node = new ASTAdditiveExprNode();
    enterNode(node);

    parseMultiplicativeExpr();

    while (Set.of(TokenType.TOK_PLUS, TokenType.TOK_MINUS).contains(lexer.getToken().getType())) {
      lexer.expectOneOf(Set.of(TokenType.TOK_PLUS, TokenType.TOK_MINUS));
      parseMultiplicativeExpr();
    }

    exitNode(node);
    return node;
  }

  public ASTMultiplicativeExprNode parseMultiplicativeExpr() {
    ASTMultiplicativeExprNode node = new ASTMultiplicativeExprNode();
    enterNode(node);

    parseAtomicExpr();

    while (Set.of(TokenType.TOK_MUL, TokenType.TOK_DIV).contains(lexer.getToken().getType())) {
      lexer.expectOneOf(Set.of(TokenType.TOK_MUL, TokenType.TOK_DIV));
      parseAtomicExpr();
    }

    exitNode(node);
    return node;
  }

  public ASTAtomicExprNode parseAtomicExpr() {
    ASTAtomicExprNode node = new ASTAtomicExprNode();
    enterNode(node);

    TokenType tokenType = lexer.getToken().getType();
    if (ASTLiteralNode.getSelectionSet().contains(tokenType)) {
      parseLiteral();
    } else if (ASTFunctionCallNode.getSelectionSet().contains(tokenType)) {
      parseFctCall();
    } else if (ASTPrintBuiltinCallNode.getSelectionSet().contains(tokenType)) {
      parsePrintBuiltinCall();
    } else if (tokenType == TokenType.TOK_IDENTIFIER) {
      node.setVariableName(lexer.getToken().getText());
      lexer.expect(TokenType.TOK_IDENTIFIER);
    } else if (tokenType == TokenType.TOK_LPAREN) {
      lexer.expect(TokenType.TOK_LPAREN);
      parseTernaryExpr();
      lexer.expect(TokenType.TOK_RPAREN);
    }

    exitNode(node);
    return node;
  }

  private void enterNode(ASTNode node) {
    // Attach CodeLoc to AST node
    node.setCodeLoc(lexer.getToken().getCodeLoc());

    if (!parentStack.empty()) {
      // Make sure the node is not pushed twice
      assert parentStack.peek() != node;
      // Link parent and child nodes so that we can traverse the tree
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
