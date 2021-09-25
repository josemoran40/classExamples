"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
Object.defineProperty(exports, "__esModule", { value: true });
exports.Print = void 0;
var sentencia_base_1 = require("./sentencia.base");
var Print = /** @class */ (function (_super) {
    __extends(Print, _super);
    function Print(mensaje, linea, columna) {
        var _this = _super.call(this, linea, columna) || this;
        _this.mensaje = mensaje;
        return _this;
    }
    Print.prototype.Ejecutar = function () {
        // \t ->  
        // \n -> salto de linea 
        // ......   
        console.log(this.mensaje);
    };
    return Print;
}(sentencia_base_1.Sentencia));
exports.Print = Print;
