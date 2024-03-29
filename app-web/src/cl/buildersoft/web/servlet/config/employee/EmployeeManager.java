package cl.buildersoft.web.servlet.config.employee;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.lib.util.crud.BSAction;
import cl.buildersoft.lib.util.crud.BSActionType;
import cl.buildersoft.lib.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.BSHttpServlet;
import cl.buildersoft.web.servlet.common.crud.BSHttpServletCRUD;

/**
 * Servlet implementation class EmployeeManager
 */
@WebServlet("/servlet/config/employee/EmployeeManager")
public class EmployeeManager extends BSHttpServletCRUD {
	private static final long serialVersionUID = -2218422182993347540L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = initTable(request, "tEmployee");
		table.setTitle("Listado de empleados");
		table.setDeleteSP("pDelEmployee");

		table.getField("cLastName1").setLabel("A. Paterno");
		table.getField("cLastName2").setLabel("A. Materno");
		table.getField("cName").setLabel("Nombre");
		table.getField("cBirthDate").setLabel("Nacimiento");
		table.getField("cAddress").setLabel("Dirección");
		table.getField("cGenere").setLabel("Género");
		table.getField("cCountry").setLabel("Nacionalidad");
		table.getField("cMaritalStatus").setLabel("Estado Civil");

		BSAction informationPrevitional = new BSAction("PREVITIONAL", BSActionType.Record);
		informationPrevitional.setLabel("Información Previsional");
		informationPrevitional.setUrl("/servlet/config/employee/InformationPrevitional");
		table.addAction(informationPrevitional);

		table.getAction("EDIT").setLabel("Informacion Personal");

		BSAction contractualInfo = new BSAction("CONTRACTUAL", BSActionType.Record);
		contractualInfo.setLabel("Información Contractual");
		contractualInfo.setUrl("/servlet/config/employee/ContractualInfo");
		table.addAction(contractualInfo);

		BSAction payMode = new BSAction("PAY_MODE", BSActionType.Record);
		payMode.setLabel("Forma de Pago");
		payMode.setUrl("/servlet/config/employee/PayMode");
		table.addAction(payMode);

		BSAction document = new BSAction("DOCUMENTS", BSActionType.Record);
		document.setLabel("Documentos");
		document.setUrl("/servlet/config/employee/DocumentEmployee");
		document.setMethod("listDocuments");
		table.addAction(document);

		return table;
	}
}
