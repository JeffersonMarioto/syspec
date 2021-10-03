//1. Inicialização
 
var localDB = null;
 
function onInit(){
    try {
        if (!window.openDatabase) {
            updateStatus("Erro: Seu navegador não permite banco de dados.");
        }
        else {
            initDB();
            createTables();
            queryAndUpdateOverview();
            carregarID();
            dataAtual();
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
 
function createTables(){
    var query = 'CREATE TABLE IF NOT EXISTS medicamentos(idmedicamento INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, animais_idanimal INTEGER NOT NULL, medicamento VARCHAR NOT NULL, data_aplicacao VARCHAR NOT NULL, FOREIGN KEY (animais_idanimal) REFERENCES animais(idanimal));';
    try {
        localDB.transaction(function(transaction){
            transaction.executeSql(query, [], nullDataHandler, errorHandler);
            updateStatus("Tabela 'medicamentos' status: OK.");
        });
    } 
    catch (e) {
        updateStatus("Erro: Data base 'medicamentos' não criada " + e + ".");
        return;
    }
}
 
//2. Query e visualização de Update
 
 
function onUpdate(){
    var idmedicamento = document.itemForm.idmedicamento.value;
    var animais_idanimal = document.itemForm.animais_idanimal.value;
    var medicamento = document.itemForm.medicamento.value;
    var data_aplicacao = document.itemForm.data_aplicacao.value;

    if (animais_idanimal == "" || medicamento == "") {
        updateStatus("'Nº do animal' e 'medicamento' são campos obrigatórios!");
    }
    else {
        var query = "update medicamentos set animais_idanimal=?, medicamento=?, data_aplicacao=? where idmedicamento=?;";
        try {
            localDB.transaction(function(transaction){
                transaction.executeSql(query, [animais_idanimal, medicamento, data_aplicacao, idmedicamento], function(transaction, results){
                    if (!results.rowsAffected) {
                        updateStatus("Erro: Update não realizado.");
                    }
                    else {
                        limparCampos();
                        updateStatus("Update realizado:" + results.rowsAffected);
                        queryAndUpdateOverview();
                    }
                }, errorHandler);
            });
        } 
        catch (e) {
            updateStatus("Erro: UPDATE não realizado " + e + ".");
        }
    }
}
 
function onDelete(){
    var idmedicamento = document.itemForm.idmedicamento.value;
    
    var query = "delete from medicamentos where idmedicamento=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idmedicamento], function(transaction, results){
                if (!results.rowsAffected) {
                    updateStatus("Erro: Delete não realizado.");
                }
                else {
                    limparCampos();
                    updateStatus("Linhas deletadas:" + results.rowsAffected);
                    queryAndUpdateOverview();
                }
            }, errorHandler);
        });
    } 
    catch (e) {
        updateStatus("Erro: DELETE não realizado " + e + ".");
    }
    
}
 
function onCreate(){
    var animais_idanimal = document.itemForm.animais_idanimal.value;
    var medicamento = document.itemForm.medicamento.value;
    var data_aplicacao = document.itemForm.data_aplicacao.value;
    if (animais_idanimal == "" || medicamento == "") {
        updateStatus("Erro: 'Nº do animal' e 'medicamento' são campos obrigatórios!");
    }
    else {
        var query = "insert into medicamentos (animais_idanimal, medicamento, data_aplicacao) VALUES (?, ?, ?);";
        try {
            localDB.transaction(function(transaction){
                transaction.executeSql(query, [animais_idanimal, medicamento, data_aplicacao], function(transaction, results){
                    if (!results.rowsAffected) {
                        updateStatus("Erro: Inserção não realizada");
                    }
                    else {
                        limparCampos();
                        updateStatus("Inserção realizada, linha id: " + results.insertId);
                        queryAndUpdateOverview();
                    }
                }, errorHandler);
            });
        } 
        catch (e) {
            updateStatus("Erro: INSERT não realizado " + e + ".");
        }
    }
}
 
function onSelect(htmlLIElement){
 var idmedicamento = htmlLIElement.getAttribute("idmedicamento");
 
 query = "SELECT * FROM medicamentos where idmedicamento=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idmedicamento], function(transaction, results){
            
                var row = results.rows.item(0);
                
                updateForm(row['idmedicamento'], row['animais_idanimal'], row['medicamento'], row['data_aplicacao']);
                
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
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
    var query = "SELECT * FROM medicamentos ORDER BY idmedicamento DESC LIMIT 10;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [], function(transaction, results){
                for (var i = 0; i < results.rows.length; i++) {
                
                    var row = results.rows.item(i);
                    var li = document.createElement("li");
                    li.setAttribute("idmedicamento", row['idmedicamento']);
                    li.setAttribute("class", "data");
                    li.setAttribute("onclick", "onSelect(this)");
                    
                    var liText = document.createTextNode(row['idmedicamento'] + " x "+ row['animais_idanimal'] + " x " + row['medicamento'] + " x " + row['data_aplicacao']);
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

function onSelectID(){
 var idmedicamento = document.itemForm.idmedicamento;
 updateForm("", "", "", "");
 query = "SELECT * FROM medicamentos where idmedicamento=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idmedicamento], function(transaction, results){
            
                var row = results.rows.item(0);
                
                updateForm(row['idmedicamento'], row['animais_idanimal'], row['medicamento'], row['data_aplicacao']);
                
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
    } 
    catch (e) {
        updateStatus("Error: SELECT não realizado " + e + ".");
    }
   
}

function carregarID(){
    document.itemForm.animais_idanimal.focus();
    query = "SELECT * FROM medicamentos ORDER BY idmedicamento DESC LIMIT 1;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query,[], function(transaction, results){
                var row = results.rows.item(0);
                var idmedicamento = row['idmedicamento'];
                document.itemForm.idmedicamento.value = idmedicamento+1;
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
    } 
    catch (e) {
        updateStatus("Erro: Carregar ID error: " + e + ".");
    }
}   

function limparCampos(){
    updateForm("", "", "", "");
    carregarID();
    dataAtual();
}

function dataAtual() {
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
    var hoje = ano + "-" + mes + "-" + dia;
    document.itemForm.data_aplicacao.value = hoje;
} 

// 3. Funções de tratamento e status.
 
// Tratando erros
 
errorHandler = function(transaction, error){
    updateStatus("Erro: " + error.message);
    return true;
}
 
nullDataHandler = function(transaction, results){
}
 
// Funções de update
 
function updateForm(idmedicamento, animais_idanimal, medicamento, data_aplicacao){
    document.itemForm.idmedicamento.value = idmedicamento;
    document.itemForm.animais_idanimal.value = animais_idanimal;
    document.itemForm.medicamento.value = medicamento;
    document.itemForm.data_aplicacao.value = data_aplicacao;
}
 
function updateStatus(status){
    document.getElementById('status').innerHTML = status;
}