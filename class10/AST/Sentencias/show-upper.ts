import { Sentencia } from "./sentencia.base";

export class ShowUpper extends Sentencia {
    mensaje: string;

    constructor(mensaje: string, linea: number, columna: number) {
        super(linea, columna);
        this.mensaje = mensaje;
    }

    Ejecutar() {
        console.log(this.mensaje.toUpperCase());
    }

}