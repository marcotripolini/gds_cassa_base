<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
         <span aria-hidden="true">&times;</span>
    </button>
	<h2>Tessera?</h2>
</div>
<div class="modal-body">
    <div class="container-fluid">
		<br>
		<div class="row">
			<div class="col-md-12 col-sm-12">
				<form method="post" action="switch" id="switchfrm" autocomplete="off">
					<input type="text" name="tessera" id="tessera" class="form-control" placeholder="Tessera" required autofocus>
				</form>
			</div>
		</div>
	</div>		
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-info2 btn-custom orange" data-dismiss="modal">Chiudi</button>
</div>

<script type="text/javascript">
$(document).ready(
		function() {
			$("#tessera").keydown(
					function(e) {
						if(e.ctrlKey) {
							e.preventDefault();
						} 
					});
			$("#switchfrm").submit(
					function(e) {
						e.preventDefault();
						var t = $(this);
						$.ajax({
							type: "post",
							url: "switch",
							data: t.serialize(),
							success:
								function(data) {
									if(data == "success") {
										location.reload();
									} else {
										alert("Tessera non riconosciuta.");
										$("#tessera").val("");
										$("#tessera").focus();
									}
							}
						});
					});
			$("#modal").on("shown.bs.modal",
					function() {
						$("#tessera").focus();
					});
			$("#modal").on("hidden.bs.modal",
					function() {
						$("#barcode").focus();
						$.ajax({
							type: "post",
							url: "noswitch"
						});
					});
		});
</script>