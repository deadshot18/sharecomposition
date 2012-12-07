package com.db.utils;


public class Tempo {
	public Tempo() {
	}
	long id;
	String tempo;
	
	public Tempo(String tempo) {
		this.tempo = tempo;
	}
	public Tempo(long id,String tempo) {
		this.id=id;
		this.tempo = tempo;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTempo() {
		return tempo;
	}
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
}
