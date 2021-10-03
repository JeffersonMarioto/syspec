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
            dataAtual();
            carregarID();
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
    var query = 'CREATE TABLE IF NOT EXISTS inseminacoes(idinseminacao INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, animais_idanimal INTEGER NOT NULL, ano_estacao VARCHAR NOT NULL, data_inseminacao VARCHAR NOT NULL, touro VARCHAR, tentativa VARCHAR, data_diagnostico VARCHAR NOT NULL, diagnostico VARCHAR, data_parto VARCHAR, FOREIGN KEY (animais_idanimal) REFERENCES animais(idanimal));';
    try {
        localDB.transaction(function(transaction){
            transaction.executeSql(query, [], nullDataHandler, errorHandler);
            updateStatus("Tabela 'inseminacoes' status: OK.");
        });
    } 
    catch (e) {
        updateStatus("Erro: Data base 'inseminacao' não criada " + e + ".");
        return;
    }
}
 
//2. Query e visualização de Update
function onUpdate(){
    var idinseminacao = document.itemForm.idinseminacao.value;
    var animais_idanimal = document.itemForm.animais_idanimal.value;
    var ano_estacao = document.itemForm.ano_estacao.value;
    var data_inseminacao = document.itemForm.data_inseminacao.value;
    var touro = document.itemForm.touro.value;
    var tentativa = document.itemForm.tentativa.value;
    var data_diagnostico = document.itemForm.data_diagnostico.value;
    var diagnostico = document.itemForm.diagnostico.value;
    var data_parto = document.itemForm.data_parto.value;
    updateStatus("Atualizando");
    var query = "update inseminacoes set animais_idanimal=?, ano_estacao=?, data_inseminacao=?, touro=?, tentativa=?, data_diagnostico=?, diagnostico=?, data_parto=? where idinseminacao=?;";
    try {
        localDB.transaction(function(transaction){
            transaction.executeSql(query, [animais_idanimal, ano_estacao, data_inseminacao, touro, tentativa, data_diagnostico, diagnostico, data_parto, idinseminacao], function(transaction, results){
                if (!results.rowsAffected) {
                    updateStatus("Erro: Update não realizado.");
                }
                else {
                    updateForm("", "", "", "", "", "", "", "", "");
                    updateStatus("Update realizado:" + results.rowsAffected);
                    queryAndUpdateOverview();
                    carregarID();
                    dataAtual();
                }
            }, errorHandler);
        });
    } 
    catch (e) {
        updateStatus("Erro: UPDATE não realizado " + e + ".");
    }
}
 
function onDelete(){
    var idinseminacao = document.itemForm.idinseminacao.value;
    
    var query = "delete from inseminacoes where idinseminacao=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idinseminacao], function(transaction, results){
                if (!results.rowsAffected) {
                    updateStatus("Erro: Delete não realizado.");
                }
                else {
                    updateForm("", "", "", "", "", "", "", "", "");
                    updateStatus("Linhas deletadas:" + results.rowsAffected);
                    queryAndUpdateOverview();
                    carregarID();
                    dataAtual();
                }
            }, errorHandler);
        });
    } 
    catch (e) {
        updateStatus("Erro: DELETE não realizado " + e + ".");
    }
    
}
 
function onCreate(){
    var idinseminacao = document.itemForm.idinseminacao.value;
    var animais_idanimal = document.itemForm.animais_idanimal.value;
    var ano_estacao = document.itemForm.ano_estacao.value;
    var data_inseminacao = document.itemForm.data_inseminacao.value;
    var touro = document.itemForm.touro.value;
    var tentativa = document.itemForm.tentativa.value;
    var data_diagnostico = document.itemForm.data_diagnostico.value;
    var diagnostico = document.itemForm.diagnostico.value;
    var data_parto = document.itemForm.data_parto.value;

    if (animais_idanimal == "" || touro == "") {
        updateStatus("'Número da vaca e touro' são campos obrigatórios!");
    }
    else {
        var query = "insert into inseminacoes (animais_idanimal, ano_estacao, data_inseminacao, touro, tentativa, data_diagnostico, diagnostico, data_parto) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            localDB.transaction(function(transaction){
                transaction.executeSql(query, [animais_idanimal, ano_estacao, data_inseminacao, touro, tentativa, data_diagnostico, diagnostico, data_parto], function(transaction, results){
                    if (!results.rowsAffected) {
                        updateStatus("Erro: Inserção não realizada");
                    }
                    else {
                        updateForm("", "", "", "", "", "", "", "", "");
                        updateStatus("Inserção realizada, linha id: " + results.insertId);
                        queryAndUpdateOverview();
                        carregarID();
                        dataAtual();
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
 var idinseminacao = htmlLIElement.getAttribute("idinseminacao");
 
 query = "SELECT * FROM inseminacoes where idinseminacao=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idinseminacao], function(transaction, results){
            
                var row = results.rows.item(0);
                
                updateForm(row['idinseminacao'], row['animais_idanimal'], row['ano_estacao'], row['data_inseminacao'], row['touro'], row['tentativa'], row['data_diagnostico'], row['diagnostico'], row['data_parto']);
                
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
 var idinseminacao = document.itemForm.idinseminacao.value;
 updateForm("", "", "", "", "", "", "", "", "");
 query = "SELECT * FROM inseminacoes where idinseminacao=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idinseminacao], function(transaction, results){
            
                var row = results.rows.item(0);
                
                updateForm(row['idinseminacao'], row['animais_idanimal'], row['ano_estacao'], row['data_inseminacao'], row['touro'], row['tentativa'], row['data_diagnostico'], row['diagnostico'], row['data_parto']);
                
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
    query = "SELECT * FROM inseminacoes ORDER BY idinseminacao DESC LIMIT 1;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query,[], function(transaction, results){
                var row = results.rows.item(0);
                var idinseminacao = row['idinseminacao'];
                document.itemForm.idinseminacao.value = idinseminacao+1;
                document.itemForm.animais_idanimal.focus();

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
    var query = "SELECT * FROM inseminacoes ORDER BY idinseminacao DESC LIMIT 10;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [], function(transaction, results){
                for (var i = 0; i < results.rows.length; i++) {
                
                    var row = results.rows.item(i);
                    var li = document.createElement("li");
                    li.setAttribute("idinseminacao", row['idinseminacao']);
                    li.setAttribute("class", "data");
                    li.setAttribute("onclick", "onSelect(this)");
                    
                    var liText = document.createTextNode(row['idinseminacao'] + " x "+ row['animais_idanimal']+ " x "+ row['ano_estacao'] + " x "+ row['tentativa'] + " x "+ row['touro'] + " x "+ row['diagnostico']);
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

function localizarInseminacaoAnterior(){
    if(confirm("Localizar inseminação anterior deste animal?")==true){
        var animais_idanimal = document.itemForm.animais_idanimal.value;
         query = "SELECT * FROM inseminacoes where animais_idanimal=? ORDER BY idinseminacao DESC LIMIT 1;";
        try {
            localDB.transaction(function(transaction){
            
                transaction.executeSql(query, [animais_idanimal], function(transaction, results){
                
                    var row = results.rows.item(0);
                    updateForm(row['idinseminacao'], row['animais_idanimal'], row['ano_estacao'], row['data_inseminacao'], row['touro'], row['tentativa'], row['data_diagnostico'], row['diagnostico'], row['data_parto']);
                    document.itemForm.data_diagnostico.value = dataAtual();
                    document.itemForm.tentativa.focus();
                }, function(transaction, error){
                    updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
                });
            });
        } 
        catch (e) {
            updateStatus("Error: SELECT não realizado " + e + ".");
        }   
    }else{document.itemForm.ano_estacao.focus();}
}

function calcularParto(){
    var date = new Date(document.itemForm.data_inseminacao.value);
       
    date.setMonth(date.getMonth() + 9);
    var dia  = date.getDate();
    if (dia < 10) {
        dia  = "0" + dia;
    }

    var mes  = date.getMonth() + 1;
    if (mes < 10) {
        mes  = "0" + mes;
    }
    var ano = date.getFullYear();
    var parto = ano + "-" + mes + "-" + dia;
    document.itemForm.data_parto.value = parto;    

}

function limparCampos(){
    updateForm("", "", "", "", "", "", "", "", "");
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
    document.itemForm.data_inseminacao.value = hoje;
    
}
 
// Funções de update
 
function updateForm(idinseminacao, animais_idanimal, ano_estacao, data_inseminacao, touro, tentativa, data_diagnostico, diagnostico, data_parto){
    document.itemForm.idinseminacao.value = idinseminacao;
    document.itemForm.animais_idanimal.value = animais_idanimal;
    document.itemForm.ano_estacao.value = ano_estacao;
    document.itemForm.data_inseminacao.value = data_inseminacao;
    document.itemForm.touro.value = touro;
    document.itemForm.tentativa.value = tentativa;
    document.itemForm.data_diagnostico.value = data_diagnostico;
    document.itemForm.diagnostico.value = diagnostico;
    document.itemForm.data_parto.value = data_parto;
}
 
function updateStatus(status){
    document.getElementById('status').innerHTML = status;
}