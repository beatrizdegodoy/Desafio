var rootURL = "http://localhost:3000/cadastro/";
var atletaAtual;

$('#btnBusca').click(function() {
    console.log('busca');
    busca($('#idBusca').val());
    return false;
});


$('#btnAdiciona').click(function() {
    novoAtleta();
    return false;
});

$('#btnSave').click(function() {
 salvaAtleta();
 return false;
});

$('#btnInsert').click(function() {
 insereAtleta();
 return false;
});

function busca(idBusca) {
 if (idBusca == '') 
    findAll();    
else
    buscaPorId(idBusca);
}

function novoAtleta() {
    atletaAtual = {};
    renderizaDetalhes(atletaAtual);
}

function buscaPorId(idBusca) {
    console.log('buscaPorId: ' + idBusca);
    $.ajax({
        type: 'GET',
        url: rootURL + 'atleta/' + idBusca,
	crossDomain: true,
        dataType:'json',
        success: function(data){
        console.log('Sucesso buscaPorId: ' +
        data.numero);
        atletaAtual = data;
    renderizaDetalhes(atletaAtual);
 },
 error: function(data)
    {
		 swal("Erro!", "O atleta "+idBusca +" não existe no banco de dados", "error");        
        console.log('Error: ');
    }
}  );
}

function findAll() {
    console.log('finadAll');
    $.ajax({
        type: 'GET',
        crossDomain: true,
        dataType:'json',
        url: rootURL + 'atleta',
        success: function(data)
        {
            console.log('Sucesso findAll');
            atleta = data;
            renderizaAtletas(atleta);
        },
    error: function(errorMsg)
        {
            swal("Erro!", "Não foi possível acessar o servidor", "error");        
            console.log('Error: '+errorMsg);
        }
});
}


function insereAtleta() {
    var newAtleta={};
    newAtleta.numero = parseInt($('#numeroCodigo').val());
    newAtleta.status = parseFloat($('#status').val());
    console.log(JSON.stringify(newAtleta));
    console.log('insereAtleta ' + newAtleta.numero + ' ' + newAtleta.status);
    $.ajax({
        type: 'POST',
        crossDomain: true,
        contentType: 'application/json',
        url: rootURL+'atleta',
        data: JSON.stringify(newAtleta),
        success: function(data){
            console.log('success');
            swal("Sucesso!", "O atleta foi inserido com sucesso", "success");
            findAll();
            },
    error: function(errorMsg){
        swal("Erro!", "Não foi possível inserir o atleta", "error");
        console.log('Error: ' + errorMsg);
        }
    });
}

function removeAtleta(numero){
    swal({
        title: "Têm certeza?",
        text: "Uma vez deletada, não será possível recuperar os dados!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      .then((willDelete) => {
        if (willDelete) {
            console.log('removeAtleta:' + numero);    
            $.ajax({
                type: 'DELETE',
                crossDomain: true,        
                url: rootURL+'atleta/'+ numero,        
                success: function(data)
                    {
                        swal("Sucesso!", "O atleta foi removido com sucesso", "success");
                        console.log('Deletado com sucesso!');
                        findAll();                
                    },
                    error:function(errorMsg){
                        swal("Erro!", "Não foi possível remover o atleta", "error");
                        console.log('Salva atleta error: ' + errorMsg);
                    }
            });          
        } else {
			console.log('Atleta não foi deletado!');
        }
      });    
       
}

function salvaAtleta() {
    console.log('salvaAtleta');
    atletaAtual={};
    atletaAtual.numero=parseInt($('#numeroCodigo').val());
    atletaAtual.saldo=parseFloat($('#saldo').val());
    console.log('Atleta Atual = ' + atletaAtual.numero + ":" + atletaAtual.saldo);
    console.log(JSON.stringify(atletaAtual));
    $.ajax({
        type: 'PUT',
        crossDomain: true,
        contentType: 'application/json',
        url: rootURL+'atleta',
        data: JSON.stringify(atletaAtual),
        success: function(data){
            swal("Sucesso!", "O atleta foi alterado com sucesso", "success");
            console.log('Salva atleta: Success');
			findAll();
        },
        error:function(errorMsg){
            swal("Erro!", "Não foi possível alterar o atleta", "error");
            console.log('Salva atleta error: ' + errorMsg);
        }
    });
}

function atualizaID(numero,id) {
    console.log('atualizaID');
	console.log(numero);
	console.log(saldo);
    atletaAtual={};
    atletaAtual.numero=numero;
    atletaAtual.status=$('#status'+id).val();
    console.log('Atleta Atual = ' + atletaAtual.numero + ":" + atletaAtual.status);
    console.log(JSON.stringify(atletaAtual));
    $.ajax({
        type: 'PUT',
        crossDomain: true,
        contentType: 'application/json',
        url: rootURL+'atelta',
        data: JSON.stringify(atletaAtual),
        success: function(data){
            swal("Sucesso!", "O atleta foi alterado com sucesso", "success");
            console.log('Salva atleta: Success');
        },
        error:function(errorMsg){
            swal("Erro!", "Não foi possível alterar o atleta", "error");
            console.log('Salva atleta error: ' + errorMsg);
        }
    });
}

function renderizaDetalhes(atleta) {
    $('#numeroCodigo').val(atleta.numero);
    $('#status').val(atleta.status);
 }

function renderizaAtletas(atletas) {
    console.log(atletas);
    var html = "<table border='1|1'>";
    html+="<tr><th>Código</th><th>Status</th><th>Atualizar</th><th>Remover</th></tr>";
    for (var i = 0; i < atletas.length; i++) {
    html+="<tr>";
    html+="<td>"+atletas[i].numero+"</td>";    
	html+="<td><input type='text' id='status"+i+"' value='"+atletas[i].status+"' class='form-control'/></td>";
	//html+="<td align='center'> <button class='btn btn-info'onclick='atualizaID("+atletas[i].numero+","+document.getElementById('status'+i).getAttribute('value')+")'>Atualizar</button></td>";
	html+="<td align='center'> <button class='btn btn-info'onclick='atualizaID("+atletas[i].numero+","+i+")'>Atualizar</button></td>";
	html+="<td align='center'> <button class='btn btn-danger'onclick='removeAtleta("+atletas[i].numero+")'>Deletar</button></td>";
    html+="</tr>";
 }
 html+="</table>";
 document.getElementById("atletas").innerHTML = html;
}