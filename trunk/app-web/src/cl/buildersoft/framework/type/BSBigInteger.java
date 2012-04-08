package cl.buildersoft.framework.type;
import java.math.BigInteger;
import java.sql.Connection;

public class BSBigInteger implements BSFieldDataType{

	@Override
	public Boolean validData(String data) {
		try {
			BigInteger.valueOf(Long.parseLong(data));
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
		// TODO Auto-generated method stub
		return null;
	}

}
