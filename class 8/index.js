const express = require('express');
var parser = require('./gramatica');
const app = express(); 


app.use(express.json());

app.post('/calcular', (req,res) => {
    const entrada = req.body.entrada;
    parser.parse(entrada);
    res.send('Código Ejecutado!');
} );


app.listen(3000, () => {
    console.log('Server running....');
});


/*
var fs = require('fs');

fs.readFile('./entrada.txt', (err, data) => {
    if (err) {
        throw err;
    } 
   
    parser.parse(data.toString());
});
*/