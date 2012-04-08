package cl.buildersoft.framework.type;

import java.sql.Connection;
import java.text.SimpleDateFormat;

public class BSTimestamp implements BSFieldDataType {

	@Override
	public Boolean validData(String data) {
		String formatDate = "dd/MM/yyyy HH:mm:ss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatDate);
		java.util.Date parsedDate;
		try {
			parsedDate = dateFormat.parse(data);
			new java.sql.Timestamp(parsedDate.getTime());
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
	public String format(Object data, String format) {
		return null;
	}

	@Override
	public Boolean validData(Connection conn, String data) {
		// TODO Auto-generated method stub
		return null;
	}

}
