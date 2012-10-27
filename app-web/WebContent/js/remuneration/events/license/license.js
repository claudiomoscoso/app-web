function changeDay() {
	var from = document.getElementById("cFrom").selectedIndex;
	var to = document.getElementById("cTo").selectedIndex;

	// alert("from " + from + "\n to " + to);

	if (from >= to) {
		document.getElementById("cTo").selectedIndex = from + 1;
		to = document.getElementById("cTo").selectedIndex;
	}
	var diff = to - from;
	document.getElementById("days").innerHTML = diff;

}

function changeCause() {
	var index = document.getElementById("cCause").selectedIndex;
	var value = document.getElementById("cCause").options[index].value;
	var position = value.indexOf("#");
	var cause = "";

	if (position > -1) {
		cause = value.substring(position + 1);
	}
	// alert(cause + " " + (cause != null));
	if (cause != "") {
		$("#RetrieveReason").fadeIn(speed);
		$("#RetrieveFile").fadeIn(speed);
	} else {
		$("#RetrieveReason").fadeOut(speed);
		$("#RetrieveFile").fadeOut(speed);
	}
}

function showForm(obj) {
	showTooltip('divShowDetail');
	loadFormat();
	changeDay();
	changeCause();
	// alert(document.getElementById("From").selectedIndex);
}

function eraseLicense(id) {
	if (confirm('¿Estas seguro de querer borrar esta licencia?')) {
		var selected = $('input[name=LicenseRadio]:checked').val();
		document.getElementById("cLicense").value = selected;
		$("#EraseLicense").submit();
	}
}