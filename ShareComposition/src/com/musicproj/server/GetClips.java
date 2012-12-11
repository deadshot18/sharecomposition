package com.musicproj.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.utils.DBController;
import com.google.gson.Gson;

public class GetClips  extends HttpServlet {
	
	DBController dbController = DBController.getInstane();
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		    throws IOException {
		List<ClipRecord> records = dbController.getClips();
		String json = new Gson().toJson(records);
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(json);
	}
}
