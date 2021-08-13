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
public class While extends Sentencia {

    private Condicion condicion; 
    private ArrayList<Sentencia> sentencias; 

    public While(Condicion condicion, ArrayList<Sentencia> sentencias, int linea, int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.sentencias = sentencias;
    }
    
    
    
    @Override
    public void Ejecutar(TablaSimbolos tablaSimbolos) {
       TablaSimbolos local = new TablaSimbolos(tablaSimbolos);
       Object response = this.condicion.getValor(local);      
        while(true){
            if((boolean)response){
               this.sentencias.forEach((t) -> {
                   t.Ejecutar(local);
               });
               response = this.condicion.getValor(local);  
            }else {
                break;
            }
        }
    }

    @Override
    public void getCodigoGraph(StringBuilder builder) {
        builder.append(this.hashCode()).append("[label=\"While\"];\n");
        this.condicion.getCodigoGraph(builder);
        builder.append(this.hashCode()).append("12345555").append("[label=\"Sentencias\"];\n");
        builder.append(this.hashCode()).append("->").append(this.condicion.hashCode()).append(";\n");
        builder.append(this.hashCode()).append("->").append(this.hashCode()).append("12345555").append(";\n");
        this.sentencias.forEach((t) -> {
            t.getCodigoGraph(builder);
            builder.append(this.hashCode()).append("12345555").append("->").append(t.hashCode()).append(";\n");
        });
    }

    /**
     * @return the condicion
     */
    public Condicion getCondicion() {
        return condicion;
    }

    /**
     * @param condicion the condicion to set
     */
    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    /**
     * @return the sentencias
     */
    public ArrayList<Sentencia> getSentencias() {
        return sentencias;
    }

    /**
     * @param sentencias the sentencias to set
     */
    public void setSentencias(ArrayList<Sentencia> sentencias) {
        this.sentencias = sentencias;
    }
    
}
