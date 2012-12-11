package com.musicproj.server;

public class ClipRecord {
	
	
	
	public ClipRecord(){}
	
	public ClipRecord(String id, String info, String genre, String instrument,
			String key, String tempo, String uploader, String updateDate,
			String updateTime, String rating) {
		super();
		this.id = id;
		this.info = info;
		this.genre = genre;
		this.instrument = instrument;
		this.key = key;
		this.tempo = tempo;
		this.uploader = uploader;
		this.updateDate = updateDate;
		this.updateTime = updateTime;
		this.rating = rating;
	}
	String id;
	String info;
	String genre;
	String instrument;
	@Override
	public String toString() {
		return "ClipRecord [id=" + id + ", info=" + info + ", genre=" + genre
				+ ", instrument=" + instrument + ", key=" + key + ", tempo="
				+ tempo + ", uploader=" + uploader + ", updateDate="
				+ updateDate + ", updateTime=" + updateTime + ", rating="
				+ rating + "]";
	}
	String key;
	String tempo;
	String uploader;
	String updateDate;
	String updateTime;
	String rating;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTempo() {
		return tempo;
	}
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
	
}
