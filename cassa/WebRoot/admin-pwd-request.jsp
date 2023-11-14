<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
         <span aria-hidden="true">&times;</span>
    </button>
	<h2>Password?</h2>
</div>
<div class="modal-body">
    <div class="container-fluid">
		<br>
		<div class="row">
			<div class="col-md-12 col-sm-12">
				<form method="post" action="admin" id="adminfrm" autocomplete="off">
					<input type="text" name="pwd" id="pwd" class="form-control" placeholder="Password" required autofocus>
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
			<div class="col-md-4 col-sm-4"><button class="btn btn-info2 btn-custom btn-full" id="acancel">C</button></div>
			<div class="col-md-4 col-sm-4"><button class="btn btn-success btn-custom btn-full" id="aok">V</button></div>
		</div>
	</div>		
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-info2 btn-custom orange" data-dismiss="modal">Chiudi</button>
</div>

<script type="text/javascript">
$(document).ready(
		function() {
			$("#modal").on("shown.bs.modal",
					function() {
						$("#pwd").focus();
					});
			$(".nr").click(
					function(e) {
						e.preventDefault();
						var t = $(this);
						$("#pwd").val($("#pwd").val() + t.text());
					});
			$("#acancel").click(
					function(e) {
						e.preventDefault();
						$("#pwd").val($("#pwd").val().substring(0, $("#pwd").val().length - 1));
					});
			$("#aok").click(
					function() {
						$("#adminfrm").submit();
					});
		});
</script>