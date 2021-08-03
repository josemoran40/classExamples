/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserexample;

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
		try {
			String texto =  "5*565+64.5+(-5)";
			System.out.println("Iniciando analisis...");
			Scanner scanner = new Scanner(new BufferedReader(new StringReader(texto)));
			Parser parser = new Parser(scanner);
			parser.parse();
			System.out.println("Finalizando analisis...");
		} catch (Exception e) {
		}
	}
	
}

/*
	5*565+64.5+(-5)
	[5*565]E + [64.5+(-5)]E
	[ [5]E * [565]E ]E + [ [64.5]E] + [(-5)]E ]E
	[ [ [5]NUM ]E * [ [565]NUM ]E ]E + [ [ [64.5]DEC ] E] + [ ( [-5]E )]E ]E
*/