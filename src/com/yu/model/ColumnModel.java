package com.yu.model;

public class ColumnModel {
	private String field;
	private String dataType;
	private String length;
	private String digits;//Ð¡ÊýÎ»

	private String isPrimaryKey;
	private String isNotNull;
	private String notes;
	
	public ColumnModel(String field, String dataType, String length,String digits, String isPrimaryKey, String isNotNull,
			String notes) {
		super();
		this.field = field.toUpperCase();
		this.dataType = dataType;
		this.length = length;
		this.digits = digits;
		this.isPrimaryKey = isPrimaryKey;
		this.isNotNull = isNotNull;
		this.notes = notes;
	}
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
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getIsPrimaryKey() {
		return isPrimaryKey;
	}
	public void setIsPrimaryKey(String isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public String getIsNotNull() {
		return isNotNull;
	}
	public void setIsNotNull(String isNotNull) {
		this.isNotNull = isNotNull;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getDigits() {
		return digits;
	}
	public void setDigits(String digits) {
		this.digits = digits;
	}
	@Override
	public String toString() {
		return "ColumnModel [field=" + field + ", dataType=" + dataType + ", length=" + length + ", digits=" + digits
				+ ", isPrimaryKey=" + isPrimaryKey + ", isNotNull=" + isNotNull + ", notes=" + notes + "]";
	}
	
}
