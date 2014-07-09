package cl.buildersoft.lib.dataType;

import java.io.Serializable;
import java.sql.Connection;

public class BSBoolean implements BSDataType, Serializable {
	private static final long serialVersionUID = 1325491582261296320L;

	@Override
	public Boolean validData(String data) {
		Boolean out = true;
		try {
			out = Boolean.parseBoolean(data);
		} catch (Exception e) {
			return false;
		}

		return out;
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
		return validData(data);
	}

	@Override
	public Object parse(Connection conn, String data) {
		return Boolean.parseBoolean(data);
	}

	@Override
	public String toString() {
		return BSDataType.BOOLEAN;
	}

}
