<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="head.jsp" %>

</head>

<body>

	<s:bean name="it.gds.point.utils.SortDetails" var="sortDetails" />
<!-- 	<button class="btn btn-danger2" style="font-size:2em !important;" id="logout"><i class="fas fa-times-circle icon1"></i>&nbsp;</button> -->
	<button class="myButtonClose" style="font-size:1.3em !important; margin-left:5px; margin-top:5px;" id="logout">
		<i class="fas fa-skull icon1"></i>&nbsp;
	</button>

	<div class="container-fluid no-padding">
		<s:action name="sessioniaperte" executeResult="true" />
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6 col-sm-6">
				<h3><s:property value="#session.CLIENTE.nome" /> <s:property value="#session.CLIENTE.cognome" /> <s:property value="#session.SCONTO.codice_sconto" /></h3>
			</div>
			<div class="col-md-6 col-sm-6">
				<h3>Tessera n. <s:property value="#session.CLIENTE.tessera.tessera" /></h3>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6 col-sm-6" >
				<div class="row" >
					<div class="col-md-12 col-sm-12" >
						<s:if test="#session.BAR_O.carrello!=null">
							<s:set var="total" value="0" />
							<div class="table-container">
							<table class="table">
								<tr class="acquisti">
									<td colspan="4"><b>Acquisti in negozio</b></td>
								</tr>
								<s:sort comparator="#sortDetails" source="#session.BAR_O.carrello">
									<s:iterator>
										<s:set var="total" value="#total+totale" />
										<tr>
											<td align="left"><s:property value="%{descrizione}" /></td>
											<td align="right">x&nbsp;<s:text name="format.integer"><s:param value="%{quantita}" /></s:text></td>
											<td align="center">&nbsp;</td>
											<td align="right">&nbsp;&euro;&nbsp;<s:text name="format.number"><s:param value="%{totale}" /></s:text></td>
										</tr>
									</s:iterator>
								</s:sort>
								<tr><td>&nbsp;</td></tr>
								<tr class="totale">
									<td colspan="3" align="right">TOTALE</td>
									<td align="right">
										&euro;&nbsp;<s:text name="format.number"><s:param value="#total" /></s:text>
									</td>
								</tr>
							</table>
							</div>
						</s:if>
					</div>
				</div>
			</div>
			<div class="col-md-6 col-sm-6">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<form method="post" action="scan" id="scanfrm">
							<input type="text" name="barcode" id="barcode" class="form-control tessera-form" required autofocus autocomplete="off">
						</form>
					</div>
				</div><br>
				<div class="row">
					<div class="col-md-4 col-sm-4"><button class="btn btn-info btn-custom btn-full nr">7</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-info btn-custom btn-full nr">8</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-info btn-custom btn-full nr">9</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-info btn-custom btn-full nr">4</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-info btn-custom btn-full nr">5</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-info btn-custom btn-full nr">6</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-info btn-custom btn-full nr">1</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-info btn-custom btn-full nr">2</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-info btn-custom btn-full nr">3</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-info btn-custom btn-full nr">0</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-info2 btn-custom btn-full" id="cancel"><i class="fas fa-ban icon1"></i></button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-success btn-custom btn-full" id="ok"><i class="fas fa-check icon1"></i></button></div>
				</div><br>
				<div class="row">
					<div class="col-md-12 col-sm-12"><button class="btn btn-success btn-custom btn-full" id="continue">CONTINUA</button></div>
				</div>
				<div class="row">
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-info btn-custom btn-full" id="bar">BAR</button>
					</div>
<!-- 					<div class="col-md-4 col-sm-4"> -->
<!-- 						<button class="btn btn-info btn-custom btn-full" id="bbar">Banco BAR</button> -->
<!-- 					</div> -->
<!-- 					<div class="col-md-4 col-sm-4"> -->
<!-- 						<button class="btn btn-info btn-custom btn-full" id="bprod">Banco prodotti</button> -->
<!-- 					</div> -->
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-info btn-custom btn-full" id="brist">Ristorante</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
$(document).ready(
		function() {
			$("#logout").click(
					function() {
						location.href = "logout";
					});
			$("#barcode").keydown(
					function(e) {
						if(e.ctrlKey) {
							e.preventDefault();
						}
					});
			$(".nr").click(
					function(e) {
						e.preventDefault();
						var t = $(this);
						$("#barcode").val($("#barcode").val() + t.text());
					});
			$("#cancel").click(
					function(e) {
						e.preventDefault();
						$("#barcode").val($("#barcode").val().substring(0, $("#barcode").val().length - 1));
					});
			$("#ok").click(
					function() {
						$("#scanfrm").submit();
					});

			$("#scanfrm").submit(
					function(e) {
						e.preventDefault();
						$.ajax ({
							type: "post",
							url: "scan",
							data: $(this).serialize(),
							success:
								function(data) {
									if(data == "success")
										location.reload();
									else
										alert("Errore barcode.");
							}
						});
					});

			$("#continue").click(
					function() {
						location.href = "complete";
					});
			$("#bar").click(
					function() {
						location.href = "barcategorie";
					});
			$("#bbar").click(
					function() {
						location.href = "bbarcategorie";
					});
			$("#bprod").click(
					function() {
						location.href = "bprodcategorie";
					});
			$("#brist").click(
					function() {
						location.href = "bristcategorie";
					});
			$(".table-container").animate({ scrollTop: $(this).height() }, 0);
		});
</script>
</html>