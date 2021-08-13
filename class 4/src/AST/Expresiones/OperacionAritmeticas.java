/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresiones;

import AST.Errores.MiError;
import AST.Errores.TipoError;
import AST.Simbolos.TablaSimbolos;

/**
 *
 * @author gm_ye
 */
public class OperacionAritmeticas extends Expresion {

    Expresion izq;
    Expresion der;
    TipoOperacion tipo;
    String valor;

    public OperacionAritmeticas(Expresion izq, Expresion der, TipoOperacion tipo,int linea, int columna) {
        super(linea, columna);
        this.izq = izq;
        this.der = der;
        this.tipo = tipo;
        this.valor = null;
    }

    public OperacionAritmeticas(TipoOperacion tipo,String valor, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.izq = null;
        this.der = null;
        this.valor = valor;
    }
    
    

    @Override
    public Object getValor(TablaSimbolos tablaSimbolos) {
        switch (tipo) {
            case DIVISION:
                double resIzq = Double.parseDouble(izq.getValor(tablaSimbolos).toString());
                double resDer = Double.parseDouble(der.getValor(tablaSimbolos).toString());
                return resIzq / resDer;
            case MULTIPLICACION:
                resIzq = Double.parseDouble(izq.getValor(tablaSimbolos).toString());
                resDer = Double.parseDouble(der.getValor(tablaSimbolos).toString());
                return resIzq * resDer;
            case RESTA:
                resIzq = Double.parseDouble(izq.getValor(tablaSimbolos).toString());
                resDer = Double.parseDouble(der.getValor(tablaSimbolos).toString());
                return resIzq - resDer;
            case SUMA:
                resIzq = Double.parseDouble(izq.getValor(tablaSimbolos).toString());
                resDer = Double.parseDouble(der.getValor(tablaSimbolos).toString());
                return resIzq + resDer;
            case BOOLEANO: 
                return Boolean.parseBoolean(this.valor);
            case CADENA:
                return this.valor.replace("\"", "");
            case NUMERO: 
                return Double.parseDouble(this.valor);
            case ID: 
                // consultar TS 
               if(tablaSimbolos.existeVariable(valor)){
                return tablaSimbolos.getValorVariable(valor);
               }else{
                   // ERROR
                   Analizador.AnalizadorLenguaje.errores.add(new MiError(this.getLinea(), this.getColumna(), 
                           TipoError.SEMANTICO, "No existe la variable "+this.valor));
               }
        }
        return null;
    }

    @Override
    public void getCodigoGraph(StringBuilder builder) {
        switch (this.tipo) {
            case NUMERO:
            case BOOLEANO:
            case CADENA:
                builder.append(this.hashCode()).append("[label=\"").append(valor.replace("\"", "\\\"")).append("\"];").append("\n");
                break;
            case ID:
                builder.append(this.hashCode()).append("[label=\"ID\"];").append("\n");
                builder.append(this.hashCode()).append(valor.hashCode())
                        .append("[label=\"").append(valor.replace("\"", ""))
                        .append("\"];").append("\n");
                builder.append(this.hashCode())
                        .append("->").
                        append(this.hashCode()).append(valor.hashCode())
                        .append(";").append("\n");
                break;
            default:
                builder.append(this.hashCode()).append("[label=\"OperacionAritmetica\"];").append("\n");
            this.izq.getCodigoGraph(builder);
            builder.append(this.hashCode()).append(this.tipo.hashCode())
                    .append("[label=\"")
                    .append(this.tipo.toString())
                    .append("\"];")
                    .append("\n");
            this.der.getCodigoGraph(builder);
            builder.append(this.hashCode()).append("->").append(this.izq.hashCode()).append(";").append("\n");
            builder.append(this.hashCode()).append("->").append(this.hashCode()).append(this.tipo.hashCode())
                    .append(";").append("\n");
            builder.append(this.hashCode())
                    .append("->")
                    .append(this.der.hashCode())
                    .append(";").append("\n");
                break;
        } 
    }

}
