// Generated from /Users/jorlensi/Documents/HAN/Compiler/icss2022-sep/startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ICSSLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, ELSE=2, BOX_BRACKET_OPEN=3, BOX_BRACKET_CLOSE=4, TRUE=5, FALSE=6, 
		PIXELSIZE=7, PERCENTAGE=8, SCALAR=9, COLOR=10, ID_IDENT=11, CLASS_IDENT=12, 
		LOWER_IDENT=13, CAPITAL_IDENT=14, WS=15, OPEN_BRACE=16, CLOSE_BRACE=17, 
		SEMICOLON=18, COLON=19, PLUS=20, MIN=21, MUL=22, ASSIGNMENT_OPERATOR=23, 
		SMALLER=24, SMALLER_EQUAL=25, GREATER=26, GREATER_EQUAL=27, EQUAL=28, 
		NOT_EQUAL=29, AND=30, OR=31;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "ELSE", "BOX_BRACKET_OPEN", "BOX_BRACKET_CLOSE", "TRUE", "FALSE", 
			"PIXELSIZE", "PERCENTAGE", "SCALAR", "COLOR", "ID_IDENT", "CLASS_IDENT", 
			"LOWER_IDENT", "CAPITAL_IDENT", "WS", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", 
			"COLON", "PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR", "SMALLER", "SMALLER_EQUAL", 
			"GREATER", "GREATER_EQUAL", "EQUAL", "NOT_EQUAL", "AND", "OR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'else'", "'['", "']'", "'TRUE'", "'FALSE'", null, null, 
			null, null, null, null, null, null, null, "'{'", "'}'", "';'", "':'", 
			"'+'", "'-'", "'*'", "':='", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", 
			"'&&'", "'||'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "ELSE", "BOX_BRACKET_OPEN", "BOX_BRACKET_CLOSE", "TRUE", 
			"FALSE", "PIXELSIZE", "PERCENTAGE", "SCALAR", "COLOR", "ID_IDENT", "CLASS_IDENT", 
			"LOWER_IDENT", "CAPITAL_IDENT", "WS", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", 
			"COLON", "PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR", "SMALLER", "SMALLER_EQUAL", 
			"GREATER", "GREATER_EQUAL", "EQUAL", "NOT_EQUAL", "AND", "OR"
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


	public ICSSLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ICSS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001f\u00ba\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d"+
		"\u0002\u001e\u0007\u001e\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0004\u0006X\b\u0006\u000b\u0006\f\u0006Y\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0004\u0007`\b\u0007\u000b"+
		"\u0007\f\u0007a\u0001\u0007\u0001\u0007\u0001\b\u0004\bg\b\b\u000b\b\f"+
		"\bh\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0004\nu\b\n\u000b\n\f\nv\u0001\u000b\u0001\u000b\u0004\u000b"+
		"{\b\u000b\u000b\u000b\f\u000b|\u0001\f\u0001\f\u0005\f\u0081\b\f\n\f\f"+
		"\f\u0084\t\f\u0001\r\u0001\r\u0005\r\u0088\b\r\n\r\f\r\u008b\t\r\u0001"+
		"\u000e\u0004\u000e\u008e\b\u000e\u000b\u000e\f\u000e\u008f\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0000\u0000\u001f\u0001"+
		"\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007"+
		"\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d"+
		"\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/"+
		"\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f\u0001\u0000\u0007"+
		"\u0001\u000009\u0002\u000009af\u0003\u0000--09az\u0001\u0000az\u0001\u0000"+
		"AZ\u0004\u000009AZ__az\u0003\u0000\t\n\r\r  \u00c1\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000"+
		"\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000"+
		"#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001"+
		"\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000"+
		"\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u0000"+
		"1\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001"+
		"\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000"+
		"\u0000\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0001"+
		"?\u0001\u0000\u0000\u0000\u0003B\u0001\u0000\u0000\u0000\u0005G\u0001"+
		"\u0000\u0000\u0000\u0007I\u0001\u0000\u0000\u0000\tK\u0001\u0000\u0000"+
		"\u0000\u000bP\u0001\u0000\u0000\u0000\rW\u0001\u0000\u0000\u0000\u000f"+
		"_\u0001\u0000\u0000\u0000\u0011f\u0001\u0000\u0000\u0000\u0013j\u0001"+
		"\u0000\u0000\u0000\u0015r\u0001\u0000\u0000\u0000\u0017x\u0001\u0000\u0000"+
		"\u0000\u0019~\u0001\u0000\u0000\u0000\u001b\u0085\u0001\u0000\u0000\u0000"+
		"\u001d\u008d\u0001\u0000\u0000\u0000\u001f\u0093\u0001\u0000\u0000\u0000"+
		"!\u0095\u0001\u0000\u0000\u0000#\u0097\u0001\u0000\u0000\u0000%\u0099"+
		"\u0001\u0000\u0000\u0000\'\u009b\u0001\u0000\u0000\u0000)\u009d\u0001"+
		"\u0000\u0000\u0000+\u009f\u0001\u0000\u0000\u0000-\u00a1\u0001\u0000\u0000"+
		"\u0000/\u00a4\u0001\u0000\u0000\u00001\u00a6\u0001\u0000\u0000\u00003"+
		"\u00a9\u0001\u0000\u0000\u00005\u00ab\u0001\u0000\u0000\u00007\u00ae\u0001"+
		"\u0000\u0000\u00009\u00b1\u0001\u0000\u0000\u0000;\u00b4\u0001\u0000\u0000"+
		"\u0000=\u00b7\u0001\u0000\u0000\u0000?@\u0005i\u0000\u0000@A\u0005f\u0000"+
		"\u0000A\u0002\u0001\u0000\u0000\u0000BC\u0005e\u0000\u0000CD\u0005l\u0000"+
		"\u0000DE\u0005s\u0000\u0000EF\u0005e\u0000\u0000F\u0004\u0001\u0000\u0000"+
		"\u0000GH\u0005[\u0000\u0000H\u0006\u0001\u0000\u0000\u0000IJ\u0005]\u0000"+
		"\u0000J\b\u0001\u0000\u0000\u0000KL\u0005T\u0000\u0000LM\u0005R\u0000"+
		"\u0000MN\u0005U\u0000\u0000NO\u0005E\u0000\u0000O\n\u0001\u0000\u0000"+
		"\u0000PQ\u0005F\u0000\u0000QR\u0005A\u0000\u0000RS\u0005L\u0000\u0000"+
		"ST\u0005S\u0000\u0000TU\u0005E\u0000\u0000U\f\u0001\u0000\u0000\u0000"+
		"VX\u0007\u0000\u0000\u0000WV\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000"+
		"\u0000YW\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z[\u0001\u0000"+
		"\u0000\u0000[\\\u0005p\u0000\u0000\\]\u0005x\u0000\u0000]\u000e\u0001"+
		"\u0000\u0000\u0000^`\u0007\u0000\u0000\u0000_^\u0001\u0000\u0000\u0000"+
		"`a\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000"+
		"\u0000bc\u0001\u0000\u0000\u0000cd\u0005%\u0000\u0000d\u0010\u0001\u0000"+
		"\u0000\u0000eg\u0007\u0000\u0000\u0000fe\u0001\u0000\u0000\u0000gh\u0001"+
		"\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000"+
		"i\u0012\u0001\u0000\u0000\u0000jk\u0005#\u0000\u0000kl\u0007\u0001\u0000"+
		"\u0000lm\u0007\u0001\u0000\u0000mn\u0007\u0001\u0000\u0000no\u0007\u0001"+
		"\u0000\u0000op\u0007\u0001\u0000\u0000pq\u0007\u0001\u0000\u0000q\u0014"+
		"\u0001\u0000\u0000\u0000rt\u0005#\u0000\u0000su\u0007\u0002\u0000\u0000"+
		"ts\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000"+
		"\u0000vw\u0001\u0000\u0000\u0000w\u0016\u0001\u0000\u0000\u0000xz\u0005"+
		".\u0000\u0000y{\u0007\u0002\u0000\u0000zy\u0001\u0000\u0000\u0000{|\u0001"+
		"\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000"+
		"}\u0018\u0001\u0000\u0000\u0000~\u0082\u0007\u0003\u0000\u0000\u007f\u0081"+
		"\u0007\u0002\u0000\u0000\u0080\u007f\u0001\u0000\u0000\u0000\u0081\u0084"+
		"\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0082\u0083"+
		"\u0001\u0000\u0000\u0000\u0083\u001a\u0001\u0000\u0000\u0000\u0084\u0082"+
		"\u0001\u0000\u0000\u0000\u0085\u0089\u0007\u0004\u0000\u0000\u0086\u0088"+
		"\u0007\u0005\u0000\u0000\u0087\u0086\u0001\u0000\u0000\u0000\u0088\u008b"+
		"\u0001\u0000\u0000\u0000\u0089\u0087\u0001\u0000\u0000\u0000\u0089\u008a"+
		"\u0001\u0000\u0000\u0000\u008a\u001c\u0001\u0000\u0000\u0000\u008b\u0089"+
		"\u0001\u0000\u0000\u0000\u008c\u008e\u0007\u0006\u0000\u0000\u008d\u008c"+
		"\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u008d"+
		"\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u0091"+
		"\u0001\u0000\u0000\u0000\u0091\u0092\u0006\u000e\u0000\u0000\u0092\u001e"+
		"\u0001\u0000\u0000\u0000\u0093\u0094\u0005{\u0000\u0000\u0094 \u0001\u0000"+
		"\u0000\u0000\u0095\u0096\u0005}\u0000\u0000\u0096\"\u0001\u0000\u0000"+
		"\u0000\u0097\u0098\u0005;\u0000\u0000\u0098$\u0001\u0000\u0000\u0000\u0099"+
		"\u009a\u0005:\u0000\u0000\u009a&\u0001\u0000\u0000\u0000\u009b\u009c\u0005"+
		"+\u0000\u0000\u009c(\u0001\u0000\u0000\u0000\u009d\u009e\u0005-\u0000"+
		"\u0000\u009e*\u0001\u0000\u0000\u0000\u009f\u00a0\u0005*\u0000\u0000\u00a0"+
		",\u0001\u0000\u0000\u0000\u00a1\u00a2\u0005:\u0000\u0000\u00a2\u00a3\u0005"+
		"=\u0000\u0000\u00a3.\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005<\u0000"+
		"\u0000\u00a50\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005<\u0000\u0000\u00a7"+
		"\u00a8\u0005=\u0000\u0000\u00a82\u0001\u0000\u0000\u0000\u00a9\u00aa\u0005"+
		">\u0000\u0000\u00aa4\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005>\u0000"+
		"\u0000\u00ac\u00ad\u0005=\u0000\u0000\u00ad6\u0001\u0000\u0000\u0000\u00ae"+
		"\u00af\u0005=\u0000\u0000\u00af\u00b0\u0005=\u0000\u0000\u00b08\u0001"+
		"\u0000\u0000\u0000\u00b1\u00b2\u0005!\u0000\u0000\u00b2\u00b3\u0005=\u0000"+
		"\u0000\u00b3:\u0001\u0000\u0000\u0000\u00b4\u00b5\u0005&\u0000\u0000\u00b5"+
		"\u00b6\u0005&\u0000\u0000\u00b6<\u0001\u0000\u0000\u0000\u00b7\u00b8\u0005"+
		"|\u0000\u0000\u00b8\u00b9\u0005|\u0000\u0000\u00b9>\u0001\u0000\u0000"+
		"\u0000\t\u0000Yahv|\u0082\u0089\u008f\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}