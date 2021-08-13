/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Simbolos;

import java.util.ArrayList;

/**
 *
 * @author gm_ye
 */
public class TablaSimbolos extends ArrayList<Simbolo>{

    TablaSimbolos padre; 
    
    public TablaSimbolos(TablaSimbolos padre) {
        super();
        this.padre = padre;
    }
    
    
    public boolean existeVariable(String nombre){
        int i; 
        boolean existe = false; 
        for(i = 0; i< this.size(); i++) {
            if(this.get(i).getNombre().equals(nombre)){
                    existe = true;
                    break;
              }
        }
        if(existe){
            return existe;
        }else {
            if(this.padre != null){
                return this.padre.existeVariable(nombre);
            }
        }
        return false;
    }
    
    public void agregarVariable(Simbolo var){
        this.add(var);
    }
    
    public String getValorVariable(String nombre){
        int i; 
        int j = 0; //indice de la variable
        boolean existe = false; 
        for(i = 0; i< this.size(); i++) {
            if(this.get(i).getNombre().equals(nombre)){
                   existe = true; 
                   j = i;
                   break;
              }
        }
        
        if(existe){
            return this.get(j).getValor();
        }else {
            if(this.padre != null){
                return this.padre.getValorVariable(nombre);
            }
        }
        return null;
    }
    
    public void asignarValor(String nombre,String valor){
        int i; 
        int j = 0; //indice de la variable
        boolean existe = false; 
        for(i = 0; i< this.size(); i++) {
            if(this.get(i).getNombre().equals(nombre)){
                     existe = true; 
                   j = i;
                   break;
              }
        }
        
        if(existe){
            this.get(j).setValor(valor);
        }else {
            if(this.padre != null){
                this.padre.asignarValor(nombre, valor);
            }
        }
    }
    
}
