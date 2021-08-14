/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizador;

import Errores.ListaErrores;
import java.io.BufferedReader;
import java.io.StringReader;

/**
 *
 * @author Emely
 */
public class AnalizadorLenguaje {

    private static AnalizadorLenguaje analizador;
    public static ListaErrores errores;

    public static boolean AnalizarCodigo(String entrada, String ubicacion) {
        try {
            Sintactico sin = new Sintactico(
                    new Lexico(new BufferedReader(new StringReader(entrada))));
            //analizando
            sin.parse();
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }

        if (AnalizadorLenguaje.errores.size() > 0) {
            return false;
        }
        return true;
    }

    public static String getDot(String entrada) {
            StringBuilder graph = new StringBuilder();
            graph.append("digraph G { \n");
        try {
            Sintactico sin = new Sintactico(
                    new Lexico(new BufferedReader(new StringReader(entrada))));
            //analizando
            sin.parse();
            
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        graph.append("} \n");
        return graph.toString();
    }

    public static AnalizadorLenguaje getInstancia() {
        if (analizador == null) {
            analizador = new AnalizadorLenguaje();
            errores = new ListaErrores();
        }
        return analizador;
    }

    public static void LimpiarInstancia() {
        if (analizador != null) {
            errores.clear();
        } else {
            System.out.println("No existe un analizador");
        }
    }

}
