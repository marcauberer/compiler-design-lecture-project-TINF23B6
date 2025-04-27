// Generated from TInf.g4 by ANTLR 4.13.2
package com.auberer.compilerdesignlectureproject.antlr.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TInfParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TInfVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TInfParser#entry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntry(TInfParser.EntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#stmtLst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtLst(TInfParser.StmtLstContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(TInfParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#varDeclStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclStmt(TInfParser.VarDeclStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#assignStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStmt(TInfParser.AssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#assignExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpr(TInfParser.AssignExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#printBuiltinCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintBuiltinCall(TInfParser.PrintBuiltinCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(TInfParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(TInfParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(TInfParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#ifBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfBody(TInfParser.IfBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#elseStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseStmt(TInfParser.ElseStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#whileLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoop(TInfParser.WhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#doWhileLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhileLoop(TInfParser.DoWhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#fctDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFctDef(TInfParser.FctDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#paramLst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamLst(TInfParser.ParamLstContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(TInfParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#fctCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFctCall(TInfParser.FctCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#argLst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgLst(TInfParser.ArgLstContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(TInfParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#forLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop(TInfParser.ForLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#switchCaseStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchCaseStmt(TInfParser.SwitchCaseStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#caseBlockLst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseBlockLst(TInfParser.CaseBlockLstContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#caseBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseBlock(TInfParser.CaseBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#defaultBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultBlock(TInfParser.DefaultBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#anonymousBlockStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnonymousBlockStmt(TInfParser.AnonymousBlockStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#ternaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryExpr(TInfParser.TernaryExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#equalityExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpr(TInfParser.EqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#additiveExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(TInfParser.AdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpr(TInfParser.MultiplicativeExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link TInfParser#atomicExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicExpr(TInfParser.AtomicExprContext ctx);
}