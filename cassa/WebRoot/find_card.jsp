<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="head.jsp" %>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
         <span aria-hidden="true">&times;</span>
    </button>
	<h2>Ricerca tessera</h2>
</div>
<div class="modal-body">
    <div class="container-fluid">
		<br>
		<div class="row">
			<div class="col-md-12 col-sm-12">
				<form method="post" action="searchcard" id="searchfrm" autocomplete="off" onsubmit="return false;">
					<input type="text" name="name" id="name" class="form-control" placeholder="Nome cliente" required autofocus>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-success btn-custom orange" id="search">Esegui</button>
    <button type="button" class="btn btn-info2 btn-custom orange" data-dismiss="modal">Chiudi</button>
    <br>
    <hr>
    <div style="max-height:300px; ">
		<table id="gable" class="table table-striped table-bordered" style="width:100%; text-align:center; font-size:14px">
			<th style="width:25%; text-align:center;">Nome</th>
			<th style="width:25%; text-align:center; ">Cognome</th>
			<th style="width:25%; text-align:center; ">Email</th>
			<th style="width:25%; text-align:center;x">Tessera</th>
		</table>
	</div>
</div>

<script type="text/javascript">
$(document).ready(
		function() {
			$("#modal").on("shown.bs.modal",
					function() {
						$("#pwd").focus();
					});
			$(".nr").click(
					function(e) {
						e.preventDefault();
						var t = $(this);
						$("#pwd").val($("#pwd").val() + t.text());
					});
			$("#acancel").click(
					function(e) {
						e.preventDefault();
						$("#pwd").val($("#pwd").val().substring(0, $("#pwd").val().length - 1));
					});

			$("#search").click(
					function(e) {
						e.preventDefault();
						$.ajax({ 				// create an AJAX call...
					        data: $("#searchfrm").serialize(), 	// get the form data
					        type: 'GET', 		// GET or POST
					        url: 'searchcard', 	// the file to call
					        success: function(response) { 					// on success..
					            append_json(response);						// call the append_json function
					        }
					    });
					    return false;
					});
		});

		//this function appends the json data to the table 'gable'
        function append_json(data){
			var table = document.getElementById('gable');
			for (var key in data.clis) {
				crunchifyName = key;
				var nome = data.clis[key].nome;
				var cognome = data.clis[key].cognome;
				var email = data.clis[key].email;
				var tessera = data.clis[key].tessera.tessera;
	            var tr = document.createElement('tr');
	            tr.innerHTML = 	'<td style="width:25%">' + nome + '</td>' +
			            			'<td style="width:25%">' + cognome + '</td>' +
			            			'<td style="width:25%">' + email + '</td>' +
			            			'<td style="width:25%"><button class="etichetta" style="margin:2px; width:100%" onclick="copyvalue(this)" data-dismiss="modal">' + tessera + '</button><br></td>';
	            table.appendChild(tr);
			}
        }
       	function copyvalue(tessera) {
			$("#tessera").val(tessera.innerText);
       	}
</script>