lexer grammar EMJLexer;

ROWMAP: (MAPEMOJI ' ')* MAPEMOJI;
MAP: '\u{1F5FA}''\u{FE0F}';
// MAP: 'map';
MAPEMOJI: POLICECAR | HOUSES | WAY | THIEF | WATER | TRACTOR | VOLCANO | CONSTRUCTION;
POLICECAR: '\u{1F693}';
HOUSES: '\u{1F3D8}' '\u{FE0F}';
WAY: '\u{1F6E3}' '\u{FE0F}';
THIEF: '\u{1F9B9}';
WATER: '\u{1F30A}';
TRACTOR: '\u{1F69C}';
VOLCANO: '\u{1F30B}';
CONSTRUCTION: '\u{1F6A7}';

IF: '\u{1F914}';//thinking-face
ELSE:'\u{1F928}';//raised-eyebrow
SKIPT: '\u{1F447}';// point-down F

WHILE:'\u{267E}' '\u{FE0F}';//infinity
FOR: '\u{1F501}';//repeat

MAINFUNC: '\u{1F3E0}'; // house
VOID: '\u{1F300}';// cyclone
IMPORT: '\u{1F4E6}'; // package
IDCARTE: ID '.map';

RETURN: '\u{21A9}' '\u{FE0F}';

IDGETTER: ONE | ZERO;
ONE: '\u{0031}' '\u{FE0F}';
ZERO: '\u{0030}' '\u{FE0F}';


QUOTE: '"' ;
LB: '(' ; // left bracket
RB: ')' ; // right bracket
LCB: '{';
RCB: '}';
SBR: ']' ; // right square bracket
SBL: '[' ; // left square bracket
COMMA: ',' ;
SMC : ';'; // semicolon
APOST: '\'';
ASSIGN: EQUALS;

SCHAR: APOST; // Start character value 
ECHAR: APOST; // End character value 

UP: '\u{2B06}' '\u{FE0F}';// up-arrow
DOWN: '\u{2B07}' '\u{FE0F}';// down-arrow
RIGHT: '\u{27A1}' '\u{FE0F}';//right-arrow
LEFT: '\u{2B05}' '\u{FE0F}'; //left-arrow

LIGHTS: '\u{1F6A8}'; // police-car-light
ARREST: '\u{270B}'; // raised-hand
SOUND: '\u{1F4FB}'; // radio

STRMOJITYPE: '\u{1F521}';
INTMOJITYPE: '\u{1F522}';
BOOLMOJITYPE: '\u{1F51F}';
CHARMOJITYPE: '\u{1F523}';
TUPLEOJITYPE: '\u{1F465}';

TRUE: '\u{2705}';
FALSE: '\u{274C}';
NUMBERS: DIGIT+;    
CHAR: APOST (LETTER | DIGIT| ' ') APOST ;
STRING: QUOTE (LETTER | DIGIT| ' ')+ QUOTE ;

ID: (LETTER | DIGIT)+;
// EMOJIS: (DIGIT | EMOJI)+;


EQUALOP:'==';
SUPR:'>';
INFR:'<';
SUPEQUALS:'>=';
INFEQUALQ:'<=';
DIFF:'!=';
NOT:'!';
AND:'&&';
OR:'||';
PLUS : '+';
MULT : '*';
DIV : '/';
DASH: '-';
EQUALS: '=';
MOD : '%';
fragment LETTER:'a'..'z'|'A'..'Z'  ;
DIGIT: '0'..'9' ;
EMOJI: 
    '\u{1F600}'..'\u{1F64F}' | // Emoticons
    '\u{1F301}'..'\u{1F5FF}' | // Miscellaneous Symbols and Pictographs
    '\u{1F680}'..'\u{1F6FF}' | // Transport and Map Symbols
    '\u{1F900}'..'\u{1F9FF}' | // Supplemental Symbols and Pictographs
    '\u{1F004}'..'\u{1F0CF}' | // Enclosed Characters
    '\u{1F170}'..'\u{1F1FF}' | // Enclosed Characters (again)
    '\u{1F200}'..'\u{1F251}' | // Squared Latin Letters
    '\u{23F2}' '\u{FE0F}'    | // Clock
    '\u{2000}'..'\u{2FFF}'   // Miscellaneous Symbols and Arrowsz
    ;


// Commentaires
COM1: (SCOM .*? ECOM) -> skip;
COM2: (SLCOM .*? NEWLINE) -> skip;

SCOM: '\u{1F50A}'; // Start of comment
ECOM: '\u{1F508}'; // End of comment
SLCOM: '\u{1F4E2}'; // Single line comment

// Whitespaces -> ignored
NEWLINE: '\r'? '\n'  -> skip ;
WS: [ \t]+ -> skip ;
