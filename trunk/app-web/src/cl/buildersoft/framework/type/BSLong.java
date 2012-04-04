package cl.buildersoft.framework.type;

public class BSLong implements BSFieldDataType{

	@Override
	public Boolean validData(String data) {
		Boolean out = true;
		try {
			Long.parseLong(data);
		} catch (Exception e) {
			out = false;
		}
		return out;
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
