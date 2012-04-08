package cl.buildersoft.framework.type;

import java.sql.Connection;

public class BSBoolean implements BSFieldDataType {

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
	public String format(Object data, String format) {

		return null;
	}

	@Override
	public Boolean validData(Connection conn, String data) {
		// TODO Auto-generated method stub
		return null;
	}

}
