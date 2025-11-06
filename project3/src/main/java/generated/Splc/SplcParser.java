// Generated from D:/0大学/大学3年级/编译原理/projects/CS323-Compilers-2025F-Projects/Splc.g4 by ANTLR 4.13.2
package generated.Splc;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class SplcParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INT=1, CHAR=2, STRUCT=3, RETURN=4, IF=5, ELSE=6, WHILE=7, ASSIGN=8, PLUS=9, 
		MINUS=10, STAR=11, DIV=12, MOD=13, LT=14, LE=15, GT=16, GE=17, EQ=18, 
		NEQ=19, AND=20, OR=21, NOT=22, INC=23, DEC=24, AMP=25, DOT=26, ARROW=27, 
		SEMI=28, COMMA=29, LPAREN=30, RPAREN=31, LBRACE=32, RBRACE=33, LBRACK=34, 
		RBRACK=35, Identifier=36, Number=37, Char=38, WS=39, LINE_COMMENT=40, 
		BLOCK_COMMENT=41;
	public static final int
		RULE_program = 0, RULE_globalDef = 1, RULE_specifier = 2, RULE_varDec = 3, 
		RULE_funcArgs = 4, RULE_statement = 5, RULE_expression = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "globalDef", "specifier", "varDec", "funcArgs", "statement", 
			"expression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'int'", "'char'", "'struct'", "'return'", "'if'", "'else'", "'while'", 
			"'='", "'+'", "'-'", "'*'", "'/'", "'%'", "'<'", "'<='", "'>'", "'>='", 
			"'=='", "'!='", "'&&'", "'||'", "'!'", "'++'", "'--'", "'&'", "'.'", 
			"'->'", "';'", "','", "'('", "')'", "'{'", "'}'", "'['", "']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INT", "CHAR", "STRUCT", "RETURN", "IF", "ELSE", "WHILE", "ASSIGN", 
			"PLUS", "MINUS", "STAR", "DIV", "MOD", "LT", "LE", "GT", "GE", "EQ", 
			"NEQ", "AND", "OR", "NOT", "INC", "DEC", "AMP", "DOT", "ARROW", "SEMI", 
			"COMMA", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", 
			"Identifier", "Number", "Char", "WS", "LINE_COMMENT", "BLOCK_COMMENT"
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
	public String getGrammarFileName() { return "Splc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SplcParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(SplcParser.EOF, 0); }
		public List<GlobalDefContext> globalDef() {
			return getRuleContexts(GlobalDefContext.class);
		}
		public GlobalDefContext globalDef(int i) {
			return getRuleContext(GlobalDefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 14L) != 0)) {
				{
				{
				setState(14);
				globalDef();
				}
				}
				setState(19);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(20);
			match(EOF);
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
	public static class GlobalDefContext extends ParserRuleContext {
		public SpecifierContext specifier() {
			return getRuleContext(SpecifierContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(SplcParser.Identifier, 0); }
		public TerminalNode LPAREN() { return getToken(SplcParser.LPAREN, 0); }
		public FuncArgsContext funcArgs() {
			return getRuleContext(FuncArgsContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SplcParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(SplcParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(SplcParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public VarDecContext varDec() {
			return getRuleContext(VarDecContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(SplcParser.SEMI, 0); }
		public GlobalDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globalDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterGlobalDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitGlobalDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitGlobalDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalDefContext globalDef() throws RecognitionException {
		GlobalDefContext _localctx = new GlobalDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_globalDef);
		int _la;
		try {
			setState(43);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(22);
				specifier();
				setState(23);
				match(Identifier);
				setState(24);
				match(LPAREN);
				setState(25);
				funcArgs();
				setState(26);
				match(RPAREN);
				setState(27);
				match(LBRACE);
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 486467964606L) != 0)) {
					{
					{
					setState(28);
					statement();
					}
					}
					setState(33);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(34);
				match(RBRACE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(36);
				specifier();
				setState(37);
				varDec(0);
				setState(38);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				specifier();
				setState(41);
				match(SEMI);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SpecifierContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SplcParser.INT, 0); }
		public TerminalNode CHAR() { return getToken(SplcParser.CHAR, 0); }
		public TerminalNode STRUCT() { return getToken(SplcParser.STRUCT, 0); }
		public TerminalNode Identifier() { return getToken(SplcParser.Identifier, 0); }
		public TerminalNode LBRACE() { return getToken(SplcParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(SplcParser.RBRACE, 0); }
		public List<SpecifierContext> specifier() {
			return getRuleContexts(SpecifierContext.class);
		}
		public SpecifierContext specifier(int i) {
			return getRuleContext(SpecifierContext.class,i);
		}
		public List<VarDecContext> varDec() {
			return getRuleContexts(VarDecContext.class);
		}
		public VarDecContext varDec(int i) {
			return getRuleContext(VarDecContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(SplcParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(SplcParser.SEMI, i);
		}
		public SpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterSpecifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitSpecifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitSpecifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecifierContext specifier() throws RecognitionException {
		SpecifierContext _localctx = new SpecifierContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_specifier);
		int _la;
		try {
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(45);
				match(INT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(46);
				match(CHAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(47);
				match(STRUCT);
				setState(48);
				match(Identifier);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(49);
				match(STRUCT);
				setState(50);
				match(Identifier);
				setState(51);
				match(LBRACE);
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 14L) != 0)) {
					{
					{
					setState(52);
					specifier();
					setState(53);
					varDec(0);
					setState(54);
					match(SEMI);
					}
					}
					setState(60);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(61);
				match(RBRACE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class VarDecContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SplcParser.Identifier, 0); }
		public TerminalNode STAR() { return getToken(SplcParser.STAR, 0); }
		public VarDecContext varDec() {
			return getRuleContext(VarDecContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(SplcParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(SplcParser.RPAREN, 0); }
		public TerminalNode LBRACK() { return getToken(SplcParser.LBRACK, 0); }
		public TerminalNode Number() { return getToken(SplcParser.Number, 0); }
		public TerminalNode RBRACK() { return getToken(SplcParser.RBRACK, 0); }
		public VarDecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterVarDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitVarDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitVarDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDecContext varDec() throws RecognitionException {
		return varDec(0);
	}

	private VarDecContext varDec(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		VarDecContext _localctx = new VarDecContext(_ctx, _parentState);
		VarDecContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_varDec, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				{
				setState(65);
				match(Identifier);
				}
				break;
			case STAR:
				{
				setState(66);
				match(STAR);
				setState(67);
				varDec(2);
				}
				break;
			case LPAREN:
				{
				setState(68);
				match(LPAREN);
				setState(69);
				varDec(0);
				setState(70);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(80);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new VarDecContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_varDec);
					setState(74);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(75);
					match(LBRACK);
					setState(76);
					match(Number);
					setState(77);
					match(RBRACK);
					}
					} 
				}
				setState(82);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncArgsContext extends ParserRuleContext {
		public List<SpecifierContext> specifier() {
			return getRuleContexts(SpecifierContext.class);
		}
		public SpecifierContext specifier(int i) {
			return getRuleContext(SpecifierContext.class,i);
		}
		public List<VarDecContext> varDec() {
			return getRuleContexts(VarDecContext.class);
		}
		public VarDecContext varDec(int i) {
			return getRuleContext(VarDecContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SplcParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SplcParser.COMMA, i);
		}
		public FuncArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterFuncArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitFuncArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitFuncArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncArgsContext funcArgs() throws RecognitionException {
		FuncArgsContext _localctx = new FuncArgsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_funcArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 14L) != 0)) {
				{
				setState(83);
				specifier();
				setState(84);
				varDec(0);
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(85);
					match(COMMA);
					setState(86);
					specifier();
					setState(87);
					varDec(0);
					}
					}
					setState(93);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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
	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends StatementContext {
		public TerminalNode IF() { return getToken(SplcParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(SplcParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SplcParser.RPAREN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(SplcParser.ELSE, 0); }
		public IfStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionStatementContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(SplcParser.SEMI, 0); }
		public ExpressionStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitExpressionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitExpressionStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarDecStmtContext extends StatementContext {
		public SpecifierContext specifier() {
			return getRuleContext(SpecifierContext.class,0);
		}
		public VarDecContext varDec() {
			return getRuleContext(VarDecContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(SplcParser.SEMI, 0); }
		public TerminalNode ASSIGN() { return getToken(SplcParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarDecStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterVarDecStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitVarDecStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitVarDecStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStatementContext extends StatementContext {
		public TerminalNode RETURN() { return getToken(SplcParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(SplcParser.SEMI, 0); }
		public ReturnStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockStatementContext extends StatementContext {
		public TerminalNode LBRACE() { return getToken(SplcParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(SplcParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitBlockStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitBlockStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatementContext extends StatementContext {
		public TerminalNode WHILE() { return getToken(SplcParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(SplcParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SplcParser.RPAREN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statement);
		int _la;
		try {
			setState(134);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				_localctx = new BlockStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(96);
				match(LBRACE);
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 486467964606L) != 0)) {
					{
					{
					setState(97);
					statement();
					}
					}
					setState(102);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(103);
				match(RBRACE);
				}
				break;
			case INT:
			case CHAR:
			case STRUCT:
				_localctx = new VarDecStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				specifier();
				setState(105);
				varDec(0);
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(106);
					match(ASSIGN);
					setState(107);
					expression(0);
					}
				}

				setState(110);
				match(SEMI);
				}
				break;
			case IF:
				_localctx = new IfStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(112);
				match(IF);
				setState(113);
				match(LPAREN);
				setState(114);
				expression(0);
				setState(115);
				match(RPAREN);
				setState(116);
				statement();
				setState(119);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(117);
					match(ELSE);
					setState(118);
					statement();
					}
					break;
				}
				}
				break;
			case WHILE:
				_localctx = new WhileStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(121);
				match(WHILE);
				setState(122);
				match(LPAREN);
				setState(123);
				expression(0);
				setState(124);
				match(RPAREN);
				setState(125);
				statement();
				}
				break;
			case RETURN:
				_localctx = new ReturnStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(127);
				match(RETURN);
				setState(128);
				expression(0);
				setState(129);
				match(SEMI);
				}
				break;
			case PLUS:
			case MINUS:
			case STAR:
			case NOT:
			case INC:
			case DEC:
			case AMP:
			case LPAREN:
			case Identifier:
			case Number:
			case Char:
				_localctx = new ExpressionStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(131);
				expression(0);
				setState(132);
				match(SEMI);
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
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(SplcParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(SplcParser.MINUS, 0); }
		public AdditiveExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitAdditiveExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitAdditiveExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationalExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LT() { return getToken(SplcParser.LT, 0); }
		public TerminalNode LE() { return getToken(SplcParser.LE, 0); }
		public TerminalNode GT() { return getToken(SplcParser.GT, 0); }
		public TerminalNode GE() { return getToken(SplcParser.GE, 0); }
		public RelationalExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterRelationalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitRelationalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitRelationalExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalAndExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(SplcParser.AND, 0); }
		public LogicalAndExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterLogicalAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitLogicalAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitLogicalAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(SplcParser.ASSIGN, 0); }
		public AssignExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterAssignExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitAssignExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitAssignExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DereferenceExpressionContext extends ExpressionContext {
		public TerminalNode STAR() { return getToken(SplcParser.STAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DereferenceExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterDereferenceExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitDereferenceExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitDereferenceExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierPrimaryContext extends ExpressionContext {
		public TerminalNode Identifier() { return getToken(SplcParser.Identifier, 0); }
		public IdentifierPrimaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterIdentifierPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitIdentifierPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitIdentifierPrimary(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixDecExpressionContext extends ExpressionContext {
		public TerminalNode DEC() { return getToken(SplcParser.DEC, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrefixDecExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterPrefixDecExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitPrefixDecExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitPrefixDecExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalOrExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OR() { return getToken(SplcParser.OR, 0); }
		public LogicalOrExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterLogicalOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitLogicalOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitLogicalOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StructPtrAccessExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(SplcParser.ARROW, 0); }
		public TerminalNode Identifier() { return getToken(SplcParser.Identifier, 0); }
		public StructPtrAccessExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterStructPtrAccessExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitStructPtrAccessExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitStructPtrAccessExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalNotExpressionContext extends ExpressionContext {
		public TerminalNode NOT() { return getToken(SplcParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LogicalNotExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterLogicalNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitLogicalNotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitLogicalNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberPrimaryContext extends ExpressionContext {
		public TerminalNode Number() { return getToken(SplcParser.Number, 0); }
		public NumberPrimaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterNumberPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitNumberPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitNumberPrimary(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenPrimaryContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(SplcParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SplcParser.RPAREN, 0); }
		public ParenPrimaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterParenPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitParenPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitParenPrimary(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallExpressionContext extends ExpressionContext {
		public TerminalNode Identifier() { return getToken(SplcParser.Identifier, 0); }
		public TerminalNode LPAREN() { return getToken(SplcParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(SplcParser.RPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SplcParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SplcParser.COMMA, i);
		}
		public FunctionCallExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterFunctionCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitFunctionCallExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitFunctionCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixDecExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DEC() { return getToken(SplcParser.DEC, 0); }
		public PostfixDecExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterPostfixDecExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitPostfixDecExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitPostfixDecExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryMinusExpressionContext extends ExpressionContext {
		public TerminalNode MINUS() { return getToken(SplcParser.MINUS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryMinusExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterUnaryMinusExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitUnaryMinusExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitUnaryMinusExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CharPrimaryContext extends ExpressionContext {
		public TerminalNode Char() { return getToken(SplcParser.Char, 0); }
		public CharPrimaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterCharPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitCharPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitCharPrimary(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddressOfExpressionContext extends ExpressionContext {
		public TerminalNode AMP() { return getToken(SplcParser.AMP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AddressOfExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterAddressOfExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitAddressOfExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitAddressOfExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixIncExpressionContext extends ExpressionContext {
		public TerminalNode INC() { return getToken(SplcParser.INC, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrefixIncExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterPrefixIncExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitPrefixIncExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitPrefixIncExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryPlusExpressionContext extends ExpressionContext {
		public TerminalNode PLUS() { return getToken(SplcParser.PLUS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryPlusExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterUnaryPlusExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitUnaryPlusExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitUnaryPlusExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixIncExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INC() { return getToken(SplcParser.INC, 0); }
		public PostfixIncExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterPostfixIncExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitPostfixIncExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitPostfixIncExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualityExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQ() { return getToken(SplcParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(SplcParser.NEQ, 0); }
		public EqualityExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterEqualityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitEqualityExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitEqualityExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicativeExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode STAR() { return getToken(SplcParser.STAR, 0); }
		public TerminalNode DIV() { return getToken(SplcParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(SplcParser.MOD, 0); }
		public MultiplicativeExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitMultiplicativeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitMultiplicativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAccessExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LBRACK() { return getToken(SplcParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(SplcParser.RBRACK, 0); }
		public ArrayAccessExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterArrayAccessExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitArrayAccessExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitArrayAccessExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StructAccessExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(SplcParser.DOT, 0); }
		public TerminalNode Identifier() { return getToken(SplcParser.Identifier, 0); }
		public StructAccessExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).enterStructAccessExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SplcListener ) ((SplcListener)listener).exitStructAccessExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SplcVisitor ) return ((SplcVisitor<? extends T>)visitor).visitStructAccessExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				_localctx = new IdentifierPrimaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(137);
				match(Identifier);
				}
				break;
			case 2:
				{
				_localctx = new NumberPrimaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(138);
				match(Number);
				}
				break;
			case 3:
				{
				_localctx = new CharPrimaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(139);
				match(Char);
				}
				break;
			case 4:
				{
				_localctx = new ParenPrimaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(140);
				match(LPAREN);
				setState(141);
				expression(0);
				setState(142);
				match(RPAREN);
				}
				break;
			case 5:
				{
				_localctx = new FunctionCallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(144);
				match(Identifier);
				setState(145);
				match(LPAREN);
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 482172997120L) != 0)) {
					{
					setState(146);
					expression(0);
					setState(151);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(147);
						match(COMMA);
						setState(148);
						expression(0);
						}
						}
						setState(153);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(156);
				match(RPAREN);
				}
				break;
			case 6:
				{
				_localctx = new PrefixIncExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(157);
				match(INC);
				setState(158);
				expression(14);
				}
				break;
			case 7:
				{
				_localctx = new PrefixDecExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(159);
				match(DEC);
				setState(160);
				expression(13);
				}
				break;
			case 8:
				{
				_localctx = new UnaryPlusExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(161);
				match(PLUS);
				setState(162);
				expression(12);
				}
				break;
			case 9:
				{
				_localctx = new UnaryMinusExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(163);
				match(MINUS);
				setState(164);
				expression(11);
				}
				break;
			case 10:
				{
				_localctx = new LogicalNotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(165);
				match(NOT);
				setState(166);
				expression(10);
				}
				break;
			case 11:
				{
				_localctx = new DereferenceExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(167);
				match(STAR);
				setState(168);
				expression(9);
				}
				break;
			case 12:
				{
				_localctx = new AddressOfExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(169);
				match(AMP);
				setState(170);
				expression(8);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(211);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(209);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicativeExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(173);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(174);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14336L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(175);
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new AdditiveExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(176);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(177);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(178);
						expression(7);
						}
						break;
					case 3:
						{
						_localctx = new RelationalExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(179);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(180);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 245760L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(181);
						expression(6);
						}
						break;
					case 4:
						{
						_localctx = new EqualityExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(182);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(183);
						_la = _input.LA(1);
						if ( !(_la==EQ || _la==NEQ) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(184);
						expression(5);
						}
						break;
					case 5:
						{
						_localctx = new LogicalAndExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(185);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(186);
						match(AND);
						setState(187);
						expression(4);
						}
						break;
					case 6:
						{
						_localctx = new LogicalOrExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(188);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(189);
						match(OR);
						setState(190);
						expression(3);
						}
						break;
					case 7:
						{
						_localctx = new AssignExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(191);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(192);
						match(ASSIGN);
						setState(193);
						expression(1);
						}
						break;
					case 8:
						{
						_localctx = new ArrayAccessExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(194);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(195);
						match(LBRACK);
						setState(196);
						expression(0);
						setState(197);
						match(RBRACK);
						}
						break;
					case 9:
						{
						_localctx = new PostfixIncExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(199);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(200);
						match(INC);
						}
						break;
					case 10:
						{
						_localctx = new PostfixDecExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(201);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(202);
						match(DEC);
						}
						break;
					case 11:
						{
						_localctx = new StructAccessExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(203);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(204);
						match(DOT);
						setState(205);
						match(Identifier);
						}
						break;
					case 12:
						{
						_localctx = new StructPtrAccessExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(206);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(207);
						match(ARROW);
						setState(208);
						match(Identifier);
						}
						break;
					}
					} 
				}
				setState(213);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return varDec_sempred((VarDecContext)_localctx, predIndex);
		case 6:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean varDec_sempred(VarDecContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 2);
		case 7:
			return precpred(_ctx, 1);
		case 8:
			return precpred(_ctx, 20);
		case 9:
			return precpred(_ctx, 19);
		case 10:
			return precpred(_ctx, 18);
		case 11:
			return precpred(_ctx, 16);
		case 12:
			return precpred(_ctx, 15);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001)\u00d7\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0001\u0000\u0005\u0000\u0010"+
		"\b\u0000\n\u0000\f\u0000\u0013\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0005\u0001\u001e\b\u0001\n\u0001\f\u0001!\t\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001,\b\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0005\u00029\b\u0002\n\u0002\f\u0002<\t"+
		"\u0002\u0001\u0002\u0003\u0002?\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003I\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005"+
		"\u0003O\b\u0003\n\u0003\f\u0003R\t\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004Z\b\u0004\n\u0004"+
		"\f\u0004]\t\u0004\u0003\u0004_\b\u0004\u0001\u0005\u0001\u0005\u0005\u0005"+
		"c\b\u0005\n\u0005\f\u0005f\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005m\b\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005x\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u0087\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006\u0096\b\u0006\n\u0006\f\u0006\u0099\t\u0006\u0003"+
		"\u0006\u009b\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00ac"+
		"\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0005\u0006\u00d2\b\u0006\n\u0006\f\u0006\u00d5\t\u0006\u0001\u0006"+
		"\u0000\u0002\u0006\f\u0007\u0000\u0002\u0004\u0006\b\n\f\u0000\u0004\u0001"+
		"\u0000\u000b\r\u0001\u0000\t\n\u0001\u0000\u000e\u0011\u0001\u0000\u0012"+
		"\u0013\u00fd\u0000\u0011\u0001\u0000\u0000\u0000\u0002+\u0001\u0000\u0000"+
		"\u0000\u0004>\u0001\u0000\u0000\u0000\u0006H\u0001\u0000\u0000\u0000\b"+
		"^\u0001\u0000\u0000\u0000\n\u0086\u0001\u0000\u0000\u0000\f\u00ab\u0001"+
		"\u0000\u0000\u0000\u000e\u0010\u0003\u0002\u0001\u0000\u000f\u000e\u0001"+
		"\u0000\u0000\u0000\u0010\u0013\u0001\u0000\u0000\u0000\u0011\u000f\u0001"+
		"\u0000\u0000\u0000\u0011\u0012\u0001\u0000\u0000\u0000\u0012\u0014\u0001"+
		"\u0000\u0000\u0000\u0013\u0011\u0001\u0000\u0000\u0000\u0014\u0015\u0005"+
		"\u0000\u0000\u0001\u0015\u0001\u0001\u0000\u0000\u0000\u0016\u0017\u0003"+
		"\u0004\u0002\u0000\u0017\u0018\u0005$\u0000\u0000\u0018\u0019\u0005\u001e"+
		"\u0000\u0000\u0019\u001a\u0003\b\u0004\u0000\u001a\u001b\u0005\u001f\u0000"+
		"\u0000\u001b\u001f\u0005 \u0000\u0000\u001c\u001e\u0003\n\u0005\u0000"+
		"\u001d\u001c\u0001\u0000\u0000\u0000\u001e!\u0001\u0000\u0000\u0000\u001f"+
		"\u001d\u0001\u0000\u0000\u0000\u001f \u0001\u0000\u0000\u0000 \"\u0001"+
		"\u0000\u0000\u0000!\u001f\u0001\u0000\u0000\u0000\"#\u0005!\u0000\u0000"+
		"#,\u0001\u0000\u0000\u0000$%\u0003\u0004\u0002\u0000%&\u0003\u0006\u0003"+
		"\u0000&\'\u0005\u001c\u0000\u0000\',\u0001\u0000\u0000\u0000()\u0003\u0004"+
		"\u0002\u0000)*\u0005\u001c\u0000\u0000*,\u0001\u0000\u0000\u0000+\u0016"+
		"\u0001\u0000\u0000\u0000+$\u0001\u0000\u0000\u0000+(\u0001\u0000\u0000"+
		"\u0000,\u0003\u0001\u0000\u0000\u0000-?\u0005\u0001\u0000\u0000.?\u0005"+
		"\u0002\u0000\u0000/0\u0005\u0003\u0000\u00000?\u0005$\u0000\u000012\u0005"+
		"\u0003\u0000\u000023\u0005$\u0000\u00003:\u0005 \u0000\u000045\u0003\u0004"+
		"\u0002\u000056\u0003\u0006\u0003\u000067\u0005\u001c\u0000\u000079\u0001"+
		"\u0000\u0000\u000084\u0001\u0000\u0000\u00009<\u0001\u0000\u0000\u0000"+
		":8\u0001\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000;=\u0001\u0000\u0000"+
		"\u0000<:\u0001\u0000\u0000\u0000=?\u0005!\u0000\u0000>-\u0001\u0000\u0000"+
		"\u0000>.\u0001\u0000\u0000\u0000>/\u0001\u0000\u0000\u0000>1\u0001\u0000"+
		"\u0000\u0000?\u0005\u0001\u0000\u0000\u0000@A\u0006\u0003\uffff\uffff"+
		"\u0000AI\u0005$\u0000\u0000BC\u0005\u000b\u0000\u0000CI\u0003\u0006\u0003"+
		"\u0002DE\u0005\u001e\u0000\u0000EF\u0003\u0006\u0003\u0000FG\u0005\u001f"+
		"\u0000\u0000GI\u0001\u0000\u0000\u0000H@\u0001\u0000\u0000\u0000HB\u0001"+
		"\u0000\u0000\u0000HD\u0001\u0000\u0000\u0000IP\u0001\u0000\u0000\u0000"+
		"JK\n\u0003\u0000\u0000KL\u0005\"\u0000\u0000LM\u0005%\u0000\u0000MO\u0005"+
		"#\u0000\u0000NJ\u0001\u0000\u0000\u0000OR\u0001\u0000\u0000\u0000PN\u0001"+
		"\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000Q\u0007\u0001\u0000\u0000"+
		"\u0000RP\u0001\u0000\u0000\u0000ST\u0003\u0004\u0002\u0000T[\u0003\u0006"+
		"\u0003\u0000UV\u0005\u001d\u0000\u0000VW\u0003\u0004\u0002\u0000WX\u0003"+
		"\u0006\u0003\u0000XZ\u0001\u0000\u0000\u0000YU\u0001\u0000\u0000\u0000"+
		"Z]\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000"+
		"\u0000\\_\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000^S\u0001\u0000"+
		"\u0000\u0000^_\u0001\u0000\u0000\u0000_\t\u0001\u0000\u0000\u0000`d\u0005"+
		" \u0000\u0000ac\u0003\n\u0005\u0000ba\u0001\u0000\u0000\u0000cf\u0001"+
		"\u0000\u0000\u0000db\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000"+
		"eg\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000g\u0087\u0005!\u0000"+
		"\u0000hi\u0003\u0004\u0002\u0000il\u0003\u0006\u0003\u0000jk\u0005\b\u0000"+
		"\u0000km\u0003\f\u0006\u0000lj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000"+
		"\u0000mn\u0001\u0000\u0000\u0000no\u0005\u001c\u0000\u0000o\u0087\u0001"+
		"\u0000\u0000\u0000pq\u0005\u0005\u0000\u0000qr\u0005\u001e\u0000\u0000"+
		"rs\u0003\f\u0006\u0000st\u0005\u001f\u0000\u0000tw\u0003\n\u0005\u0000"+
		"uv\u0005\u0006\u0000\u0000vx\u0003\n\u0005\u0000wu\u0001\u0000\u0000\u0000"+
		"wx\u0001\u0000\u0000\u0000x\u0087\u0001\u0000\u0000\u0000yz\u0005\u0007"+
		"\u0000\u0000z{\u0005\u001e\u0000\u0000{|\u0003\f\u0006\u0000|}\u0005\u001f"+
		"\u0000\u0000}~\u0003\n\u0005\u0000~\u0087\u0001\u0000\u0000\u0000\u007f"+
		"\u0080\u0005\u0004\u0000\u0000\u0080\u0081\u0003\f\u0006\u0000\u0081\u0082"+
		"\u0005\u001c\u0000\u0000\u0082\u0087\u0001\u0000\u0000\u0000\u0083\u0084"+
		"\u0003\f\u0006\u0000\u0084\u0085\u0005\u001c\u0000\u0000\u0085\u0087\u0001"+
		"\u0000\u0000\u0000\u0086`\u0001\u0000\u0000\u0000\u0086h\u0001\u0000\u0000"+
		"\u0000\u0086p\u0001\u0000\u0000\u0000\u0086y\u0001\u0000\u0000\u0000\u0086"+
		"\u007f\u0001\u0000\u0000\u0000\u0086\u0083\u0001\u0000\u0000\u0000\u0087"+
		"\u000b\u0001\u0000\u0000\u0000\u0088\u0089\u0006\u0006\uffff\uffff\u0000"+
		"\u0089\u00ac\u0005$\u0000\u0000\u008a\u00ac\u0005%\u0000\u0000\u008b\u00ac"+
		"\u0005&\u0000\u0000\u008c\u008d\u0005\u001e\u0000\u0000\u008d\u008e\u0003"+
		"\f\u0006\u0000\u008e\u008f\u0005\u001f\u0000\u0000\u008f\u00ac\u0001\u0000"+
		"\u0000\u0000\u0090\u0091\u0005$\u0000\u0000\u0091\u009a\u0005\u001e\u0000"+
		"\u0000\u0092\u0097\u0003\f\u0006\u0000\u0093\u0094\u0005\u001d\u0000\u0000"+
		"\u0094\u0096\u0003\f\u0006\u0000\u0095\u0093\u0001\u0000\u0000\u0000\u0096"+
		"\u0099\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000\u0097"+
		"\u0098\u0001\u0000\u0000\u0000\u0098\u009b\u0001\u0000\u0000\u0000\u0099"+
		"\u0097\u0001\u0000\u0000\u0000\u009a\u0092\u0001\u0000\u0000\u0000\u009a"+
		"\u009b\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c"+
		"\u00ac\u0005\u001f\u0000\u0000\u009d\u009e\u0005\u0017\u0000\u0000\u009e"+
		"\u00ac\u0003\f\u0006\u000e\u009f\u00a0\u0005\u0018\u0000\u0000\u00a0\u00ac"+
		"\u0003\f\u0006\r\u00a1\u00a2\u0005\t\u0000\u0000\u00a2\u00ac\u0003\f\u0006"+
		"\f\u00a3\u00a4\u0005\n\u0000\u0000\u00a4\u00ac\u0003\f\u0006\u000b\u00a5"+
		"\u00a6\u0005\u0016\u0000\u0000\u00a6\u00ac\u0003\f\u0006\n\u00a7\u00a8"+
		"\u0005\u000b\u0000\u0000\u00a8\u00ac\u0003\f\u0006\t\u00a9\u00aa\u0005"+
		"\u0019\u0000\u0000\u00aa\u00ac\u0003\f\u0006\b\u00ab\u0088\u0001\u0000"+
		"\u0000\u0000\u00ab\u008a\u0001\u0000\u0000\u0000\u00ab\u008b\u0001\u0000"+
		"\u0000\u0000\u00ab\u008c\u0001\u0000\u0000\u0000\u00ab\u0090\u0001\u0000"+
		"\u0000\u0000\u00ab\u009d\u0001\u0000\u0000\u0000\u00ab\u009f\u0001\u0000"+
		"\u0000\u0000\u00ab\u00a1\u0001\u0000\u0000\u0000\u00ab\u00a3\u0001\u0000"+
		"\u0000\u0000\u00ab\u00a5\u0001\u0000\u0000\u0000\u00ab\u00a7\u0001\u0000"+
		"\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ac\u00d3\u0001\u0000"+
		"\u0000\u0000\u00ad\u00ae\n\u0007\u0000\u0000\u00ae\u00af\u0007\u0000\u0000"+
		"\u0000\u00af\u00d2\u0003\f\u0006\b\u00b0\u00b1\n\u0006\u0000\u0000\u00b1"+
		"\u00b2\u0007\u0001\u0000\u0000\u00b2\u00d2\u0003\f\u0006\u0007\u00b3\u00b4"+
		"\n\u0005\u0000\u0000\u00b4\u00b5\u0007\u0002\u0000\u0000\u00b5\u00d2\u0003"+
		"\f\u0006\u0006\u00b6\u00b7\n\u0004\u0000\u0000\u00b7\u00b8\u0007\u0003"+
		"\u0000\u0000\u00b8\u00d2\u0003\f\u0006\u0005\u00b9\u00ba\n\u0003\u0000"+
		"\u0000\u00ba\u00bb\u0005\u0014\u0000\u0000\u00bb\u00d2\u0003\f\u0006\u0004"+
		"\u00bc\u00bd\n\u0002\u0000\u0000\u00bd\u00be\u0005\u0015\u0000\u0000\u00be"+
		"\u00d2\u0003\f\u0006\u0003\u00bf\u00c0\n\u0001\u0000\u0000\u00c0\u00c1"+
		"\u0005\b\u0000\u0000\u00c1\u00d2\u0003\f\u0006\u0001\u00c2\u00c3\n\u0014"+
		"\u0000\u0000\u00c3\u00c4\u0005\"\u0000\u0000\u00c4\u00c5\u0003\f\u0006"+
		"\u0000\u00c5\u00c6\u0005#\u0000\u0000\u00c6\u00d2\u0001\u0000\u0000\u0000"+
		"\u00c7\u00c8\n\u0013\u0000\u0000\u00c8\u00d2\u0005\u0017\u0000\u0000\u00c9"+
		"\u00ca\n\u0012\u0000\u0000\u00ca\u00d2\u0005\u0018\u0000\u0000\u00cb\u00cc"+
		"\n\u0010\u0000\u0000\u00cc\u00cd\u0005\u001a\u0000\u0000\u00cd\u00d2\u0005"+
		"$\u0000\u0000\u00ce\u00cf\n\u000f\u0000\u0000\u00cf\u00d0\u0005\u001b"+
		"\u0000\u0000\u00d0\u00d2\u0005$\u0000\u0000\u00d1\u00ad\u0001\u0000\u0000"+
		"\u0000\u00d1\u00b0\u0001\u0000\u0000\u0000\u00d1\u00b3\u0001\u0000\u0000"+
		"\u0000\u00d1\u00b6\u0001\u0000\u0000\u0000\u00d1\u00b9\u0001\u0000\u0000"+
		"\u0000\u00d1\u00bc\u0001\u0000\u0000\u0000\u00d1\u00bf\u0001\u0000\u0000"+
		"\u0000\u00d1\u00c2\u0001\u0000\u0000\u0000\u00d1\u00c7\u0001\u0000\u0000"+
		"\u0000\u00d1\u00c9\u0001\u0000\u0000\u0000\u00d1\u00cb\u0001\u0000\u0000"+
		"\u0000\u00d1\u00ce\u0001\u0000\u0000\u0000\u00d2\u00d5\u0001\u0000\u0000"+
		"\u0000\u00d3\u00d1\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000"+
		"\u0000\u00d4\r\u0001\u0000\u0000\u0000\u00d5\u00d3\u0001\u0000\u0000\u0000"+
		"\u0012\u0011\u001f+:>HP[^dlw\u0086\u0097\u009a\u00ab\u00d1\u00d3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}