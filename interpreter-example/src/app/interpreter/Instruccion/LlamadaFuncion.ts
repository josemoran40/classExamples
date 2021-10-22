

import { Instruccion } from '../Instruccion/Instruccion';
import { Ambito } from '../Extra/Ambito';
import { Expresion } from '../Expresion/Expresion';
import { Error_ } from '../Error/Error';

export class LlamadaFuncion extends Instruccion {

    constructor(private id: string, private expresiones: Array<Expresion>, line: number, column: number) {
        super(line, column);
    }
    public execute(ambito: Ambito) {
        const func = ambito.getFuncion(this.id);
        if (func == undefined) throw new Error_(this.line, this.column, 'Semantico', `Funcion ${this.id} no encontrada`)
        if (this.expresiones.length != func.parametros.length) throw new Error_(this.line, this.column, 'Semantico', "Cantidad de parametros incorrecta")

        const newEnv = new Ambito(ambito.getGlobal());
        for (let i = 0; i < this.expresiones.length; i++) {
            const value = this.expresiones[i].execute(ambito);
            newEnv.setVal(func.parametros[i], value.value, value.type, this.line, this.column);
        }
        func.statment.execute(newEnv);
    }
}