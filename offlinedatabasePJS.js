//1. Inicialização
 
var localDB = null;
 
function onInit(){
    try {
        if (!window.openDatabase) {
            updateStatus("Erro: Seu navegador não permite banco de dados.");
        }
        else {
            //initDB();
            //createTables();
            updateStatus("Passou");
            //queryAndUpdateOverview();
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
    var query = 'CREATE TABLE IF NOT EXISTS pesos(idpesos INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, animais_idanimal INTEGER NOT NULL, data_atual VARCHAR NOT NULL, data_anterior VARCHAR, peso_anterior VARCHAR, peso_atual VARCHAR NOT NULl, resultado VARCHAR, media VARCHAR, FOREIGN KEY (animais_idanimal) REFERENCES animais(idanimal));';
    try {
        localDB.transaction(function(transaction){
            transaction.executeSql(query, [], nullDataHandler, errorHandler);
            updateStatus("Tabela 'pesos' status: OK.");
        });
    } 
    catch (e) {
        updateStatus("Erro: Data base 'pesos' não criada " + e + ".");
        return;
    }
}
 
 
 
 
//2. Query e visualização de Update
 
 
function onUpdate(){
    var idpesos = document.itemForm.idpesos.value;
    var idanimal = document.itemForm.idanimal.value;
    var data_atual = document.itemForm.data_atual.value;
    var data_anterior = document.itemForm.data_anterior.value;
    var peso_atual = document.itemForm.peso_atual.value;
    var peso_anterior = document.itemForm.peso_anterior.value;
    var resultado = document.itemForm.resultado.value;
    var media = document.itemForm.media.value;

    if (idanimal == "" || data_atual == "") {
        updateStatus("'Número do animal' e 'data atual da pesagem' são campos obrigatórios!");
    }
    if (peso_atual == "") {
        updateStatus("'Pai' e 'nascimento' são campos obrigatórios!");
    }
    else {
        var query = "update pesos set idanimal=?, data_atual=?, data_anterior=?, peso_atual=?, peso_anterior=?, resultado=?, media=? where idpesos=?;";
        try {
            localDB.transaction(function(transaction){
                transaction.executeSql(query, [idanimal, data_atual, data_anterior, peso_atual, peso_anterior, resultado, media], function(transaction, results){
                    if (!results.rowsAffected) {
                        updateStatus("Erro: Update não realizado.");
                    }
                    else {
                        updateForm("", "", "", "", "", "", "", "");
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
    var idpesos = document.itemForm.idpesos.value;
    
    var query = "delete from pesos where idpesos=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idpesos], function(transaction, results){
                if (!results.rowsAffected) {
                    updateStatus("Erro: Delete não realizado.");
                }
                else {
                    updateForm("", "", "", "", "", "","");
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
    var idpesos = document.itemForm.idpesos.value;
    var idanimal = document.itemForm.idanimal.value;
    var data_atual = document.itemForm.data_atual.value;
    var data_anterior = document.itemForm.data_anterior.value;
    var peso_atual = document.itemForm.peso_atual.value;
    var peso_anterior = document.itemForm.peso_anterior.value;
    var resultado = document.itemForm.resultado.value;
    var media = document.itemForm.media.value;

   if (idanimal == "" || data_atual == "") {
        updateStatus("'Número do animal' e 'data atual da pesagem' são campos obrigatórios!");
    }
    if (peso_atual == "") {
        updateStatus("'Peso atual' é campo obrigatório!");
    }
    else {
        var query = "insert into pesos (idanimal, data_atual, data_anterior, peso_atual, peso_anterior, resultado, media) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            localDB.transaction(function(transaction){
                transaction.executeSql(query, [idanimal, descricao, pai, nascimento, propriedade, sexo], function(transaction, results){
                    if (!results.rowsAffected) {
                        updateStatus("Erro: Inserção não realizada");
                    }
                    else {
                        updateForm("", "", "", "", "", "", "","");
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
 var id = htmlLIElement.getAttribute("idpesos");
 
 query = "SELECT * FROM pesos where idpesos=?;";
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

function localizarPesoDataAnterior(){
    var idanimal = document.itemForm.idanimal.value;
     query = "SELECT * FROM pesos where animais_idanimal=? ORDER BY idpesos DESC LIMIT 1;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [id], function(transaction, results){
            
                var row = results.rows.item(0);
                
                updateForm2(row['idanimal'], row['data_atual'], row['peso_atual']);
                
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
    var query = "SELECT * FROM pesos;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [], function(transaction, results){
                for (var i = 0; i < results.rows.length; i++) {
                
                    var row = results.rows.item(i);
                    var li = document.createElement("li");
                    li.setAttribute("idpesos", row['idpesos']);
                    li.setAttribute("class", "data");
                    li.setAttribute("onclick", "onSelect(this)");
                    
                    var liText = document.createTextNode(row['idpesos'] + " x "+ row['idanimal']+ " x "+row['data_atual'] + " x "+ row['data_anterior']+ " x " +row['peso_atual'] + " x "+ row['peso_anterior'] + " x "+ );
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

function updateForm2(idanimal, data_atual, peso_atual){
    document.itemForm.idanimal.value = idanimal;
    document.itemForm.data_anterior.value = data_atual;
    document.itemForm.peso_anterior.value = peso_atual;
}
 
function updateStatus(status){
    document.getElementById('status').innerHTML = status;
}