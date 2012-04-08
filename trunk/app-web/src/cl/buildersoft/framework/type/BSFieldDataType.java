package cl.buildersoft.framework.type;

import java.sql.Connection;

public interface BSFieldDataType {
	public Boolean validData(Connection conn, String data);

	public Boolean validData(String data);

	public Object convert(String data);

	public String format(Object data, String format);
}
