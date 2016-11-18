import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.*;

public class SecondAPIclass{

	private JSONArray jsonarray;
	private String[] movieTitleArray; 
	private String[] releaseDateArray;
	private MovieObjectBuilder[] movieArray;
	
	//private final String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=4e7c712ecea3f30f533b2d29bedb2018&language=en-US";
	private String URL;

	public SecondAPIclass(String URL){
		this.URL = URL;
		try {
			jsonarray = FetchJSON();
			movieTitleArray = ExtractTitle();
			releaseDateArray = ExtractReleaseDate();
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		movieArray = BuildMoiveObjectArray();
	}
	
	
	
	
	
	

	private JSONArray FetchJSON() throws MalformedURLException, IOException, JSONException{
		
	    URLConnection connection = new URL(URL).openConnection();
		InputStream response = connection.getInputStream();
		
		Scanner scanner = new Scanner(response); 
		String responseBody = scanner.useDelimiter("\\A").next();
		scanner.close();
		JSONObject json = new JSONObject(responseBody);
		JSONArray jsonarray = json.getJSONArray("results");
		
		return jsonarray;
	}
		
		
		
		
		
		
	private String[] ExtractTitle() throws JSONException{
		String[] titles = new String[jsonarray.length()];
		for(int i=0; i<jsonarray.length(); i++){
			JSONObject jsonTemp = jsonarray.getJSONObject(i);
			String movieTitle = jsonTemp.getString("title");
			titles[i] = movieTitle;
		}
		return titles;
	}	
		
	private String[] ExtractReleaseDate() throws JSONException{
		String[] dates = new String[jsonarray.length()];
		for(int i=0; i<jsonarray.length(); i++){
			JSONObject jsonTemp = jsonarray.getJSONObject(i);
			String releaseDate = jsonTemp.getString("release_date");
			dates[i] = releaseDate;
		}
		return dates;
	}
	
	
	private MovieObjectBuilder[] BuildMoiveObjectArray(){
	
		MovieObjectBuilder[] MOBarray = new MovieObjectBuilder[movieTitleArray.length]; 
		for(int i=0; i<movieTitleArray.length; i++){
			String justTheYear = releaseDateArray[i].substring(0,4);
			//System.out.println(movieTitleArray[i]);
			//System.out.println(justTheYear);
			MovieObjectBuilder movie = new MovieObjectBuilder(movieTitleArray[i], justTheYear);
			MOBarray[i] = movie;
		}
		return MOBarray;
	}
	
	
	public MovieObjectBuilder[] getMovieArray(){
		return movieArray;
	}
	

	
	
	
}
