


/*Imports and define package*/
package Analizadores;
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

%%

/*
	Sym.* will be the name of the token
	yytext() is the actual value


	function (param, param, param){
	
	}
*/

"(" {return new Symbol(sym.PARLEFT, yytext());} 
")" {return new Symbol(sym.PARRIGHT, yytext());} 
";" {return new Symbol(sym.PUNTOYCOMA, yytext());} 
"{" {return new Symbol(sym.LEFT, yytext());} 
"}" {return new Symbol(sym.RIGHT, yytext());} 

"function" {return new Symbol(sym.FUNCTION, yytext());} 
"param" {return new Symbol(sym.PARAMETRO, yytext());} 

\*Ignore white spaces*\
{WHITE} {}


. {
    System.out.println("Lexical error: "+yytext());

}