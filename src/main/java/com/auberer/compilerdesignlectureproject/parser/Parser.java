package com.auberer.compilerdesignlectureproject.parser;

import com.auberer.compilerdesignlectureproject.ast.*;
import com.auberer.compilerdesignlectureproject.lexer.ILexer;
import com.auberer.compilerdesignlectureproject.lexer.TokenType;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
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

    do {
      parseFctDef();
    } while (!lexer.isEOF());

    exitNode(node);
    return node;
  }

  public void parseFctDef() {
    // ToDo(Team 4)
  }

  public ASTStmtLstNode parseStmtLst() {
    ASTStmtLstNode node = new ASTStmtLstNode();
    enterNode(node);

    Set<TokenType> selectionSet = ASTStmtNode.getSelectionSet();
    while (selectionSet.contains(lexer.getToken().getType())) {
      ASTStmtNode stmt = parseStmt();
      node.addChild(stmt);
    }

    exitNode(node);
    return node;
  }

  public ASTStmtNode parseStmt() {
    ASTStmtNode node = new ASTStmtNode();
    enterNode(node);

    TokenType tokenType = lexer.getToken().getType();
    ASTNode childNode = null;
    if (ASTVarDeclNode.getSelectionSet().contains(tokenType)) {
      childNode = parseVarDeclStmt();
    } else if (ASTAssignStmtNode.getSelectionSet().contains(tokenType)) {
      childNode = parseAssignStmt();
    } else if (ASTDoWhileLoopNode.getSelectionSet().contains(tokenType)) {
      childNode = parseDoWhileLoop();
    } else if (ASTAnonymousBlockStmtNode.getSelectionSet().contains(tokenType)) {
      childNode = parseAnonymousBlockStmt();
    }
    else if (ASTWhileLoopStmtNode.getSelectionSet().contains(tokenType)){
      childNode = parseWhileLoopStmt();
    }
    // ToDo(Marc): Add others
    node.addChild(childNode);

    exitNode(node);
    return node;
  }

  public ASTVarDeclNode parseVarDeclStmt() {
    ASTVarDeclNode node = new ASTVarDeclNode();
    enterNode(node);

    ASTTypeNode typeNode = parseType();
    node.addChild(typeNode);
    lexer.expect(TokenType.TOK_IDENTIFIER);
    lexer.expect(TokenType.TOK_ASSIGN);
    ASTTernaryExprNode ternaryExprNode = parseTernaryExpr();
    node.addChild(ternaryExprNode);
    lexer.expect(TokenType.TOK_SEMICOLON);

    exitNode(node);
    return node;
  }

  public ASTAssignStmtNode parseAssignStmt() {
    ASTAssignStmtNode node = new ASTAssignStmtNode();
    enterNode(node);

    ASTAssignExprNode assignExprNode = parseAssignExpr();
    node.addChild(assignExprNode);
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
    ASTTernaryExprNode ternaryExprNode = parseTernaryExpr();
    node.addChild(ternaryExprNode);

    exitNode(node);
    return node;
  }

  public ASTPrintBuiltinCallNode parsePrintBuiltinCall() {
    ASTPrintBuiltinCallNode node = new ASTPrintBuiltinCallNode();
    enterNode(node);

    lexer.expect(TokenType.TOK_PRINT);
    lexer.expect(TokenType.TOK_LPAREN);
    ASTTernaryExprNode ternaryExprNode = parseTernaryExpr();
    node.addChild(ternaryExprNode);
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

    exitNode(node);
    return node;
  }


    /**
     * Parses a while loop statement.
     *
     * @return the AST node representing the while loop statement
     * Rule : whileLoop: WHILE LPAREN ternaryExpr RPAREN LBRACE stmtLst RBRACE;
     */
    public ASTWhileLoopStmtNode parseWhileLoopStmt() {

      ASTWhileLoopStmtNode node = new ASTWhileLoopStmtNode();
      enterNode(node);
      lexer.expect(TokenType.TOK_WHILE);
      lexer.expect(TokenType.TOK_LPAREN);

      ASTTernaryExprNode ternaryExprNode = parseTernaryExpr();
      node.addChild(ternaryExprNode);

      lexer.expect(TokenType.TOK_RPAREN);
      lexer.expect(TokenType.TOK_LBRACE);

      ASTStmtLstNode stmtLst = parseStmtLst();
      node.addChild(stmtLst);

      lexer.expect(TokenType.TOK_RBRACE);

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

  public ASTTernaryExprNode parseTernaryExpr() {
    ASTTernaryExprNode node = new ASTTernaryExprNode();
    enterNode(node);

    ASTEqualityExprNode condition = parseEqualityExpr();
    node.addChild(condition);

    TokenType tokenType = lexer.getToken().getType();
    if (tokenType == TokenType.TOK_QUESTION_MARK) {
      node.setExpanded(true);

      lexer.expect(TokenType.TOK_QUESTION_MARK);
      ASTEqualityExprNode thenExpr = parseEqualityExpr();
      node.addChild(thenExpr);

      lexer.expect(TokenType.TOK_COLON);
      ASTEqualityExprNode elseExpr = parseEqualityExpr();
      node.addChild(elseExpr);
    }

    exitNode(node);
    return node;
  }

  public ASTEqualityExprNode parseEqualityExpr() {
    ASTEqualityExprNode node = new ASTEqualityExprNode();
    enterNode(node);

    ASTAdditiveExprNode lhs = parseAdditiveExpr();
    node.addChild(lhs);

    TokenType tokenType = lexer.getToken().getType();
    if (tokenType == TokenType.TOK_EQUAL || tokenType == TokenType.TOK_NOT_EQUAL) {
      lexer.expectOneOf(Set.of(TokenType.TOK_EQUAL, TokenType.TOK_NOT_EQUAL));
      ASTAdditiveExprNode rhs = parseAdditiveExpr();
      node.addChild(rhs);
    }

    exitNode(node);
    return node;
  }

  public ASTAdditiveExprNode parseAdditiveExpr() {
    ASTAdditiveExprNode node = new ASTAdditiveExprNode();
    enterNode(node);

    ASTMultiplicativeExprNode lhs = parseMultiplicativeExpr();
    node.addChild(lhs);

    while (Set.of(TokenType.TOK_PLUS, TokenType.TOK_MINUS).contains(lexer.getToken().getType())) {
      lexer.expectOneOf(Set.of(TokenType.TOK_PLUS, TokenType.TOK_MINUS));
      ASTMultiplicativeExprNode rhs = parseMultiplicativeExpr();
      node.addChild(rhs);
    }

    exitNode(node);
    return node;
  }

  public ASTMultiplicativeExprNode parseMultiplicativeExpr() {
    ASTMultiplicativeExprNode node = new ASTMultiplicativeExprNode();
    enterNode(node);

    ASTAtomicExprNode lhs = parseAtomicExpr();
    node.addChild(lhs);

    while (Set.of(TokenType.TOK_MUL, TokenType.TOK_DIV).contains(lexer.getToken().getType())) {
      lexer.expectOneOf(Set.of(TokenType.TOK_MUL, TokenType.TOK_DIV));
      ASTAtomicExprNode rhs = parseAtomicExpr();
      node.addChild(rhs);
    }

    exitNode(node);
    return node;
  }

  public ASTAtomicExprNode parseAtomicExpr() {
    ASTAtomicExprNode node = new ASTAtomicExprNode();
    enterNode(node);

    TokenType tokenType = lexer.getToken().getType();
    ASTNode childNode = null;
    if (ASTLiteralNode.getSelectionSet().contains(tokenType)) {
      childNode = parseLiteral();
    } else if (ASTPrintBuiltinCallNode.getSelectionSet().contains(tokenType)) {
      childNode = parsePrintBuiltinCall();
    } else if (tokenType == TokenType.TOK_IDENTIFIER) {
      node.setVariableName(lexer.getToken().getText());
      lexer.expect(TokenType.TOK_IDENTIFIER);
    } else if (tokenType == TokenType.TOK_LPAREN) {
      lexer.expect(TokenType.TOK_LPAREN);
      childNode = parseTernaryExpr();
      lexer.expect(TokenType.TOK_RPAREN);
    }
    // ToDo(Marc): Add others
    node.addChild(childNode);

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
