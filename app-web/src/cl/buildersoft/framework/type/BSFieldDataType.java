package cl.buildersoft.framework.type;

public interface BSFieldDataType {
	public Boolean validData(String data);

	public Object convert(String data);

	public String format(Object data, String format);
}
