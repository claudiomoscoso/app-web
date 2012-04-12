package cl.buildersoft.web.servlet.admin;

import java.sql.Connection;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSAction;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.type.BSActionType;
import cl.buildersoft.web.servlet.BSHttpServlet;

@WebServlet("/servlet/admin/PersonManager")
public class PersonManager extends BSHttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	public PersonManager() {
		super();
	}

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = new BSTableConfig("remu", "tPerson");

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(),
				"remu");
		table.configFields(conn, mysql);

		table.setSortField("cNombre");
		table.setTitle("Mantenedor de Personas");

		// BSField field;

		table.getField("cFechaRegistro").setLabel("Incorporación");
		table.getField("cApellidoPaterno").setLabel("A. Paterno");
		table.getField("cApellidoMaterno").setLabel("A. Materno");

		/**
		table.getField("cComuna").setFK("bscommon", "tComuna", "cName");
		table.getField("cSexo").setFK("bscommon", "vSex", "cName");
*/
		String[] noVisibleFields = { "cNumero", "cDireccion", "cDepartamento",
				"cVilla", "cBlock", "cMail" };
		for (String fieldName : noVisibleFields) {
			table.getField(fieldName).setVisible(false);
		}

		BSAction uploadFile = new BSAction("UPLOAD_PERSON", BSActionType.Table);
		uploadFile.setLabel("Carga por archivo");
		uploadFile.setUrl("/servlet/csv/UploadFile");
		table.addAction(uploadFile);

		BSAction download = new BSAction("DOWNLOAD_PERSON", BSActionType.Table);
		download.setLabel("Descargar como CSV");
		download.setUrl("/servlet/admin/PersonCSV");
		table.addAction(download);

		BSAction viewFKdetails = new BSAction("FK_DETAIL", BSActionType.Table);
		viewFKdetails.setLabel("Ver detalle de tablas secundarias");
		viewFKdetails.setUrl("/servlet/admin/PersonFK");
		table.addAction(viewFKdetails);

		return table;
	}

}
