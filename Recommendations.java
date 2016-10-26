import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Recommendations {

	private JSONArray jsonarray;
	private String[] movieTitleArray; 
	private String[] releaseDateArray;
	private MovieObjectBuilder[] NewReleaseList;
	
	private final String URL = "https://api.themoviedb.org/3/search/movie?api_key=4e7c712ecea3f30f533b2d29bedb2018&language=en-US&query=Nightmare%20before%20christmas";
	private final String CHARSET = "UTF-8";
	
	private LinkedList<MovieObjectBuilder> HaveSeenList = new LinkedList();
	
	
	public Recommendations(LinkedList<MovieObjectBuilder> HaveSeenList){
		this.HaveSeenList = HaveSeenList;
	}
	
	
	
	private MovieObjectBuilder FindHighestRatedMoive(){
		ListIterator<MovieObjectBuilder> iterator = HaveSeenList.listIterator();
		MovieObjectBuilder highestrated = HaveSeenList.get(0);
		while(iterator.hasNext()){
			MovieObjectBuilder current = iterator.next();
			if(Integer.parseInt(current.getMyRating()) > Integer.parseInt(highestrated.getMyRating())){
				highestrated = current;
			}
		}
		
		return highestrated;
	}
	
	
	
	private int FindMovieID(MovieObjectBuilder movie) throws MalformedURLException, IOException, JSONException{
			int id = 0;
			String query = String.format("t=%s",URLEncoder.encode(movie.getMovieTitle(), CHARSET));
			
		    URLConnection connection = new URL(URL + query).openConnection();
			InputStream response = connection.getInputStream();
			
			Scanner scanner = new Scanner(response); 
			String responseBody = scanner.useDelimiter("\\A").next();
			scanner.close();
			JSONObject json = new JSONObject(responseBody);
			JSONObject jsonTemp = new JSONObject();
			JSONArray jsonarray = json.getJSONArray("results");
			
			return id;
		}
	
}
