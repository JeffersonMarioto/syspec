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
    var query = 'CREATE TABLE IF NOT EXISTS minerais(idminerais INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, produto VARCHAR NOT NULL, quantidade VARCHAR NOT NULL, total VARCHAR NOT NULL, propriedade VARCHAR NOT NULL, data_inicial VARCHAR NOT NULL, data_final VARCHAR, consumo VARCHAR);';
    try {
        localDB.transaction(function(transaction){
            transaction.executeSql(query, [], nullDataHandler, errorHandler);
            updateStatus("Tabela 'minerais' status: OK.");
        });
    } 
    catch (e) {
        updateStatus("Erro: Data base 'minerais' não criada " + e + ".");
        return;
    }
}
 
//2. Query e visualização de Update
 
 
function onUpdate(){
    var idminerais = document.itemForm.idminerais.value;
    var produto = document.itemForm.produto.value;
    var quantidade = document.itemForm.quantidade.value;
    var total = document.itemForm.total.value;
    var propriedade = document.itemForm.propriedade.value;
    var data_inicial = document.itemForm.data_inicial.value;
    var data_final = document.itemForm.data_final.value;
    var consumo = document.itemForm.consumo.value.replace(",",".");

    if (produto == "" || quantidade == "") {
        updateStatus("'Produto' e 'quantidade' são campos obrigatórios!");
    }
    else {
        var query = "update minerais set produto=?, quantidade=?, total=?, propriedade=?, data_inicial=?, data_final=?, consumo=? where idminerais=?;";
        try {
            localDB.transaction(function(transaction){
                transaction.executeSql(query, [produto, quantidade, total, propriedade, data_inicial, data_final, consumo, idminerais], function(transaction, results){
                    if (!results.rowsAffected) {
                        updateStatus("Erro: Update não realizado.");
                    }
                    else {
                        updateForm("", "", "", "", "", "", "", "");
                        updateStatus("Update realizado:" + results.rowsAffected);
                        carregarID();
                        dataAtual();
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
    var idminerais = document.itemForm.idminerais.value;
    
    var query = "delete from minerais where idminerais=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idminerais], function(transaction, results){
                if (!results.rowsAffected) {
                    updateStatus("Erro: Delete não realizado.");
                }
                else {
                    updateForm("", "", "", "", "", "", "", "");
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
    var idminerais = document.itemForm.idminerais.value;
    var produto = document.itemForm.produto.value;
    var quantidade = document.itemForm.quantidade.value;
    var total = document.itemForm.total.value;
    var propriedade = document.itemForm.propriedade.value;
    var data_inicial = document.itemForm.data_inicial.value;
    var data_final = document.itemForm.data_final.value;
    var consumo = document.itemForm.consumo.value.replace(",",".");
    if (produto == "" || quantidade == "") {
        updateStatus("Erro: 'Produto' e 'quantidade' são campos obrigatórios!");
    }
    else {
        var query = "insert into minerais (produto, quantidade, total, propriedade, data_inicial, data_final, consumo) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            localDB.transaction(function(transaction){
                transaction.executeSql(query, [produto, quantidade, total, propriedade, data_inicial, data_final, consumo], function(transaction, results){
                    if (!results.rowsAffected) {
                        updateStatus("Erro: Inserção não realizada");
                    }
                    else {
                        updateForm("", "", "", "", "", "", "", "");
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
 var idminerais = htmlLIElement.getAttribute("idminerais");
 
 query = "SELECT * FROM minerais where idminerais=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idminerais], function(transaction, results){
            
                var row = results.rows.item(0);
                
                updateForm(row['idminerais'], row['produto'], row['quantidade'], row['total'], row['propriedade'], row['data_inicial'], row['data_final'], row['consumo']);
                
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
 var idminerais = document.itemForm.idminerais.value;
 updateForm("", "", "", "", "", "", "", "");
 query = "SELECT * FROM minerais where idminerais=?;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [idminerais], function(transaction, results){
            
                var row = results.rows.item(0);
                
                updateForm(row['idminerais'], row['produto'], row['quantidade'], row['total'], row['propriedade'], row['data_inicial'], row['data_final'], row['consumo']);
                
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
    var query = "SELECT * FROM minerais ORDER BY idminerais DESC LIMIT 10;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [], function(transaction, results){
                for (var i = 0; i < results.rows.length; i++) {
                
                    var row = results.rows.item(i);
                    var li = document.createElement("li");
                    li.setAttribute("idminerais", row['idminerais']);
                    li.setAttribute("class", "data");
                    li.setAttribute("onclick", "onSelect(this)");
                    
                    var liText = document.createTextNode(row['idminerais'] + " x "+ row['produto'] + " x " + row['quantidade'] + " x "+ row['propriedade']   + " x " + row['data_inicial'] + " x "+ row['data_final'] + " x "+ row['consumo']);
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

function carregarID(){
    query = "SELECT * FROM minerais ORDER BY idminerais DESC LIMIT 1;";
    try {
        localDB.transaction(function(transaction){
        
            transaction.executeSql(query,[], function(transaction, results){
            
                var row = results.rows.item(0);
                var idminerais = row['idminerais'];
                document.itemForm.idminerais.value = idminerais+1;
                dataAtual();
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
    } 
    catch (e) {
        updateStatus("Erro: Carregar ID error: " + e + ".");
    } 
    document.itemForm.produto.focus();  
}

function limparCampos(){
    updateForm("", "", "", "", "", "", "", "");
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
    var hoje = ano +"-"+ mes + "-" + dia;
    document.itemForm.data_inicial.value = hoje;
}

// calcular consumo por quantidade e dias
function calcularConsumoQuantidadeDias(){
    var quantidade = document.itemForm.quantidade.value;
    var total = document.itemForm.total.value;
    var date1 = new Date(document.itemForm.data_inicial.value);
    var date2 = new Date(document.itemForm.data_final.value);
    var timeDiff = Math.abs(date1.getTime() - date2.getTime());
    var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
    var consumo = (quantidade / diffDays)/total;
    document.itemForm.consumo.value = consumo.toString().substring(0,5).concat(" kg/dia animal");

}

// calcular pelo consumo número de dias (data_final) que a quantidade dara para um total de animais

function calcularReversoConsumo(){
    var consumo = parseFloat(document.itemForm.consumo.value.replace(",","."));
    var quantidade = parseFloat(document.itemForm.quantidade.value);
    var total = parseFloat(document.itemForm.total.value);
    var dias = parseFloat;
    var consumo_diario = parseFloat;
    consumo_diario = parseFloat(consumo * total);
    dias = parseFloat(quantidade / consumo_diario);
    
    dias = Math.round(dias);



    var date = new Date(document.itemForm.data_inicial.value);
    var dia = date.getDay();
    if (dia < 10) {
        dia  = "0" + dia;
    }
    var mes  = date.getMonth() + 1;
    if (mes < 10) {
        mes  = "0" + mes;
    }
    var ano = date.getFullYear();
    let d = new Date(ano, mes, dia);
   
    d.setDate(d.getDate() + dias);
    dia = d.getDate();
    if (dia < 10) {
        dia  = "0" + dia;
    }
    mes  = d.getMonth();
    if (mes < 10) {
        mes  = "0" + mes;
    }
    ano = d.getFullYear();
    var data_final = ano + "-" + mes + "-" + dia;

    document.itemForm.data_final.value = data_final;
    document.itemForm.consumo.value = consumo.toString().concat(" kg/dia animal");
}

// calcular pelo consumo, número de animais, dias a quantidade necessária

function calcularReversoQuantidade(){
    var consumo = parseFloat(document.itemForm.consumo.value.replace(",","."));
    var total = parseFloat(document.itemForm.total.value);
    var date1 = new Date(document.itemForm.data_inicial.value);
    var date2 = new Date(document.itemForm.data_final.value);
    var timeDiff = Math.abs(date1.getTime() - date2.getTime());
    var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
    var gasto_diario = parseFloat;
    gasto_diario = parseFloat(consumo * total);
    var quantidade = parseFloat;
    quantidade = parseFloat(gasto_diario * diffDays);
    document.itemForm.quantidade.value = quantidade.toString();
    document.itemForm.consumo.value = consumo.toString().concat(" kg/dia animal")
}

// calcular pela quantidade, consumo, e dias o total de animais que podem ser atendidos

function calcularReversoTotal(){
    var consumo = parseFloat(document.itemForm.consumo.value.replace(",", "."));
    var quantidade = parseFloat(document.itemForm.quantidade.value);
    var date1 = new Date(document.itemForm.data_inicial.value);
    var date2 = new Date(document.itemForm.data_final.value);
    var timeDiff = Math.abs(date1.getTime() - date2.getTime());
    var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
    var total = parseFloat;
    total = parseFloat(quantidade / (consumo * diffDays));
    document.itemForm.total.value = Math.round(total);
    document.itemForm.consumo.value = consumo.toString().concat(" kg/dia animal")
}

// descobrir qual cálculo usar e calcular

function descobrirCalcular(){
    var quantidade = document.itemForm.quantidade.value;
    var total = document.itemForm.total.value;
    var data_inicial = document.itemForm.data_inicial.value;
    var data_final = document.itemForm.data_final.value;
    var consumo = document.itemForm.consumo.value;
    if(consumo == ""){
        calcularConsumoQuantidadeDias();
    }else{
        if(data_final == ""){
            calcularReversoConsumo();
        }else{
            if(total == ""){
                calcularReversoTotal();
            }else{
                if(quantidade == ""){
                    calcularReversoQuantidade();
                }
            }
        }
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
 
function updateForm(idminerais, produto, quantidade, total, propriedade, data_inicial, data_final, consumo){
    document.itemForm.idminerais.value = idminerais;
    document.itemForm.produto.value = produto;
    document.itemForm.quantidade.value = quantidade;
    document.itemForm.total.value = total;
    document.itemForm.propriedade.value = propriedade;
    document.itemForm.data_inicial.value = data_inicial;
    document.itemForm.data_final.value = data_final;
    document.itemForm.consumo.value = consumo;
}
 
function updateStatus(status){
    document.getElementById('status').innerHTML = status;
}