/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizador;
/**
 *
 * @author Emely
 */
public class GeneradorCompilador {
    public static void main(String[] args){
        generarCompilador();
    }

    
       public static void generarCompilador() {
        try {
            String ruta = "src/Analizador/";
            String opcFlex[] = {ruta + "Lexico", "-d", ruta};
            jflex.Main.generate(opcFlex);
           
            String opcCUP[] = {"-destdir", ruta, "-parser", "Sintactico", ruta + "Sintactico"};
            java_cup.Main.main(opcCUP);
        } catch (Exception e) {
       }
    }
}
