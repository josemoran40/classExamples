import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import parser from './interpreter/grammar/grammar.js';
import { Expresion } from './interpreter/Expresion/Expresion.js';

import * as ace from 'ace-builds'; // ace module ..
import 'ace-builds/src-noconflict/ext-beautify';
import 'ace-builds/src-noconflict/mode-typescript'
import 'ace-builds/src-noconflict/theme-tomorrow_night_eighties'; import { Ambito } from './interpreter/Extra/Ambito.js';
import { Funcion } from './interpreter/Instruccion/Funcion.js';
;
const THEME = 'ace/theme/tomorrow_night_eighties';
const LANG = 'ace/mode/typescript';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  @ViewChild('codeEditor', { static: true }) codeEditorElmRef: ElementRef;
  @ViewChild('network') el: ElementRef;
  private codeEditor: ace.Ace.Editor;
  private editorBeautify;

  ngOnInit(): void {
    this.editorBeautify = ace.require('ace/ext/beautify');
    const element = this.codeEditorElmRef.nativeElement;
    const editorOptions: Partial<ace.Ace.EditorOptions> = {
      highlightActiveLine: false,
      minLines: 20,
      maxLines: Infinity,
      maxPixelHeight: 800,
      fontSize: 20
    };
    this.codeEditor = ace.edit(element, editorOptions);
    this.codeEditor.setTheme(THEME);
    this.codeEditor.getSession().setMode(LANG);
    this.codeEditor.setShowFoldWidgets(true);

  }

  ejecutar() {
    const entrada = this.codeEditor.getValue()
    if (entrada == "") {
      alert("Entrada vacia")
      return
    }
    const ast = parser.parse(entrada)
    console.log(ast)


    const ambito = new Ambito(null)
    for (const instr of ast) {
      try {
        if (instr instanceof Funcion)
          instr.execute(ambito);
      } catch (error) {
        console.log(error)
      }
    }


    try {
      for (const inst of ast) {
        const retorno = inst.execute(ambito)
      }
    } catch (error) {
      console.log(error)
    }

    console.log("Ejecucion terminada")

  }

}