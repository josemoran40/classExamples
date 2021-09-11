/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccions;

import Error.LinkedListError;
import analyzers.Parser;
import analyzers.Scanner;
import java.io.BufferedReader;
import java.io.StringReader;

/**
 *
 * @author jose_
 */
public class Instruction {
	
	public static LinkedListError lista;
	public static Instruction instruccion;
	
	private Instruction() {
	}
	
	public void analize(String text){
		try {
			
			lista = new LinkedListError();
			Scanner scanner = new Scanner(new BufferedReader(new StringReader(text)));
			Parser parser = new Parser(scanner);
			parser.parse();
			System.out.println("Finalizando analisis...");
		} catch (Exception e) {
		}
	}
	
	  public static Instruction getInstancia() {
        if (instruccion == null) {
            instruccion = new Instruction();
            lista = new LinkedListError();
        }
        return instruccion;
    }

	public static LinkedListError getLista() {
		return lista;
	}

	public static void setLista(LinkedListError lista) {
		Instruction.lista = lista;
	}

	  
	  
}
