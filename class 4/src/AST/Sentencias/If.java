/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Sentencias;

import AST.Expresiones.Condicion;
import AST.Simbolos.TablaSimbolos;
import java.util.ArrayList;

/**
 *
 * @author gm_ye
 */
public class If extends Sentencia{

    private final Condicion condicion; 
    private final ArrayList<Sentencia> listaVerdadero; 
    private final ArrayList<Sentencia> listaFalso; 

    public If(Condicion condicion, ArrayList<Sentencia> listaVerdadero, ArrayList<Sentencia> listaFalso, int linea, int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.listaVerdadero = listaVerdadero;
        this.listaFalso = listaFalso;
    }
    
    public If(Condicion condicion, ArrayList<Sentencia> listaVerdadero, int linea, int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.listaVerdadero = listaVerdadero;
        this.listaFalso = new ArrayList<>();
    }
    
    
    @Override
    public void Ejecutar(TablaSimbolos tabla) {
        boolean value = Boolean.parseBoolean(this.condicion.getValor(tabla).toString());
        if(value){
            this.listaVerdadero.forEach(sentencia -> {
                sentencia.Ejecutar(new TablaSimbolos(tabla));
            });
        }else {
            this.listaFalso.forEach(sentencia -> {
                sentencia.Ejecutar(new TablaSimbolos(tabla));
            });
        }
    }
    
        @Override
    public void getCodigoGraph(StringBuilder builder) {
        builder.append(this.hashCode()).append("[label=\"If\"];\n");
        builder.append(this.hashCode()).append("1111").append("[label=\"Verdadero\"];\n");
        builder.append(this.hashCode()).append("0000").append("[label=\"Falso\"];\n");
        builder.append(this.hashCode()).append("->").append(this.hashCode()).append("1111").append(";\n");
        builder.append(this.hashCode()).append("->").append(this.hashCode()).append("0000").append(";\n");
        this.listaVerdadero.forEach((t) -> {
            t.getCodigoGraph(builder);
            builder.append(this.hashCode()).append("1111").append("->").append(t.hashCode()).append(";\n");
        });
        this.listaFalso.forEach((t) -> {
            t.getCodigoGraph(builder);
            builder.append(this.hashCode()).append("0000").append("->").append(t.hashCode()).append(";\n");
        });   
    }
    
}
