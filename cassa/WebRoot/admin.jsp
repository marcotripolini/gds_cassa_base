<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
    
<%@ include file="head.jsp" %>

</head>

<body>
	
	<br>	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12 col-sm-12">
				<h3>CONSOLE</h3>
			</div>
		</div>
	</div>
	<br>
	
	<div class="container-fluid">
		<div class="row">
		
		<div class="col-md-6 col-sm-6">
			<button class="btn btn-info btgn-custom btn-full" id="day">Chiusura giornaliera</button>
		</div>
		
		</div>
	</div>
	<br>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6 col-md-offset-6 col-sm-6 col-sm-offset-6">
				<button class="btn btn-info2 btn-custom btn-full" id="close">CHIUDI</button>
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
			$("#day").click(
					function() {
						$.ajax({
							type: "post",
							url: "operations",
							data: {cmd: "day"}
						});
					});
		});
</script>
</html>