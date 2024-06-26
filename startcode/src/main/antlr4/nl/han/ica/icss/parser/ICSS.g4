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

// binary operators
SMALLER: '<';
SMALLER_EQUAL: '<=';
GREATER: '>';
GREATER_EQUAL: '>=';
EQUAL: '==';
NOT_EQUAL: '!=';
AND: '&&';
OR: '||';


//--- PARSER: ---
stylesheet: variableAssignment* stylerule*;
stylerule: selector OPEN_BRACE (styleDeclaration | ifStatement | variableAssignment)* CLOSE_BRACE;
styleDeclaration: styleTag COLON (literal | variableName | expression) SEMICOLON;

literal: SCALAR #scalarLiteral | PIXELSIZE #pixelLiteral | COLOR #colorLiteral | PERCENTAGE #percentageLiteral | boolLiteral #booleanLiteral;
boolLiteral: TRUE | FALSE;

styleTag: LOWER_IDENT;
variableName: CAPITAL_IDENT;
variableAssignment: variableName ASSIGNMENT_OPERATOR literal SEMICOLON;

selector: (tagSelector | classSelector | idSelector);
tagSelector: LOWER_IDENT;
classSelector: CLASS_IDENT;
idSelector: ID_IDENT;

expression: (literal | variableName) | expression MUL expression | expression (PLUS | MIN) expression;
//boolExpression: (variableName | boolLiteral) | boolExpression (AND | OR) boolExpression | boolCheck;
boolExpression: boolCheck | boolExpression (AND | OR) boolExpression | boolLiteral | variableName;
boolCheck:  (expression) (SMALLER | SMALLER_EQUAL | GREATER | GREATER_EQUAL | EQUAL | NOT_EQUAL) (expression);

ifStatement: IF BOX_BRACKET_OPEN boolExpression BOX_BRACKET_CLOSE OPEN_BRACE (styleDeclaration | ifStatement | variableAssignment)*  CLOSE_BRACE elseStatement?;
elseStatement: ELSE OPEN_BRACE (styleDeclaration | ifStatement | variableAssignment)* CLOSE_BRACE;