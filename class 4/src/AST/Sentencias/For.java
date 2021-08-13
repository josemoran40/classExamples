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
public class For extends Sentencia {

    private Sentencia asignacion; 
    private Condicion condicion; 
    private Sentencia cambio; 
    private ArrayList<Sentencia> sentencias; 

    public For(Sentencia asignacion, Condicion condicion, Sentencia cambio, ArrayList<Sentencia> sentencias, int linea, int columna) {
        super(linea, columna);
        this.asignacion = asignacion;
        this.condicion = condicion;
        this.cambio = cambio;
        this.sentencias = sentencias;
    }
    
    
    

    @Override
    public void Ejecutar(TablaSimbolos tablaSimbolos) {
        TablaSimbolos entorno = new TablaSimbolos(tablaSimbolos);
        this.asignacion.Ejecutar(entorno);
        while (true) {
            if(this.condicion.getValor(entorno).toString().equals("true")){
                this.sentencias.forEach((t) -> {
                    t.Ejecutar(entorno);
                });
                this.cambio.Ejecutar(tablaSimbolos);
            }else {
                break;
            }            
        }
    }

    @Override
    public void getCodigoGraph(StringBuilder builder) {
        builder.append(this.hashCode()).append("[label=\"For\"];\n");
        builder.append(this.hashCode()).append("12345555").append("[label=\"Sentencias\"];\n");
        this.asignacion.getCodigoGraph(builder);
        this.condicion.getCodigoGraph(builder);
        this.cambio.getCodigoGraph(builder);
        builder.append(this.hashCode()).append("->").append(this.asignacion.hashCode()).append(";\n");
        builder.append(this.hashCode()).append("->").append(this.condicion.hashCode()).append(";\n");
        builder.append(this.hashCode()).append("->").append(this.cambio.hashCode()).append(";\n");
        
        builder.append(this.hashCode()).append("->").append(this.hashCode()).append("12345555").append(";\n");
        this.sentencias.forEach((t) -> {
            t.getCodigoGraph(builder);
            builder.append(this.hashCode()).append("12345555").append("->").append(t.hashCode()).append(";\n");
        });
    }

    /**
     * @return the asignacion
     */
    public Sentencia getAsignacion() {
        return asignacion;
    }

    /**
     * @param asignacion the asignacion to set
     */
    public void setAsignacion(Asignacion asignacion) {
        this.asignacion = asignacion;
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
     * @return the cambio
     */
    public Sentencia getCambio() {
        return cambio;
    }

    /**
     * @param cambio the cambio to set
     */
    public void setCambio(Sentencia cambio) {
        this.cambio = cambio;
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
