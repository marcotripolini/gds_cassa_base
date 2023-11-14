<%@ page import="it.gds.point.beans.CodiceSconto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="head.jsp" %>
<style>
#indicator{
  position: relative;
  top:0;
  left:0;
  height:6px;
  background-color: green;
  z-index: 999;
}

input[type=checkbox]
{
  /* Double-sized Checkboxes */
  -ms-transform: scale(2.5); /* IE */
  -moz-transform: scale(2.5); /* FF */
  -webkit-transform: scale(2.5); /* Safari and Chrome */
  -o-transform: scale(2.5); /* Opera */
  transform: scale(2.5);
  padding: 2px;
}

/* Might want to wrap a span around your checkbox text */
.checkboxtext
{
  /* Checkbox text */
  font-size: 110%;
  display: inline;
}</style>
</head>
<body>

    <s:bean name="it.gds.point.utils.SortDetails" var="sortDetails" />

	<button class="myButtonClose" style="font-size:1.3em !important; margin-left:5px; margin-top:5px;" id="logout">
		<i class="fas fa-skull icon1"></i>&nbsp;
	</button>

	<div class="container-fluid no-padding">
		<s:action name="sessioniaperte" executeResult="true" />
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4 col-sm-4">
				<h4>
					<s:property value="#session.CLIENTE.nome" /> <s:property value="#session.CLIENTE.cognome" />
					<u><s:property value="#session.SCONTO.codice_sconto" /></u>
				</h4>
			</div>
			<div class="col-md-2 col-sm-2">
				<button
					type="button"
					id="scorpora"
					class="myButtonDel"
					href="scorpora"
					style="margin:0px">Scorpora
				</button>

				<button
					type="button"
					id="nuovoordine"
					class="myButtonDel"
					href="scorpora"
					style="margin:0px">N.D.S.
				</button>

				</a>
			</div>

			<div class="col-md-3 col-sm-3">
				<h3>Tessera n. <s:property value="#session.CLIENTE.tessera.tessera" /></h3>
			</div>

			<div class="col-md-3 col-sm-3">
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
				  Sposta al tavolo...
				</button>
			</div>

			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">Sposta al tavolo</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <form action="change_table.action">
			      <div class="modal-body">
					<h3>
						Ordine:
						<s:property value="#session.BAR_O.id" /><br>
					</h3>
					<h3>
						<label for="table">Scegli un tavolo:</label>
						<select name="table" id="table">
						  <option value="001">Tavolo 1</option>
						  <option value="002">Tavolo 2</option>
						  <option value="003">Tavolo 3</option>
						  <option value="004">Tavolo 4</option>
						  <option value="005">Tavolo 5</option>
						  <option value="006">Tavolo 6</option>
						  <option value="007">Tavolo 7</option>
						  <option value="008">Tavolo 8</option>
						  <option value="009">Tavolo 9</option>
						  <option value="010">Tavolo 10</option>
						  <option value="011">Tavolo 11</option>
						  <option value="012">Tavolo 12</option>
						  <option value="013">Tavolo 13</option>
						  <option value="015">Tavolo 14</option>
						  <option value="015">Tavolo 15</option>
						  <option value="016">Tavolo 16</option>
						  <option value="017">Tavolo 17</option>
						  <option value="018">Tavolo 18</option>
						  <option value="019">Tavolo 19</option>
						  <option value="020">Tavolo 20</option>
						  <option value="021">Tavolo 21</option>
						  <option value="022">Tavolo 22</option>
						  <option value="023">Tavolo 23</option>
						  <option value="024">Tavolo 24</option>
						  <option value="025">Tavolo 25</option>
						  <option value="026">Tavolo 26</option>
						  <option value="027">Tavolo 27</option>
						  <option value="028">Tavolo 28</option>
						  <option value="029">Tavolo 29</option>
						  <option value="030">Tavolo 30</option>
						  <option value="031">Tavolo 31</option>
						  <option value="032">Tavolo 32</option>
						  <option value="033">Tavolo 33</option>
						  <option value="034">Tavolo 34</option>
						  <option value="035">Tavolo 35</option>
						  <option value="036">Tavolo 36</option>
						</select>
						<input type="hidden" id="order_id" name="order_id"
							value='<s:property value="#session.BAR_O.id" />'>
					</h3>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
			        <button type="submit" class="btn btn-primary">Salva</button>
			      </div>
			      </form>
			    </div>
			  </div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6 col-sm-6">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<s:if test="#session.BAR_O.carrello!=null">
							<s:set var="total" value="0" />
							<div id="indicator"></div>
							<div id="cart_container" class="table-container" >
								<table class="table" id="tabella">
									<tr class="acquisti">
										<td colspan="6"><b>Acquisti in negozio</b></td>
									</tr>
									<s:sort comparator="#sortDetails" source="#session.BAR_O.carrello">
										<s:iterator>
											<s:set var="total" value="#total+totale" />
											<s:if test="%{codice_gds != 'BISVG000000000'}">
											<tr table-content>
												<td class="col-md-3" style="vertical-align: middle; padding:1px" align="left"><s:property value="%{descrizione}" /></td>
												<td class="col-md-1" style="vertical-align: middle; padding:1px" align="right">x&nbsp;
													<s:text name="format.integer">
														<s:param value="%{quantita}" />
													</s:text>
												</td>
												<td class="col-md-2" style="vertical-align: middle; padding:1px" align="right">&euro;
													<s:text name="format.number">
														<s:param value="%{totale}" />
													</s:text>
												</td>
												<td class="col-md-3" style="vertical-align: middle; padding:1px" align="center">
													<form action="barmodifica.action" class="form-inline">
														<input type="hidden" name="id" id="id" value='<s:property value="%{id}"/>'>
														<input style="width:70px; font-size:16px !important;" type="text" name="valore" id="valore">
														<button type="submit" class="myButtonDel fas fa-edit icon1">
														</button>
													</form>
												</td>
												<td class="col-md-1" style="vertical-align: middle; padding:1px" align="center">
													  <input name="s_item" type="checkbox" data-id='<s:property value="%{id}"/>'>
												</td>
												<td class="col-md-1" style="vertical-align: middle; padding:1px" align="center">
													<form action="barrimuovi.action">
														<input type="hidden" name="id" id="id" value='<s:property value="%{id}"/>'>
														<button type="submit" class="myButtonDel fas fa-times-circle icon1">
														</button>
													</form>
												</td>
											</tr>
											</s:if>

										</s:iterator>
									</s:sort>
									<tr class="totale">
										<td colspan="5" align="right"><b>TOTALE</b></td>
										<td align="right">
											<b>&euro;&nbsp;<s:text name="format.number"><s:param value="#total" /></s:text></b>
										</td>
									</tr>
								</table>
								<s:if test="#session.CLIENTE.tessera.abbonamenti.size > 0" >
									<table class="table" id="tabella2">
										<tr class="acquisti">
											<td colspan="5"><b>Abbonamenti</b></td>
										</tr>
										<tr class="table-content">
									      <s:iterator value="#session.CLIENTE.tessera.abbonamenti" >

											<td class="col-md-4" align="left">
												<%-- <s:property value="id" /> --%>
												<s:property value="descrizione" />
								 			</td>

											<td class="col-md-4" align="left">
												<s:property value="contatore" />
											</td>

											<td class="col-md-4" align="center">
												<button name="abbonamento"
														id="abbonamento"
														data-codicegds="<s:property value='codice_gds'/>"
														data-idabbonamento="<s:property value='id'/>"
													class="myButtonPlus .abbonamento">
													<span class= "fas fa-minus icon1"> </span>
													scarica
												</button>
											</td>
									      </s:iterator>
										</tr>
									</table>
								</s:if>
							<hr>
							</div>
						</s:if>
						<br>
						<s:if test="#session.TOTEM_O.carrello!=null">
							<s:set var="total" value="0" />
							<div class="table-container">
								<table class="table">
									<tr class="acquisti">
										<td colspan="4"><b>Acquisti al totem</b></td>
									</tr>
									<s:sort comparator="#sortDetails" source="#session.TOTEM_O.carrello">
										<s:iterator>
											<s:set var="total" value="#total+totale" />
											<tr class="table-content">
												<td align="left"><s:property value="%{descrizione}" /></td>
												<td align="right">x&nbsp;<s:text name="format.integer"><s:param value="%{quantita}" /></s:text></td>
												<td align="right">&nbsp;&euro;&nbsp;<s:text name="format.number"><s:param value="%{totale}" /></s:text></td>
											</tr>
										</s:iterator>
									</s:sort>
									<tr><td>&nbsp;</td></tr>
									<tr class="totale">
										<td colspan="3" align="right"><b>TOTALE</b></td>
										<td align="right">
											<b>&euro;&nbsp;<s:text name="format.number"><s:param value="#total" /></s:text></b>
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
						<form method="post" action="scan" id="scanfrm" autocomplete="off">
							<input type="text" name="barcode" id="barcode" class="form-control tessera-form" required autofocus>
						</form>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="form-group">
						<form method="post" action="getcard" id="loginfrm" autocomplete="off">
				           <div class="col-sm-8 col-md-8">
							<input type="text" name="tessera" id="tessera"
								class="form-control tessera-form"
								placeholder="Tessera">
						   </div>
				           <div class="col-sm-1 col-md-1">
								<button class="btn btn-success" type="submit" value="Submit" style = "height:48px">Ok</button>
				           </div>
						</form>
						<div class="col-md-3 col-sm-3">
							<button class="btn btn-success" id="search_card" style = "height:48px"><i class="fas fa-search icon1"></i> Card</button>
						</div>
				    </div>

				</div>
				<br>
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


	<%@ include file="modal.jsp" %>

	<s:if test="#session.CLIENTE.tessera.tessera.contains(\"TAV\") && #session.NOSWITCH!=true">
	<script type="text/javascript">
	$(document).ready(
			function() {
				$("#modal").find(".modal-content").load("switch-request.jsp",
						function() {
							$("#modal").modal("show");
				});
			});
	</script>
	</s:if>

</body>
<script type="text/javascript">
$(document).ready(
		function() {
			$("#logout").click(
					function() {
						location.href = "logout";
					});
			$("#abbonamento").click(
					function(e) {
						var t = $(this);
						var codice_gds = (t.attr('data-codicegds'));
						var id_abbonamento = (t.attr('data-idabbonamento'));
						$.ajax({url: "abbonamento_scala1?codice_gds=" + codice_gds + "&id_abbonamento=" + id_abbonamento, success: function(result){
//     						location.reload();
    						$('#cart_container').load("scontrino2.action");
  						}});
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
						if($("#barcode").val().length > 7) {
							$.ajax ({
								type: "post",
								url: "scan",
								data: $(this).serialize(),
								success:
									function(data) {
										if(data == "success") {
											location.reload();
										} else {
											alert("Errore barcode.");
											$("#barcode").val("");
											$("#barcode").focus();
										}
								}
							});
						}
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
			$("#search_card").click(
					function() {
						$("#modal").find(".modal-content").load("find_card.jsp",
								function() {
									$("#modal").modal("show");
							});
					});
			$("#nuovoordine").click( function() {
				var ids = "";
				var values = $("[name='s_item']").filter('input:checked').each(function(){
					// alert ($(this).attr( "data-id"));
					ids = ids + ($(this).attr("data-id")) + ",";
				});
				ids = ids.slice(0, -1);
				alert(ids);
				window.open("nocardwi.action?items=" + ids ,"_self");
			});

			$("#scorpora").click( function() {
				window.open("scorpora.action","_self");
			});

			$(".table-container").animate({ scrollTop: $(this).height() }, 0);

		});

$('#cart_container').on('scroll', function () {
  var scrollPos = $('#cart_container').scrollTop()
  var winHeight = $('#cart_container').height()
  var docHeight = $(document).height()
  var perc = 100 * scrollPos / (docHeight - winHeight)
  $('#indicator').width(perc + '%')
})

</script>
</html>