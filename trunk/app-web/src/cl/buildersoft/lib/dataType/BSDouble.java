package cl.buildersoft.lib.dataType;

import java.io.Serializable;
import java.sql.Connection;

public class BSDouble implements BSDataType, Serializable { 
	private static final long serialVersionUID = 5463917496026940808L;

	@Override
	public Boolean validData(String data) {
		try {
			Double.parseDouble(data);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Object convert(String data) {
		return null;
	}

	@Override
	public String format(Connection conn, Object data) {
		return data.toString();
	}

	@Override
	public Boolean validData(Connection conn, String data) {
		return null;
	}

	@Override
	public Object parse(Connection conn, String data) {
		data = data.replaceAll(",", ".");
		Double out = Double.parseDouble(data);
		return out;
	}

	@Override
	public String toString() {
		return BSDataType.DOUBLE;
	}
}
