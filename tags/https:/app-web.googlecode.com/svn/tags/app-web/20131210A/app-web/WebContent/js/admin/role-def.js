function changeSelect(select) {
	var url = contextPath+'/servlet/admin/RoleDef?cId='
			+ $(select).val();
	//alert(url);
	self.location.href = url;
}

function save(){
	$('#frm').submit();
}