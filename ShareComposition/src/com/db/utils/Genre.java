package com.db.utils;


public class Genre {
	public Genre() {
	}
	
	
	public Genre(String genre) {
		this.genre = genre;
	}
	public Genre(long id,String genre) {
		this.id=id;
		this.genre = genre;
	}
	long id;
	String genre;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
}
