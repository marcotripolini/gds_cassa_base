<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="head.jsp" %>

</head>

<body>

<!-- 	<button class="btn btn-danger" style="font-size:2em !important;" id="logout">&nbsp; -->
<!-- 		<i class="fas fa-times-circle icon1"></i>&nbsp; -->
<!-- 	</button> -->

	<button class="myButtonClose" style="font-size:1.3em !important; margin-left:5px; margin-top:5px;" id="logout">
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
					<h3>Tessera n. <s:property value="#session.CLIENTE.tessera.tessera" /></h3>
				</div>
			</div>
		</div>

		<div class="row">
<!-- 			<div class="col-md-12 col-sm-12"> -->
<!-- 				<form method="post" action="cerca" id="searchfrm" autocomplete="off"> -->
<!-- 					<input type="text" name="cerca" id="cerca" class="form-control tessera-form" placeholder="Cerca..." required autofocus> -->
<!-- 				</form> -->
<!-- 			</div> -->
		</div>

		<hr>

		<div class="row">
			<div class="col-md-12 col-sm-12">
				<div class="row">
					<div class="col-md-3 col-sm-3">
						<button class="btn btn-info2 btn-custom btn-full" id="back">INDIETRO</button>
					</div>
					<s:iterator value="cl">
						<div class="col-md-3 col-sm-3">
							<button class="btn btn-info btn-custom btn-full cat" value="<s:property value="%{categoria_gds}" />"><s:property value="%{categoria_2}" /></button>
						</div>
					</s:iterator>
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
						location.href = "index";
					});
			$(".cat").click(
					function() {
						location.href = "barprodotti?categoria_gds=" + $(this).attr("value");
					});
		});
</script>
</html>