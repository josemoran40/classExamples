/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Sentencias;

import AST.Simbolos.TablaSimbolos;
import AST.Simbolos.Simbolo;
import AST.Simbolos.TipoDatoSimbolo;

/**
 *
 * @author gm_ye
 */
public class Declaracion extends Sentencia {

    TipoDatoSimbolo tipo; 
    String nombre; 

    
    public Declaracion(TipoDatoSimbolo tipo, String nombre, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.nombre = nombre;
    }
    
    
    @Override
    public void Ejecutar(TablaSimbolos tabla) {
        

       if(!tabla.existeVariable(nombre)){
           Simbolo sim = null;
            String determinado = this.getValorDeterminado(this.tipo);
            if(determinado != null){
               sim = new Simbolo(this.nombre,this.tipo,this.getLinea(),this.getColumna(),determinado);
            }
            if(sim != null){
                tabla.add(sim);
            }
       }else {
           // ERROR 
       }
    }
    
    public String getValorDeterminado(TipoDatoSimbolo tipo){
        switch(tipo){
            case BOOLEANO: 
                return "true";
            case CADENA: 
                return "";
            case NUMERO: 
                return "0";
                default:
                    //error
                    // el tipo de variable no existe
                    return null;
        }
    }
    
    
    @Override
    public void getCodigoGraph(StringBuilder builder) {
        builder.append(this.hashCode()).append("[label=\"Declaracion\"];");
        builder.append(this.hashCode()).append(this.nombre.hashCode()).append("[label=\"").append(nombre).append("\"];\n");
        builder.append(this.hashCode()).append("->").append(this.hashCode()).append(this.nombre.hashCode()).append(";");
    }
}
