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

// Self added
OPEN_PAREN: '(';
CLOSE_PAREN: ')';
DIV: '/';


//--- PARSER: ---
stylesheet: variableAssignment* stylerule*;
stylerule: selector OPEN_BRACE (styleDeclaration | ifStatement | variableAssignment)* CLOSE_BRACE;
styleDeclaration: styleTag COLON (literal | variableName | expression) SEMICOLON;

literal: SCALAR #scalarLiteral | PIXELSIZE #pixelLiteral | COLOR #colorLiteral | PERCENTAGE #percentageLiteral | (TRUE  | FALSE ) #booleanLiteral;

styleTag: LOWER_IDENT;
variableName: CAPITAL_IDENT;
variableAssignment: variableName ASSIGNMENT_OPERATOR literal SEMICOLON;
selector: (ID_IDENT | CLASS_IDENT | LOWER_IDENT);

expression: (literal | variableName) | expression MUL expression | expression (PLUS | MIN) expression;

ifStatement: IF BOX_BRACKET_OPEN (variableName | (TRUE | FALSE)) BOX_BRACKET_CLOSE OPEN_BRACE (styleDeclaration | ifStatement | variableName)*  CLOSE_BRACE elseStatement?;
elseStatement: ELSE OPEN_BRACE (styleDeclaration | ifStatement | variableAssignment)* CLOSE_BRACE;




