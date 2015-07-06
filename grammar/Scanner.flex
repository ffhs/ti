package ch.ffhs.ti.umk.skript.parser;

import static ch.ffhs.ti.umk.skript.parser.Terminals.*;
import java_cup.runtime.Symbol;

%%

%public
%apiprivate
%class Scanner
%cupsym Terminals
%cup
%unicode
%line
%column

%{

    private Symbol sym(int type)
    {
         return new Symbol(type, yyline, yycolumn);
    }

    private Symbol sym(int type, boolean bValue)
    {
         return new Symbol(type, yyline, yycolumn, bValue);
    }

    private Symbol symVal(int type)
    {
         return new Symbol(type, yyline, yycolumn, yytext());
    }
   
%}

OPT_SPACE = [ \t]+
BREAK     = [\r|\n|\r\n]+
COMMENT   = "//".*
%%

"-"     { return sym(MINUS); }
"+"     { return sym(PLUS); }
"*"     { return sym(TIMES); }
"/"     { return sym(DIV); }
"%"     { return sym(MOD); }
"^"    { return sym(POW); }
":="    { return sym(ASSIGN); }
"("     { return sym(LPAR); }
")"     { return sym(RPAR); }

"{"     { return sym(LBRACE); }
"}"     { return sym(RBRACE); }
","     { return sym(COMMA); }
"var"   { return sym(VAR); }
"fun"   { return sym(FUNCTION); }
";"     { return sym(SEMICOLON); }

"IF"    { return sym(IF); }
"END"   { return sym(END); }
"ELSE"  { return sym(ELSE); }

"WHILE" { return sym(WHILE); }
"=="     { return sym(EQUAL); }
"!="     { return sym(NOTEQUAL); }
"<"      { return sym(LESS); }
">"      { return sym(BIGGER); }
"FOR"    { return sym(FOR); }
"OR"    { return sym(OR); }
"AND"    { return sym(AND); }

  /* boolean literals */
"true"                         { return sym(BOOLCONST, true); }
"false"                        { return sym(BOOLCONST, false); }

[0-9]+  { return symVal(NUMBER); }
([:jletter:]|_)([:jletterdigit:]|_)*    {return symVal(NAME); }

{BREAK} { return sym(SEP); }
{OPT_SPACE} { }
{COMMENT}   { } 

.       { throw new RuntimeException("Illegal Symbol '" + yytext() + '\''
             + " in line " + yyline + ", column" + yycolumn); } 






