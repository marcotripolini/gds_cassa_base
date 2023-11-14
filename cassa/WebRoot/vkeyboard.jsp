<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link type="text/css" rel="stylesheet" href="css/jsKeyboard.css">
<script type="text/javascript" src="js/jsKeyboard.js"></script>

<div class="keyboard-container">
	<div class="close-keyboard">X</div>
	<div id="virtualKeyboard"></div>
</div>

<script>
$(function () {
    jsKeyboard.init("virtualKeyboard");

    //first input focus
    var $firstInput = $(':input').first().focus();
    jsKeyboard.currentElement = $firstInput;
    jsKeyboard.currentElementCursorPosition = 0;
});

$(document).ready(
		function() {
			$(".close-keyboard").click(
					function() {
						$(".keyboard-container").hide(0);
					});
		});
</script>
