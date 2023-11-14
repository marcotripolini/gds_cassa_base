<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="head.jsp" %>

<style>
	/* width */
	::-webkit-scrollbar {
	  width: 20px;
	}

	/* Track */
	::-webkit-scrollbar-track {
	  box-shadow: inset 0 0 5px grey;
	  border-radius: 10px;
	}

	/* Handle */
	::-webkit-scrollbar-thumb {
	  background: red;
	  border-radius: 10px;
	}

	/* Handle on hover */
	::-webkit-scrollbar-thumb:hover {
	  background: #b30000;
	}
</style>

</head>

<body>

    <s:bean name="it.gds.point.utils.SortDetails" var="sortDetails" />
<!-- 	<button class="btn btn-danger2" style="font-size:2em !important;" id="logout"><i class="fas fa-times-circle icon1"></i>&nbsp;</button> -->

	<button class="myButtonClose"
		style="font-size:1.3em !important; margin-left:5px; margin-top:5px;" id="logout">
		<i class="fas fa-times-circle icon1"></i>&nbsp;
	</button>

	<div class="container-fluid no-padding">
		<s:action name="sessioniaperte" executeResult="true" />
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12 col-sm-12">
				<div class="col-md-6 col-sm-6">
					<h3>
						<s:property value="#session.CLIENTE.nome" /> <s:property value="#session.CLIENTE.cognome" />
						<u><s:property value="#session.SCONTO.codice_sconto" /></u>
					</h3>
				</div>
				<div class="col-md-6 col-sm-6">
					<h3>
						Tessera n. <s:property value="#session.CLIENTE.tessera.tessera" />
						(livello: <s:property value="#session.CLIENTE.tessera.livello_listino" />)
					</h3>
				</div>
			</div>
		</div>

		<div class="row" style="padding:1px;">
			<div class="col-md-6 col-sm-6"
				style="padding:16px; max-height: 630px; overflow-y:scroll; background: rgba(64, 64, 64, 0.1);  border-radius: 8px; border: 4px solid #73AD21;" >
			<div class="col-md-12 col-sm-12">
			 <form class="form-inline">
			  <div class="form-group" >
			    <input type="text" placeholder="Cerca..." class="form-control col-sm-10" id="cerca" name="cerca" style="width:100%" >
			  </div>
			  <div class="btn btn-primary btn-lg" id="clear"><i class="fas fa-times-circle icon1"></i></div>
			</form>

<!-- 				<input type="text" name="cerca" id="cerca" -->
<!-- 				class="col-xs-10" -->
<!-- 				placeholder="Cerca..." -->
<!-- 				autofocus style="height:100% !important"> -->
<!-- 				<div class="col-xs-2 btn btn-primary btn-lg input" id="clear"><i class="fas fa-times-circle icon1"></i></div> -->
<!-- 				<hr> -->
			</div>
				<div class="row" style=" ">
					<s:iterator value="pl">
						<div class="product col-md-4 col-sm-4">
							<button class="btn btn-info btn-custom btn-full prod" style="height:110px;"  value="<s:property value="%{codice_gds}" />">
								<div class="title" ><s:property value="%{titolo}" /></div>
								<div>&euro;&nbsp;
									<s:if test="#session.CLIENTE.tessera.tessera.equals('NOCARD') || #session.CLIENTE.tessera.tessera.contains('TAV')">
										<s:text name="format.number"><s:param value="%{p_alpubblico_0}" /></s:text>
									</s:if>
									<s:elseif test="#session.CLIENTE.tessera.livello_listino == 0">
										<del>
											<s:text name="format.number"><s:param value="%{p_alpubblico_0}" /></s:text>
										</del>
										&nbsp;
										<s:text name="format.number"><s:param value="%{p_alpubblico_1}" /></s:text>
									</s:elseif>
									<s:elseif test="#session.CLIENTE.tessera.livello_listino == 1">
										<del>
											<s:text name="format.number"><s:param value="%{p_alpubblico_0}" /></s:text>
										</del>
										&nbsp;
										<s:text name="format.number"><s:param value="%{p_alpubblico_2}" /></s:text>
									</s:elseif>
									<s:elseif test="#session.CLIENTE.tessera.livello_listino == 2">
										<del>
											<s:text name="format.number"><s:param value="%{p_alpubblico_0}" /></s:text>
										</del>
										&nbsp;
										<s:text name="format.number"><s:param value="%{p_alpubblico_3}" /></s:text>
									</s:elseif>
									<s:elseif test="#session.CLIENTE.tessera.livello_listino == 3">
										<del>
											<s:text name="format.number"><s:param value="%{p_alpubblico_0}" /></s:text>
										</del>
										&nbsp;
										<s:text name="format.number"><s:param value="%{p_alpubblico_4}" /></s:text>
									</s:elseif>
									<s:elseif test="#session.CLIENTE.tessera.livello_listino == 4">
										<del>
											<s:text name="format.number"><s:param value="%{p_alpubblico_0}" /></s:text>
										</del>
										&nbsp;
										<s:text name="format.number"><s:param value="%{p_alpubblico_5}" /></s:text>
									</s:elseif>
								</div>
							</button>
						</div>
					</s:iterator>
				</div>
				<a href="barcategorie" class="btn btn-primary btn-custom btn-full" style="height:60px; font-size:16px;  border-radius: 50px;"  value="<s:property value="%{codice_gds}" />">
					BAR
				</a>
			</div>
			<div class="col-md-6 col-sm-6 ">
				<div class="row" id="scontrino" >
					<div class="col-md-12 col-sm-12">
						<div class="table-container"  style="height:230px;">
						  <div id="progress"></div>

							<s:if test="#session.BAR_O.carrello!=null">
								<s:set var="total" value="0" />
								<table class="table">
									<s:sort comparator="#sortDetails" source="#session.BAR_O.carrello">
										<s:iterator>
											<s:if test="%{reparto.equals(\"1\")}">
												<s:set var="total" value="#total+totale" />
												<tr>
													<td style="vertical-align: middle;" align="left"><s:property value="%{descrizione}" /></td>
													<td style="vertical-align: middle;" align="right">x&nbsp;<s:text name="format.integer"><s:param value="%{quantita}" /></s:text></td>
													<td style="vertical-align: middle;" align="center">&nbsp;</td>
													<td style="vertical-align: middle;" align="right">&nbsp;&euro;&nbsp;<s:text name="format.number"><s:param value="%{totale}" /></s:text></td>
													<td style="vertical-align: middle;" class="col-md-1">
														<form id="barrimuovi">
															<input type="hidden" name="id" id="id" value='<s:property value="%{id}"/>'>
															<button type="submit" class="myButtonDel fas fa-times-circle icon1">
															</button>
														</form>
													</td>
												</tr>
											</s:if>
										</s:iterator>
									</s:sort>
								</table>
							</s:if>
						</div>
						<div style="font-size:1.5em;">
						TOTALE: <b>&euro;&nbsp;<s:text name="format.number"><s:param value="#total" /></s:text></b>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12 col-sm-12">
						<form id="variousfrm" autocomplete="off">
							<input type="hidden" name="codice_gds" value="DUMMY_BAR">
							<input type="text" name="prezzo" id="various" class="form-control" required readonly>
						</form>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-info btn-custom btn-full nr">7</button>
					</div>
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-info btn-custom btn-full nr">8</button>
					</div>
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-info btn-custom btn-full nr">9</button>
					</div>
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-info btn-custom btn-full nr">4</button>
					</div>
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-info btn-custom btn-full nr">5</button>
					</div>
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-info btn-custom btn-full nr">6</button>
					</div>
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-info btn-custom btn-full nr">1</button>
					</div>
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-info btn-custom btn-full nr">2</button>
					</div>
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-info btn-custom btn-full nr">3</button>
					</div>
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-info btn-custom btn-full nr">0</button>
					</div>
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-info2 btn-custom btn-full" id="cancel">
							<i class="fas fa-ban icon1"></i>
						</button>
					</div>
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-success btn-custom btn-full" id="ok">
							<i class="fas fa-check icon1"></i>
						</button>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-sm-4"><button class="btn btn-info btn-custom btn-full" id="back">INDIETRO</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-info2 btn-custom btn-full" id="return">RITORNA</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-success btn-custom btn-full" id="continue">CONTINUA</button></div>
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
			$("#back").click(
					function() {
						window.history.back();
					});
			$("#return").click(
					function() {
						location.href = "main";
					});
			$("#continue").click(
					function() {
						location.href = "main";
					});
			$(".prod").click(
					function() {
						var t = $(this);
						$('#scontrino').html("<div style='height:256px;'><img style='margin-left:256px; margin-top: 80px' width='96px' height='96px' src='images/spinner.gif' style='position: relative; margin: auto; '></img></div>");
						$.ajax({
							type: "post",
							url: "baraggiungi",
							data: {codice_gds : t.attr("value")},
							success:
								function(data) {
									if(data == "success"){
										$('#scontrino').load("scontrino.action");
									} else {
										alert("Si è verificato un errore sconosciuto.");
									}
								}
						});
					});
			$(".nr").click(
					function(e) {
						e.preventDefault();
						var t = $(this);
						var v = $("#various").val().replace(",", "");
						v = v + t.text();
						if(v.length > 2) {
							var dec = v.slice(-2);
							var interi = v.replace(dec, "");
							v = interi + "," + dec;
						}
						$("#various").val(v);
					});
			$("#cancel").click(
					function(e) {
						e.preventDefault();
						var v = $("#various").val().replace(",", "");
						v = v.substring(0, v.length - 1);
						if(v.length > 2) {
							var dec = v.slice(-2);
							var interi = v.replace(dec, "");
							v = interi + "," + dec;
						}
						$("#various").val(v);
					});
			$("#ok").click(
					function() {
						$("#variousfrm").submit();
					});
			$("#variousfrm").submit(
					function(e) {
						e.preventDefault();
						var t = $(this);
						$('#scontrino').html("<div style='height:256px;'><img style='margin-left:256px; margin-top: 80px' width='96px' height='96px' src='images/spinner.gif' style='position: relative; margin: auto; '></img></div>");
						$.ajax({
							type: "post",
							url: "baraggiungi",
							data: t.serialize(),
							success:
								function(data) {
									if (data == "success") {
// 										$('#scontrino').html("<div style='height:256px;'><img style='position: absolute; margin: auto; top: 0; left: 0; right: 0; bottom: 0;' width='96px' height='96px' src='images/spinner.gif' style='position: relative; margin: auto; '></img></div>");
										$('#scontrino').load("scontrino.action");
									}
									else
										alert("Si è verificato un errore sconosciuto.");
								}
						});
					});


			$("#barrimuovi").submit(
					function(e) {
						e.preventDefault();
						var t = $(this);
						$('#scontrino').html("<div style='height:256px;'><img style='margin-left:256px; margin-top: 80px' width='96px' height='96px' src='images/spinner.gif' style='position: relative; margin: auto; '></img></div>");
						$.ajax({
							type: "post",
							url: "barrimuovi1",
							data: t.serialize(),
							success:
								function(data) {
									if (data == "success") {
										$('#scontrino').load("scontrino.action");
									}
									else
										alert("Si è verificato un errore sconosciuto.");
								}
						});
					});
			$(".table-container").animate({ scrollTop: $(this).height() }, 0);
		});

	$("#cerca").on("keyup", function() {
		var search = $(this).val().trim().toLowerCase();
		// alert (search);
		var products = $(".product");
		const copy = [];

		for (let i = 0; i < products.length; i++) {
			// alert(products[i].innerText);
			if (products[i].innerText.toLowerCase().includes(search)) {
				products[i].style.visibility = 'initial';
				products[i].style.visibility = 'visible';
				products[i].style.display = '';
			} else {
				products[i].style.visibility = 'hidden';
				products[i].style.display = 'none';
			}
		}
	});

	$("#clear").click(
		function() {
		var products = $(".product");
		for (let i = 0; i < products.length; i++) {
			products[i].style.display = '';
			products[i].style.visibility = 'initial';
			products[i].style.visibility = 'visible';
		}
		$("#cerca").val("");
	});

</script>
</html>