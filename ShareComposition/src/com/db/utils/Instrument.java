package com.db.utils;

public class Instrument {
	long id;
	String value;
	
	public Instrument() {
	}
	public Instrument(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public Instrument(long id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
