package cl.buildersoft.web.servlet.remu;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.csv.DownloadCSVServlet;

@WebServlet("/servlet/admin/PersonCSV")
public class PersonDownloadCSV extends DownloadCSVServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	public PersonDownloadCSV() {
		super();
	}

	@Override
	protected BSTableConfig getBSTableConfig() {
		BSTableConfig table = new BSTableConfig("remu", "tPerson");
		return table;
	}

}
