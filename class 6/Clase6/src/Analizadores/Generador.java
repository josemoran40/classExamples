/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizadores;

/**
 *
 * @author jose_
 */
public class Generador {
		public static void main(String[] args) {
		try {
			String ruta =  "src/Analizadores/";
			String opcFlex[] =  {ruta+"lex.lex","-d",ruta};
			jflex.Main.generate(opcFlex);
			
			String opcCup[] =  {"-destdir", ruta,"-parser","Parser",ruta+"sintactico.cup"};
			java_cup.Main.main(opcCup);
		} catch (Exception e) {
		}
	}
}
