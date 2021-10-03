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
    var query = 'CREATE TABLE IF NOT EXISTS sanitarios(idsanitario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, produto VARCHAR NOT NULL, total VARCHAR NOT NULL, propriedade VARCHAR NOT NULL, data_uso VARCHAR NOT NULL);';
    try {
        localDB.transaction(function(transaction){
            transaction.executeSql(query, [], nullDataHandler, errorHandler);
            updateStatus("Tabela 'sanitarios' status: OK.");
        });
    } 
    catch (e) {
        updateStatus("Erro: Data base 'sanitarios' não criada " + e + ".");
        return;
    }
}
 
//2. Query e visualização de Update
 
 
function onUpdate(){
    var idsanitario = document.itemForm.idsanitario.value;
    var produto = document.itemForm.produto.value;
    var total = document.itemForm.total.value;
    var propriedade = document.itemForm.propriedade.value;
    var data_uso = document.itemForm.data_uso.value;

    if (produto == "" || propriedade == "") {
        updateStatus("'Produto' e 'propriedade' são campos obrigatórios!");
    }
    else {
        var query = "update sanitarios set produto=?, total=?, propriedade=?, data_uso=? where idsanitario=?;";
        try {
            localDB.transaction(function(transaction){
                transaction.executeSql(query, [produto, total, propriedade, data_uso, idsanitario], function(transaction, results){
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
    var idsanitario = document.itemForm.idsanitario.value;
    
    var query = "delete from sanitarios where idsanitario=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idsanitario], function(transaction, results){
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
    var produto = document.itemForm.produto.value;
    var total = document.itemForm.total.value;
    var propriedade = document.itemForm.propriedade.value;
    var data_uso = document.itemForm.data_uso.value;
    if (produto == "" || propriedade == "") {
        updateStatus("Erro: 'Produto' e 'propriedade' são campos obrigatórios!");
    }
    else {
        var query = "insert into sanitarios (produto, total, propriedade, data_uso) VALUES (?, ?, ?, ?);";
        try {
            localDB.transaction(function(transaction){
                transaction.executeSql(query, [produto, total, propriedade, data_uso], function(transaction, results){
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
 var idsanitario = htmlLIElement.getAttribute("idsanitario");
 
 query = "SELECT * FROM sanitarios where idsanitario=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idsanitario], function(transaction, results){
            
                var row = results.rows.item(0);
                
                updateForm(row['idsanitario'], row['produto'], row['total'], row['propriedade'], row['data_uso']);
                
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
    var query = "SELECT * FROM sanitarios ORDER BY idsanitario DESC LIMIT 10;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [], function(transaction, results){
                for (var i = 0; i < results.rows.length; i++) {
                
                    var row = results.rows.item(i);
                    var li = document.createElement("li");
                    li.setAttribute("idsanitario", row['idsanitario']);
                    li.setAttribute("class", "data");
                    li.setAttribute("onclick", "onSelect(this)");
                    
                    var liText = document.createTextNode(row['idsanitario'] + " x "+ row['produto'] + " x "+ row['total'] + " x "+ row['propriedade'] + " x "+ row['data_uso']);
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
 var idsanitario = document.itemForm.idsanitario.value;
 updateForm("", "", "", "", "");
 query = "SELECT * FROM sanitarios where idsanitario=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idsanitario], function(transaction, results){
            
                var row = results.rows.item(0);
                
                updateForm(row['idsanitario'], row['produto'], row['total'], row['propriedade'], row['data_uso']);
                
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
    document.itemForm.produto.focus();
    query = "SELECT * FROM sanitarios ORDER BY idsanitario DESC LIMIT 1;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query,[], function(transaction, results){
                var row = results.rows.item(0);
                var idsanitario = row['idsanitario'];
                document.itemForm.idsanitario.value = idsanitario+1;

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
    updateForm("", "", "", "", "");
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
    document.itemForm.data_uso.value = hoje;
    
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
 
function updateForm(idsanitario, produto, total, propriedade, data_uso){
    document.itemForm.idsanitario.value = idsanitario;
    document.itemForm.produto.value = produto;
    document.itemForm.total.value = total;
    document.itemForm.propriedade.value = propriedade;
    document.itemForm.data_uso.value = data_uso;
}
 
function updateStatus(status){
    document.getElementById('status').innerHTML = status;
}