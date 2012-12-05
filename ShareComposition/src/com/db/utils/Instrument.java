package com.db.utils;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Instrument {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	long id;
	@Persistent
	String value;
	
	public Instrument() {
	}
	public Instrument(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
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
