///configuraciones
%{
    const { Print } = require('./dist/AST/Sentencias/print');
    const { ShowLower } = require('./dist/AST/Sentencias/show-lower');
    const { ShowUpper } = require('./dist/AST/Sentencias/show-upper');
%}

%lex
%options case-insensitive

CADENA =[\"\“\'] [^\"\”\'\n]* [\"\”\'\n]

%% 
//definir tokens 
// ER      retrun 'NOMBRE_TOKEN'

"print"    return 'pr_print'; 
"lower"    return 'pr_lower'; 
"upper"    return 'pr_upper'; 

"+"         return 'TK_SUMA';
"-"         return 'TK_RESTA';
"*"         return 'TK_MULTIPLICACION';
"/"         return 'TK_DIVISION';
"("         return 'TK_PAR1';
")"         return 'TK_PAR2';

";"         return 'TK_PTCOMA';

[0-9]+("."[0-9]+)?\b    return 'DECIMAL';
[0-9]+\b                return 'ENTERO';

{CADENA}               return 'val_cadena';
[\r\t\n\s]+                 {}

<<EOF>>                 return 'EOF';

.                           { console.log("error léxico: ", yytext, 'en línea ', yylloc.first_line, 'en columna: ', yylloc.first_column); }

/lex


%left 'TK_SUMA' 'TK_RESTA'
%left 'TK_MULTIPLICACION' 'TK_DIVISION'


%start inicio

%% 
// Producciones


inicio : instrucciones EOF {
    $1.forEach((sentencia) => sentencia.Ejecutar());
};

instrucciones : instrucciones instruccion { $$ = $1.concat($2); } 
| instruccion {$$ = [$1] }
| error {console.log('Error Sintáctico con !!', yytext); }; 

instruccion : pr_print TK_PAR1 val_cadena TK_PAR2 TK_PTCOMA {$$ = new Print($3,1,1); }
    | pr_upper TK_PAR1 val_cadena TK_PAR2 TK_PTCOMA {$$ = new ShowUpper($3,1,1); }
    | pr_lower TK_PAR1 val_cadena TK_PAR2 TK_PTCOMA {$$ = new ShowLower($3,1,1); }; 

/*
EXPRESION : EXPRESION TK_SUMA EXPRESION { $$ = $1   +  $3  ; }
|EXPRESION TK_RESTA EXPRESION { $$ = $1   -  $3  ; }
|EXPRESION TK_MULTIPLICACION EXPRESION { $$ = $1   *  $3  ; }
|EXPRESION TK_DIVISION EXPRESION { $$ = $1   /  $3  ; }
| VALOR { $$ = Number($1); }; 

VALOR : DECIMAL { $$ = $1; }
| ENTERO { $$ = $1; };
*/

/*
EXPRESION TK_SUMA EXPRESION 
1           2       3
*/