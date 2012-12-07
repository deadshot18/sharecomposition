<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.db.utils.*"%>



<%@ page import="com.db.utils.*"%>

<%
	BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
%>


<html>
<head>
<title>Upload Test</title>
</head>
<body>
	<form action="<%=blobstoreService.createUploadUrl("/upload")%>"
		method="post" enctype="multipart/form-data">
		Short info: <br /> <input type="text" name="info"> <br />
		Genre: <select name="genre">
			<%
				ArrayList<Genre> genres = DBController.getInstane().getListOfGenres();
				for (int s = 0; s < genres.size(); s++) {
			%>
			<option value="<%out.print(genres.get(s).getId());%>"><%=genres.get(s).getGenre()%></option>
			<%
				}
			%> %>
		</select> <br /> Instrument: <select name="instrument">
			<%
				ArrayList<Instrument> instruments =  DBController.getInstane().getListOfInstruments();
				for(int i=0;i<instruments.size();i++)
			    {
					Instrument instrument = instruments.get(i);
			        %>
					<option value="<%out.print(instrument.getId());%>">
					<%=instrument.getValue()%></option>
					<%
			    }
			
			%>
		</select> <br /> Key: <select name="key">
		<%
			ArrayList<MusicKey> keys = DBController.getInstane().getListOfKeys();
				for(int i=0;i<keys.size();i++)
			    {
					MusicKey key = keys.get(i);
		%>
					<option value="<%out.print(key.getId());%>">
					<%=key.getKey()%></option>
					<%
			    }
			
			%>
		</select> <br /> Tempo: <select name="tempo">
		<%
			ArrayList<Tempo> tempos = DBController.getInstane().getListOfTempos();
				for(int i=0;i<tempos.size();i++)
			    {
					Tempo tempo = tempos.get(i);
		%>
					<option value="<%out.print(tempo.getId());%>">
					<%=tempo.getTempo()%></option>
					<%
			    }
			
			%>
		</select> <br /> <input type="file" name="myFile"> <br /> <input
			type="submit" value="Submit">
	</form>
	<br /><br /><br />Add Item to the list:
	
	<form action="/addoptions"
		method="get" enctype="multipart/form-data">
		<select name="addoption">
			<option value="genre">Genre</option>
			<option value="key">Key</option>
			<option value="tempo">Tempo</option>
			<option value="instrument">Instrument</option>
		</select>
		<input type="text" name="info">
		  <input
			type="submit" value="Submit">
	</form>
</body>
</html>