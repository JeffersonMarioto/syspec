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
    var query = 'CREATE TABLE IF NOT EXISTS pesagem(idpesos INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, animais_idanimal INTEGER NOT NULL, data_atual VARCHAR NOT NULL, data_anterior VARCHAR, peso_anterior VARCHAR, peso_atual VARCHAR NOT NULl, resultado VARCHAR, media VARCHAR, FOREIGN KEY (animais_idanimal) REFERENCES animais(idanimal));';
    try {
        localDB.transaction(function(transaction){
            transaction.executeSql(query, [], nullDataHandler, errorHandler);
            updateStatus("Tabela 'pesagem' status: OK.");
        });
    } 
    catch (e) {
        updateStatus("Erro: Data base 'pesagem' não criada " + e + ".");
        return;
    }
}
 
//2. Query e visualização de Update
function onUpdate(){
    var idpesos = document.itemForm.idpesos.value;
    var animais_idanimal = document.itemForm.animais_idanimal.value;
    var data_atual = document.itemForm.data_atual.value;
    var data_anterior = document.itemForm.data_anterior.value;
    var peso_atual = document.itemForm.peso_atual.value;
    var peso_anterior = document.itemForm.peso_anterior.value;
    var resultado = document.itemForm.resultado.value;
    var media = document.itemForm.media.value;
    updateStatus("Atualizando");
    var query = "update pesagem set animais_idanimal=?, data_atual=?, data_anterior=?, peso_atual=?, peso_anterior=?, resultado=?, media=? where idpesos=?;";
    try {
        localDB.transaction(function(transaction){
            transaction.executeSql(query, [animais_idanimal, data_atual, data_anterior, peso_atual, peso_anterior, resultado, media, idpesos], function(transaction, results){
                if (!results.rowsAffected) {
                    updateStatus("Erro: Update não realizado.");
                }
                else {
                    updateForm("", "", "", "", "", "", "", "", "");
                    updateStatus("Update realizado:" + results.rowsAffected);
                    queryAndUpdateOverview();
                    carregarID();
                }
            }, errorHandler);
        });
    } 
    catch (e) {
        updateStatus("Erro: UPDATE não realizado " + e + ".");
    }
}
 
function onDelete(){
    var idpesos = document.itemForm.idpesos.value;
    
    var query = "delete from pesagem where idpesos=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idpesos], function(transaction, results){
                if (!results.rowsAffected) {
                    updateStatus("Erro: Delete não realizado.");
                }
                else {
                    updateForm("", "", "", "", "", "", "", "", "");
                    updateStatus("Linhas deletadas:" + results.rowsAffected);
                    queryAndUpdateOverview();
                    carregarID();
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
    var animais_idanimal = document.itemForm.animais_idanimal.value;
    var data_atual = document.itemForm.data_atual.value;
    var data_anterior = document.itemForm.data_anterior.value;
    var peso_atual = document.itemForm.peso_atual.value;
    var peso_anterior = document.itemForm.peso_anterior.value;
    var resultado = document.itemForm.resultado.value;
    var media = document.itemForm.media.value;

    if (peso_atual == "") {
        updateStatus("'Peso atual' é campo obrigatório!");
    }
    else {
        var query = "insert into pesagem (animais_idanimal, data_atual, data_anterior, peso_atual, peso_anterior, resultado, media) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            localDB.transaction(function(transaction){
                transaction.executeSql(query, [animais_idanimal, data_atual, data_anterior, peso_atual, peso_anterior, resultado, media], function(transaction, results){
                    if (!results.rowsAffected) {
                        updateStatus("Erro: Inserção não realizada");
                    }
                    else {
                        updateForm("", "", "", "", "", "", "", "", "");
                        updateStatus("Inserção realizada, linha id: " + results.insertId);
                        queryAndUpdateOverview();
                        carregarID();
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
 var idpesos = htmlLIElement.getAttribute("idpesos");
 var idanimal = "";
 query = "SELECT * FROM pesagem where idpesos=?;";
 query2 = "SELECT nascimento FROM animais where idanimal=?;";

    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idpesos], function(transaction, results){
            
                var row = results.rows.item(0);
                
                updateForm(row['idpesos'], row['animais_idanimal'], "", row['data_atual'], row['data_anterior'], row['peso_atual'], row['peso_anterior'], row['resultado'], row['media']);
                idanimal = row['animais_idanimal'];
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query2, [idanimal], function(transaction, results){
            
                var row = results.rows.item(0);
                document.itemForm.idade.value = calcularIdade(row['nascimento']);
                
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
 var idpesos = document.itemForm.idpesos.value;
 updateForm("", "", "", "", "", "", "", "", "");
 query = "SELECT * FROM pesagem where idpesos=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idpesos], function(transaction, results){
                var row = results.rows.item(0);
                updateForm(row['idpesos'], row['animais_idanimal'], "", row['data_atual'], row['data_anterior'], row['peso_atual'], row['peso_anterior'], row['resultado'], row['media']);
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
    query = "SELECT * FROM pesagem ORDER BY idpesos DESC LIMIT 1;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query,[], function(transaction, results){
            
                var row = results.rows.item(0);
                var idpesos = row['idpesos'];
                document.itemForm.idpesos.value = idpesos+1;
                document.itemForm.animais_idanimal.focus();
                dataAtual();
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
    } 
    catch (e) {
        updateStatus("Erro: Carregar ID error: " + e + ".");
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
    var query = "SELECT * FROM pesagem ORDER BY idpesos DESC LIMIT 10;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [], function(transaction, results){
                for (var i = 0; i < results.rows.length; i++) {
                
                    var row = results.rows.item(i);
                    var li = document.createElement("li");
                    li.setAttribute("idpesos", row['idpesos']);
                    li.setAttribute("class", "data");
                    li.setAttribute("onclick", "onSelect(this)");
                    
                    var liText = document.createTextNode(row['idpesos'] + " x "+ row['animais_idanimal']+ " x "+ row['data_atual'] + " x "+ row['peso_atual'] + " x "+ row['resultado'] + " x "+ row['media']);
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

function localizarPesoDataAnterior(){
    var animais_idanimal = document.itemForm.animais_idanimal.value;
     query = "SELECT * FROM pesagem WHERE animais_idanimal=? ORDER BY idpesos DESC LIMIT 1;";
     query2 = "SELECT * FROM animais where idanimal=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [animais_idanimal], function(transaction, results){
                var row = results.rows.item(0);
                updateForm2(row['animais_idanimal'], row['data_atual'], row['peso_atual']);
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query2, [animais_idanimal], function(transaction, results){
                var row = results.rows.item(0);
                document.itemForm.idade.value = calcularIdade(row['nascimento']);
                document.itemForm.peso_atual.focus();
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
    } 
    catch (e) {
        updateStatus("Error: SELECT não realizado " + e + ".");
    }   
}

 
function calcularIdade(datanascimento){
    var datanascimento = new Date(datanascimento);
    var data = new Date();
    var mes = 0;
    mes = data.getMonth() - datanascimento.getMonth();
    var ano = 0;
    ano = 12 * (data.getFullYear() - datanascimento.getFullYear());
    var meses = 0;
    meses = ano + (mes);
    return meses;
}

function Calcular(){
    var date1 = new Date(document.itemForm.data_atual.value);
    var date2 = new Date(document.itemForm.data_anterior.value);
    var timeDiff = Math.abs(date1.getTime() - date2.getTime());
    var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
    var peso_atual=0;
    var peso_anterior=0;
    var peso_atual = parseFloat(document.getElementById("peso_atual").value);
    var peso_anterior = parseFloat(document.getElementById("peso_anterior").value);
    var resultado = parseFloat;
    resultado = parseFloat(peso_atual - peso_anterior);
    document.itemForm.resultado.value = resultado;
    var media = 0;
    media = parseFloat(resultado / diffDays);
    document.itemForm.media.value = media.toString().substring(0,5).concat("kg / dia - "+diffDays+" dias");
    if(document.itemForm.idade.value == ""){
        try{
            var animais_idanimal = document.itemForm.animais_idanimal.value;
            query2 = "SELECT * FROM animais where idanimal=?;";
            localDB.transaction(function(transaction){
        
                transaction.executeSql(query2, [animais_idanimal], function(transaction, results){
                    var row = results.rows.item(0);
                    document.itemForm.idade.value = calcularIdade(row['nascimento']);
                }, function(transaction, error){
                    updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
                });
            });
        } 
        catch (e) {
            updateStatus("Error: SELECT não realizado " + e + ".");
        }   
    }
}

function limparCampos(){
    updateForm("", "", "", "", "", "", "", "", "");
    carregarID();
}

function dataAtual() {
    var data = new Date();
    var dia  = data.getDate();
    if (dia< 10) {
        dia  = "0" + dia;
    }

    var mes  = data.getMonth() + 1;
    if (mes < 10) {
        mes  = "0" + mes;
    }
    var ano = data.getFullYear();
    var hoje = ano +"-"+ mes + "-" + dia;;
    document.itemForm.data_atual.value = hoje;
}
 
// Funções de update
 
function updateForm(idpesos, animais_idanimal, idade, data_atual, data_anterior, peso_atual, peso_anterior, resultado, media){
    document.itemForm.idpesos.value = idpesos;
    document.itemForm.animais_idanimal.value = animais_idanimal;
    document.itemForm.idade.value = idade;
    document.itemForm.data_atual.value = data_atual;
    document.itemForm.data_anterior.value = data_anterior;
    document.itemForm.peso_atual.value = peso_atual;
    document.itemForm.peso_anterior.value = peso_anterior;
    document.itemForm.resultado.value = resultado;
    document.itemForm.media.value = media;
}

function updateForm2(animais_idanimal, data_atual, peso_atual){
    document.itemForm.animais_idanimal.value = animais_idanimal;
    document.itemForm.data_anterior.value = data_atual;
    document.itemForm.peso_anterior.value = peso_atual;
}
 
function updateStatus(status){
    document.getElementById('status').innerHTML = status;
}