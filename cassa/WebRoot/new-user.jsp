<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="head.jsp"%>
</head>
<body>

	<div class="container-fluid no-padding">
		<s:action name="sessioniaperte" executeResult="true" />
	</div>

	<br>

	<div class="container-fluid">
		<div class="row">
			<form id="saveuserfrm" autocomplete="off">
				<div class="col-md-3 col-md-offset-3 col-sm-4 col-sm-offset-2">

					<div class="form-group">
						<input type="text" name="nome" id="nome" placeholder="Nome"
							class="form-control tessera-form tessera-form" required disabled>
					</div>

					<div class="form-group">
						<input type="text" name="cognome" id="cognome"
							placeholder="Cognome" class="form-control tessera-form" required
							disabled>
					</div>

					<div class="form-group">
						<input type="text" name="email" id="email" placeholder="Email"
							class="form-control tessera-form" required disabled>
					</div>

					<div class="form-group">
						<input type="number" name="cap" id="cap"
							placeholder="CAP" class="form-control tessera-form" required disabled>
					</div>

					<div class="form-group">
						<input type="text" name="localita" id="localita"
							placeholder="Località" class="form-control tessera-form" required disabled>
					</div>

					<div class="form-group">
						<input type="text" name="tessera" id="tessera"
							placeholder="Tessera" class="form-control tessera-form" required
							readonly>
					</div>


					<div class="form-group">
						<button class="btn btn-success btn-custom" id="save" disabled>Salva</button>
						<button type="button" class="btn btn-info2 btn-custom" id="close">Chiudi</button>
					</div>
				</div>
				<input type="hidden" name="codice_alternativo"
					id="codice_alternativo">
			</form>
			<div class="col-md-3 col-sm-4">
				<form id="scantesserafrm" autocomplete="off">
					<div class="form-group">
						<input type="text" id="barcode" name="barcode"
							placeholder="Tessera" class="form-control tessera-form" required
							autofocus>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-info2 btn-custom btn-full"
							id="reset" disabled>Annulla</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="vkeyboard.jsp"%>
</body>
<script type="text/javascript">
$(document).ready(
		function() {
			$("#close").click(
					function() {
						location.href = "index";
					});
			$("#scantesserafrm").submit(
					function(e) {
						e.preventDefault();
						var t = $(this);
						$.ajax({
							type: "post",
							url: "scantessera",
							data: t.serialize(),
							success:
								function(data) {
									if(data != "error") {
										var s = data.split("|");
										$("#tessera").val(s[0]);
										$("#codice_alternativo").val(s[1]);
										$("#nome").removeAttr("disabled");
										$("#cognome").removeAttr("disabled");
										$("#email").removeAttr("disabled");
										$("#save").removeAttr("disabled");
										$("#barcode").attr("readonly", "readonly");
										$("#reset").removeAttr("disabled");
										$("#cap").removeAttr("disabled");
										$("#localita").removeAttr("disabled");
										$("#nome").focus();
										$(".keyboard-container").show(0);
									} else {
										alert("Tessera non riconosciuta.");
										$("#barcode").val("");
										$("#barcode").focus();
									}
								}
						});
					});

			$("#saveuserfrm").submit(
					function(e) {
						e.preventDefault();
						var t = $(this);
						var email= document.getElementById("email").value
						// alert(email);
						$.ajax({
							type: "post",
							url: "checkemail?email=" + email,
							success:
								function(data) {
									if(data == "OK") {
										$.ajax({
											type: "post",
											url: "saveuser",
											data: t.serialize(),
											success:
												function(data) {
													if(data == "success") {
														location.href = "index";
													} else {
														alert("Si è verificato un errore.");
													}
												}
										});
									} else {
										alert("Questo indirizzo email non può essere usato perché è già registrato nei nostri archivi.");
									}
								}
						});

					});

			$("#reset").click(
					function() {
						$.ajax({
							type: "post",
							url: "resettessera",
							data: $("#scantesserafrm").serialize(),
							success:
								function(data) {
									if(data == "success") {
										$("#tessera").val("");
										$("#codice_alternativo").val("");
										$("#nome").val("");
										$("#nome").attr("disabled", "disabled");
										$("#cognome").val("");
										$("#cognome").attr("disabled", "disabled");
										$("#email").val("");
										$("#email").attr("disabled", "disabled");
										$("#barcode").val("");
										$("#barcode").removeAttr("readonly");
										$("#save").attr("disabled", "disabled");
										$("#reset").attr("disabled", "disabled");
										$("#cap").val("");
										$("#cap").attr("disabled", "disabled");
										$("#localita").val("");
										$("#localita").attr("disabled", "disabled");
										$("#barcode").focus();
										$(".keyboard-container").hide(0);
									} else {
										alert("Si è verificato un errore sconosciuto.");
									}
								}
						});
					});

		});
</script>
</html>