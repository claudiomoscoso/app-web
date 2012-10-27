<%@page import="cl.buildersoft.business.beans.VoucherType"%>
<%@page import="cl.buildersoft.framework.util.BSConfig"%>
<%@page import="cl.buildersoft.framework.util.BSUtils"%>
<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%@page import="cl.buildersoft.business.beans.Voucher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
List<Voucher> voucherList = (List<Voucher>)request.getAttribute("VoucherList");
List<VoucherType> voucherTypeList = (List<VoucherType> )request.getAttribute("VoucherTypeList" );
%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Comprobantes Pendientes</h1>

<!-- 
<input type="text" name="SomeObject" id="SomeObject"
onfocus="javascript:doubleFocus(this);"
onblur="javascript:doubleBlur(this);"
value="<%=BSWeb.formatDouble(request, 1234.567)%>">
 -->
<table class="cList" cellpadding="0" cellspacing="0">
	<tr>
		<td class='cHeadTD'>Sel.</td>
		<td class='cHeadTD'>Fecha Creación</td>
		<td class='cHeadTD'>Tipo</td>
		<td class='cHeadTD'>Número</td>

	</tr>
	<%
		Integer index = 0;
		String color = "cDataTD";
		for (Voucher voucher : voucherList) {
			color = index % 2 == 0 ? "cDataTD" : "cDataTD_odd";
	%>
	<tr>
		<td class='<%=color%>'><input type="radio" name="VoucherId" value="<%=voucher.getId() %>"
			onclick="javascript:document.getElementById('VoucherId').value = this.value;$('#RecordActions').show(speed);"></td>
		<td class='<%=color%>'><%=BSDateTimeUtil.dateTime2String(request, voucher.getCreationTime())%></td>
		<td class='<%=color%>'><%=getVoucherType(voucher, voucherTypeList)%></td>
		<td class='<%=color%>'><%=voucher.getNumber() == null ? "-Sin número-" : voucher.getNumber()%></td>
	</tr>
	<%
		index++;
		}
	%>
</table>

<br>
<div id='TableActions' style='float: left;'>
	<button type="button" onclick="javascript:document.getElementById('Force').value=1;sendForm('frm', '${pageContext.request.contextPath}/servlet/conta/voucher/VoucherManager?<%=BSWeb.randomString()%>');">Nuevo</button>
</div>
<div id="RecordActions" style="float: left; display: none;">
	<button type="button" onclick="javascript:sendForm('frm', '${pageContext.request.contextPath}/servlet/conta/voucher/ReadVoucher?<%=BSWeb.randomString()%>')">Retomar</button>
</div>

<form method="post" id="frm">
	<input name="Force" id="Force" type="hidden" value="1">
	<input name="VoucherId" id="VoucherId" type="hidden">
</form>

<!-- 
<a class="cCancel"
	href="${pageContext.request.contextPath}/servlet/conta/voucher/VoucherManager">Cancelar</a>

<div id="divShowDetail" style="display: none">
	<h2 class="cTitle2">Detalle de valores</h2>

	<div class="contentScroll">
		<table class="cList" cellpadding="0" cellspacing="0" id="movesTable">
			<tr>
				<td class="cHeadTD">Detalle</td>
				<td class="cHeadTD">Comentario</td>
				<td class="cHeadTD">Tipo</td>
				<td class="cHeadTD">Monto</td>
			</tr>
		</table>
	</div>
	<br /> <input type="button" value="Cancelar"
		onclick="javascript:closeTooltip()" />
</div>
 -->


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private String getVoucherType(Voucher voucher, List<VoucherType> voucherTypeList) {
		String out = "";
		for (VoucherType voucherType : voucherTypeList) {
			if (voucherType.getId().equals(voucher.getVoucherType())) {
				out = voucherType.getName();
				break;
			}
		}
		return out;
	}%>
