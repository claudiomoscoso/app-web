function acceptHoliday() {
	document.getElementById("frm").submit();
}

function calculateEndDate() {
	var url = contextPath
			+ '/servlet/remuneration/events/holiday/ValidateHolidayParameters';
	var from = document.getElementById("cFrom").value;
	var normal = document.getElementById("cNormal").value;
	var creeping = document.getElementById("cCreeping").value;

	var data = {
		cFrom : from,
		cNormal : normal,
		cCreeping : creeping
	};

	$.ajax({
		url : url,
		type : "post",
		cache : false,
		data : data,
		error : error,
		success : success
	});
}

function error(jqXHR, textStatus, errorThrown) {
	document.getElementById("dateMessage").innerHTML = jqXHR.responseText;
}

function success(response) {
	document.getElementById("cTo").value = response;
}

function showCertificate(id) {
	var url = contextPath
			+ "/servlet/remuneration/events/holiday/ReadCertificate?cId=" + id;
	var left = 100;
	var top = 200;
	var width = screen.width - (left * 2);
	var height = screen.height - (top * 2);
	var prop = "dialogWidth:" + width + "px;dialogHeight:" + height
			+ "px;dialogLeft:" + left + "px;dialogTop:" + top
			+ "resizable:yes;toolbar:no;menubar:no;scrollbars:yes;help:no";

	window.showModalDialog(url, "Certificado", prop);
}