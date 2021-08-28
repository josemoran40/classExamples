/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

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

	public Instruction() {
	}
	
	public void analize(String text){
		try {
			System.out.println("Iniciando analisis...");
			lista = new LinkedListError();
			Scanner scanner = new Scanner(new BufferedReader(new StringReader(text)));
			Parser parser = new Parser(scanner);
			parser.parse();
			System.out.println("Finalizando analisis...");
		} catch (Exception e) {
		}
	}
	
	public static Instruction getInstacia(){
		if(instruccion==null){
			lista = new LinkedListError();
			instruccion = new Instruction();
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
