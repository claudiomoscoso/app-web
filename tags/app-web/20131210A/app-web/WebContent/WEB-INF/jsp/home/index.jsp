<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>

<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<script lang="javascript">
function onLoadPage(){
	//alert($('#time'));
//	$('#time').load(contextPath+'/servlet/ajax/GetTime');

	$.ajax({
	    url: contextPath+'/servlet/ajax/GetTime',
	    type: 'POST',
	    success: function (data, textStatus, jqXHR) {
			$('#time').text(jqXHR.responseText); //jqXHR.responseText;
		//	alert(1);
	    },
	    error: function (data, textStatus, jqXHR) { 
	    	alert(textStatus); 
	//    	alert(2);
	    }
	});
}
</script>

<h1 class="cTitle">Bienvenidos...</h1>
<div id='time' class='cLabel'>hora:</div>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
