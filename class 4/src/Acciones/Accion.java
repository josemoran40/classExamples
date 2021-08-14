/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import interfaz.Principal;

/**
 *
 * @author devemg
 */
public class Accion {
    Movimiento movimiento; 
    Double valor; 
    String cadena;

    public Accion(Movimiento movimiento, Double valor) {
        this.movimiento = movimiento;
        this.valor = valor;
    }
    
    public Accion(Movimiento movimiento, String valor) {
        this.movimiento = movimiento;
        this.cadena = valor;
    }
    
    public void hacer() {
        String message = this.getMessage();
        System.out.println(message);
        Principal.escribirMensajeEnConsola(message);
    }
    
    private String getMessage() {
        switch(this.movimiento) {
            case ABAJO:
                return "Moviendome "+this.valor+" hacia abajo";
            case ARRIBA:
                return "Moviendome "+this.valor+" hacia arriba";
            case DERECHA:
                return "Moviendome "+this.valor+" hacia la derecha";
            case IZQUIERDA:
                return "Moviendome "+this.valor+" hacia la izquierda";
            case SALUDAR:
                return "Tengo un mensaje: "+this.cadena;
            default:
                return "";
        }
    }
}
