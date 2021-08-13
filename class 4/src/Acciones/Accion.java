/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

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
        System.out.println("Mover "+ this.valor);
    }
    
}
