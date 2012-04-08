package cl.buildersoft.web.servlet.csv;

import javax.servlet.annotation.WebServlet;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.BSCSVServlet;

@WebServlet("/servlet/csv/Upload")
public class BSPersonCSV extends BSCSVServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected BSTableConfig getBSTableConfig() {
		return new BSTableConfig("remu", "tPerson");
	}

}
