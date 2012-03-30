package cl.buildersoft.web.servlet.admin;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSHeadConfig;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.BSHttpServlet;

@WebServlet("/servlet/admin/PersonManager")
public class PersonManager extends BSHttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	public PersonManager() {
		super();
	}

	@Override
	protected BSTableConfig getBSTableConfig() {
		BSTableConfig table = new BSTableConfig("tPerson");
		BSField field;
		
		table.setSortField("cNombre");
		
		table.setTitle("Mantenedor de Personas");

		field = new BSField("cId", "C�digo");
		table.addField(field);

		field = new BSField("cRUT", "Rut");
		table.addField(field);

		field = new BSField("cMasculino", "Varon");
		table.addField(field);

		field = new BSField("cFechaRegistro", "Fecha Ingreso");
		table.addField(field);

		field = new BSField("cNombre", "Nombre");
		table.addField(field);

		field = new BSField("cApellidoPaterno", "A. Paterno");
		table.addField(field);

		field = new BSField("cApellidoMaterno", "A. Materno");
		table.addField(field);

		field = new BSField("cDireccion", "Direcci�n");
		field.setVisible(false);
		table.addField(field);

		field = new BSField("cNumero", "N�mero");
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
		field.setFK("tComuna", "cNombre");
		table.addField(field);

		field = new BSField("cTelefono", "Tel�fono");
		table.addField(field);

		field = new BSField("cCelular", "Celular");
		table.addField(field);

		field = new BSField("cMail", "Mail");
		field.setVisible(false);
		table.addField(field);

		return table;
	}

	@Override
	protected BSHeadConfig getBSHeadConfig() {
		return null;
	}

}