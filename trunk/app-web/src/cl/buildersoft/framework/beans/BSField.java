package cl.buildersoft.framework.beans;

import cl.buildersoft.framework.type.BSFieldType;

public class BSField {
	private String name = null;
	private String label = null;
	private Boolean pk = null;
	private Boolean unique = Boolean.TRUE;
	private Boolean readonly = Boolean.FALSE;
	private Integer length = null;
	private BSFieldType type = null;
	private Object value = null;

	public BSField(String name, String label) {
		super();
		this.name = name;
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean isPk() {
		return pk;
	}

	public void setPk(Boolean pk) {
		this.pk = pk;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public BSFieldType getType() {
		return type;
	}

	public void setType(BSFieldType type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

	public Boolean isUnique() {
		return unique;
	}

	public void setUnique(Boolean unique) {
		this.unique = unique;
	}
}
