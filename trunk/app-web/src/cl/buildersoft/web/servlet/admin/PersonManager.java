package cl.buildersoft.web.servlet.admin;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;

import cl.buildersoft.framework.beans.BSAction;
import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSHeadConfig;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.type.BSActionType;
import cl.buildersoft.web.servlet.BSHttpServlet;

@WebServlet("/servlet/admin/PersonManager")
public class PersonManager extends BSHttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	public PersonManager() {
		super();
	}

	@Override
	protected BSTableConfig getBSTableConfig() {
		BSTableConfig table = new BSTableConfig("remu", "tPerson");
		BSField field;

		table.setSortField("cNombre");

		table.setTitle("Mantenedor de Personas");

		field = new BSField("cId", "Código");
		table.addField(field);

		field = new BSField("cRUT", "Rut");
		table.addField(field);

		field = new BSField("cSexo", "Sexo");
		field.setFK("bscommon", "vSex", "cName");
		table.addField(field);

		field = new BSField("cFechaRegistro", "Fecha Ingreso");
		table.addField(field);

		field = new BSField("cNombre", "Nombre");
		table.addField(field);

		field = new BSField("cApellidoPaterno", "A. Paterno");
		table.addField(field);

		field = new BSField("cApellidoMaterno", "A. Materno");
		table.addField(field);

		field = new BSField("cDireccion", "Dirección");
		field.setVisible(false);
		table.addField(field);

		field = new BSField("cNumero", "Número");
		field.setVisible(false);
		table.addField(field);

		field = new BSField("cVilla", "Villa");
		field.setVisible(false);
		table.addField(field);

		field = new BSField("cBlock", "Block");
		table.addField(field);

		field = new BSField("cDepartamento", "Departamento");
		field.setVisible(false);
		table.addField(field);

		field = new BSField("cComuna", "Comuna");
		field.setFK("bscommon", "tComuna", "cName");
		table.addField(field);

		field = new BSField("cTelefono", "Teléfono");
		table.addField(field);

		field = new BSField("cCelular", "Celular");
		table.addField(field);

		field = new BSField("cMail", "Mail");
		field.setVisible(false);
		table.addField(field);

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

	@Override
	protected BSHeadConfig getBSHeadConfig() {
		return null;
	}

}
