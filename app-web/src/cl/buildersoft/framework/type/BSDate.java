package cl.buildersoft.framework.type;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BSDate implements BSFieldDataType{

	@Override
	public Boolean validData(String data) {
		String formatDate = "yyyy-MM-dd";
		DateFormat formatter = new SimpleDateFormat(formatDate);
		try {
			formatter.parse(data);
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

}
