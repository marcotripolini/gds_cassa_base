<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="head.jsp" %>

</head>

<body>

	<button class="btn btn-danger2" style="font-size:2em !important;" id="logout"><i class="fas fa-times-circle icon1"></i>&nbsp;</button>

	<div class="container-fluid no-padding">
		<s:action name="sessioniaperte" executeResult="true" />
	</div>
	<br>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6 col-sm-6">
				<h3><s:property value="#session.CLIENTE.nome" /> <s:property value="#session.CLIENTE.cognome" /></h3>
			</div>
			<div class="col-md-6 col-sm-6">
				<h3>Tessera n. <s:property value="#session.CLIENTE.tessera.tessera" /></h3>
			</div>
		</div>
		<br>

		<div class="row">
			<s:if test="#session.TOTEM_O!=null">
				<div class="col-md-3 col-sm-3">
					<s:if test="#session.BAR_O.carrello!=null">
						<div class="row">
							<div class="col-md-12 col-sm-12">
								<h class="">Acquisto in negozio</h>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-sm-12">
								<h><b>Tipo di pagamento</b></h>
								<form id="ptypefrm">
									<s:if test="#session.PTYPE.equals(\"CO\")">
										<div class="form-group">
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="hide();" name="ptype" value="CO" checked><label>Contanti</label>
											</div>
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="hide();" name="ptype" value="CC"><label>Carte</label>
											</div>
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="show();" name="ptype" value="TK"><label>Ticket</label>
											</div>
										</div>
									</s:if>
									<s:elseif test="#session.PTYPE.equals(\"CC\")">
										<div class="form-group">
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="hide();" name="ptype" value="CO"><label>Contanti</label>
											</div>
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="hide();" name="ptype" value="CC" checked><label>Carte</label>
											</div>
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="show();" name="ptype" value="TK"><label>Ticket</label>
											</div>
										</div>
									</s:elseif>
									<s:elseif test="#session.PTYPE.equals(\"TK\")">
										<div class="form-group">
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="hide();" name="ptype" value="CO"><label>Contanti</label>
											</div>
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="hide();" name="ptype" value="CC" checked><label>Carte</label>
											</div>
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="show();" name="ptype" value="TK"><label>Ticket</label>
											</div>
										</div>
									</s:elseif>
									<br>
									<br>
									<br>
									<div class="ticket00">
<%-- 										<select name="ticketrest" class="combobox input-large form-control" style="display: none;"> --%>
<!-- 											<option value="" selected="selected">Seleziona il valore del buono pasto</option> -->
<!-- 											<option value="7">7,00</option> -->
<!-- 											<option value="5">5,00</option> -->
<!-- 											<option value="10">10,00</option> -->
<%-- 										</select> --%>
										<input type="text" style="width:200px;" name="ticketrest" placeholder="importo ticket"> &nbsp;
										<button class="myButton" id="clear">X</button>&nbsp;
										<button class="myButton" onclick="plus_7();">7,00</button>&nbsp;
										<button class="myButton" onclick="plus_5();">5,00</button>&nbsp;
										<div class="description_ticket" name="description_ticket"
											style="font-size:18px;">
											Inserire l'importo pagato con ticket.<br> Lasciando il
											campo vuoto, si considera l'importo del ticket pari all'intero
											scontrino.
										</div>
									</div>
								</form>
							</div>
						</div>
					</s:if>
				</div>
			</s:if>
			<s:else>
				<div class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">
					<s:if test="#session.BAR_O.carrello!=null">
						<div class="row">
							<div class="col-md-12 col-sm-12 acquisti">
								<h>Acquisto in negozio</h>
							</div>
						</div>
<!-- 						totalizzatore acquisti in negozio. -->
						<div class="row">
							<div class="col-md-12 col-sm-12" style="margin-top: 20px">
							<s:bean name="it.gds.point.utils.SortDetails" var="sortDetails" />
							<s:if test="#session.BAR_O.carrello!=null">
								<s:set var="total" value="0" />
										<s:sort comparator="#sortDetails" source="#session.BAR_O.carrello">
											<s:iterator>
												<s:set var="total" value="#total+totale" />
											</s:iterator>
										</s:sort>
										<SPAN style="font-size: 20pt;"> TOTALE: <b>&euro;&nbsp;<s:text name="format.number"><s:param value="#total" /></s:text></b></SPAN>
							</s:if>
							</div>
						</div>
<!-- fine totalizzatore	 -->
						<div class="row">
							<div class="col-md-12 col-sm-12" style="margin-top: 20px">
								<h style="font-size: 20pt;">
								<b>Tipo di pagamento</b></h>
								<form id="ptypefrm">
									<s:if test="#session.PTYPE.equals(\"CO\")">
										<div class="form-group">
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="hide();"
													name="ptype" value="CO" checked><label>Contanti</label>
											</div>
											&nbsp;
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="hide();"
													name="ptype" value="CC"><label>Carte</label>
											</div>
											&nbsp;
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="show();"
													name="ptype" value="TK"><label>Ticket</label>
											</div>
										</div>
									</s:if>
									<s:elseif test="#session.PTYPE.equals(\"CC\")">
										<div class="form-group">
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="hide();"
													name="ptype" value="CO"><label>Contanti</label>
											</div>
											&nbsp;
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="hide();"
													name="ptype" value="CC" checked><label>Carte</label>
											</div>
											&nbsp;
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="show();"
													name="ptype" value="TK"><label>Ticket</label>
											</div>
										</div>
									</s:elseif>
									<s:elseif test="#session.PTYPE.equals(\"TK\")">
										<div class="form-group">
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="hide();"
													name="ptype" value="CO"><label>Contanti</label>
											</div>
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="hide();"
													name="ptype" value="CC" checked><label>Carte</label>
											</div>
											<div class="radio-container">
												<input type="radio" class="ptype" onclick="show();"
													name="ptype" value="TK"><label>Ticket</label>
											</div>
										</div>
									</s:elseif>
									<br>
									<br>
									<br>
									<div class="ticket00">
<%-- 										<select name="ticketrest" class="combobox input-large form-control" style="display: none;"> --%>
<!-- 											<option name="ticketrest" value="" selected="selected">Seleziona il valore del buono pasto</option> -->
<!-- 											<option name="ticketrest" value="7">7,00</option> -->
<!-- 											<option name="ticketrest" value="5">5,00</option> -->
<!-- 											<option name="ticketrest" value="10">10,00</option> -->
<%-- 										</select> --%>
										<input type="text" style="width:200px;" name="ticketrest" placeholder="importo ticket"> &nbsp;
										<button class="myButton" id="clear">X</button>&nbsp;
										<button class="myButton" onclick="plus_7();">7,00</button>&nbsp;
										<button class="myButton" onclick="plus_5();">5,00</button>&nbsp;
										<div class="description_ticket" name="description_ticket"
											style="font-size:18px;">
											Inserire l'importo pagato con ticket.<br> Lasciando il
											campo vuoto, si considera l'importo del ticket pari all'intero
											scontrino.
										</div>
									</div>
							</form>
							</div>
						</div>
					</s:if>
				</div>
			</s:else>

			<div class="col-md-9 col-sm-9">
				<s:if test="#session.TOTEM_O!=null">
					<div class="row">
						<div class="col-md-12 col-sm-12 acquisti">
							<h>Acquisto al totem</h>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 col-sm-4">
							<p><b>Pagamento</b></p>
							<form id="paymentfrm">
								<s:if test="#session.TOTEM_O.contrassegno==false">
									<div class="form-group">
										<div class="radio-container">
											<input type="radio" class="payment" name="payment" value="C" checked><label>Ora</label>
										</div>
										<div class="radio-container">
											<input type="radio" class="payment" name="payment" value="P"><label>Alla consegna</label>
										</div>
									</div>
								</s:if>
								<s:elseif test="#session.TOTEM_O.contrassegno==true">
									<div class="form-group">
										<div class="radio-container">
											<input type="radio" class="payment" name="payment" value="C"><label>Ora</label>
										</div>
										<div class="radio-container">
											<input type="radio" class="payment" name="payment" value="P" checked><label>Alla consegna</label>
										</div>
									</div>
								</s:elseif>
							</form>
						</div>
						<div class="col-md-8 col-sm-8" style="background:#f4f4f4;">
							<p><b>Dati di spedizione</b></p>
							<form id="changeadfrm">
								<div class="row">
									<div class="col-md-6 col-sm-6">
										<div class="form-group">
											<input type="text" name="d_nominativo" class="form-control tessera-form tessera-form" value="<s:property value="#session.CLIENTE.d_nominativo" />">
										</div>
									</div>
									<div class="col-md-6 col-sm-6">
										<div class="form-group">
											<s:if test="#session.TOTEM_O.change_delivery==true">
												<label class="blink">CAMBIA</label>
											</s:if>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 col-sm-6">
										<div class="form-group">
											<input type="text" name="d_indirizzo" class="form-control tessera-form" value="<s:property value="#session.CLIENTE.d_indirizzo" />" placeholder="Indirizzo">
										</div>
									</div>
									<div class="col-md-6 col-sm-6">
										<div class="form-group">
											<input type="text" name="d_cap" class="form-control tessera-form" value="<s:property value="#session.CLIENTE.d_cap" />" placeholder="CAP">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 col-sm-6">
										<div class="form-group">
											<input type="text" name="d_localita" class="form-control tessera-form" value="<s:property value="#session.CLIENTE.d_localita" />" placeholder="Località">
										</div>
									</div>
									<div class="col-md-6 col-sm-6">
										<div class="form-group">
											<input type="text" name="d_provincia" class="form-control tessera-form" value="<s:property value="#session.CLIENTE.d_provincia" />" placeholder="Provincia">
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</s:if>
			</div>
		</div>
	</div>

	<br>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3 col-md-offset-3 col-sm-3 col-sm-offset-3">
				<button class="btn btn-info2 btn-custom btn-full" id="back">INDIETRO</button>
			</div>
			<div class="col-md-3 col-sm-3 ">
				<s:if test="#session.TOTEM_O.change_delivery==true">
					<button class="btn btn-success btn-custom btn-full" id="end" disabled>CONCLUDI</button>
				</s:if>
				<s:else>
					<button class="btn btn-success btn-custom btn-full" id="end">CONCLUDI</button>
				</s:else>
			</div>
		</div>
	</div>

<%-- 	<%@ include file="vkeyboard.jsp" %> --%>

	<%@ include file="modal.jsp" %>

</body>
<script type="text/javascript">
$(document).ready(
		function() {
			$("#logout").click(
					function() {
						location.href = "logout";
					});
			$(".ptype").click(
					function() {
						$.ajax({
							type: "post",
							url: "paymenttype",
							data: $("#ptypefrm").serialize(),
							success:
								function() {
							}
						});
					});
			$(".payment").click(
					function() {
						$.ajax({
							type: "post",
							url: "payment",
							data: $("#paymentfrm").serialize()
						});
					});
			$("#changeadfrm").find("input[type=text]").focusout(
					function() {
						$.ajax({
							type: "post",
							url: "changedelivery",
							data: $("#changeadfrm").serialize(),
							success:
								function(data) {
									if(data == "success") {
										$("#blink").remove();
										$("#end").removeAttr("disabled");
									} else {
										alert("Si è verificato un errore sconosciuto.");
									}
								}
						});
					});
			$("#changeadfrm").find("input[type=text]").focus(
					function() {
						$(".keyboard-container").show(0);
					});
			$("#ptypefrm").submit(
					function(e) {
						e.preventDefault();
					});
			$("#paymentfrm").submit(
					function(e) {
						e.preventDefault();
					});
			$("#changeadfrm").submit(
					function(e) {
						e.preventDefault();
					});
			$("#end").click(
					function() {
						$.ajax({
							type: "post",
							url: "paymenttype",
							data: $("#ptypefrm").serialize(),
							success:
								function() {
								location.href = "process";
							}
						});

					});
			$("#back").click(
				function() {
					location.href = "main";
				});
		});

	function plus_5() {
		if ($("input[name='ticketrest']").val() == ''){
			$("input[name='ticketrest']").val('5');
		} else {
			$("input[name='ticketrest']").val(parseInt($("input[name='ticketrest']").val()) + 5);
		}
	}

	function plus_7() {
		if ($("input[name='ticketrest']").val() == ''){
			$("input[name='ticketrest']").val('7');
		} else {
			$("input[name='ticketrest']").val(parseInt($("input[name='ticketrest']").val()) + 7);
		}
	}

   function hide() {
		$("input[name='ticketrest']").hide();
		$("input[name='ticketrest']").val('');
		$("input[name='description_ticket']").hide();
		$(".description_ticket").hide();
		$(".ticket00").hide();
	}
	function show() {
		$("input[name='ticketrest']").show();
		$("input[name='description_ticket']").show();
		$(".description_ticket").show();
		$(".ticket00").show();
	}
	$("input[name='ticketrest']").hide();
	$("input[name='ticketrest']").val('');
	$(".description_ticket").hide();
	$(".ticket00").hide();

	$("#clear").click(
		function() {
			$("input[name='ticketrest']").val('');
		});
</script>

    <script type="text/javascript">
        $(document).ready(function(){
          $('.combobox').combobox()
        });
    </script>
</html>