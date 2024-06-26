// Generated from /Users/jorey/Documents/School/ASD/APP/Programmeertalen/Compiler Opdracht/icss2022-sep/startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ICSSLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, ELSE=2, BOX_BRACKET_OPEN=3, BOX_BRACKET_CLOSE=4, TRUE=5, FALSE=6, 
		PIXELSIZE=7, PERCENTAGE=8, SCALAR=9, COLOR=10, ID_IDENT=11, CLASS_IDENT=12, 
		LOWER_IDENT=13, CAPITAL_IDENT=14, WS=15, OPEN_BRACE=16, CLOSE_BRACE=17, 
		SEMICOLON=18, COLON=19, PLUS=20, MIN=21, MUL=22, ASSIGNMENT_OPERATOR=23, 
		OPEN_PAREN=24, CLOSE_PAREN=25, SMALLER=26, SMALLER_EQUAL=27, GREATER=28, 
		GREATER_EQUAL=29, EQUAL=30, NOT_EQUAL=31, AND=32, OR=33;
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
			"COLON", "PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR", "OPEN_PAREN", "CLOSE_PAREN", 
			"SMALLER", "SMALLER_EQUAL", "GREATER", "GREATER_EQUAL", "EQUAL", "NOT_EQUAL", 
			"AND", "OR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'else'", "'['", "']'", "'TRUE'", "'FALSE'", null, null, 
			null, null, null, null, null, null, null, "'{'", "'}'", "';'", "':'", 
			"'+'", "'-'", "'*'", "':='", "'('", "')'", "'<'", "'<='", "'>'", "'>='", 
			"'=='", "'!='", "'&&'", "'||'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "ELSE", "BOX_BRACKET_OPEN", "BOX_BRACKET_CLOSE", "TRUE", 
			"FALSE", "PIXELSIZE", "PERCENTAGE", "SCALAR", "COLOR", "ID_IDENT", "CLASS_IDENT", 
			"LOWER_IDENT", "CAPITAL_IDENT", "WS", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", 
			"COLON", "PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR", "OPEN_PAREN", "CLOSE_PAREN", 
			"SMALLER", "SMALLER_EQUAL", "GREATER", "GREATER_EQUAL", "EQUAL", "NOT_EQUAL", 
			"AND", "OR"
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
		"\u0004\u0000!\u00c2\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0004\u0006\\\b"+
		"\u0006\u000b\u0006\f\u0006]\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0004\u0007d\b\u0007\u000b\u0007\f\u0007e\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0004\bk\b\b\u000b\b\f\bl\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0004\ny\b\n\u000b\n\f\nz\u0001"+
		"\u000b\u0001\u000b\u0004\u000b\u007f\b\u000b\u000b\u000b\f\u000b\u0080"+
		"\u0001\f\u0001\f\u0005\f\u0085\b\f\n\f\f\f\u0088\t\f\u0001\r\u0001\r\u0005"+
		"\r\u008c\b\r\n\r\f\r\u008f\t\r\u0001\u000e\u0004\u000e\u0092\b\u000e\u000b"+
		"\u000e\f\u000e\u0093\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0000\u0000!\u0001\u0001\u0003"+
		"\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011"+
		"\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010"+
		"!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a"+
		"5\u001b7\u001c9\u001d;\u001e=\u001f? A!\u0001\u0000\u0007\u0001\u0000"+
		"09\u0002\u000009af\u0003\u0000--09az\u0001\u0000az\u0001\u0000AZ\u0004"+
		"\u000009AZ__az\u0003\u0000\t\n\r\r  \u00c9\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001"+
		"\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000"+
		"\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000"+
		"\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001"+
		"\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000"+
		"\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000"+
		"\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?"+
		"\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0001C\u0001\u0000"+
		"\u0000\u0000\u0003F\u0001\u0000\u0000\u0000\u0005K\u0001\u0000\u0000\u0000"+
		"\u0007M\u0001\u0000\u0000\u0000\tO\u0001\u0000\u0000\u0000\u000bT\u0001"+
		"\u0000\u0000\u0000\r[\u0001\u0000\u0000\u0000\u000fc\u0001\u0000\u0000"+
		"\u0000\u0011j\u0001\u0000\u0000\u0000\u0013n\u0001\u0000\u0000\u0000\u0015"+
		"v\u0001\u0000\u0000\u0000\u0017|\u0001\u0000\u0000\u0000\u0019\u0082\u0001"+
		"\u0000\u0000\u0000\u001b\u0089\u0001\u0000\u0000\u0000\u001d\u0091\u0001"+
		"\u0000\u0000\u0000\u001f\u0097\u0001\u0000\u0000\u0000!\u0099\u0001\u0000"+
		"\u0000\u0000#\u009b\u0001\u0000\u0000\u0000%\u009d\u0001\u0000\u0000\u0000"+
		"\'\u009f\u0001\u0000\u0000\u0000)\u00a1\u0001\u0000\u0000\u0000+\u00a3"+
		"\u0001\u0000\u0000\u0000-\u00a5\u0001\u0000\u0000\u0000/\u00a8\u0001\u0000"+
		"\u0000\u00001\u00aa\u0001\u0000\u0000\u00003\u00ac\u0001\u0000\u0000\u0000"+
		"5\u00ae\u0001\u0000\u0000\u00007\u00b1\u0001\u0000\u0000\u00009\u00b3"+
		"\u0001\u0000\u0000\u0000;\u00b6\u0001\u0000\u0000\u0000=\u00b9\u0001\u0000"+
		"\u0000\u0000?\u00bc\u0001\u0000\u0000\u0000A\u00bf\u0001\u0000\u0000\u0000"+
		"CD\u0005i\u0000\u0000DE\u0005f\u0000\u0000E\u0002\u0001\u0000\u0000\u0000"+
		"FG\u0005e\u0000\u0000GH\u0005l\u0000\u0000HI\u0005s\u0000\u0000IJ\u0005"+
		"e\u0000\u0000J\u0004\u0001\u0000\u0000\u0000KL\u0005[\u0000\u0000L\u0006"+
		"\u0001\u0000\u0000\u0000MN\u0005]\u0000\u0000N\b\u0001\u0000\u0000\u0000"+
		"OP\u0005T\u0000\u0000PQ\u0005R\u0000\u0000QR\u0005U\u0000\u0000RS\u0005"+
		"E\u0000\u0000S\n\u0001\u0000\u0000\u0000TU\u0005F\u0000\u0000UV\u0005"+
		"A\u0000\u0000VW\u0005L\u0000\u0000WX\u0005S\u0000\u0000XY\u0005E\u0000"+
		"\u0000Y\f\u0001\u0000\u0000\u0000Z\\\u0007\u0000\u0000\u0000[Z\u0001\u0000"+
		"\u0000\u0000\\]\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001"+
		"\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_`\u0005p\u0000\u0000`a\u0005"+
		"x\u0000\u0000a\u000e\u0001\u0000\u0000\u0000bd\u0007\u0000\u0000\u0000"+
		"cb\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000ec\u0001\u0000\u0000"+
		"\u0000ef\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0005%\u0000"+
		"\u0000h\u0010\u0001\u0000\u0000\u0000ik\u0007\u0000\u0000\u0000ji\u0001"+
		"\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000"+
		"lm\u0001\u0000\u0000\u0000m\u0012\u0001\u0000\u0000\u0000no\u0005#\u0000"+
		"\u0000op\u0007\u0001\u0000\u0000pq\u0007\u0001\u0000\u0000qr\u0007\u0001"+
		"\u0000\u0000rs\u0007\u0001\u0000\u0000st\u0007\u0001\u0000\u0000tu\u0007"+
		"\u0001\u0000\u0000u\u0014\u0001\u0000\u0000\u0000vx\u0005#\u0000\u0000"+
		"wy\u0007\u0002\u0000\u0000xw\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000"+
		"\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{\u0016\u0001"+
		"\u0000\u0000\u0000|~\u0005.\u0000\u0000}\u007f\u0007\u0002\u0000\u0000"+
		"~}\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080~"+
		"\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0018"+
		"\u0001\u0000\u0000\u0000\u0082\u0086\u0007\u0003\u0000\u0000\u0083\u0085"+
		"\u0007\u0002\u0000\u0000\u0084\u0083\u0001\u0000\u0000\u0000\u0085\u0088"+
		"\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0086\u0087"+
		"\u0001\u0000\u0000\u0000\u0087\u001a\u0001\u0000\u0000\u0000\u0088\u0086"+
		"\u0001\u0000\u0000\u0000\u0089\u008d\u0007\u0004\u0000\u0000\u008a\u008c"+
		"\u0007\u0005\u0000\u0000\u008b\u008a\u0001\u0000\u0000\u0000\u008c\u008f"+
		"\u0001\u0000\u0000\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008d\u008e"+
		"\u0001\u0000\u0000\u0000\u008e\u001c\u0001\u0000\u0000\u0000\u008f\u008d"+
		"\u0001\u0000\u0000\u0000\u0090\u0092\u0007\u0006\u0000\u0000\u0091\u0090"+
		"\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0091"+
		"\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0094\u0095"+
		"\u0001\u0000\u0000\u0000\u0095\u0096\u0006\u000e\u0000\u0000\u0096\u001e"+
		"\u0001\u0000\u0000\u0000\u0097\u0098\u0005{\u0000\u0000\u0098 \u0001\u0000"+
		"\u0000\u0000\u0099\u009a\u0005}\u0000\u0000\u009a\"\u0001\u0000\u0000"+
		"\u0000\u009b\u009c\u0005;\u0000\u0000\u009c$\u0001\u0000\u0000\u0000\u009d"+
		"\u009e\u0005:\u0000\u0000\u009e&\u0001\u0000\u0000\u0000\u009f\u00a0\u0005"+
		"+\u0000\u0000\u00a0(\u0001\u0000\u0000\u0000\u00a1\u00a2\u0005-\u0000"+
		"\u0000\u00a2*\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005*\u0000\u0000\u00a4"+
		",\u0001\u0000\u0000\u0000\u00a5\u00a6\u0005:\u0000\u0000\u00a6\u00a7\u0005"+
		"=\u0000\u0000\u00a7.\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005(\u0000"+
		"\u0000\u00a90\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005)\u0000\u0000\u00ab"+
		"2\u0001\u0000\u0000\u0000\u00ac\u00ad\u0005<\u0000\u0000\u00ad4\u0001"+
		"\u0000\u0000\u0000\u00ae\u00af\u0005<\u0000\u0000\u00af\u00b0\u0005=\u0000"+
		"\u0000\u00b06\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005>\u0000\u0000\u00b2"+
		"8\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005>\u0000\u0000\u00b4\u00b5\u0005"+
		"=\u0000\u0000\u00b5:\u0001\u0000\u0000\u0000\u00b6\u00b7\u0005=\u0000"+
		"\u0000\u00b7\u00b8\u0005=\u0000\u0000\u00b8<\u0001\u0000\u0000\u0000\u00b9"+
		"\u00ba\u0005!\u0000\u0000\u00ba\u00bb\u0005=\u0000\u0000\u00bb>\u0001"+
		"\u0000\u0000\u0000\u00bc\u00bd\u0005&\u0000\u0000\u00bd\u00be\u0005&\u0000"+
		"\u0000\u00be@\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005|\u0000\u0000\u00c0"+
		"\u00c1\u0005|\u0000\u0000\u00c1B\u0001\u0000\u0000\u0000\t\u0000]elz\u0080"+
		"\u0086\u008d\u0093\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}