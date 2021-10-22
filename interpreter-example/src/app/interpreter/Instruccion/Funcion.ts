import { Ambito } from "../Extra/Ambito";
import { Instruccion } from "./Instruccion";


export class Funcion extends Instruccion {

    constructor(public id: string, public statment: Instruccion, public parametros: Array<string>, line: number, column: number) {
        super(line, column);
    }

    public execute(ambito: Ambito): any {
        ambito.guardarFuncion(this.id, this);
    }
}