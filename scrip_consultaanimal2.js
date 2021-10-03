//1. Inicialização
 
var localDB = null;
 
function onInit(){
    try {
        if (!window.openDatabase) {
            updateStatus("Erro: Seu navegador não permite banco de dados.");
        }
        else {
            initDB();
            consultar();
            updateStatus("contadores atualizados");
        }
    } 
    catch (e) {
        if (e == 2) {
            updateStatus("Erro: Versão de banco de dados inválida.");
        }
        else {
            updateStatus("Erro: Erro desconhecido: " + e + ".");
        }
        return;
    }
}
 
function initDB(){
    var shortName = 'stuffDB';
    var version = '1.0';
    var displayName = 'MyStuffDB';
    var maxSize = 65536; // Em bytes
    localDB = window.openDatabase(shortName, version, displayName, maxSize);
}

function consultar(){
    var query = "SELECT * FROM animais;";
 
    try {
        var contador_animais = 0;
        var contador_machos = 0;
        var contador_femeas = 0;
        var era = "";
        var contador_bezerros = 0;
        var contador_novilhos = 0;
        var contador_adultos = 0;   
        var contador_machos_bezerros = 0;
        var contador_machos_novilhos = 0;
        var contador_machos_bois = 0;
        var contador_femeas_bezerras = 0;
        var contador_femeas_novilhas = 0;
        var contador_femeas_vacas = 0;
        
        var data = new Date();
        var dia  = data.getDate();
        if (dia < 10) {
            dia  = "0" + dia;
        }

        var mes  = data.getMonth() + 1;
        if (mes < 10) {
            mes  = "0" + mes;
        }
        var ano = data.getFullYear();
        var hoje = new Date(ano, mes, dia);

        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [], function(transaction, results){
                for (var i = 0; i < results.rows.length; i++) {
                    var row = results.rows.item(i);
                    var data = new Date(row["nascimento"]);
                    era = retornaEra(retornaMeses(hoje, data));
                    var sexo = row['sexo']
                    if(row['sexo'] == "macho"){
                        contador_machos++;
                    }
                    if(row['sexo'] == "femea"){
                        contador_femeas++;
                    }
                    if(era == "adulto"){
                        contador_adultos++;
                    }
                    if(era == "novilho"){
                        contador_novilhos++;
                    }
                    if(era == "bezerro"){
                        contador_bezerros++;
                    }
                    if(era == "adulto" && sexo == "macho"){
                        contador_machos_bois++;
                    }
                     if(era == "novilho" && sexo == "macho"){
                        contador_machos_novilhos++;
                    }
                    if(era == "bezerro" && sexo == "macho"){
                        contador_machos_bezerros++;
                    }
                    if(era == "adulto" && sexo == "femea"){
                        contador_femeas_vacas++;
                    }
                    if(era == "novilho" && row['sexo'] == "femea"){
                        contador_femeas_novilhas++;
                    }
                    if(era == "bezerro" && row['sexo'] == "femea"){
                        contador_femeas_bezerras++;
                    }
                }
                
                document.itemForm.total.value = i;
                document.itemForm.machos.value = contador_machos;
                document.itemForm.femeas.value = contador_femeas;
                document.itemForm.bezerro.value = contador_bezerros;
                document.itemForm.novilho.value = contador_novilhos;
                document.itemForm.adulto.value = contador_adultos;
                document.itemForm.novilhos.value = contador_machos_novilhos;
                document.itemForm.bezerros.value = contador_machos_bezerros;
                document.itemForm.bois.value = contador_machos_bois;
                document.itemForm.novilhas.value = contador_femeas_novilhas;
                document.itemForm.bezerras.value = contador_femeas_bezerras;
                document.itemForm.vacas.value = contador_femeas_vacas;
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
    }
    catch (e) {
        updateStatus("Error: SELECT não realizado " + e + ".");
    }
    
}

 
function retornaMeses(datahoje, datanascimento){
    return datahoje.getMonth() - datanascimento.getMonth() + (12 * (datahoje.getFullYear() - datanascimento.getFullYear()));
}

function retornaEra(numeromeses){
    var era = "";
    if(numeromeses >= 24){
        era = "adulto";
    }else{
        if(numeromeses <= 8){
            era = "bezerro";
        }else{
            era = "novilho";
        }
    }
    return era;
}


// Tratando erros
 
errorHandler = function(transaction, error){
    updateStatus("Erro: " + error.message);
    return true;
}
 
nullDataHandler = function(transaction, results){
}
 
function updateStatus(status){
    document.getElementById('status').innerHTML = status;
}