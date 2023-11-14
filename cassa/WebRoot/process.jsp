<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="head.jsp" %>

</head>
<script>
// $(document).ready(function () {
//     // Handler for .ready() called.
//     window.setTimeout(function () {
//         location.href = "index";
//     }, 5000);
// });
</script>

<body>

	<br>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2 col-md-offset-5 col-sm-2 col-sm-offset-5">
				<img class="img-responsive" src="images/logo.png">
			</div>
		</div>
	</div>

	<br>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6 col-sm-6 col-md-offset-3 col-sm-6 col-sm-offset-3">
				<div class="row">
					<div class="col-md-6 col-sm-6 col-sm-6">
						<h3><s:property value="cli.nome" /> <s:property value="cli.cognome" /></h3>
					</div>
					<div class="col-md-6 col-sm-6 col-sm-6">
						<h3>Tessera n. <s:property value="cli.tessera.tessera" /></h3>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>

	<div class="container-fluid">
		<div class="row">
			<s:if test="%{od1!=null}">
				<s:if test="%{od2!=null}">
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<div class="col-md-12 col-sm-12">
								<h3>Ordine n. <s:property value="od1.id" /></h3>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-sm-12">
								<div class="table-container large">
									<table class="table">
										<s:iterator value="od1.dettagli">
											<tr>
												<td align="left"><s:property value="%{descrizione}" /></td>
												<td align="right">x <s:text name="format.integer"><s:param value="%{quantita}" /></s:text></td>
												<td align="right">&euro;&nbsp;<s:text name="format.number"><s:param value="%{totale}" /></s:text></td>
											</tr>
										</s:iterator>
										<tr><td colspan="3">&nbsp;</td></tr>
										<tr>
											<td colspan="2" style="font-size=16px" align="right"><b>TOTALE</b></td>
											<td align="right" style="font-size=16px"><b>&euro;&nbsp;<s:text name="format.number"><s:param value="od1.totale" /></s:text></b></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="col-md-6 col-sm-6 col-md-offset-3">
						<div class="row">
							<div class="col-md-12 col-sm-12">
								<h3>Ordine n. <s:property value="od1.id" /></h3>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-sm-12">
								<div class="table-container large">
									<table class="table">
										<s:iterator value="od1.dettagli">
											<tr>
												<td align="left"><s:property value="%{descrizione}" /></td>
												<td align="right">x <s:text name="format.integer"><s:param value="%{quantita}" /></s:text></td>
												<td align="right">&euro;&nbsp;<s:text name="format.number"><s:param value="%{totale}" /></s:text></td>
											</tr>
										</s:iterator>
										<tr><td colspan="3">&nbsp;</td></tr>
										<tr>
											<td colspan="2" align="right"><b>TOTALE</b></td>
											<td align="right"><b>&euro;&nbsp;<s:text name="format.number"><s:param value="od1.totale" /></s:text></b>

											</td>
										</tr>

										<tr>
											<td colspan="2" align="right"><b>CONTANTI</b></td>
											<td align="right"><b>
												<input type="hidden" id="val_totale" value='<s:property value="%{od1.totale}"/>'/>
												<input type="number" id="val_contanti" placeholder="Contanti"/>
												</b>
											</td>
										</tr>
										<tr>
											<td colspan="2" align="right"><b>RESTO</b></td>
											<td align="right"><b>
												<input type="number" id="val_resto" placeholder="Resto"/>
												</b>
											</td>
											<td align="right">
												<button id="calcola">Calcola</button>
											</td>
										</tr>
										<script>
											$(document).ready(function () {
												$("#calcola").click(function() {
												  	var totale = $("#val_totale").val();
												  	var contanti = $("#val_contanti").val().replace(",",".");
													var resto = $("#val_resto").val();
													resto = contanti - totale;
													$("#val_resto").val(parseFloat(Math.round(resto * 100) / 100).toFixed(2));
												});
											});
										</script>
									</table>
								</div>
							</div>
						</div>
					</div>
				</s:else>
			</s:if>
			<s:if test="%{od2!=null}">
				<div class="col-md-6 col-sm-6">
					<div class="row">
						<div class="col-md-12 col-sm-12">
							<h3>Ordine n. <s:property value="od2.id" /></h3>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-sm-12">
							<div class="table-container large">
								<table class="table">
									<s:iterator value="od2.dettagli">
										<tr>
											<td align="left"><s:property value="%{descrizione}" /></td>
											<td align="right">x <s:property value="%{quantita}" /></td>
											<td align="right">&euro;&nbsp;<s:text name="format.number"><s:param value="%{totale}" /></s:text></td>
										</tr>
									</s:iterator>
									<tr><td colspan="3">&nbsp;</td></tr>
									<tr>
										<td colspan="2" align="right"><b>TOTALE</b></td>
										<td align="right"><b>&euro;&nbsp;<s:text name="format.number"><s:param value="od2.totale" /></s:text></b></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</s:if>
		</div>
	</div>
	<br>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4">
				<button class="btn btn-default btn-custom btn-full" id="close">CHIUDI</button>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
$(document).ready(
		function() {
			$("#close").click(
					function() {
						location.href = "index";
					});
			$(".table-container").animate({ scrollTop: $(this).height() }, 0);
		});
</script>
</html>