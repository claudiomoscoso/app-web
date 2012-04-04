package cl.buildersoft.framework.type;

public class BSBoolean implements BSFieldDataType {

	@Override
	public Boolean validData(String data) {
		Boolean out = true;
		try {
			Boolean.parseBoolean(data);
		} catch (Exception e) {
			out = false;
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

}
