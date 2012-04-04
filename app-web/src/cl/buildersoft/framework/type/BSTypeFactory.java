package cl.buildersoft.framework.type;

import java.lang.reflect.InvocationTargetException;

import cl.buildersoft.framework.beans.BSField;


public class BSTypeFactory {


	private static final String BS = ".BS";

	@SuppressWarnings("rawtypes")
	public boolean evaluate(String evaluateData, BSField field)
	{
		Boolean response = null;
		try {
			Class newClass = Class.forName(this.getClass().getPackage().getName() + BS + field.getType().toString());
			BSFieldDataType data= (BSFieldDataType)newClass.newInstance();
			Class partypes[] = new Class[1]; 
			partypes[0] = String.class;
			Object[] obj = {evaluateData}; 
			response = (Boolean)data.getClass().getMethod("validData",partypes).invoke(data, obj);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return response;
	}
}
