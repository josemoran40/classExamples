
/*Imports and define package*/
package analyzers;
import java_cup.runtime.Symbol; 

%%

/*Define how will works our scanner*/
%class Scanner /*Class name will be Scanner*/
%public /*Will be public*/
%line /*Count of lines*/
%char /*Count of recognized characters*/
%cup /*Will works with cup*/
%unicode /*Set of characters is unicode*/
%ignorecase /*Will be ignore case; Example: Compi1 equals cOmpI1*/


%init{ 
   /*Constructor
	*you can declare variables for example String x = ""; or instance Objects
	*/
%init} 


/*Section or regular expressions*/
WHITE = [ \r\t]+
NUMBER = [0-9]+
DECIMAL = [0-9]+("."[0-9]+)?

%%

/*
	Sym.* will be the name of the token
	yytext() is the actual value
*/

"(" {return new Symbol(sym.PARLEFT, yytext());} 
")" {return new Symbol(sym.PARRIGHT, yytext());} 

"+" {return new Symbol(sym.ADD, yytext());} 
"-" {return new Symbol(sym.MINUS, yytext());} 
"*" {return new Symbol(sym.TIMES, yytext());} 
"/" {return new Symbol(sym.DIV, yytext());} 

\*Ignore white spaces*\
{WHITE} {}

{NUMBER} {return new Symbol(sym.NUMBER, yytext());} 
{DECIMAL} {return new Symbol(sym.DECIMAL, yytext());} 

. {
    System.out.println("Lexical error: "+yytext());
}