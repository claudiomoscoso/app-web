package cl.buildersoft.lib.dataType;

import java.io.Serializable;
import java.sql.Connection;

public class BSString implements BSDataType, Serializable {
	private static final long serialVersionUID = 3687275098899337703L;

	@Override
	public Boolean validData(String data) {
		try {
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Object convert(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String format(Connection conn, Object data) {
		return data.toString();
	}

	@Override
	public Boolean validData(Connection conn, String data) {
		return true;
	}

	@Override
	public Object parse(Connection conn, String data) {
		return data.toString();
	}

	@Override
	public String toString() {
		return BSDataType.STRING;
	}
}
