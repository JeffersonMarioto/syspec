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
    var query = 'CREATE TABLE IF NOT EXISTS animais(idanimal INTEGER NOT NULL PRIMARY KEY, descricao VARCHAR NOT NULL, pai VARCHAR NOT NULL, nascimento VARCHAR NOT NULL, propriedade VARCHAR NOT NULL, sexo VARCHAR NOT NULL);';
    try {
        localDB.transaction(function(transaction){
            transaction.executeSql(query, [], nullDataHandler, errorHandler);
            updateStatus("Tabela 'animais' status: OK.");
        });
    } 
    catch (e) {
        updateStatus("Erro: Data base 'animais' não criada " + e + ".");
        return;
    }
}
 
 
 
 
//2. Query e visualização de Update
 
 
function onUpdate(){
    var idanimal = document.itemForm.idanimal.value;
    var descricao = document.itemForm.descricao.value;
    var pai = document.itemForm.pai.value;
    var nascimento = document.itemForm.nascimento.value;
    var propriedade = document.itemForm.propriedade.value;
    var sexo = document.itemForm.sexo.value;

    if (idanimal == "" || descricao == "") {
        updateStatus("'Número' e 'descrição' são campos obrigatórios!");
    }
    if (pai == "" || nascimento == "") {
        updateStatus("'Pai' e 'nascimento' são campos obrigatórios!");
    }
    if (propriedade == "" || sexo == "") {
        updateStatus("'Propriedade' e 'sexo' são campos obrigatórios!");
    }
    else {
        var query = "update animais set descricao=?, pai=?, nascimento=?, propriedade=?, sexo=? where idanimal=?;";
        try {
            localDB.transaction(function(transaction){
                transaction.executeSql(query, [descricao, pai, nascimento, propriedade, sexo, idanimal], function(transaction, results){
                    if (!results.rowsAffected) {
                        updateStatus("Erro: Update não realizado.");
                    }
                    else {
                        updateForm("", "", "", "", "", "");
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
    var idanimal = document.itemForm.idanimal.value;
    
    var query = "delete from animais where idanimal=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idanimal], function(transaction, results){
                if (!results.rowsAffected) {
                    updateStatus("Erro: Delete não realizado.");
                }
                else {
                    updateForm("", "", "", "", "", "");
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
    var idanimal = document.itemForm.idanimal.value;
    var descricao = document.itemForm.descricao.value;
    var pai = document.itemForm.pai.value;
    var nascimento = document.itemForm.nascimento.value;
    var propriedade = document.itemForm.propriedade.value;
    var sexo = document.itemForm.sexo.value;

    if (idanimal == "" || descricao == "") {
        updateStatus("'Número' e 'descrição' são campos obrigatórios!");
    }
    if (pai == "" || nascimento == "") {
        updateStatus("'Pai' e 'nascimento' são campos obrigatórios!");
    }
    if (propriedade == "" || sexo == "") {
        updateStatus("'Propriedade' e 'sexo' são campos obrigatórios!");
    }
    else {
        var query = "insert into animais (idanimal, descricao, pai, nascimento, propriedade, sexo) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            localDB.transaction(function(transaction){
                transaction.executeSql(query, [idanimal, descricao, pai, nascimento, propriedade, sexo], function(transaction, results){
                    if (!results.rowsAffected) {
                        updateStatus("Erro: Inserção não realizada");
                    }
                    else {
                        updateForm("", "", "", "", "", "");
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
 var id = htmlLIElement.getAttribute("idanimal");
 
 query = "SELECT * FROM animais where idanimal=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [id], function(transaction, results){
            
                var row = results.rows.item(0);
                
                updateForm(row['idanimal'], row['descricao'], row['pai'], row['nascimento'], row['propriedade'], row['sexo']);
                
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
    } 
    catch (e) {
        updateStatus("Error: SELECT não realizado " + e + ".");
    }
   
}

function localizarAnimal(){
 var id = document.itemForm.idanimal.value;
 
 query = "SELECT * FROM animais where idanimal=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [id], function(transaction, results){
            
                var row = results.rows.item(0);
                
                updateForm(row['idanimal'], row['descricao'], row['pai'], row['nascimento'], row['propriedade'], row['sexo']);
                
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
    } 
    catch (e) {
        updateStatus("Error: SELECT não realizado " + e + ".");
    }
   
}

function limparCampos(){
    updateForm("", "", "", "", "", "");
}
 
function queryAndUpdateOverview(){
 
 //Remove as linhas existentes para inserção das novas
    var dataRows = document.getElementById("itemData").getElementsByClassName("data");
 
    while (dataRows.length > 0) {
        row = dataRows[0];
        document.getElementById("itemData").removeChild(row);
    };
    
 //Realiza a leitura no banco e cria novas linhas na tabela.
    var query = "SELECT * FROM animais ORDER BY propriedade ASC, idanimal DESC LIMIT 10";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [], function(transaction, results){
                for (var i = 0; i < results.rows.length; i++) {
                
                    var row = results.rows.item(i);
                    var li = document.createElement("li");
                    li.setAttribute("idanimal", row['idanimal']);
                    li.setAttribute("class", "data");
                    li.setAttribute("onclick", "onSelect(this)");
                    
                    var liText = document.createTextNode(row['idanimal'] + " x "+ row['descricao']+ " x "+ row['nascimento']+ " x " +row['propriedade'] + " x "+ row['sexo']);
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
 
// Funções de update
 
function updateForm(idanimal, descricao, pai, nascimento, propriedade, sexo){
    document.itemForm.idanimal.value = idanimal;
    document.itemForm.descricao.value = descricao;
    document.itemForm.pai.value = pai;
    document.itemForm.nascimento.value = nascimento;
    document.itemForm.propriedade.value = propriedade;
    document.itemForm.sexo.value = sexo;
}
 
function updateStatus(status){
    document.getElementById('status').innerHTML = status;
}