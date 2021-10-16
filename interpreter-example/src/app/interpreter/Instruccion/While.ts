import { Error_ } from "../Error/Error";
import { Expresion } from "../Expresion/Expresion";
import { Type } from "../Expresion/Retorno";
import { Ambito } from "../Extra/Ambito";
import { Instruccion } from "./Instruccion";

export class While extends Instruccion {
    constructor(private condicion: Expresion, private cuerpo: Instruccion, line: number, column: number) {
        super(line, column)
    }

    public execute(ambito: Ambito) {
        let value = this.condicion.execute(ambito)

        if (value.type != Type.BOOLEAN) throw new Error_(this.line, this.column, 'Semantico', "La condicion a evaluar no es de tipo boolean")

        while (value.value) {
            const retorno = this.cuerpo.execute(ambito)
            if (retorno != null && retorno != undefined) {
                if (retorno.type == 'Break') {
                    break
                } else if (retorno.type == 'Continue') {
                    continue
                }
            }


            value = this.condicion.execute(ambito)
        }
    }
}