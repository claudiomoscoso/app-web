var selectedRadio = 1;
var overtimeId = 0;
var action = "";
var day = null;
var percent = null;
var amount = null;

function add(lastDayMonth, overtimePercent, periodName) {
	$("#defaultButtons").hide();
	$("#commitButtons").fadeIn(speed);

	action = "ADD";

	// $('#overtimeTable').append("<tr><td class='cDataTD'>my data</td><td
	// class='cDataTD'>more data</td></tr>");

	var row = document.getElementById('overtimeTable').insertRow(-1);

	buidInputs(row, true, lastDayMonth, overtimePercent, periodName);

	loadFormat();

}

function commit() {
	if (action == "ERASE") {
		document
				.getElementById("frm")
				.setAttribute(
						"action",
						contextPath
								+ "/servlet/remuneration/events/overtime/EraseOvertime");
		document.getElementById("cOvertime").value = overtimeId;
		document.getElementById("frm").submit();

	} else {
		var combo = document.getElementById("day");
		document.getElementById("cDay").value = combo.options[combo.selectedIndex].text;
		document.getElementById("cPercent").value = document
				.getElementById("percent").value;
		document.getElementById("cAmount").value = document
				.getElementById("amount").value;
		if (action == "ADD") {
			document
					.getElementById("frm")
					.setAttribute(
							"action",
							contextPath
									+ "/servlet/remuneration/events/overtime/AddOvertime");
		} else if (action == "EDIT") {
			document.getElementById("cOvertime").value = overtimeId;
			document
					.getElementById("frm")
					.setAttribute(
							"action",
							contextPath
									+ "/servlet/remuneration/events/overtime/UpdateOvertime");
		}
	}
	document.getElementById("frm").submit();
}

function cancel() {
	$("#commitButtons").hide();
	$("#defaultButtons").fadeIn(speed);

	if (action == "ADD") {
		document.getElementById('overtimeTable').deleteRow(-1);
	} else if (action == "EDIT") {
		document.getElementById("frm").setAttribute(
				"action",
				contextPath
						+ "/servlet/remuneration/events/overtime/OvertimeMain");
		document.getElementById("frm").submit();
	}
}

function selectRadio(index, id) {
	selectedRadio = index;
	overtimeId = id;
}

function editOvertime(lastDayMonth, overtimePercent, periodName) {
	$("#defaultButtons").hide();
	$("#commitButtons").fadeIn(speed);
	action = "EDIT";

	var row = document.getElementById('overtimeTable').rows[selectedRadio];

	day = row.cells[1].innerHTML;
	day = day.substr(0, 2);
	day = parseInt(day) - 1;

	percent = row.cells[2].innerHTML;
	amount = row.cells[3].innerHTML;

	buidInputs(row, false, lastDayMonth, overtimePercent, periodName);

	document.getElementById("day").selectedIndex = day;
	// combo.options[combo.selectedIndex].text = day;

	// document.getElementById("day").value = day;
	document.getElementById("percent").value = percent;
	document.getElementById("amount").value = amount;

	loadFormat();

}

function buidInputs(row, isNew, lastDayMonth, overtimePercent, periodName) {
	var cell = isNew ? row.insertCell(-1) : row.cells[0];
	cell.setAttribute("class", "cDataTD");
	cell.innerHTML = "&nbsp;-&nbsp;";

	var cell = isNew ? row.insertCell(-1) : row.cells[1];
	cell.setAttribute("class", "cDataTD");
	var html = "<select id='day'>";
	for ( var i = 1; i <= lastDayMonth; i++) {
		html += "<option value='" + i + "'>" + i + "</option>";
	}
	html += "</select> de " + periodName + "";
	cell.innerHTML = html;

	var cell = isNew ? row.insertCell(-1) : row.cells[2];
	cell.setAttribute("class", "cDataTD");
	cell.innerHTML = "<input type='text' id='percent' value='"
			+ overtimePercent + "'>";

	var cell = isNew ? row.insertCell(-1) : row.cells[3];
	cell.setAttribute("class", "cDataTD");
	cell.innerHTML = "<input type='text' id='amount' value='1'>";

}

function eraseOvertime() {
	action = "ERASE";
	if (confirm('Esta seguro de borrar esta asignacion de horas extras?')) {
		commit();
	}
}