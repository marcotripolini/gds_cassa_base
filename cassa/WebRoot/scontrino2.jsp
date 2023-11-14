<%@ page import="it.gds.point.beans.CodiceSconto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
    <s:bean name="it.gds.point.utils.SortDetails" var="sortDetails" />
						<s:if test="#session.BAR_O.carrello!=null">
							<s:set var="total" value="0" />
							<div id="cart_container" class="table-container">
								<table class="table">
									<tr class="acquisti">
										<td colspan="5"><b>Acquisti in negozio</b></td>
									</tr>
									<s:sort comparator="#sortDetails" source="#session.BAR_O.carrello">
										<s:iterator>
											<s:set var="total" value="#total+totale" />
											<tr table-content>
												<td class="col-md-5" style="vertical-align: middle;" align="left"><s:property value="%{descrizione}" /></td>
												<td class="col-md-1" style="vertical-align: middle;" align="right">x&nbsp;<s:text name="format.integer"><s:param value="%{quantita}" /></s:text></td>
												<td class="col-md-2" style="vertical-align: middle;" align="right">&euro;&nbsp;
													<s:text name="format.number"><s:param value="%{totale}" /></s:text>
												</td>
												<td class="col-md-3" style="vertical-align: middle;" align="right">
													<form action="barmodifica.action" class="form-inline">
														<input type="hidden" name="id" id="id" value='<s:property value="%{id}"/>'>
														<input style="width:75px; font-size:16px !important;" type="text" name="valore" id="valore">
														<button type="submit" class="myButtonDel fas fa-edit icon1">
														</button>
													</form>
												</td>
												<td class="col-md-1" style="vertical-align: middle;" align="right">
													<form action="barrimuovi.action">
														<input type="hidden" name="id" id="id" value='<s:property value="%{id}"/>'>
														<button type="submit" class="myButtonDel fas fa-times-circle icon1">
														</button>
													</form>
												</td>
											</tr>
										</s:iterator>
									</s:sort>
									<tr class="totale">
										<td colspan="4" align="right"><b>TOTALE</b></td>
										<td align="right">
											<b>&euro;&nbsp;<s:text name="format.number"><s:param value="#total" /></s:text></b>
										</td>
									</tr>
								</table>
								<table class="table">
									<tr class="acquisti">
										<td colspan="5"><b>Abbonamenti</b></td>
									</tr>
									<tr class="table-content">
								      <s:iterator value="#session.CLIENTE.tessera.abbonamenti" >

										<td class="col-md-4" align="left">
											<s:property value="id" />
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
							<hr>
							</div>
						</s:if>
<script type="text/javascript">
$(document).ready(
		function() {
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
		});

</script>
