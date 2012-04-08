package cl.buildersoft.framework.type;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.util.BSConfig;

public class BSDate implements BSFieldDataType {

	@Override
	public Boolean validData(String data) {
		throw new BSProgrammerException("",
				"Hay que llamar al metodo validData(Connection, String); para el tipo BSDate");
	}

	@Override
	public Object convert(String data) {
		return null;
	}

	@Override
	public String format(Object data, String format) {
		return null;
	}

	@Override
	public Boolean validData(Connection conn, String data) {
		BSConfig config = new BSConfig();

		String formatDate = config.getString(conn, "FORMAT_DATE");
		DateFormat formatter = new SimpleDateFormat(formatDate);
		try {
			formatter.parse(data);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
