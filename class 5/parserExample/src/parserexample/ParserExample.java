/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserexample;

import Instructions.Instruction;
import analyzers.Scanner;
import analyzers.Parser;
import java.io.BufferedReader;
import java.io.StringReader;

/**
 *
 * @author jose_
 */
public class ParserExample {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		String texto =  "5*565+64.5+(-5);"
				+ "5*565+64.5+(-5@);"
				+ "5*565+64.5+(-5);"
				+ "6+6+6;";
		
		Instruction ins = Instruction.getInstacia();
		
		ins.analize(texto);
		
		for (int i = 0; i < ins.lista.size(); i++) {
			System.err.println("MENSAJE: "+ins.lista.get(i).getMessage()+" -- TIPO: "+ins.lista.get(i).getType());
		}
		
		Instruction ins2 =  Instruction.getInstacia();
		
		for (int i = 0; i < ins2.lista.size(); i++) {
			System.err.println("MENSAJE: "+ins2.lista.get(i).getMessage()+" -- TIPO: "+ins2.lista.get(i).getType());
		}
	}
	
}

/*
	5*565+64.5+(-5)
	[5*565]E + [64.5+(-5)]E
	[ [5]E * [565]E ]E + [ [64.5]E] + [(-5)]E ]E
	[ [ [5]NUM ]E * [ [565]NUM ]E ]E + [ [ [64.5]DEC ] E] + [ ( [-5]E )]E ]E
*/