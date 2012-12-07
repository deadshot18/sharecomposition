package com.musicproj.server;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.utils.DBController;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class Upload extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    private DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
    
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
    	// dsds
        Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
        BlobKey blobKey = blobs.get("myFile");

        if (blobKey == null) {
            res.sendRedirect("/");
        } else {
        	Entity clipRecord = new Entity("clipRecord", blobKey.getKeyString());
        	clipRecord.setProperty("info", req.getParameter("info"));
        	clipRecord.setProperty("genre",  req.getParameter("genre"));
        	clipRecord.setProperty("instrument", req.getParameter("instrument"));
        	clipRecord.setProperty("key", req.getParameter("key"));
        	clipRecord.setProperty("tempo", req.getParameter("tempo"));
        	clipRecord.setProperty("uploader", "site-team");
        	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        	Date date = new Date();
        	
        	clipRecord.setProperty("uploadDate", dateFormat.format(date));
        	clipRecord.setProperty("uploadTime", timeFormat.format(date));
        	clipRecord.setProperty("rating" , "5");
        	datastoreService.put(clipRecord);
        	
            res.sendRedirect("/serve?blob-key=" + blobKey.getKeyString());
        }
    }
    
    
    
    
    
}