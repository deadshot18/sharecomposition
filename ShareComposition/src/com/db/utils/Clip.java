package com.db.utils;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Clip {
	long id;

	String blobkey;
	@Column(name="KEY_ID")
	Key key;
	@Column(name="GENRE_ID")
	Genre genre;
	@Column(name="Instrument_ID")
	Instrument instrument;
	
}
