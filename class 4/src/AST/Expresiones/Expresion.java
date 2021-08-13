/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresiones;

import AST.Simbolos.TablaSimbolos;

/**
 *
 * @author gm_ye
 */
public abstract class Expresion {

    private int linea = 1; 
    private int columna = 1;
    
    public abstract Object getValor(TablaSimbolos tablaSimbolos);
    public abstract void getCodigoGraph(StringBuilder builder);

    public Expresion(int linea, int columna) {
        this.linea = linea; 
        this.columna = columna; 
    }

    
    /**
     * @return the linea
     */
    public int getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(int linea) {
        this.linea = linea;
    }

    /**
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

}
