///configuraciones
%lex
%options case-insensitive

%% 
//definir tokens 
// ER      retrun 'NOMBRE_TOKEN'

"calcular"    return 'TK_CALCULAR'; 

"+"         return 'TK_SUMA';
"-"         return 'TK_RESTA';
"*"         return 'TK_MULTIPLICACION';
"/"         return 'TK_DIVISION';

";"         return 'TK_PTCOMA';

[0-9]+("."[0-9]+)?\b    return 'DECIMAL';
[0-9]+\b                return 'ENTERO';


[\r\t\n\s]+                 {}

<<EOF>>                 return 'EOF';

.                           { console.log("error léxico: ", yytext, 'en línea ', yylloc.first_line, 'en columna: ', yylloc.first_column); }

/lex


%left 'TK_SUMA' 'TK_RESTA'
%left 'TK_MULTIPLICACION' 'TK_DIVISION'


%start inicio

%% 
// Producciones


inicio : instrucciones EOF;

instrucciones : instrucciones instruccion 
| instruccion
| error {console.log('Error Sintáctico con !!', yytext); }; 

instruccion : TK_CALCULAR EXPRESION TK_PTCOMA { console.log('El valor calculado es: ',$2); }; 

EXPRESION : EXPRESION TK_SUMA EXPRESION { $$ = $1   +  $3  ; }
|EXPRESION TK_RESTA EXPRESION { $$ = $1   -  $3  ; }
|EXPRESION TK_MULTIPLICACION EXPRESION { $$ = $1   *  $3  ; }
|EXPRESION TK_DIVISION EXPRESION { $$ = $1   /  $3  ; }
| VALOR { $$ = Number($1); }; 

VALOR : DECIMAL { $$ = $1; }
| ENTERO { $$ = $1; };


/*
EXPRESION TK_SUMA EXPRESION 
1           2       3
*/