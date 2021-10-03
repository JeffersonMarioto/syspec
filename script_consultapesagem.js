//1. Inicialização
 
var localDB = null;
 
function onInit(){
    try {
        if (!window.openDatabase) {
            updateStatus("Erro: Seu navegador não permite banco de dados.");
        }
        else {
            initDB();
            queryAndUpdateOverview();
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
 

 
function onSelect(htmlLIElement){
 var data_atual = htmlLIElement.getAttribute("data_atual");
 document.itemForm.data.value = data_atual;
 localizarPesagem();
}

function localizarPesagem(){
 var data_atual = document.itemForm.data.value;

 //Remove as linhas existentes para inserção das novas
    var dataRows = document.getElementById("itemData2").getElementsByClassName("data");
 
    while (dataRows.length > 0) {
        row = dataRows[0];
        document.getElementById("itemData2").removeChild(row);
    };   
 var data_atual = document.itemForm.data.value;
 query = "SELECT * FROM pesagem where data_atual=?;";

    try {
        var numero = 0;
        var soma = 0;
        var media = 0;
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [data_atual], function(transaction, results){
                for (var i = 0; i < results.rows.length; i++) {
                    var row = results.rows.item(i);
                    var li = document.createElement("li");
                    li.setAttribute("class", "data");
                    var peso = 0;
                    peso = parseFloat(row['peso_atual']);
                    soma += peso;
                    
                    var liText = document.createTextNode(row['animais_idanimal']+"|| "+row['peso_atual']+" ||| "+row['peso_anterior']+" ||"+row['resultado']+" || "+row['media']);
                    li.appendChild(liText);
                    numero++;
                    document.getElementById("itemData2").appendChild(li);
                }      
                document.itemForm.numero.value = numero;
                media = parseFloat(soma / numero);
                document.itemForm.media.value = media;               
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
        updateStatus("carregado dados da pesagem.");
    } 
    catch (e) {
        updateStatus("Error: SELECT não realizado " + e + ".");
    } 
}
 
function queryAndUpdateOverview(){
 
 //Remove as linhas existentes para inserção das novas
    var dataRows = document.getElementById("itemData").getElementsByClassName("data");
 
    while (dataRows.length > 0) {
        row = dataRows[0];
        document.getElementById("itemData").removeChild(row);
    };
    
 //Realiza a leitura no banco e cria novas linhas na tabela.
    var query = "SELECT data_atual FROM pesagem ORDER BY data_atual DESC LIMIT 1;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [], function(transaction, results){
                for (var i = 0; i < results.rows.length; i++) {
                
                    var row = results.rows.item(i);
                    var li = document.createElement("li");
                    li.setAttribute("data_atual", row['data_atual']);
                    li.setAttribute("class", "data");
                    li.setAttribute("onclick", "onSelect(this)");
                    
                    var liText = document.createTextNode(row['data_atual']);
                    li.appendChild(liText);

                    
                    document.getElementById("itemData").appendChild(li);
                }
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
    } 
    catch (e) {
        updateStatus("Error: SELECT não realizado " + e + ".");
    }
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