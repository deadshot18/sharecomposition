package com.db.utils;


public class MusicKey {
	long id;
	String key;
	public MusicKey() {
	}
	
	
	public MusicKey(long id, String key) {
		this.id = id;
		this.key = key;
	}


	public MusicKey(String key) {
		this.key = key;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

}
