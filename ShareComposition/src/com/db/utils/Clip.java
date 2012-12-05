package com.db.utils;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Clip {
	long id;

	String blobkey;
	Key key;
	Genre genre;
	Instrument instrument;
	
}
