/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Sentencias;

import AST.Expresiones.Expresion;
import AST.Simbolos.TablaSimbolos;

/**
 *
 * @author gm_ye
 */
public class Asignacion extends Sentencia {

    String nombre; 
    Expresion expresion;

    public Asignacion(String nombre, Expresion expresion, int linea, int columna) {
        super(linea, columna);
        this.nombre = nombre;
        this.expresion = expresion;
    }
   
    
    @Override
    public void Ejecutar(TablaSimbolos tabla) {
        System.out.println("ejecutando sentencia...");
        System.out.println(this.nombre);
        // ver si la variable existe 
        if(tabla.existeVariable(nombre)){
            tabla.asignarValor(nombre,this.expresion.getValor(tabla).toString());
        }else {
            // ERROR
        }
    }
    
    public void getCodigoGraph(StringBuilder builder) {
        builder.append(this.hashCode()).append("[label=\"Asignacion\"];");
        builder.append(this.hashCode()).append(this.nombre.hashCode()).append("[label=\"").append(nombre).append("\"];\n");
        this.expresion.getCodigoGraph(builder);
        builder.append(this.hashCode()).append("->").append(this.hashCode()).append(this.nombre.hashCode()).append(";");
        builder.append(this.hashCode()).append("->").append(this.expresion.hashCode()).append(";");
    }
    
}
