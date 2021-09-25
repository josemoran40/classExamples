import { Sentencia } from "./sentencia.base";

export class Print extends Sentencia {

    mensaje: string;

    constructor(mensaje: string, linea: number, columna: number) {
        super(linea, columna);
        this.mensaje = mensaje;
    }

    Ejecutar() {
        // \t ->  
        // \n -> salto de linea 
        // ......   
        console.log(this.mensaje);
    }

}