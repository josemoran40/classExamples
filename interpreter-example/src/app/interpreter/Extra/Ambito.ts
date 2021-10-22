import { Error_ } from "../Error/Error";
import { Type } from "../Expresion/Retorno";
import { Funcion } from "../Instruccion/Funcion";
import { Simbolo } from "./Simbolo"

export class Ambito {
    private variables: Map<string, Simbolo>
    public funciones: Map<string, Funcion>;

    constructor(public anterior: Ambito | null) {
        this.variables = new Map();
        this.funciones = new Map();
    }

    public setVal(id: string, value: any, type: Type, line, column) {
        let env: Ambito | null = this;

        while (env != null) {
            if (env.variables.has(id)) {
                const val = env.variables.get(id)
                if (val.type == type) {
                    env.variables.set(id, new Simbolo(value, id, type))
                } else {
                    throw new Error_(line, column, 'Semantico', 'No se puede asignar: ' + value + ' a ' + id)
                }
            }
            env = env.anterior
        }
        this.variables.set(id, new Simbolo(value, id, type))
    }

    public getVal(id: string): Simbolo {
        let env: Ambito | null = this;

        while (env != null) {
            if (env.variables.has(id)) {
                return env.variables.get(id)
            }
            env = env.anterior
        }
        return null;
    }


    public guardarFuncion(id: string, funcion: Funcion) {
        //TODO ver si la funcion ya existe, reportar error
        this.funciones.set(id, funcion);
    }

    public getFuncion(id: string): Funcion | undefined {
        let env: Ambito | null = this;
        while (env != null) {
            if (env.funciones.has(id)) {
                return env.funciones.get(id);
            }
            env = env.anterior;
        }
        return undefined;
    }


    public getGlobal(): Ambito {
        let env: Ambito | null = this;
        while (env?.anterior != null) {
            env = env.anterior;
        }
        return env;
    }

}
