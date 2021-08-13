/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizador;

import AST.Errores.ListaErrores;
import AST.Metodo;
import AST.Sentencias.Sentencia;
import AST.Simbolos.TablaSimbolos;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;

/**
 *
 * @author Emely
 */
public class AnalizadorLenguaje {

    private static AnalizadorLenguaje analizador;
    public static ArrayList<Sentencia> sentencias;
    public static ListaErrores errores;
    public static ArrayList<Metodo> listaMetodos;

    public static boolean AnalizarCodigo(String entrada, String ubicacion) {
        try {
            Sintactico sin = new Sintactico(
                    new Lexico(new BufferedReader(new StringReader(entrada))));
            //analizando
            sin.parse();
            if (AnalizadorLenguaje.listaMetodos != null) {

                for (Metodo t : listaMetodos) {
                    if ("main".equals(t.getNombre())) {
                        int i;
                        TablaSimbolos global = new TablaSimbolos(null);
                        for (i = 0; i < t.getSentencias().size(); i++) {
                            t.getSentencias().get(i).Ejecutar(global);
                        }
                        break;
                    }
                }
                System.out.println("Sin errores");
            } else {
                throw new Exception("No hay sentencias reconocidas");
            }
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
            if (AnalizadorLenguaje.listaMetodos != null) {
                for (Metodo t : listaMetodos) {
                    if ("main".equals(t.getNombre())) {
                         graph.append(t.hashCode()).append("[label=\"main\"];\n");
                         int i;
                    for (i = 0; i < t.getSentencias().size(); i++) {
                        t.getSentencias().get(i).getCodigoGraph(graph);
                        graph.append(t.hashCode()).append("->").append(t.getSentencias().get(i).hashCode()).append(";\n");
         }
                        break;
                    }
                }
                System.out.println("Sin errores");
            } else {
                throw new Exception("No hay sentencias reconocidas");
            }
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
            listaMetodos = new ArrayList<>();
        }
        return analizador;
    }

    public static void LimpiarInstancia() {
        if (analizador != null) {
            errores.clear();
            listaMetodos.clear();
        } else {
            System.out.println("No existe un analizador");
        }
    }

}
