import { Expresion } from "./Expresion";
import { Retorno, Type } from './Retorno';
export class Literal extends Expresion {

    constructor(private value: any, private tipo: TipoLiteral, line: number, column: number) {
        super(line, column);
    }
    public execute(): Retorno {
        if (this.tipo == 0) {
            return { value: this.value.toString(), type: Type.STRING };
        } else if (this.tipo == 1) {
            return { value: Number(this.value), type: Type.NUMBER };
        } else if (this.tipo == 2) {
            if (this.value.toString().toLowerCase() == "true") {
                return { value: true, type: Type.BOOLEAN }
            }
            return { value: false, type: Type.BOOLEAN }
        }
    }
}

export enum TipoLiteral {
    STRING = 0,
    NUMBER = 1,
    BOOL = 2
}