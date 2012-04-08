package cl.buildersoft.framework.type;

import java.sql.Connection;

public class BSLong implements BSFieldDataType{

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
	public String format(Object data, String format) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validData(Connection conn, String data) {
		return validData(data);
	}

}
