/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST;

import AST.Sentencias.Sentencia;
import java.util.ArrayList;

/**
 *
 * @author gm_ye
 */
public class Metodo {

    public Metodo(String nombre, ArrayList<Sentencia> sentencias) {
        this.nombre = nombre;
        this.sentencias = sentencias;
    }

    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    private String nombre; 
    private ArrayList<Sentencia> sentencias;
    
}
