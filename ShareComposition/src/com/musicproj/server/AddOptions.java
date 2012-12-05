package com.musicproj.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.utils.DBController;
import com.google.gson.Gson;

public class AddOptions  extends HttpServlet {
	
	DBController dbController = new DBController();
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		    throws IOException {
		String option=req.getParameter("name");
		if(option.compareTo("key")==0){
			dbController.addKey(req.getParameter("info"));
			
		}
		else if(option.compareTo("instrument")==0){
			dbController.addInstrument(req.getParameter("info"));
			
		}
		else{
			dbController.addGenre(req.getParameter("info"));
			
		}
		
	}
}
