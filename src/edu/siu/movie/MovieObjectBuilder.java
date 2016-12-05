package edu.siu.movie;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.*;

public class MovieObjectBuilder {
	
	private String MovieTitle;
	private String ReleaseDate;
	private String Plot;
	private String IMDBRating;
	private String myRating;
	
	private final String URL = "http://www.omdbapi.com/?";
	private final String CHARSET = "UTF-8";
	
	
	public MovieObjectBuilder(String title){
		MovieTitle = title;
		myRating = "n/a";
		JSONObject output;
		try {
			output = SearchWithTitle(title);
			ReleaseDate =  output.getString("Released");
			Plot =  output.getString("Plot");
			IMDBRating =  output.getString("imdbRating");
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public MovieObjectBuilder(String title, String year){
		MovieTitle = title;
		myRating = "n/a";
		JSONObject output;
		try {
			output = SearchWithTitleAndYear(title, year);
			Plot = output.getString("Plot");
			IMDBRating = output.getString("imdbRating");
			ReleaseDate = output.getString("Released");
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public String getMovieTitle() {
		return MovieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		MovieTitle = movieTitle;
	}

	public String getReleaseDate() {
		return ReleaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		ReleaseDate = releaseDate;
	}

	public String getIMDBRating() {
		return IMDBRating;
	}

	public void setIMDBRating(String rating) {
		IMDBRating = rating;
	}
	
	public String getPlot() {
		return Plot;
	}

	public void setPlot(String plot) {
		Plot = plot;
	}
	
	public String getMyRating(){
		return myRating;
	}
	
	public void setMyRating(String myrating){
		myRating = myrating;
	}

	private JSONObject SearchWithTitle(String title) throws IOException, JSONException{
		String query = String.format("t=%s",URLEncoder.encode(title, CHARSET));
		    

		URLConnection connection = new URL(URL + query).openConnection();
		connection.setRequestProperty("Accept-Charset", CHARSET);
		InputStream response = connection.getInputStream();
		Scanner scanner = new Scanner(response); 
		String responseBody = scanner.useDelimiter("\\A").next();
		scanner.close();
		JSONObject json = new JSONObject(responseBody);
		return json;
	}
	
	private JSONObject SearchWithTitleAndYear(String title, String year) throws IOException, JSONException{
		String query = String.format("t=%s&y=%s",URLEncoder.encode(title, CHARSET), year);
		    

		URLConnection connection = new URL(URL + query).openConnection();
		connection.setRequestProperty("Accept-Charset", CHARSET);
		InputStream response = connection.getInputStream();
		Scanner scanner = new Scanner(response); 
		String responseBody = scanner.useDelimiter("\\A").next();
		scanner.close();
		JSONObject json = new JSONObject(responseBody);
		return json;
	}


	@Override
	public String toString() {
		return "[MOVIE TITLE] " +  getMovieTitle()
				+  "\n" +"[RELEASE DATE] " + getReleaseDate() 
				+ "\n" + "[PLOT] " + getPlot()
				+ "\n" + "[IMDB RATING] " +getIMDBRating() 
				+ "\n" + "[MY RATING] " + getMyRating();
				
	}

	

}
