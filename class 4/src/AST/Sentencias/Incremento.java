/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Sentencias;

import AST.Errores.MiError;
import AST.Errores.TipoError;
import AST.Simbolos.TablaSimbolos;

/**
 *
 * @author gm_ye
 */
public class Incremento extends Sentencia {
    private String variable;

    public Incremento(String variable, int linea, int columna) {
        super(linea, columna);
        this.variable = variable;
    }
    
    

    @Override
    public void Ejecutar(TablaSimbolos tablaSimbolos) {
        if(tablaSimbolos.existeVariable(variable)) {
            double inc = Double.parseDouble(tablaSimbolos.getValorVariable(variable));
            inc++;
            tablaSimbolos.asignarValor(variable,String.valueOf(inc));
        }else{
            Analizador.AnalizadorLenguaje.errores.add(new MiError(getLinea(), getColumna(), TipoError.SEMANTICO, 
                    "La variable\""+this.variable+"\" no existe"));
        }
    }

    @Override
    public void getCodigoGraph(StringBuilder builder) {
        builder.append(this.hashCode()).append("[label=\"").append(variable).append("++\"];\n");
    }

    /**
     * @return the variable
     */
    public String getVariable() {
        return variable;
    }

    /**
     * @param variable the variable to set
     */
    public void setVariable(String variable) {
        this.variable = variable;
    }
    
}
