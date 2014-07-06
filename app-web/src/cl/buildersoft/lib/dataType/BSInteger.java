package cl.buildersoft.lib.dataType;

import java.io.Serializable;
import java.sql.Connection;

public class BSInteger implements BSDataType, Serializable {
	private static final long serialVersionUID = 3979631388923761259L;

	@Override
	public Boolean validData(String data) {
		try {
			Integer.parseInt(data);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object parse(Connection conn, String data) {
		return Integer.parseInt(data);
	}

	@Override
	public String toString() {
		return BSDataType.INTEGER;
	}
}
