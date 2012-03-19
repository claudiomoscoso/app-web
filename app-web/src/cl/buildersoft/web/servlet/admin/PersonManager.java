package cl.buildersoft.web.servlet.admin;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;

import cl.buildersoft.framework.beans.BSField;
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

		table.setTitle("Mantenedor de Personas");

		field = new BSField("cId", "Código");
		table.addField(field);

		field = new BSField("cName", "Nombre");
		table.addField(field);

		field = new BSField("cBorn", "Fecha Nacimiento");
		table.addField(field);

		field = new BSField("cLastLogin", "Último Acceso");
		table.addField(field);

		field = new BSField("cSalary", "Sueldo");
		table.addField(field);

		field = new BSField("cActive", "Activo");
		table.addField(field);

		return table;
	}

}
