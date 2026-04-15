parser grammar EMJParser;
    options { tokenVocab = EMJLexer; }

root: map #rootMap
    | programme #rootProgramme
    ;  // root of the parse tree is either a map or a programme

// MAP

map: MAP NUMBERS NUMBERS orientation (ROWMAP)+;
orientation: UP | DOWN | LEFT | RIGHT;

// PROGRAMME

programme: importation main (funcdecl)* EOF;

importation: IMPORT QUOTE IDCARTE QUOTE;

// function
main: VOID MAINFUNC LB RB LCB innerFunction returnMain RCB;
funcdecl: functype identif LB funcparams RB LCB innerFunction returnInstruction RCB;

innerFunction: (vardecl | instruction)*;
returnInstruction: RETURN funcreturn SMC;
returnMain: RETURN VOID SMC;
funcreturn: rightexp | VOID;

funcparams: funcparamsnotempty?;
funcparamsnotempty: type identif (COMMA funcparamsnotempty)*;


boolexp: booleanVal
       | (LB boolexp RB)
       | boolexp (connector boolexp)+
       | NOT boolexp
       | boolexp (comparator boolexp)+
       | intexp 
       | callfunc | CHAR | STRING | tuple
       ;

intexp: integer 
       | leftexp
       | (LB intexp RB)
       | (DASH intexp)
       | intexp (operator intexp)+
       ;
operator: PLUS | DASH | MULT | DIV | MOD;
comparator: SUPR | INFR | EQUALOP | SUPEQUALS | INFEQUALQ | DIFF;
connector: AND | OR;

callfunc: identif LB callfuncargs? RB;
callfuncargs: callfuncarg (COMMA callfuncargs)*;
callfuncarg: rightexp| leftexp;
// expressions
rightexp: intexp 
        | callfunc 
        | CHAR 
        | STRING 
        | tuple
        | boolexp
        ;
        
leftexp: identif IDGETTER?; 

// Instructions
instruction: ifinstr | loopinstr | (callfunc | preFunc | affectinstr) SMC;
vardecl: type identif assignation? SMC;

assignation:ASSIGN rightexp; // Use for declaration as plain text value 
affectinstr: leftexp ASSIGN rightexp; // Use for affectation can be complexe expression value


// prefunction
preFunc: upFunc | downFunc | leftFunc | rightFunc | lightsFunc | soundFunc | arrestFunc;
upFunc: UP LB intexp RB;
downFunc: DOWN LB intexp RB;
leftFunc: LEFT LB intexp RB;
rightFunc: RIGHT LB intexp RB;
lightsFunc: LIGHTS LB RB;
soundFunc: SOUND LB RB;
arrestFunc: ARREST LB RB;

// if 
ifinstr: IF LB boolexp RB LCB (instruction | vardecl)+ RCB ELSE LCB elseinstr RCB;
elseinstr: (instruction | vardecl)+ | (SKIPT SMC);

// loop
loopinstr: whileLoop | forLoop;
whileLoop: WHILE LB boolexp RB LCB (instruction | vardecl)+ RCB;
forLoop: FOR LB intexp RB LCB (instruction | vardecl)+ RCB;

// Value
integer: DASH? NUMBERS; // ? means optionnal
booleanVal: TRUE | FALSE;
tuple : LB integer COMMA integer RB 
      | LB CHAR COMMA CHAR RB 
      | LB STRING COMMA STRING RB 
      | LB booleanVal COMMA booleanVal RB
      ;

functype: type | VOID;
type: emojitype | (TUPLEOJITYPE LB emojitype RB);
emojitype: INTMOJITYPE 
         | STRMOJITYPE 
         | BOOLMOJITYPE 
         | CHARMOJITYPE
         ;
emojis: (DIGIT | EMOJI | emojitype | TRUE | FALSE)+;

identif: SBL emojis SBR;