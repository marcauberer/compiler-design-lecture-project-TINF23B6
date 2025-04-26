// Generated from TInf.g4 by ANTLR 4.13.2
package com.auberer.compilerdesignlectureproject.antlr.gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TInfParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		QUESTION_MARK=1, COLON=2, ASSIGN=3, EQUALS=4, NOT_EQUALS=5, PLUS=6, MINUS=7, 
		MUL=8, DIV=9, LPAREN=10, RPAREN=11, LBRACE=12, RBRACE=13, SEMICOLON=14, 
		IF=15, ELSE=16, WHILE=17, DO=18, FOR=19, SWITCH=20, CASE=21, DEFAULT=22, 
		RET=23, PRINT=24, TYPE_INT=25, TYPE_DOUBLE=26, TYPE_STRING=27, TYPE_BOOL=28, 
		CALL=29, INT_LIT=30, DOUBLE_LIT=31, STRING_LIT=32, IDENTIFIER=33, LINE_COMMENT=34,
		WS=35;
	public static final int
		RULE_entry = 0, RULE_stmtLst = 1, RULE_stmt = 2, RULE_varDeclStmt = 3, 
		RULE_assignStmt = 4, RULE_assignExpr = 5, RULE_printBuiltinCall = 6, RULE_literal = 7, 
		RULE_type = 8, RULE_ifStmt = 9, RULE_ifBody = 10, RULE_elseStmt = 11, 
		RULE_whileLoop = 12, RULE_doWhileLoop = 13, RULE_fctDef = 14, RULE_paramLst = 15, 
		RULE_param = 16, RULE_fctCall = 17, RULE_argLst = 18, RULE_returnStmt = 19, 
		RULE_forLoop = 20, RULE_switchCaseStmt = 21, RULE_caseBlockLst = 22, RULE_caseBlock = 23, 
		RULE_defaultBlock = 24, RULE_anonymousBlockStmt = 25, RULE_ternaryExpr = 26, 
		RULE_equalityExpr = 27, RULE_additiveExpr = 28, RULE_multiplicativeExpr = 29, 
		RULE_atomicExpr = 30;
	private static String[] makeRuleNames() {
		return new String[] {
			"entry", "stmtLst", "stmt", "varDeclStmt", "assignStmt", "assignExpr", 
			"printBuiltinCall", "literal", "type", "ifStmt", "ifBody", "elseStmt", 
			"whileLoop", "doWhileLoop", "fctDef", "paramLst", "param", "fctCall", 
			"argLst", "returnStmt", "forLoop", "switchCaseStmt", "caseBlockLst", 
			"caseBlock", "defaultBlock", "anonymousBlockStmt", "ternaryExpr", "equalityExpr", 
			"additiveExpr", "multiplicativeExpr", "atomicExpr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'?'", "':'", "'='", "'=='", "'!='", "'+'", "'-'", "'*'", "'/'", 
			"'('", "')'", "'{'", "'}'", "';'", "'if'", "'else'", "'while'", "'do'", 
			"'for'", "'switch'", "'case'", "'default'", "'ret'", "'print'", "'int'", 
			"'double'", "'string'", "'bool'", "'call'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "QUESTION_MARK", "COLON", "ASSIGN", "EQUALS", "NOT_EQUALS", "PLUS", 
			"MINUS", "MUL", "DIV", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "SEMICOLON", 
			"IF", "ELSE", "WHILE", "DO", "FOR", "SWITCH", "CASE", "DEFAULT", "RET", 
			"PRINT", "TYPE_INT", "TYPE_DOUBLE", "TYPE_STRING", "TYPE_BOOL", "CALL",
			"INT_LIT", "DOUBLE_LIT", "STRING_LIT", "IDENTIFIER", "LINE_COMMENT",
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TInf.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TInfParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EntryContext extends ParserRuleContext {
		public List<FctDefContext> fctDef() {
			return getRuleContexts(FctDefContext.class);
		}
		public FctDefContext fctDef(int i) {
			return getRuleContext(FctDefContext.class,i);
		}
		public EntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntryContext entry() throws RecognitionException {
		EntryContext _localctx = new EntryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_entry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(62);
				fctDef();
				}
				}
				setState(65); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 503316480L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtLstContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public StmtLstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtLst; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitStmtLst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtLstContext stmtLst() throws RecognitionException {
		StmtLstContext _localctx = new StmtLstContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stmtLst);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17173484544L) != 0)) {
				{
				{
				setState(67);
				stmt();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public VarDeclStmtContext varDeclStmt() {
			return getRuleContext(VarDeclStmtContext.class,0);
		}
		public AssignStmtContext assignStmt() {
			return getRuleContext(AssignStmtContext.class,0);
		}
		public ReturnStmtContext returnStmt() {
			return getRuleContext(ReturnStmtContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public WhileLoopContext whileLoop() {
			return getRuleContext(WhileLoopContext.class,0);
		}
		public DoWhileLoopContext doWhileLoop() {
			return getRuleContext(DoWhileLoopContext.class,0);
		}
		public ForLoopContext forLoop() {
			return getRuleContext(ForLoopContext.class,0);
		}
		public SwitchCaseStmtContext switchCaseStmt() {
			return getRuleContext(SwitchCaseStmtContext.class,0);
		}
		public AnonymousBlockStmtContext anonymousBlockStmt() {
			return getRuleContext(AnonymousBlockStmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_stmt);
		try {
			setState(82);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE_INT:
			case TYPE_DOUBLE:
			case TYPE_STRING:
			case TYPE_BOOL:
				enterOuterAlt(_localctx, 1);
				{
				setState(73);
				varDeclStmt();
				}
				break;
			case LPAREN:
			case PRINT:
			case CALL:
			case INT_LIT:
			case DOUBLE_LIT:
			case STRING_LIT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				assignStmt();
				}
				break;
			case RET:
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				returnStmt();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 4);
				{
				setState(76);
				ifStmt();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 5);
				{
				setState(77);
				whileLoop();
				}
				break;
			case DO:
				enterOuterAlt(_localctx, 6);
				{
				setState(78);
				doWhileLoop();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 7);
				{
				setState(79);
				forLoop();
				}
				break;
			case SWITCH:
				enterOuterAlt(_localctx, 8);
				{
				setState(80);
				switchCaseStmt();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 9);
				{
				setState(81);
				anonymousBlockStmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclStmtContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TInfParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(TInfParser.ASSIGN, 0); }
		public TernaryExprContext ternaryExpr() {
			return getRuleContext(TernaryExprContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(TInfParser.SEMICOLON, 0); }
		public VarDeclStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitVarDeclStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclStmtContext varDeclStmt() throws RecognitionException {
		VarDeclStmtContext _localctx = new VarDeclStmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varDeclStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			type();
			setState(85);
			match(IDENTIFIER);
			setState(86);
			match(ASSIGN);
			setState(87);
			ternaryExpr();
			setState(88);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignStmtContext extends ParserRuleContext {
		public AssignExprContext assignExpr() {
			return getRuleContext(AssignExprContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(TInfParser.SEMICOLON, 0); }
		public AssignStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitAssignStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignStmtContext assignStmt() throws RecognitionException {
		AssignStmtContext _localctx = new AssignStmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assignStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			assignExpr();
			setState(91);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignExprContext extends ParserRuleContext {
		public TernaryExprContext ternaryExpr() {
			return getRuleContext(TernaryExprContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TInfParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(TInfParser.ASSIGN, 0); }
		public AssignExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignExprContext assignExpr() throws RecognitionException {
		AssignExprContext _localctx = new AssignExprContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(93);
				match(IDENTIFIER);
				setState(94);
				match(ASSIGN);
				}
				break;
			}
			setState(97);
			ternaryExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrintBuiltinCallContext extends ParserRuleContext {
		public TerminalNode PRINT() { return getToken(TInfParser.PRINT, 0); }
		public TerminalNode LPAREN() { return getToken(TInfParser.LPAREN, 0); }
		public TernaryExprContext ternaryExpr() {
			return getRuleContext(TernaryExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TInfParser.RPAREN, 0); }
		public PrintBuiltinCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printBuiltinCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitPrintBuiltinCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintBuiltinCallContext printBuiltinCall() throws RecognitionException {
		PrintBuiltinCallContext _localctx = new PrintBuiltinCallContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_printBuiltinCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(PRINT);
			setState(100);
			match(LPAREN);
			setState(101);
			ternaryExpr();
			setState(102);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode INT_LIT() { return getToken(TInfParser.INT_LIT, 0); }
		public TerminalNode DOUBLE_LIT() { return getToken(TInfParser.DOUBLE_LIT, 0); }
		public TerminalNode STRING_LIT() { return getToken(TInfParser.STRING_LIT, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7516192768L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode TYPE_INT() { return getToken(TInfParser.TYPE_INT, 0); }
		public TerminalNode TYPE_DOUBLE() { return getToken(TInfParser.TYPE_DOUBLE, 0); }
		public TerminalNode TYPE_STRING() { return getToken(TInfParser.TYPE_STRING, 0); }
		public TerminalNode TYPE_BOOL() { return getToken(TInfParser.TYPE_BOOL, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 503316480L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(TInfParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(TInfParser.LPAREN, 0); }
		public TernaryExprContext ternaryExpr() {
			return getRuleContext(TernaryExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TInfParser.RPAREN, 0); }
		public IfBodyContext ifBody() {
			return getRuleContext(IfBodyContext.class,0);
		}
		public ElseStmtContext elseStmt() {
			return getRuleContext(ElseStmtContext.class,0);
		}
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ifStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(IF);
			setState(109);
			match(LPAREN);
			setState(110);
			ternaryExpr();
			setState(111);
			match(RPAREN);
			setState(112);
			ifBody();
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(113);
				elseStmt();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(TInfParser.LBRACE, 0); }
		public StmtLstContext stmtLst() {
			return getRuleContext(StmtLstContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(TInfParser.RBRACE, 0); }
		public IfBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitIfBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfBodyContext ifBody() throws RecognitionException {
		IfBodyContext _localctx = new IfBodyContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ifBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(LBRACE);
			setState(117);
			stmtLst();
			setState(118);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElseStmtContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(TInfParser.ELSE, 0); }
		public IfBodyContext ifBody() {
			return getRuleContext(IfBodyContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public ElseStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitElseStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseStmtContext elseStmt() throws RecognitionException {
		ElseStmtContext _localctx = new ElseStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_elseStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(ELSE);
			setState(123);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(121);
				ifBody();
				}
				break;
			case IF:
				{
				setState(122);
				ifStmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileLoopContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(TInfParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(TInfParser.LPAREN, 0); }
		public TernaryExprContext ternaryExpr() {
			return getRuleContext(TernaryExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TInfParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(TInfParser.LBRACE, 0); }
		public StmtLstContext stmtLst() {
			return getRuleContext(StmtLstContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(TInfParser.RBRACE, 0); }
		public WhileLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileLoop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitWhileLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileLoopContext whileLoop() throws RecognitionException {
		WhileLoopContext _localctx = new WhileLoopContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_whileLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(WHILE);
			setState(126);
			match(LPAREN);
			setState(127);
			ternaryExpr();
			setState(128);
			match(RPAREN);
			setState(129);
			match(LBRACE);
			setState(130);
			stmtLst();
			setState(131);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DoWhileLoopContext extends ParserRuleContext {
		public TerminalNode DO() { return getToken(TInfParser.DO, 0); }
		public TerminalNode LBRACE() { return getToken(TInfParser.LBRACE, 0); }
		public StmtLstContext stmtLst() {
			return getRuleContext(StmtLstContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(TInfParser.RBRACE, 0); }
		public TerminalNode WHILE() { return getToken(TInfParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(TInfParser.LPAREN, 0); }
		public TernaryExprContext ternaryExpr() {
			return getRuleContext(TernaryExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TInfParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(TInfParser.SEMICOLON, 0); }
		public DoWhileLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doWhileLoop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitDoWhileLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoWhileLoopContext doWhileLoop() throws RecognitionException {
		DoWhileLoopContext _localctx = new DoWhileLoopContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_doWhileLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(DO);
			setState(134);
			match(LBRACE);
			setState(135);
			stmtLst();
			setState(136);
			match(RBRACE);
			setState(137);
			match(WHILE);
			setState(138);
			match(LPAREN);
			setState(139);
			ternaryExpr();
			setState(140);
			match(RPAREN);
			setState(141);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FctDefContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TInfParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(TInfParser.COLON, 0); }
		public TerminalNode ASSIGN() { return getToken(TInfParser.ASSIGN, 0); }
		public TerminalNode LPAREN() { return getToken(TInfParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(TInfParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(TInfParser.LBRACE, 0); }
		public StmtLstContext stmtLst() {
			return getRuleContext(StmtLstContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(TInfParser.RBRACE, 0); }
		public ParamLstContext paramLst() {
			return getRuleContext(ParamLstContext.class,0);
		}
		public FctDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fctDef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitFctDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FctDefContext fctDef() throws RecognitionException {
		FctDefContext _localctx = new FctDefContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_fctDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			type();
			setState(144);
			match(IDENTIFIER);
			setState(145);
			match(COLON);
			setState(146);
			match(ASSIGN);
			setState(147);
			match(LPAREN);
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 503316480L) != 0)) {
				{
				setState(148);
				paramLst();
				}
			}

			setState(151);
			match(RPAREN);
			setState(152);
			match(LBRACE);
			setState(153);
			stmtLst();
			setState(154);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamLstContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(TInfParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(TInfParser.SEMICOLON, i);
		}
		public ParamLstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramLst; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitParamLst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamLstContext paramLst() throws RecognitionException {
		ParamLstContext _localctx = new ParamLstContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_paramLst);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			param();
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(157);
				match(SEMICOLON);
				setState(158);
				param();
				}
				}
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TInfParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(TInfParser.ASSIGN, 0); }
		public AtomicExprContext atomicExpr() {
			return getRuleContext(AtomicExprContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_param);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			type();
			setState(165);
			match(IDENTIFIER);
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(166);
				match(ASSIGN);
				setState(167);
				atomicExpr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FctCallContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TInfParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(TInfParser.LPAREN, 0); }
		public ArgLstContext argLst() {
			return getRuleContext(ArgLstContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TInfParser.RPAREN, 0); }
		public FctCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fctCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitFctCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FctCallContext fctCall() throws RecognitionException {
		FctCallContext _localctx = new FctCallContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_fctCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(IDENTIFIER);
			setState(171);
			match(LPAREN);
			setState(172);
			argLst();
			setState(173);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgLstContext extends ParserRuleContext {
		public List<AtomicExprContext> atomicExpr() {
			return getRuleContexts(AtomicExprContext.class);
		}
		public AtomicExprContext atomicExpr(int i) {
			return getRuleContext(AtomicExprContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(TInfParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(TInfParser.SEMICOLON, i);
		}
		public ArgLstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argLst; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitArgLst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgLstContext argLst() throws RecognitionException {
		ArgLstContext _localctx = new ArgLstContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_argLst);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			atomicExpr();
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(176);
				match(SEMICOLON);
				setState(177);
				atomicExpr();
				}
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStmtContext extends ParserRuleContext {
		public TerminalNode RET() { return getToken(TInfParser.RET, 0); }
		public TernaryExprContext ternaryExpr() {
			return getRuleContext(TernaryExprContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(TInfParser.SEMICOLON, 0); }
		public ReturnStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStmtContext returnStmt() throws RecognitionException {
		ReturnStmtContext _localctx = new ReturnStmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_returnStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(RET);
			setState(184);
			ternaryExpr();
			setState(185);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForLoopContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(TInfParser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(TInfParser.LPAREN, 0); }
		public VarDeclStmtContext varDeclStmt() {
			return getRuleContext(VarDeclStmtContext.class,0);
		}
		public TernaryExprContext ternaryExpr() {
			return getRuleContext(TernaryExprContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(TInfParser.SEMICOLON, 0); }
		public AssignExprContext assignExpr() {
			return getRuleContext(AssignExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TInfParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(TInfParser.LBRACE, 0); }
		public StmtLstContext stmtLst() {
			return getRuleContext(StmtLstContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(TInfParser.RBRACE, 0); }
		public ForLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forLoop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitForLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForLoopContext forLoop() throws RecognitionException {
		ForLoopContext _localctx = new ForLoopContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_forLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(FOR);
			setState(188);
			match(LPAREN);
			setState(189);
			varDeclStmt();
			setState(190);
			ternaryExpr();
			setState(191);
			match(SEMICOLON);
			setState(192);
			assignExpr();
			setState(193);
			match(RPAREN);
			setState(194);
			match(LBRACE);
			setState(195);
			stmtLst();
			setState(196);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchCaseStmtContext extends ParserRuleContext {
		public TerminalNode SWITCH() { return getToken(TInfParser.SWITCH, 0); }
		public TerminalNode LPAREN() { return getToken(TInfParser.LPAREN, 0); }
		public TernaryExprContext ternaryExpr() {
			return getRuleContext(TernaryExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TInfParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(TInfParser.LBRACE, 0); }
		public CaseBlockLstContext caseBlockLst() {
			return getRuleContext(CaseBlockLstContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(TInfParser.RBRACE, 0); }
		public DefaultBlockContext defaultBlock() {
			return getRuleContext(DefaultBlockContext.class,0);
		}
		public SwitchCaseStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchCaseStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitSwitchCaseStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchCaseStmtContext switchCaseStmt() throws RecognitionException {
		SwitchCaseStmtContext _localctx = new SwitchCaseStmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_switchCaseStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(SWITCH);
			setState(199);
			match(LPAREN);
			setState(200);
			ternaryExpr();
			setState(201);
			match(RPAREN);
			setState(202);
			match(LBRACE);
			setState(203);
			caseBlockLst();
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(204);
				defaultBlock();
				}
			}

			setState(207);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaseBlockLstContext extends ParserRuleContext {
		public List<CaseBlockContext> caseBlock() {
			return getRuleContexts(CaseBlockContext.class);
		}
		public CaseBlockContext caseBlock(int i) {
			return getRuleContext(CaseBlockContext.class,i);
		}
		public CaseBlockLstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseBlockLst; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitCaseBlockLst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseBlockLstContext caseBlockLst() throws RecognitionException {
		CaseBlockLstContext _localctx = new CaseBlockLstContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_caseBlockLst);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(209);
				caseBlock();
				}
				}
				setState(212); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CASE );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaseBlockContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(TInfParser.CASE, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TInfParser.COLON, 0); }
		public StmtLstContext stmtLst() {
			return getRuleContext(StmtLstContext.class,0);
		}
		public CaseBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitCaseBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseBlockContext caseBlock() throws RecognitionException {
		CaseBlockContext _localctx = new CaseBlockContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_caseBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(CASE);
			setState(215);
			literal();
			setState(216);
			match(COLON);
			setState(217);
			stmtLst();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefaultBlockContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(TInfParser.DEFAULT, 0); }
		public TerminalNode COLON() { return getToken(TInfParser.COLON, 0); }
		public StmtLstContext stmtLst() {
			return getRuleContext(StmtLstContext.class,0);
		}
		public DefaultBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitDefaultBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultBlockContext defaultBlock() throws RecognitionException {
		DefaultBlockContext _localctx = new DefaultBlockContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_defaultBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(DEFAULT);
			setState(220);
			match(COLON);
			setState(221);
			stmtLst();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnonymousBlockStmtContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(TInfParser.LBRACE, 0); }
		public StmtLstContext stmtLst() {
			return getRuleContext(StmtLstContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(TInfParser.RBRACE, 0); }
		public AnonymousBlockStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonymousBlockStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitAnonymousBlockStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnonymousBlockStmtContext anonymousBlockStmt() throws RecognitionException {
		AnonymousBlockStmtContext _localctx = new AnonymousBlockStmtContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_anonymousBlockStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(LBRACE);
			setState(224);
			stmtLst();
			setState(225);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TernaryExprContext extends ParserRuleContext {
		public List<EqualityExprContext> equalityExpr() {
			return getRuleContexts(EqualityExprContext.class);
		}
		public EqualityExprContext equalityExpr(int i) {
			return getRuleContext(EqualityExprContext.class,i);
		}
		public TerminalNode QUESTION_MARK() { return getToken(TInfParser.QUESTION_MARK, 0); }
		public TerminalNode COLON() { return getToken(TInfParser.COLON, 0); }
		public TernaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ternaryExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitTernaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TernaryExprContext ternaryExpr() throws RecognitionException {
		TernaryExprContext _localctx = new TernaryExprContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_ternaryExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			equalityExpr();
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION_MARK) {
				{
				setState(228);
				match(QUESTION_MARK);
				setState(229);
				equalityExpr();
				setState(230);
				match(COLON);
				setState(231);
				equalityExpr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EqualityExprContext extends ParserRuleContext {
		public List<AdditiveExprContext> additiveExpr() {
			return getRuleContexts(AdditiveExprContext.class);
		}
		public AdditiveExprContext additiveExpr(int i) {
			return getRuleContext(AdditiveExprContext.class,i);
		}
		public TerminalNode EQUALS() { return getToken(TInfParser.EQUALS, 0); }
		public TerminalNode NOT_EQUALS() { return getToken(TInfParser.NOT_EQUALS, 0); }
		public EqualityExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitEqualityExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityExprContext equalityExpr() throws RecognitionException {
		EqualityExprContext _localctx = new EqualityExprContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_equalityExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			additiveExpr();
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQUALS || _la==NOT_EQUALS) {
				{
				setState(236);
				_la = _input.LA(1);
				if ( !(_la==EQUALS || _la==NOT_EQUALS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(237);
				additiveExpr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveExprContext extends ParserRuleContext {
		public List<MultiplicativeExprContext> multiplicativeExpr() {
			return getRuleContexts(MultiplicativeExprContext.class);
		}
		public MultiplicativeExprContext multiplicativeExpr(int i) {
			return getRuleContext(MultiplicativeExprContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(TInfParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(TInfParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(TInfParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(TInfParser.MINUS, i);
		}
		public AdditiveExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitAdditiveExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExprContext additiveExpr() throws RecognitionException {
		AdditiveExprContext _localctx = new AdditiveExprContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_additiveExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			multiplicativeExpr();
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(241);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(242);
				multiplicativeExpr();
				}
				}
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicativeExprContext extends ParserRuleContext {
		public List<AtomicExprContext> atomicExpr() {
			return getRuleContexts(AtomicExprContext.class);
		}
		public AtomicExprContext atomicExpr(int i) {
			return getRuleContext(AtomicExprContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(TInfParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(TInfParser.MUL, i);
		}
		public List<TerminalNode> DIV() { return getTokens(TInfParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(TInfParser.DIV, i);
		}
		public MultiplicativeExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitMultiplicativeExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExprContext multiplicativeExpr() throws RecognitionException {
		MultiplicativeExprContext _localctx = new MultiplicativeExprContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_multiplicativeExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			atomicExpr();
			setState(253);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MUL || _la==DIV) {
				{
				{
				setState(249);
				_la = _input.LA(1);
				if ( !(_la==MUL || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(250);
				atomicExpr();
				}
				}
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomicExprContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public FctCallContext fctCall() {
			return getRuleContext(FctCallContext.class,0);
		}
		public PrintBuiltinCallContext printBuiltinCall() {
			return getRuleContext(PrintBuiltinCallContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TInfParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(TInfParser.LPAREN, 0); }
		public TernaryExprContext ternaryExpr() {
			return getRuleContext(TernaryExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TInfParser.RPAREN, 0); }
		public AtomicExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomicExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TInfVisitor ) return ((TInfVisitor<? extends T>)visitor).visitAtomicExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomicExprContext atomicExpr() throws RecognitionException {
		AtomicExprContext _localctx = new AtomicExprContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_atomicExpr);
		try {
			setState(264);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(257);
				fctCall();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(258);
				printBuiltinCall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(259);
				match(IDENTIFIER);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(260);
				match(LPAREN);
				setState(261);
				ternaryExpr();
				setState(262);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\"\u010b\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0001\u0000\u0004\u0000@\b\u0000\u000b\u0000\f\u0000A\u0001\u0001\u0005"+
		"\u0001E\b\u0001\n\u0001\f\u0001H\t\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002S\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0003\u0005`\b\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t"+
		"\u0003\ts\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b|\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u0096\b\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0005\u000f\u00a0\b\u000f\n\u000f\f\u000f\u00a3\t\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00a9\b\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0005\u0012\u00b3\b\u0012\n\u0012\f\u0012\u00b6\t\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u00ce\b\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0016\u0004\u0016\u00d3\b\u0016\u000b\u0016"+
		"\f\u0016\u00d4\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0003\u001a\u00ea\b\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0003\u001b\u00ef\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0005\u001c\u00f4\b\u001c\n\u001c\f\u001c\u00f7\t\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0005\u001d\u00fc\b\u001d\n\u001d\f\u001d\u00ff\t\u001d"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0003\u001e\u0109\b\u001e\u0001\u001e\u0000\u0000"+
		"\u001f\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&(*,.02468:<\u0000\u0005\u0001\u0000\u001d\u001f"+
		"\u0001\u0000\u0019\u001c\u0001\u0000\u0004\u0005\u0001\u0000\u0006\u0007"+
		"\u0001\u0000\b\t\u0106\u0000?\u0001\u0000\u0000\u0000\u0002F\u0001\u0000"+
		"\u0000\u0000\u0004R\u0001\u0000\u0000\u0000\u0006T\u0001\u0000\u0000\u0000"+
		"\bZ\u0001\u0000\u0000\u0000\n_\u0001\u0000\u0000\u0000\fc\u0001\u0000"+
		"\u0000\u0000\u000eh\u0001\u0000\u0000\u0000\u0010j\u0001\u0000\u0000\u0000"+
		"\u0012l\u0001\u0000\u0000\u0000\u0014t\u0001\u0000\u0000\u0000\u0016x"+
		"\u0001\u0000\u0000\u0000\u0018}\u0001\u0000\u0000\u0000\u001a\u0085\u0001"+
		"\u0000\u0000\u0000\u001c\u008f\u0001\u0000\u0000\u0000\u001e\u009c\u0001"+
		"\u0000\u0000\u0000 \u00a4\u0001\u0000\u0000\u0000\"\u00aa\u0001\u0000"+
		"\u0000\u0000$\u00af\u0001\u0000\u0000\u0000&\u00b7\u0001\u0000\u0000\u0000"+
		"(\u00bb\u0001\u0000\u0000\u0000*\u00c6\u0001\u0000\u0000\u0000,\u00d2"+
		"\u0001\u0000\u0000\u0000.\u00d6\u0001\u0000\u0000\u00000\u00db\u0001\u0000"+
		"\u0000\u00002\u00df\u0001\u0000\u0000\u00004\u00e3\u0001\u0000\u0000\u0000"+
		"6\u00eb\u0001\u0000\u0000\u00008\u00f0\u0001\u0000\u0000\u0000:\u00f8"+
		"\u0001\u0000\u0000\u0000<\u0108\u0001\u0000\u0000\u0000>@\u0003\u001c"+
		"\u000e\u0000?>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000A?\u0001"+
		"\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000B\u0001\u0001\u0000\u0000"+
		"\u0000CE\u0003\u0004\u0002\u0000DC\u0001\u0000\u0000\u0000EH\u0001\u0000"+
		"\u0000\u0000FD\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000G\u0003"+
		"\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000IS\u0003\u0006\u0003"+
		"\u0000JS\u0003\b\u0004\u0000KS\u0003&\u0013\u0000LS\u0003\u0012\t\u0000"+
		"MS\u0003\u0018\f\u0000NS\u0003\u001a\r\u0000OS\u0003(\u0014\u0000PS\u0003"+
		"*\u0015\u0000QS\u00032\u0019\u0000RI\u0001\u0000\u0000\u0000RJ\u0001\u0000"+
		"\u0000\u0000RK\u0001\u0000\u0000\u0000RL\u0001\u0000\u0000\u0000RM\u0001"+
		"\u0000\u0000\u0000RN\u0001\u0000\u0000\u0000RO\u0001\u0000\u0000\u0000"+
		"RP\u0001\u0000\u0000\u0000RQ\u0001\u0000\u0000\u0000S\u0005\u0001\u0000"+
		"\u0000\u0000TU\u0003\u0010\b\u0000UV\u0005 \u0000\u0000VW\u0005\u0003"+
		"\u0000\u0000WX\u00034\u001a\u0000XY\u0005\u000e\u0000\u0000Y\u0007\u0001"+
		"\u0000\u0000\u0000Z[\u0003\n\u0005\u0000[\\\u0005\u000e\u0000\u0000\\"+
		"\t\u0001\u0000\u0000\u0000]^\u0005 \u0000\u0000^`\u0005\u0003\u0000\u0000"+
		"_]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000"+
		"\u0000ab\u00034\u001a\u0000b\u000b\u0001\u0000\u0000\u0000cd\u0005\u0018"+
		"\u0000\u0000de\u0005\n\u0000\u0000ef\u00034\u001a\u0000fg\u0005\u000b"+
		"\u0000\u0000g\r\u0001\u0000\u0000\u0000hi\u0007\u0000\u0000\u0000i\u000f"+
		"\u0001\u0000\u0000\u0000jk\u0007\u0001\u0000\u0000k\u0011\u0001\u0000"+
		"\u0000\u0000lm\u0005\u000f\u0000\u0000mn\u0005\n\u0000\u0000no\u00034"+
		"\u001a\u0000op\u0005\u000b\u0000\u0000pr\u0003\u0014\n\u0000qs\u0003\u0016"+
		"\u000b\u0000rq\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000s\u0013"+
		"\u0001\u0000\u0000\u0000tu\u0005\f\u0000\u0000uv\u0003\u0002\u0001\u0000"+
		"vw\u0005\r\u0000\u0000w\u0015\u0001\u0000\u0000\u0000x{\u0005\u0010\u0000"+
		"\u0000y|\u0003\u0014\n\u0000z|\u0003\u0012\t\u0000{y\u0001\u0000\u0000"+
		"\u0000{z\u0001\u0000\u0000\u0000|\u0017\u0001\u0000\u0000\u0000}~\u0005"+
		"\u0011\u0000\u0000~\u007f\u0005\n\u0000\u0000\u007f\u0080\u00034\u001a"+
		"\u0000\u0080\u0081\u0005\u000b\u0000\u0000\u0081\u0082\u0005\f\u0000\u0000"+
		"\u0082\u0083\u0003\u0002\u0001\u0000\u0083\u0084\u0005\r\u0000\u0000\u0084"+
		"\u0019\u0001\u0000\u0000\u0000\u0085\u0086\u0005\u0012\u0000\u0000\u0086"+
		"\u0087\u0005\f\u0000\u0000\u0087\u0088\u0003\u0002\u0001\u0000\u0088\u0089"+
		"\u0005\r\u0000\u0000\u0089\u008a\u0005\u0011\u0000\u0000\u008a\u008b\u0005"+
		"\n\u0000\u0000\u008b\u008c\u00034\u001a\u0000\u008c\u008d\u0005\u000b"+
		"\u0000\u0000\u008d\u008e\u0005\u000e\u0000\u0000\u008e\u001b\u0001\u0000"+
		"\u0000\u0000\u008f\u0090\u0003\u0010\b\u0000\u0090\u0091\u0005 \u0000"+
		"\u0000\u0091\u0092\u0005\u0002\u0000\u0000\u0092\u0093\u0005\u0003\u0000"+
		"\u0000\u0093\u0095\u0005\n\u0000\u0000\u0094\u0096\u0003\u001e\u000f\u0000"+
		"\u0095\u0094\u0001\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000"+
		"\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u0098\u0005\u000b\u0000\u0000"+
		"\u0098\u0099\u0005\f\u0000\u0000\u0099\u009a\u0003\u0002\u0001\u0000\u009a"+
		"\u009b\u0005\r\u0000\u0000\u009b\u001d\u0001\u0000\u0000\u0000\u009c\u00a1"+
		"\u0003 \u0010\u0000\u009d\u009e\u0005\u000e\u0000\u0000\u009e\u00a0\u0003"+
		" \u0010\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u00a0\u00a3\u0001\u0000"+
		"\u0000\u0000\u00a1\u009f\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000"+
		"\u0000\u0000\u00a2\u001f\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a5\u0003\u0010\b\u0000\u00a5\u00a8\u0005 \u0000"+
		"\u0000\u00a6\u00a7\u0005\u0003\u0000\u0000\u00a7\u00a9\u0003<\u001e\u0000"+
		"\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000"+
		"\u00a9!\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005 \u0000\u0000\u00ab\u00ac"+
		"\u0005\n\u0000\u0000\u00ac\u00ad\u0003$\u0012\u0000\u00ad\u00ae\u0005"+
		"\u000b\u0000\u0000\u00ae#\u0001\u0000\u0000\u0000\u00af\u00b4\u0003<\u001e"+
		"\u0000\u00b0\u00b1\u0005\u000e\u0000\u0000\u00b1\u00b3\u0003<\u001e\u0000"+
		"\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b3\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000"+
		"\u00b5%\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b8\u0005\u0017\u0000\u0000\u00b8\u00b9\u00034\u001a\u0000\u00b9\u00ba"+
		"\u0005\u000e\u0000\u0000\u00ba\'\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005"+
		"\u0013\u0000\u0000\u00bc\u00bd\u0005\n\u0000\u0000\u00bd\u00be\u0003\u0006"+
		"\u0003\u0000\u00be\u00bf\u00034\u001a\u0000\u00bf\u00c0\u0005\u000e\u0000"+
		"\u0000\u00c0\u00c1\u0003\n\u0005\u0000\u00c1\u00c2\u0005\u000b\u0000\u0000"+
		"\u00c2\u00c3\u0005\f\u0000\u0000\u00c3\u00c4\u0003\u0002\u0001\u0000\u00c4"+
		"\u00c5\u0005\r\u0000\u0000\u00c5)\u0001\u0000\u0000\u0000\u00c6\u00c7"+
		"\u0005\u0014\u0000\u0000\u00c7\u00c8\u0005\n\u0000\u0000\u00c8\u00c9\u0003"+
		"4\u001a\u0000\u00c9\u00ca\u0005\u000b\u0000\u0000\u00ca\u00cb\u0005\f"+
		"\u0000\u0000\u00cb\u00cd\u0003,\u0016\u0000\u00cc\u00ce\u00030\u0018\u0000"+
		"\u00cd\u00cc\u0001\u0000\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000"+
		"\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf\u00d0\u0005\r\u0000\u0000\u00d0"+
		"+\u0001\u0000\u0000\u0000\u00d1\u00d3\u0003.\u0017\u0000\u00d2\u00d1\u0001"+
		"\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d4\u00d2\u0001"+
		"\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5-\u0001\u0000"+
		"\u0000\u0000\u00d6\u00d7\u0005\u0015\u0000\u0000\u00d7\u00d8\u0003\u000e"+
		"\u0007\u0000\u00d8\u00d9\u0005\u0002\u0000\u0000\u00d9\u00da\u0003\u0002"+
		"\u0001\u0000\u00da/\u0001\u0000\u0000\u0000\u00db\u00dc\u0005\u0016\u0000"+
		"\u0000\u00dc\u00dd\u0005\u0002\u0000\u0000\u00dd\u00de\u0003\u0002\u0001"+
		"\u0000\u00de1\u0001\u0000\u0000\u0000\u00df\u00e0\u0005\f\u0000\u0000"+
		"\u00e0\u00e1\u0003\u0002\u0001\u0000\u00e1\u00e2\u0005\r\u0000\u0000\u00e2"+
		"3\u0001\u0000\u0000\u0000\u00e3\u00e9\u00036\u001b\u0000\u00e4\u00e5\u0005"+
		"\u0001\u0000\u0000\u00e5\u00e6\u00036\u001b\u0000\u00e6\u00e7\u0005\u0002"+
		"\u0000\u0000\u00e7\u00e8\u00036\u001b\u0000\u00e8\u00ea\u0001\u0000\u0000"+
		"\u0000\u00e9\u00e4\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000"+
		"\u0000\u00ea5\u0001\u0000\u0000\u0000\u00eb\u00ee\u00038\u001c\u0000\u00ec"+
		"\u00ed\u0007\u0002\u0000\u0000\u00ed\u00ef\u00038\u001c\u0000\u00ee\u00ec"+
		"\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef7\u0001"+
		"\u0000\u0000\u0000\u00f0\u00f5\u0003:\u001d\u0000\u00f1\u00f2\u0007\u0003"+
		"\u0000\u0000\u00f2\u00f4\u0003:\u001d\u0000\u00f3\u00f1\u0001\u0000\u0000"+
		"\u0000\u00f4\u00f7\u0001\u0000\u0000\u0000\u00f5\u00f3\u0001\u0000\u0000"+
		"\u0000\u00f5\u00f6\u0001\u0000\u0000\u0000\u00f69\u0001\u0000\u0000\u0000"+
		"\u00f7\u00f5\u0001\u0000\u0000\u0000\u00f8\u00fd\u0003<\u001e\u0000\u00f9"+
		"\u00fa\u0007\u0004\u0000\u0000\u00fa\u00fc\u0003<\u001e\u0000\u00fb\u00f9"+
		"\u0001\u0000\u0000\u0000\u00fc\u00ff\u0001\u0000\u0000\u0000\u00fd\u00fb"+
		"\u0001\u0000\u0000\u0000\u00fd\u00fe\u0001\u0000\u0000\u0000\u00fe;\u0001"+
		"\u0000\u0000\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000\u0100\u0109\u0003"+
		"\u000e\u0007\u0000\u0101\u0109\u0003\"\u0011\u0000\u0102\u0109\u0003\f"+
		"\u0006\u0000\u0103\u0109\u0005 \u0000\u0000\u0104\u0105\u0005\n\u0000"+
		"\u0000\u0105\u0106\u00034\u001a\u0000\u0106\u0107\u0005\u000b\u0000\u0000"+
		"\u0107\u0109\u0001\u0000\u0000\u0000\u0108\u0100\u0001\u0000\u0000\u0000"+
		"\u0108\u0101\u0001\u0000\u0000\u0000\u0108\u0102\u0001\u0000\u0000\u0000"+
		"\u0108\u0103\u0001\u0000\u0000\u0000\u0108\u0104\u0001\u0000\u0000\u0000"+
		"\u0109=\u0001\u0000\u0000\u0000\u0011AFR_r{\u0095\u00a1\u00a8\u00b4\u00cd"+
		"\u00d4\u00e9\u00ee\u00f5\u00fd\u0108";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}