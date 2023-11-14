<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="head.jsp" %>
<style>
	hr.new5 {
	  border: 4px solid #333333;
	  border-radius: 0%;
	}
</style>
	<meta http-equiv="refresh" content="120"/>
</head>

<body>
	<div class="container-fluid no-padding" id="sessions">
		<s:action name="sessioniaperte" executeResult="true" />
	</div>
	<br>
	<div class="container-fluid cont-cassa">
		<div class="row">
			<div class="col-md-6 col-sm-6">
				<div class="row">
					<div class="col-md-4 col-sm-4"><button class="btn btn-info btn-custom btn-full" id="nocard">NO CARD</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-info btn-custom btn-full" id="newuser">NUOVO</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-info2 btn-custom btn-full" id="admin">ADM <i class="fas fa-tools icon1"></i></button></div>
				</div>
				<div class="row" id="buttons">
					<%@include file= "buttons.jsp" %>
				</div>
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<button class="btn btn-info2 btn-custom btn-full tav" onclick="location.reload();"> Refresh
							<i class="fas fa-sync icon1"></i>
						</button>
					</div>
				</div>
			</div>
			<div class="col-md-5 col-sm-5 col-md-offset-1 col-sm-offset-1">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<form method="post" action="login" id="loginfrm" autocomplete="off">
							<input type="text" name="tessera" id="tessera"
								class="form-control tessera-form"
								placeholder="Tessera"
								required autofocus>
						</form>
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
					<div class="col-md-4 col-sm-4"><button class="btn btn-success btn-custom btn-full" id="search_card"><i class="fas fa-search icon1"></i> Tessera</button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-success btn-custom btn-full"></button></div>
					<div class="col-md-4 col-sm-4"><button class="btn btn-success btn-custom btn-full"></button></div>
				</div>
			</div>
		</div>
		<hr class="new5">
		<div class="row">
			<div class="img-cassa">
				<img src="images/logo.png">
			</div>
		</div>
	</div>
	<%@ include file="modal.jsp" %>
</body>
<script type="text/javascript">
$(document).ready(
		function() {
			$("#tessera").keydown(
					function(e) {
						if(e.ctrlKey) {
							e.preventDefault();
						}
					});
			$(".nr").click(
					function(e) {
						e.preventDefault();
						var t = $(this);
						$("#tessera").val($("#tessera").val() + t.text());
					});
			$("#cancel").click(
					function(e) {
						e.preventDefault();
						$("#tessera").val($("#tessera").val().substring(0, $("#tessera").val().length - 1));
					});
			$("#ok").click(
					function() {
						$("#loginfrm").submit();
					});
			$("#nocard").click(
					function() {
						location.href = "nocard";
					});
			$(".tav").click(
					function() {
						var t = $(this);
						$("#tessera").val(t.text());
						$("#loginfrm").submit();
					});
			$("#admin").click(
					function() {
						$("#modal").find(".modal-content").load("admin-pwd-request.jsp",
								function() {
									$("#modal").modal("show");
							});
					});
			$("#search_card").click(
					function() {
						$("#modal").find(".modal-content").load("find_card.jsp",
								function() {
									$("#modal").modal("show");
							});
					});
			$("#newuser").click(
					function() {
						location.href = "newuser";
					});
		});

	$(document).ready(function() {
		setInterval(function() {
			// alert("");
			// $('#buttons').text('hello');
			$('#buttons').load('buttons');
		}, 15000);
	});

	$(document).ready(function() {
		setInterval(function() {
			$('#sessions').load('sessioniaperte');
		}, 60000);
	});

</script>
</html>
