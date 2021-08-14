/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errores;

import interfaz.Principal;
import java.util.ArrayList;

/**
 *
 * @author gm_ye
 */
public class ListaErrores extends ArrayList<MiError> {

    public ListaErrores() {
    super();
    }
    
    public void addError(MiError error){
        this.add(error);
    }
    
    public void mostrarEnConsola(){
        this.forEach((t) -> {
            Principal.escribirErrorEnConsola(t.toString());
        });
    }
    
}
