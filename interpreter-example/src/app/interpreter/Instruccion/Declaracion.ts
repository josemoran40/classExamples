import { Expresion } from "../Expresion/Expresion";
import { Instruccion } from "./Instruccion"
import { Ambito } from '../Extra/Ambito';

export class Declaracion extends Instruccion {

    private id: string;
    private value: Expresion;

    constructor(id: string, value: Expresion, line: number, column: number) {
        super(line, column)
        this.id = id
        this.value = value
    }

    public execute(ambito: Ambito) {
        let val = this.value.execute(ambito)
        ambito.setVal(this.id, val.value, val.type, this.line, this.column)
    }
}