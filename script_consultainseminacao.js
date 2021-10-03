//1. Inicialização
 
var localDB = null;
 
function onInit(){
    try {
        if (!window.openDatabase) {
            updateStatus("Erro: Seu navegador não permite banco de dados.");
        }
        else {
            initDB();
            document.itemForm.ano.focus();
            updateStatus("pronta");
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


function localizarInseminacoes(){
 var ano = document.itemForm.ano.value;

 //Remove as linhas existentes para inserção das novas
    var dataRows = document.getElementById("itemData").getElementsByClassName("data");
 
    while (dataRows.length > 0) {
        row = dataRows[0];
        document.getElementById("itemData").removeChild(row);
    };   

   var  query = "SELECT * FROM inseminacoes where ano_estacao=?;";

    try {
        var prenhas = 0;
        var vazias = 0;

        localDB.transaction(function(transaction){
        
            transaction.executeSql(query, [ano], function(transaction, results){
                for (var i = 0; i < results.rows.length; i++) {
                    var row = results.rows.item(i);
                    var diagnostico = row['diagnostico'];
                    if(diagnostico == "prenha"){
                        prenhas++;
                    }
                    var data = row['data_inseminacao'];
                    data = corrigeData(data);
                    var li = document.createElement("li");
                    li.setAttribute("class", "data");
                    var liText = document.createTextNode(row["idinseminacao"] +" | "+ row['animais_idanimal']+" || "+data+" || "+row['touro']+" ||"+row['diagnostico']);
                    li.appendChild(liText);
                    document.getElementById("itemData").appendChild(li);
                    document.itemForm.prenhas.value = prenhas;
                    var total = document.itemForm.total.value;
                    vazias = parseFloat(total - prenhas);
                    document.itemForm.vazias.value = vazias;
                }                
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

function contarAptas(){

    var query = "SELECT * FROM animais WHERE sexo LIKE 'femea';";
 
    try {

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
                    if(era == "vaca"){
                        contador_femeas_vacas++;
                    }
                }
                
                document.itemForm.total.value = contador_femeas_vacas;
            }, function(transaction, error){
                updateStatus("Erro: " + error.code + "<br>Mensagem: " + error.message);
            });
        });
    }
    catch (e) {
        updateStatus("Error: SELECT não realizado " + e + ".");
    }
    
}

function limparDados(){
    // limpa a lista
    var dataRows = document.getElementById("itemData").getElementsByClassName("data");
 
    while (dataRows.length > 0) {
        row = dataRows[0];
        document.getElementById("itemData").removeChild(row);
    };   
    // limpa os campos
    document.itemForm.ano.value = "";
    document.itemForm.total.value = "";
    document.itemForm.ano.focus();
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
 
function retornaMeses(datahoje, datanascimento){
    return datahoje.getMonth() - datanascimento.getMonth() + (12 * (datahoje.getFullYear() - datanascimento.getFullYear()));
}

function retornaEra(numeromeses){
    var era = "";
    if(numeromeses >= 14){
        era = "vaca";
    }else{
        if(numeromeses <= 8){
            era = "bezerra";
        }else{
            era = "novilha";
        }
    }
    return era;
}

function carregarDados(){
    var ano = document.itemForm.ano.value;
    if(ano == ""){
        alert("Digite o ano da estação");
    }else{
        contarAptas();
        localizarInseminacoes();
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