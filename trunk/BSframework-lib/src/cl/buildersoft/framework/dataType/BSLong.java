package cl.buildersoft.framework.dataType;

import java.sql.Connection;

public class BSLong implements BSDataType {

	@Override
	public Boolean validData(String data) {
		try {
			Long.parseLong(data);
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
		return validData(data);
	}

	@Override
	public Object parse(Connection conn, String data) {
		return Long.parseLong(data);
	}

	@Override
	public String toString() {
		return BSDataType.LONG;
	}
}
