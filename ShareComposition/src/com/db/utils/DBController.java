package com.db.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.musicproj.server.ClipRecord;

public class DBController {
	
	
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	private DBController() {
		
	}
	static class DBControllerHolder{
		static DBController instance= new DBController();
		
	}
	public static DBController getInstane(){
		return DBControllerHolder.instance;
	}
	public List<ClipRecord> getClips(){
		List<ClipRecord> clipList = new ArrayList<ClipRecord>();
		
		
        
        Query query = new Query("clipRecord");
        PreparedQuery pq = datastore.prepare(query);
        List<Entity> detailList = pq.asList(FetchOptions.Builder.withLimit(100).offset(0));
        
        
        for (Entity entity : detailList) {
        	Key detailKey = KeyFactory.createKey("clipRecord",entity.getKey().getName());
        	
        	ClipRecord cr = new ClipRecord();
        	cr.setId(detailKey.toString());
        	cr.setInfo((String) entity.getProperty("info"));
        	
        	try {
				cr.setGenre((String) getItemNameFromTable("Genre",(long)entity.getProperty("genre")));
				cr.setGenre((String) getItemNameFromTable("Instrument",(long)entity.getProperty("instrument")));
				cr.setGenre((String) getItemNameFromTable("Key",(long)entity.getProperty("key")));
				cr.setGenre((String) getItemNameFromTable("Tempo",(long)entity.getProperty("tempo")));
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
        	cr.setUploader((String) entity.getProperty("uploader"));
        	cr.setUpdateDate((String) entity.getProperty("uploadDate"));
        	cr.setUpdateTime((String) entity.getProperty("uploadTime"));
        	
        	//TODO: solve this:: cr.setRating(String.valueOf( entity.getProperty("rating") ));
        	cr.setRating("5");
        	clipList.add(cr);
        }
        
        
		return clipList;
	}
	public boolean addInstrument(String instrument){
		Query query = new Query("Instrument");
		
		Filter nameFilter =
				  new FilterPredicate("name",
				                      FilterOperator.EQUAL,
				                      instrument);
		query.setFilter(nameFilter);
        PreparedQuery pq = datastore.prepare(query);
        List<Entity> detailList = pq.asList(FetchOptions.Builder.withLimit(100).offset(0));
		if(detailList.size()==0){
			Entity instrumentEntity = new Entity("Instrument");
			instrumentEntity.setProperty("name", instrument );
			datastore.put(instrumentEntity);
			
			return true;
		}
		return false;
	}
	
	public boolean addGenre(String genreStr){
		Query query = new Query("Genre");
		
		Filter nameFilter =
				  new FilterPredicate("name",
				                      FilterOperator.EQUAL,
				                      genreStr);
		query.setFilter(nameFilter);
        PreparedQuery pq = datastore.prepare(query);
        List<Entity> detailList = pq.asList(FetchOptions.Builder.withLimit(100).offset(0));
		if(detailList.size()==0){
			Entity genreEntity = new Entity("Genre");
			genreEntity.setProperty("name", genreStr );
			datastore.put(genreEntity);
			
			return true;
		}
		
		
		return false;
	}
	
	public boolean addKey(String key){
		Query query = new Query("MusicKey");
		
		Filter nameFilter =
				  new FilterPredicate("name",
				                      FilterOperator.EQUAL,
				                      key);
		query.setFilter(nameFilter);
        PreparedQuery pq = datastore.prepare(query);
        List<Entity> detailList = pq.asList(FetchOptions.Builder.withLimit(100).offset(0));
		if(detailList.size()==0){
			Entity keyEntity = new Entity("MusicKey");
			keyEntity.setProperty("name", key );
			datastore.put(keyEntity);
			
			return true;
		}
		return false;
	}
	public boolean addTempo(String tempo){
		Query query = new Query("Tempo");
		
		Filter nameFilter =
				  new FilterPredicate("name",
				                      FilterOperator.EQUAL,
				                      tempo);
		query.setFilter(nameFilter);
        PreparedQuery pq = datastore.prepare(query);
        List<Entity> detailList = pq.asList(FetchOptions.Builder.withLimit(100).offset(0));
		if(detailList.size()==0){
			Entity keyEntity = new Entity("Tempo");
			keyEntity.setProperty("name", tempo );
			datastore.put(keyEntity);
			
			return true;
		}
		return false;
	}
	public long getItemIdFromTable(String table,String key){
		Query query = new Query(key);
		
		Filter nameFilter =
				  new FilterPredicate("name",
				                      FilterOperator.EQUAL,
				                      key);
		query.setFilter(nameFilter);
        PreparedQuery pq = datastore.prepare(query);
        List<Entity> detailList = pq.asList(FetchOptions.Builder.withLimit(100).offset(0));
        return detailList.get(0).getKey().getId();
	}
	public String getItemNameFromTable(String table,long id) throws EntityNotFoundException{
		Key detailKey = KeyFactory.createKey(table,id);
        return (String) datastore.get(detailKey).getProperty("name");
	}
	
	public ArrayList<Genre> getListOfGenres(){
		Query query = new Query("Genre");
		ArrayList<Genre> genres= new ArrayList<Genre>();
        PreparedQuery pq = datastore.prepare(query);
        List<Entity> detailList = pq.asList(FetchOptions.Builder.withLimit(100).offset(0));
        for (Iterator iterator = detailList.iterator(); iterator.hasNext();) {
			Entity entity = (Entity) iterator.next();
			String genre=(String)entity.getProperty("name");
			long id=entity.getKey().getId();
			genres.add(new Genre(id,genre));
		}
        return genres;
	}
	public ArrayList<MusicKey> getListOfKeys(){
		Query query = new Query("MusicKey");
		ArrayList<MusicKey> keys= new ArrayList<MusicKey>();
        PreparedQuery pq = datastore.prepare(query);
        List<Entity> detailList = pq.asList(FetchOptions.Builder.withLimit(100).offset(0));
        for (Iterator iterator = detailList.iterator(); iterator.hasNext();) {
			Entity entity = (Entity) iterator.next();
			String genre=(String)entity.getProperty("name");
			long id=entity.getKey().getId();
			keys.add(new MusicKey(id,genre));
		}
        return keys;
	}
	public ArrayList<Instrument> getListOfInstruments(){
		Query query = new Query("Instrument");
		ArrayList<Instrument> instruments= new ArrayList<Instrument>();
        PreparedQuery pq = datastore.prepare(query);
        List<Entity> detailList = pq.asList(FetchOptions.Builder.withLimit(100).offset(0));
        for (Iterator iterator = detailList.iterator(); iterator.hasNext();) {
			Entity entity = (Entity) iterator.next();
			String genre=(String)entity.getProperty("name");
			long id=entity.getKey().getId();
			instruments.add(new Instrument(id,genre));
		}
        return instruments;
	}
	
	public ArrayList<Tempo> getListOfTempos(){
		Query query = new Query("Tempo");
		ArrayList<Tempo> tempos= new ArrayList<Tempo>();
        PreparedQuery pq = datastore.prepare(query);
        List<Entity> detailList = pq.asList(FetchOptions.Builder.withLimit(100).offset(0));
        for (Iterator iterator = detailList.iterator(); iterator.hasNext();) {
			Entity entity = (Entity) iterator.next();
			String genre=(String)entity.getProperty("name");
			long id=entity.getKey().getId();
			tempos.add(new Tempo(id,genre));
		}
        return tempos;
	}
//	public static String [] getListOfGenres(){
//		
//		String [] genres={
//		"African",
//		"Asian",
//		"Avant-Garde",
//		"Blues",
//		"Brazilian Music",
//		"Classic",
//		"Comedy music",
//		"Country",
//		"Dance",
//		"Electronic",
//		"Metal",
//		"Modern folk",
//		"Hip Hop",
//		"Jazz",
//		"Latin American",
//		"Opera",
//		"Pop",
//		"R&B",
//		"Rock",
//		"Ska",
//		"Other",
//		};
//		return genres;
//	}
	
	
//public static ArrayList<Instrument> getListOfInstruments(){
//	ArrayList<Instrument> instruments= new ArrayList<Instrument>();
//	instruments.add(new Instrument("12g","12-string guitar"));
//	instruments.add(new Instrument("42g","42-string Pikasa guitar"));
//	instruments.add(new Instrument("acc","Accordion"));
//	instruments.add(new Instrument("abg","Acoustic Bass Guitar"));
//	instruments.add(new Instrument("acg","Acoustic Guitar"));
//	instruments.add(new Instrument("acl","Alto Clarinet"));
//	instruments.add(new Instrument("af","Alto Flute"));
//	instruments.add(new Instrument("ah","Alto Horn (Peck Horn))"));
//	instruments.add(new Instrument("arc","Alto Recorder"));
//	instruments.add(new Instrument("as","Alto Sax"));
//	instruments.add(new Instrument("atb","Alto Trombone"));
//	instruments.add(new Instrument("arg","Argol"));
//	instruments.add(new Instrument("arr","Arranger"));
//	instruments.add(new Instrument("ahp","Autoharp"));
//	instruments.add(new Instrument("bkv","Backing Vocals"));
//	instruments.add(new Instrument("bgp","Bagpipes"));
//	instruments.add(new Instrument("ban","Bandoneon"));
//	instruments.add(new Instrument("bj","Banjo"));
//	instruments.add(new Instrument("bns","Bansuri Flute"));
//	instruments.add(new Instrument("bar","Baritone Sax"));
//	instruments.add(new Instrument("b","Bass"));
//	instruments.add(new Instrument("bcl","Bass Clarinet"));
//	instruments.add(new Instrument("bd","Bass Drum"));
//	instruments.add(new Instrument("bf","Bass Flute"));
//	instruments.add(new Instrument("bh","Bass Harmonica"));
//	instruments.add(new Instrument("brc","Bass Recorder"));
//	instruments.add(new Instrument("bsx","Bass Sax"));
//	instruments.add(new Instrument("btb","Bass Trombone"));
//	instruments.add(new Instrument("bt","Bass Trumpet"));
//	instruments.add(new Instrument("bst","Bassett Horn"));
//	instruments.add(new Instrument("bsn","Bassoon"));
//	instruments.add(new Instrument("bat","Bat? Drums"));
//	instruments.add(new Instrument("bel","Bells/Glockenspiel"));
//	instruments.add(new Instrument("ber","Berimbau"));
//	instruments.add(new Instrument("bw","Bird Whistle"));
//	instruments.add(new Instrument("bgo","Bongos"));
//	instruments.add(new Instrument("bzk","Bouzouki"));
//	instruments.add(new Instrument("Cm","C-Melody Sax"));
//	instruments.add(new Instrument("cab","Cabasa/Afuche"));
//	instruments.add(new Instrument("caj","Cajon"));
//	instruments.add(new Instrument("cst","Castanets"));
//	instruments.add(new Instrument("cel","Celeste"));
//	instruments.add(new Instrument("stk","Chapman Stick"));
//	instruments.add(new Instrument("chk","Chekere/Shaker"));
//	instruments.add(new Instrument("chg","Cheng"));
//	instruments.add(new Instrument("chi","Chimes (Tubular Bells))"));
//	instruments.add(new Instrument("cl","Clarinet"));
//	instruments.add(new Instrument("cvs","Claves"));
//	instruments.add(new Instrument("clv","Clavinet"));
//	instruments.add(new Instrument("shl","Conch Shells"));
//	instruments.add(new Instrument("con","Conductor"));
//	instruments.add(new Instrument("cga","Congas"));
//	instruments.add(new Instrument("cb","Contrabass"));
//	instruments.add(new Instrument("cbc","Contrabass Clarinet"));
//	instruments.add(new Instrument("csr","Contrabass Sarrusophone"));
//	instruments.add(new Instrument("cbs","Contrabass Saxophone"));
//	instruments.add(new Instrument("ctb","Contrabass Trombone"));
//	instruments.add(new Instrument("cbn","Contrabassoon"));
//	instruments.add(new Instrument("cac","Contralto Clarinet"));
//	instruments.add(new Instrument("c","Cornet"));
//	instruments.add(new Instrument("cwb","Cowbell"));
//	instruments.add(new Instrument("cro","Crotales"));
//	instruments.add(new Instrument("cua","Cuatro"));
//	instruments.add(new Instrument("cym","Cymbals"));
//	instruments.add(new Instrument("dnc","Dance"));
//	instruments.add(new Instrument("dg","Didgeridoo"));
//	instruments.add(new Instrument("dir","Director"));
//	instruments.add(new Instrument("dmb","Doumbek"));
//	instruments.add(new Instrument("dng","Doussn'gouni (Hunter's Guitar))"));
//	instruments.add(new Instrument("d","Drums"));
//	instruments.add(new Instrument("dul","Dulcimer"));
//	instruments.add(new Instrument("eb","Electric Bass"));
//	instruments.add(new Instrument("eg","Electric Guitar"));
//	instruments.add(new Instrument("org","Electric Organ"));
//	instruments.add(new Instrument("ep","Electric Piano"));
//	instruments.add(new Instrument("eub","Electric Upright Bass"));
//	instruments.add(new Instrument("EVI","Electronic Valve Instrument"));
//	instruments.add(new Instrument("EWI","Electronic Wind Instrument"));
//	instruments.add(new Instrument("enh","English Horn"));
//	instruments.add(new Instrument("eu","Euphonium"));
//	instruments.add(new Instrument("fc","Finger Cymbals"));
//	instruments.add(new Instrument("fh","Fl?gelhorn"));
//	instruments.add(new Instrument("f","Flute"));
//	instruments.add(new Instrument("frd","Frame Drum"));
//	instruments.add(new Instrument("frh","French Horn"));
//	instruments.add(new Instrument("gai","Gaita (Colombian))"));
//	instruments.add(new Instrument("gha","Ghatam"));
//	instruments.add(new Instrument("gng","Gong"));
//	instruments.add(new Instrument("gfs","Goofus/Cuesnophone"));
//	instruments.add(new Instrument("gui","Guiro"));
//	instruments.add(new Instrument("g","Guitar"));
//	instruments.add(new Instrument("hag","Hag'houge/Gembri"));
//	instruments.add(new Instrument("hnd","Handclaps"));
//	instruments.add(new Instrument("h","Harmonica"));
//	instruments.add(new Instrument("har","Harmonium"));
//	instruments.add(new Instrument("hrp","Harp"));
//	instruments.add(new Instrument("hps","Harpsichord"));
//	instruments.add(new Instrument("hfp","Hot Fountain Pen"));
//	instruments.add(new Instrument("kal","Kalimba/Mbira (Thumb Piano))"));
//	instruments.add(new Instrument("kar","Karkaba (Metal Castanets))"));
//	instruments.add(new Instrument("kaz","Kazoo"));
//	instruments.add(new Instrument("key","Keyboards"));
//	instruments.add(new Instrument("kor","Kora"));
//	instruments.add(new Instrument("kot","Koto"));
//	instruments.add(new Instrument("ldr","Leader"));
//	instruments.add(new Instrument("lut","Lute"));
//	instruments.add(new Instrument("mda","Mandola"));
//	instruments.add(new Instrument("man","Mandolin"));
//	instruments.add(new Instrument("mnz","Manzello"));
//	instruments.add(new Instrument("mcs","Maracas"));
//	instruments.add(new Instrument("mar","Marimba"));
//	instruments.add(new Instrument("mc","Master of Ceremonies"));
//	instruments.add(new Instrument("mp","Mellophone"));
//	instruments.add(new Instrument("mpm","Mellophonium"));
//	instruments.add(new Instrument("mld","Melodica"));
//	instruments.add(new Instrument("mri","Mridangam"));
//	instruments.add(new Instrument("mus","Musette"));
//	instruments.add(new Instrument("saw","Musical Saw"));
//	instruments.add(new Instrument("NED","NED Synclavier"));
//	instruments.add(new Instrument("ob","Oboe"));
//	instruments.add(new Instrument("oc","Ocarina"));
//	instruments.add(new Instrument("oth","Other"));
//	instruments.add(new Instrument("oud","Oud"));
//	instruments.add(new Instrument("pan","Pan Flute"));
//	instruments.add(new Instrument("pw","Penny Whistle"));
//	instruments.add(new Instrument("per","Percussion"));
//	instruments.add(new Instrument("p","Piano"));
//	instruments.add(new Instrument("pic","Piccolo"));
//	instruments.add(new Instrument("pb","Piccolo Bass"));
//	instruments.add(new Instrument("pt","Piccolo Trumpet"));
//	instruments.add(new Instrument("p-o","Pipe Organ"));
//	instruments.add(new Instrument("prp","Prepared Piano"));
//	instruments.add(new Instrument("prg","Programming Synth/Drum Machine"));
//	instruments.add(new Instrument("rab","Rabab"));
//	instruments.add(new Instrument("rap","Rap"));
//	instruments.add(new Instrument("rec","Recorder"));
//	instruments.add(new Instrument("sar","Sarod"));
//	instruments.add(new Instrument("sr","Sarrusophone"));
//	instruments.add(new Instrument("sxl","Saxello"));
//	instruments.add(new Instrument("saz","Saz"));
//	instruments.add(new Instrument("shk","Shakuhachi Flute"));
//	instruments.add(new Instrument("shm","Shawm"));
//	instruments.add(new Instrument("shn","Shenai"));
//	instruments.add(new Instrument("sit","Sitar"));
//	instruments.add(new Instrument("slb","Sleigh Bells"));
//	instruments.add(new Instrument("s-g","Slide Guitar"));
//	instruments.add(new Instrument("s-s","Slide Saxophone"));
//	instruments.add(new Instrument("s-t","Slide Trumpet"));
//	instruments.add(new Instrument("s-w","Slide Whistle"));
//	instruments.add(new Instrument("sd","Snare Drum"));
//	instruments.add(new Instrument("scl","Sopranino Clarinet"));
//	instruments.add(new Instrument("sss","Sopranino Saxophone"));
//	instruments.add(new Instrument("src","Soprano Recorder"));
//	instruments.add(new Instrument("ss","Soprano Sax"));
//	instruments.add(new Instrument("spk","Speaking"));
//	instruments.add(new Instrument("std","Steel Drums"));
//	instruments.add(new Instrument("stg","Steel Guitar"));
//	instruments.add(new Instrument("str","Strings"));
//	instruments.add(new Instrument("st","Stritch"));
//	instruments.add(new Instrument("syn","Synthesizer"));
//	instruments.add(new Instrument("tbl","Tablas"));
//	instruments.add(new Instrument("tmb","Tamboura"));
//	instruments.add(new Instrument("tam","Tambourine"));
//	instruments.add(new Instrument("trg","Tarogato"));
//	instruments.add(new Instrument("td","Tenor Drum"));
//	instruments.add(new Instrument("th","Tenor Horn"));
//	instruments.add(new Instrument("trc","Tenor Recorder"));
//	instruments.add(new Instrument("ts","Tenor Sax"));
//	instruments.add(new Instrument("thr","Theremin"));
//	instruments.add(new Instrument("tim","Timbales"));
//	instruments.add(new Instrument("tmp","Timpani"));
//	instruments.add(new Instrument("tri","Triangle"));
//	instruments.add(new Instrument("tb","Trombone"));
//	instruments.add(new Instrument("tbm","Trombonium"));
//	instruments.add(new Instrument("t","Trumpet"));
//	instruments.add(new Instrument("tu","Tuba"));
//	instruments.add(new Instrument("tt","Turntable/Electronics"));
//	instruments.add(new Instrument("uke","Ukelele"));
//	instruments.add(new Instrument("unk","unknown"));
//	instruments.add(new Instrument("vtb","Valve Trombone"));
//	instruments.add(new Instrument("vib","Vibraphone"));
//	instruments.add(new Instrument("vl","Viola"));
//	instruments.add(new Instrument("gam","Viola da Gamba"));
//	instruments.add(new Instrument("vn","Violin"));
//	instruments.add(new Instrument("vc","Violoncello"));
//	instruments.add(new Instrument("vit","Vitar"));
//	instruments.add(new Instrument("v","Vocals"));
//	instruments.add(new Instrument("wb","Washboard"));
//	instruments.add(new Instrument("wtr","Waterphone"));
//	instruments.add(new Instrument("w","Whistling"));
//	instruments.add(new Instrument("wf","Wood Flute"));
//	instruments.add(new Instrument("wbk","Woodblock"));
//	instruments.add(new Instrument("ww","Woodwinds"));
//	instruments.add(new Instrument("xyl","Xylophone"));
//		return instruments;
//	}


}
