import { Ambito } from "../Extra/Ambito";
import { Instruccion } from "./Instruccion";

export class Break extends Instruccion {
    constructor(line, column) {
        super(line, column)
    }

    public execute(ambito: Ambito) {
        return { type: 'Break', line: this.line, column: this.column }
    }

}