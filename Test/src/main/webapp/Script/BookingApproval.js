function OpenComments() {
	$('#mdlAck').modal('show');
}

function onclickReject() {
	if ($('#idComments').val() == "") {
		alert("Comments required.");
		return;
	}
	$('#btnReject').click();
}