package edu.siu.movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Recommendations{
	
	private MovieObjectBuilder[] RecommendationList;
	
	private final String TitleSearchURL = "https://api.themoviedb.org/3/search/movie?api_key=4e7c712ecea3f30f533b2d29bedb2018&language=en-US&query=";
	private final String IDSearchURL = "https://api.themoviedb.org/3/movie/ /recommendations?api_key=4e7c712ecea3f30f533b2d29bedb2018&language=en-US&page=1";
	

	private MovieObjectBuilder highestRated;
	private String title;
	private String ID;
	
	public Recommendations(MovieObjectBuilder highestRated){
		this.highestRated = highestRated;
		title = highestRated.getMovieTitle();
		try {
			this.ID = FindMovieID();
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		SecondAPIclass Recommendations = new SecondAPIclass(BuildIdURL());
	    this.RecommendationList = Recommendations.getMovieArray();
	}

	
	private String BuildTitleURL(){
		String encode = title.replace(" ","%20");
		String query = TitleSearchURL.concat(encode);
		return query;
	}
	
	private String BuildIdURL(){
		String query = IDSearchURL.replace(" ",ID);
		return query;
	}

	
	private String FindMovieID() throws MalformedURLException, IOException, JSONException{
			//String query = String.format("t=%s",URLEncoder.encode(buildTitleURL(), CHARSET));
			String query = BuildTitleURL();
		    URLConnection connection = new URL(query).openConnection();
			InputStream response = connection.getInputStream();
			Scanner scanner = new Scanner(response); 
			String responseBody = scanner.useDelimiter("\\A").next();
			scanner.close();
			
			JSONObject json = new JSONObject(responseBody);
			JSONArray jsonarray = json.getJSONArray("results");
			JSONObject temp = jsonarray.getJSONObject(0);
			String id = temp.getString("id");
			
			return id;
		}

	
	
	public MovieObjectBuilder[] getRecommendationList(){
		return RecommendationList;
	}
	
	
}
