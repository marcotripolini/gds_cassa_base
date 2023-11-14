<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:bean name="it.gds.point.utils.SortDetails" var="sortDetails" />
					<div class="col-md-12 col-sm-12">
						<div class="table-container"  style="height:230px; ">
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
						<div style="font-size:1.3em;">
						TOTALE: <b>&euro;&nbsp;<s:text name="format.number"><s:param value="#total" /></s:text></b>
						</div>
					</div>

<script type="text/javascript">
$(document).ready(
		function() {
// 			$("#variousfrm").submit(
// 					function(e) {
// 						e.preventDefault();
// 						var t = $(this);
// 						$.ajax({
// 							type: "post",
// 							url: "baraggiungi",
// 							data: t.serialize(),
// 							success:
// 								function(data) {
// 									if (data == "success") {
// 										$('#scontrino').load("scontrino.action");
// 										// location.reload();
// 									}
// 									else
// 										alert("Si è verificato un errore sconosciuto.");
// 								}
// 						});
// 					});
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
									} else
										alert("Si è verificato un errore sconosciuto.");
								}
						});
					});
		});
</script>
