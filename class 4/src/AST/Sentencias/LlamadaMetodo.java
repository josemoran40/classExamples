/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Sentencias;

import AST.Errores.MiError;
import AST.Errores.TipoError;
import AST.Expresiones.Expresion;
import AST.Metodo;
import AST.Simbolos.TablaSimbolos;
import interfaz.Principal;
import java.util.ArrayList;

/**
 *
 * @author gm_ye
 */
public class LlamadaMetodo extends Sentencia{
    ArrayList<Expresion> parametros;
    String nombre; 

    public LlamadaMetodo(ArrayList<Expresion> parametros, String nombre, int linea, int columna) {
        super(linea, columna);
        this.parametros = parametros;
        this.nombre = nombre;
    }

    @Override
    public void Ejecutar(TablaSimbolos tabla) {
        if("print".equals(nombre.toLowerCase())){
            int i; 
            for(i = 0; i < this.parametros.size(); i++){
                Principal.escribirMensajeEnConsola(this.parametros.get(i).getValor(tabla).toString());
            }
        }else {
             Metodo metodo = null;
             for(Metodo m : Analizador.AnalizadorLenguaje.listaMetodos){
                 if(m.getNombre().equals(this.nombre)){
                     metodo = m; 
                     break;
                 }
             }
             if(metodo != null){
                 TablaSimbolos tablaMetodo = new TablaSimbolos(tabla);
                 metodo.getSentencias().forEach((t) -> {
                     t.Ejecutar(tablaMetodo);
                 });
             }else {
                 Analizador.AnalizadorLenguaje.errores.add(
                 new MiError(this.getLinea(), this.getColumna(), 
                         TipoError.SEMANTICO, "No existe el método o función"));
             }
        }
    }
    
        @Override
    public void getCodigoGraph(StringBuilder builder) {
        builder.append(this.hashCode()).append("[label=\"").append(this.nombre).append("\"];\n");
        // graficando sentencias de método 
        
        if("print".equals(nombre.toLowerCase())){
            int i; 
            for(i = 0; i < this.parametros.size(); i++){
               this.parametros.get(i).getCodigoGraph(builder);
               builder.append(this.hashCode()).append("->").append(this.parametros.get(i).hashCode()).append(";\n");
            }
        }
        else {
        Metodo metodo = null;
             for(Metodo m : Analizador.AnalizadorLenguaje.listaMetodos){
                 if(m.getNombre().equals(this.nombre)){
                     metodo = m; 
                     break;
                 }
             }
             
             if(metodo != null){
                 metodo.getSentencias().forEach((t) -> {
                     t.getCodigoGraph(builder);
                     builder.append(this.hashCode()).append("->").append(t.hashCode()).append("\n");
                 });
                 
             }else {
                 Analizador.AnalizadorLenguaje.errores.add(
                 new MiError(this.getLinea(), this.getColumna(), 
                         TipoError.SEMANTICO, "No existe el método o función"));
             }
        }
    }
}
