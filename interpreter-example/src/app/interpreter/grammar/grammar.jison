%{
    const {Aritmetica,TipoAritmetica} = require('../Expresion/Aritmetica')
    const {Relacional,TipoRelacional} = require('../Expresion/Relacional')
    const {Literal,TipoLiteral} = require('../Expresion/Literal')
    const {Acceso} = require('../Expresion/Acceso')
    const {Declaracion} = require('../Instruccion/Declaracion')
    const {Print} = require('../Instruccion/Print')
%}

%lex

%options case-sensitive

%%

\s+											// se ignoran espacios en blanco
"//".*										// comentario simple línea
[/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]			// comentario multiple líneas

//palabras reservadas

"true"                  return 'TRUE';
"false"                 return 'FALSE';
"print"                 return 'PRINT';

//'dijofdjf'+${}'
[0-9]+("."[0-9]+)?\b  	return 'ENTERO';
[0-9]+\b				return 'ENTERO';
([a-zA-Z])[a-zA-Z0-9_]*	return 'IDENTIFICADOR';

"("                     return 'PAR_ABRE';
")"                     return 'PAR_CIERRA';

//logicos
"=="                    return 'D_IGUAL';
"<="                    return 'MENOR_IGUAL';
"<"                     return 'MENOR';
">="                    return 'MAYOR_IGUAL';                     
">"                     return 'MAYOR';
"!="                    return 'DIFERENTE';
//*/

','                     return 'COMA'
"+"					    return 'MAS';
"-"					    return 'MENOS';
"*"					    return 'POR';
"/"					    return 'DIVIDIR';
"="                     return 'IGUAL';
";"                     return 'PUNTO_Y_COMA';

\"[^\"]*\"				{ yytext = yytext.substr(1,yyleng-2); return 'CADENA'; }
\'[^\']*\'				{ yytext = yytext.substr(1,yyleng-2); return 'CADENA'; }
<<EOF>>				    return 'EOF';
.					   {console.log(yylloc.first_line, yylloc.first_column,'Lexico',yytext)}
/lex


%left 'INTERROGACION' 'DOS_PUNTOS'
%left 'OR'
%left 'AND'
%left 'DIFERENTE' 'D_IGUAL'
%left 'MENOR_IGUAL' 'MAYOR_IGUAL' 'MENOR' 'MAYOR'
%left 'MAS' 'MENOS' 
%left 'POR' 'DIVIDIR'
%left UMENOS
%right 'NOT' 



%start ini

%% 

ini
	: instrucciones EOF{
		return $1;
	}
;

instrucciones
    :instrucciones inicio 
        { $1.push($2); $$ = $1; }
    |inicio 
        { $$ = [$1]; }
;


inicio
    :declaracion
    |print
;

declaracion 
    : IDENTIFICADOR IGUAL expresion PUNTO_Y_COMA 
        {$$ = new Declaracion($1,$3,@1.first_line, @1.first_column)}
;

print
    :PRINT PAR_ABRE ListaExpr PAR_CIERRA PUNTO_Y_COMA 
        {$$ = new Print($3,@1.first_line, @1.first_column)}
;

ListaExpr 
    : ListaExpr COMA expresion
        { $1.push($3);$$ = $1;}
    | expresion
        {$$ = [$1];}
;


//EXPRESION

expresion
    :MENOS expresion %prec UMENOS		{$$= new Aritmetica($2,new Literal("-1",TipoLiteral.NUMBER, @1.first_line, @1.first_column),TipoAritmetica.MULTIPLICACION, @1.first_line, @1.first_column)}
    |expresion MAS expresion            {$$= new Aritmetica($1,$3,TipoAritmetica.SUMA, @1.first_line, @1.first_column)} 
    |expresion MENOS expresion          {$$= new Aritmetica($1,$3,TipoAritmetica.RESTA, @1.first_line, @1.first_column)} 
    |expresion POR expresion            {$$= new Aritmetica($1,$3,TipoAritmetica.MULTIPLICACION, @1.first_line, @1.first_column)}   
    |expresion DIVIDIR expresion        {$$= new Aritmetica($1,$3,TipoAritmetica.DIVISION, @1.first_line, @1.first_column)} 
    |expresion D_IGUAL expresion        {$$= new Relacional($1,$3,TipoRelacional.IGUALIGUAL, @1.first_line, @1.first_column)} 
    |expresion DIFERENTE expresion      {$$= new Relacional($1,$3,TipoRelacional.DIFERENTE, @1.first_line, @1.first_column)} 
    |expresion MAYOR_IGUAL expresion    {$$= new Relacional($1,$3,TipoRelacional.MAYOR_IGUAL, @1.first_line, @1.first_column)} 
    |expresion MENOR_IGUAL expresion    {$$= new Relacional($1,$3,TipoRelacional.MENOR_IGUAL, @1.first_line, @1.first_column)} 
    |expresion MAYOR expresion          {$$= new Relacional($1,$3,TipoRelacional.MAYOR, @1.first_line, @1.first_column)}         
    |expresion MENOR expresion          {$$= new Relacional($1,$3,TipoRelacional.MENOR, @1.first_line, @1.first_column)}
    |PAR_ABRE expresion PAR_CIERRA      {$$= $2}
	|ENTERO	                            {$$= new Literal($1,TipoLiteral.NUMBER, @1.first_line, @1.first_column)}							
	|CADENA                             {$$= new Literal($1,TipoLiteral.STRING, @1.first_line, @1.first_column)}        					
    |TRUE                               {$$= new Literal($1,TipoLiteral.BOOL, @1.first_line, @1.first_column)}                              
    |FALSE                              {$$= new Literal($1,TipoLiteral.BOOL, @1.first_line, @1.first_column)}
    |IDENTIFICADOR                      {$$= new Acceso($1, @1.first_line, @1.first_column)}
;
