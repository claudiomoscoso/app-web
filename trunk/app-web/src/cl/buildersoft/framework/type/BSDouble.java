package cl.buildersoft.framework.type;

public class BSDouble implements BSFieldDataType{

	@Override
	public Boolean validData(String data) {
		try {
			Double.parseDouble(data);
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
