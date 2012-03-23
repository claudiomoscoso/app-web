function add() {
	moveElement("left", "right");
	return;
	var value = $('#left option:selected').val();
	var html = $('#left option:selected').html();

	$('#right').append($('<option></option>').val(value).html(html));

	$('#left option:selected').remove();

}

function remove() {
	moveElement("right", "left");
	return;

	var value = $('#right option:selected').val();
	var html = $('#right option:selected').html();

	$('#left').append($('<option></option>').val(value).html(html));

	$('#right option:selected').remove();
}

function moveElement(source, target) {
	if ($('#' + source + ' option:selected').size() > 0) {
		var value = $('#' + source + ' option:selected').val();
		var html = $('#' + source + ' option:selected').html();

		$('#' + target).append($('<option></option>').val(value).html(html));

		$('#' + source + ' option:selected').remove();
	}
}