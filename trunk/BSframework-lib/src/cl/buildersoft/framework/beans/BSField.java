package cl.buildersoft.framework.beans;

import java.util.Arrays;
import java.util.List;

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
	private String validationOnBlur = null;
	private Boolean visible = Boolean.TRUE;
	private String[] fk = null;
	private List<Object[]> fkData = null;
	private String typeHtml = "text";
	Boolean isNullable = false; // rs.getMetaData().isNullable(index);

	public BSField(String name, String label) {
		super();
		this.name = name;
		this.label = label;
	}

	/**
	 * @deprecated Use isPK()
	 * */
	public Boolean isId() {
		return "id".equalsIgnoreCase(this.name) || "cid".equalsIgnoreCase(this.name);
	}

	public Boolean showField() {
		Boolean out = !isPK() && isVisible();
		return out;
	}

	public Boolean isFK() {
		Boolean out = Boolean.FALSE;
		List<Object[]> data = getFKData();
		out = data != null;
		return out;
	}

	public Boolean isNumber() {
		return getType().equals(BSFieldType.Double) || getType().equals(BSFieldType.Integer)
				|| getType().equals(BSFieldType.Long);
	}

	public Boolean isTime() {
		return getType().equals(BSFieldType.Date) || getType().equals(BSFieldType.Timestamp);
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

	public Boolean isPK() {
		return pk;
	}

	public void setPK(Boolean pk) {
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

	public String getValueAsString() {
		return getValue().toString();
	}

	public Long getValueAsLong() {
		return Long.parseLong(getValueAsString());
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

	public String getValidationOnBlur() {
		return validationOnBlur;
	}

	public void setValidationOnBlur(String validationOnBlur) {
		this.validationOnBlur = validationOnBlur;
	}

	public Boolean isVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	/***/
	public String getFKDatabase() {
		return fk != null ? fk[0] : null;
	}

	public String getFKTable() {
		return fk != null ? fk[1] : null;
	}

	public String getFKField() {
		return fk != null ? fk[2] : null;
	}

	public void setFK(String fkDatabase, String fkTable, String fkField) {
		this.fk = new String[3];
		this.fk[0] = fkDatabase;
		this.fk[1] = fkTable;
		this.fk[2] = fkField;
	}

	public List<Object[]> getFKData() {
		return fkData;
	}

	public void setFkData(List<Object[]> fkData) {
		this.fkData = fkData;
	}

	@Override
	public String toString() {
		return "BSField [name=" + name + ", label=" + label + ", pk=" + pk + ", unique=" + unique + ", readonly=" + readonly
				+ ", length=" + length + ", type=" + type + ", value=" + value + ", validationOnBlur=" + validationOnBlur
				+ ", visible=" + visible + ", fk=" + Arrays.toString(fk) + ", fkData=" + fkData + "]";
	}

	public String getTypeHtml() {
		return typeHtml;
	}

	public void setTypeHtml(String typeHtml) {
		this.typeHtml = typeHtml;
	}

	public Boolean getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(Boolean isNullable) {
		this.isNullable = isNullable;
	}

}
