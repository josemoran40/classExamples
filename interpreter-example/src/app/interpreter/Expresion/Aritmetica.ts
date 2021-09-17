import { Expresion } from "./Expresion";
import { Retorno, Type } from "./Retorno"
import { Error_ } from "../Error/Error";

export class Aritmetica extends Expresion {

    constructor(private left: Expresion, private right: Expresion, private tipo: TipoAritmetica, line: number, column: number) {
        super(line, column);
    }

    public execute(): Retorno {
        const leftValue = this.left.execute();

        const rightValue = this.right.execute();

        let dominante = this.tipoDominante(leftValue.type, rightValue.type);

        if (this.tipo == TipoAritmetica.SUMA) {
            if (dominante == Type.STRING) {
                return { value: (leftValue.value.toString() + rightValue.value.toString()), type: Type.STRING };
            } else if (dominante == Type.NUMBER) {
                return { value: (leftValue.value + rightValue.value), type: Type.NUMBER };
            } else {
                throw new Error_(this.line, this.column, 'Semantico', 'No se puede operar: ' + leftValue.type + ' _ ' + rightValue.type);
            }
        } else if (this.tipo == TipoAritmetica.RESTA) {
            if (dominante == Type.NUMBER) {
                return { value: (leftValue.value - rightValue.value), type: Type.NUMBER };
            } else {
                throw new Error_(this.line, this.column, 'Semantico', 'No se puede operar: ' + leftValue.type + ' _ ' + rightValue.type);
            }
        }
        else if (this.tipo == TipoAritmetica.MULTIPLICACION) {
            if (dominante == Type.NUMBER) {
                return { value: (leftValue.value * rightValue.value), type: Type.NUMBER };
            } else {
                throw new Error_(this.line, this.column, 'Semantico', 'No se puede operar: ' + leftValue.type + ' _ ' + rightValue.type);
            }
        }
        else if (this.tipo == TipoAritmetica.DIVISION) {
            if (dominante == Type.NUMBER) {
                if (rightValue.value == 0) {
                    throw new Error_(this.line, this.column, "Semantico", "No se puede dividir entre 0");
                } else {
                    return { value: (leftValue.value / rightValue.value), type: Type.NUMBER };
                }
            } else {
                throw new Error_(this.line, this.column, 'Semantico', 'No se puede operar: ' + leftValue.type + ' _ ' + rightValue.type);
            }
        }
    }
}

export enum TipoAritmetica {
    SUMA,
    RESTA,
    MULTIPLICACION,
    DIVISION
}