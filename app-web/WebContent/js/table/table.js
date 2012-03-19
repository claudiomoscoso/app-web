function swapCheck(obj) {
	var elements = $('input:checkbox');
	elements.each(swap);

	verifyDeleteButton();
}

function verifyDeleteButton() {
	var elementsSelected = $("input:checked");
	if (elementsSelected.size() == 1
			&& 'mainCheck' == elementsSelected.get(0).id) {
		$('#mainCheck').attr('checked', false);
		$('#deleteButton').attr('disabled', true);
	} else {
		$('#deleteButton').attr('disabled', elementsSelected.size() == 0);
	}
}

function swap(i, o) {
	var checked = $('#mainCheck').prop("checked");
	if (i > 0) {
		$(o).attr('checked', checked);
	}
}

function deleteRecords(){
	var elements = $("input:checked");
	var mainChecked = $('#mainCheck').prop("checked");
	var count = elements.size();
	
	var elementoString = elements.size() == 1 ? ' elemento' : ' elementos';
	if(confirm('¿Esta seguro de querer borrar ' + elements.size() + elementoString + '?')){
		$('#frm').submit();		
	}
	

}