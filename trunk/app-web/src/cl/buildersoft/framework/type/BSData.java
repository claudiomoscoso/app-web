package cl.buildersoft.framework.type;

import java.io.Serializable;

public class BSData implements Serializable{

	private String value;
	private boolean state = false;
	
	public BSData(String value)
	{
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
	
}
