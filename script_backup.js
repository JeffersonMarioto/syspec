//1. Inicialização
 
const util = require('util');
const fs = require('fs');
const path = require('path');
const copyFilePromise = util.promisify(fs.copyFile);
var srcD = "C:\Users\jeffe\OneDrive\Documentos\Conpec";
var destD = "D:";
var arq = "ajuda.html";

function onInit(){
    alert("Funciona...");
}

function copyFile(){
    alert("copiando arquivos...");
}

function recovery(){
    alert("Restaurando o sistema...");
}

