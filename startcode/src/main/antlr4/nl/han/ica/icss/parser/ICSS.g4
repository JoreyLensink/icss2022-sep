grammar ICSS;

//--- LEXER: ---

// IF support:
IF: 'if';
ELSE: 'else';
BOX_BRACKET_OPEN: '[';
BOX_BRACKET_CLOSE: ']';


//Literals
TRUE: 'TRUE';
FALSE: 'FALSE';
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;


//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';




//--- PARSER: ---
stylesheet: varDeclaration* stylerule*;
stylerule: (ID_IDENT | CLASS_IDENT | LOWER_IDENT) + OPEN_BRACE + styleDeclaration* CLOSE_BRACE;
styleDeclaration: property + COLON + ((value | CAPITAL_IDENT) + operation*)  + SEMICOLON | ifStatement;
property: LOWER_IDENT;
value: SCALAR | PIXELSIZE #pixelLiteral | COLOR #colorLiteral | PERCENTAGE | TRUE | FALSE;

varDeclaration: CAPITAL_IDENT + ASSIGNMENT_OPERATOR + value + SEMICOLON;
operation: (PLUS | MIN | MUL) + (value | CAPITAL_IDENT);

ifStatement: IF + BOX_BRACKET_OPEN + CAPITAL_IDENT + BOX_BRACKET_CLOSE + OPEN_BRACE + (styleDeclaration* | ifStatement)  CLOSE_BRACE (ELSE OPEN_BRACE (styleDeclaration* | ifStatement) CLOSE_BRACE)?;

