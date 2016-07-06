package com.yu.model;

public class ColumnValue {
	private String field;
	private Object value;
	private String dataType;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field.toUpperCase();
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public ColumnValue(String field, Object value, String dataType) {
		super();
		this.field = field.toUpperCase();
		this.value = value;
		this.dataType = dataType;
	}
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "ColumnValue [field=" + field + ", value=" + value + ", dataType=" + dataType + "]";
	}
	
}
