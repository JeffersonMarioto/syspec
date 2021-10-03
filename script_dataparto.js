//1. Inicialização
 
var localDB = null;
 
function onInit(){
    try {
        if (!window.openDatabase) {
            updateStatus("Erro: Seu navegador não permite banco de dados.");
        }
        else {
            initDB();
            document.itemForm.mes.focus();
            updateStatus("pronto");
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


function carregarDados(){
 var mes = document.itemForm.mes.value;
 //Remove as linhas existentes para inserção das novas
    var dataRows = document.getElementById("itemData").getElementsByClassName("data");
 
    while (dataRows.length > 0) {
        row = dataRows[0];
        document.getElementById("itemData").removeChild(row);
    };   

 query = "SELECT * FROM inseminacoes where diagnostico='prenha';";

    try {
        var parto = 0;
        var hoje = new Date();
        var ano = hoje.getFullYear();
        var mes_selecionado = document.itemForm.mes.value;
        localDB.transaction(function(transaction){
            transaction.executeSql(query, [], function(transaction, results){
                for (var i = 0; i < results.rows.length; i++) {
                    var row = results.rows.item(i);
                    var data_parto = new Date(row['data_parto']);
                    var ano_parto = data_parto.getFullYear();
                    var mes_parto = data_parto.getMonth()+1;
                    var data = row['data_parto'];
                    data = corrigeData(data);
                    if(ano == ano_parto && mes_selecionado == mes_parto){
                        var li = document.createElement("li");
                        li.setAttribute("class", "data");
                        var liText = document.createTextNode(row['animais_idanimal']+ " ||| "+ row['touro']+" || "+ data);
                        li.appendChild(liText);
                        document.getElementById("itemData").appendChild(li);
                        parto++;
                    }
                }
                document.itemForm.total.value = parto;                
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
        updateStatus("carregado dados da estação.");
    } 
    catch (e) {
        updateStatus("Error: SELECT não realizado " + e + ".");
    } 
}

function corrigeData(data){
    var ano = data.substring(0,4); 
    var mes = data.substring(6,7);
    var dia = data.substring(9,10);
    if (dia.length  < 10) {
        dia  = "0" + dia;
    }
    if (mes.length < 10) {
        mes  = "0" + mes;
    }    
    data = dia + "/" + mes + "/" + ano;
    return data;
}

function limparDados(){
    // limpa a lista
    var dataRows = document.getElementById("itemData").getElementsByClassName("data");
 
    while (dataRows.length > 0) {
        row = dataRows[0];
        document.getElementById("itemData").removeChild(row);
    };   
    // limpa os campos
    document.itemForm.mes.value = 1;
    document.itemForm.total.value = "";
}

// 3. Funções de tratamento e status.
 
// Tratando erros
 
errorHandler = function(transaction, error){
    updateStatus("Erro: " + error.message);
    return true;
}
 
nullDataHandler = function(transaction, results){
}
 
function updateForm(data){
    document.itemForm.idinseminacao.data = data;
}
 
function updateStatus(status){
    document.getElementById('status').innerHTML = status;
}