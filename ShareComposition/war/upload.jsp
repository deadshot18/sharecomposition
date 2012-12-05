<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.db.utils.*"%>



<%@ page import="com.db.utils.DBController"%>
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
				String[] genres = DBController.getListOfGenres();
				for (int s = 0; s < genres.length; s++) {
			%>
			<option value="<%out.print(genres[s]);%>"><%=genres[s]%></option>
			<%
				}
			%> %>
		</select> <br /> Instrument: <select name="instrument">
			<%
				ArrayList<Instrument> instruments = DBController.getListOfInstruments();
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
		</select> <br /> Tempo: <select name="tempo">
		</select> <br /> <input type="file" name="myFile"> <br /> <input
			type="submit" value="Submit">
	</form>
	<form action="/addoptions"
		method="get" enctype="multipart/form-data">
		<select name="addoption">
			<option value="genre">Genre</option>
			<option value="key">Key</option>
			<option value="instrument">Instrument</option>
		</select>
		<input type="text" name="info">
		  <input
			type="submit" value="Submit">
	</form>
</body>
</html>