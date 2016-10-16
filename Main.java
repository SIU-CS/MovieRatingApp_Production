import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLConnection;
import java.util.Scanner;


public class Main {
	
	

	public static void main(String[] args) throws MalformedURLException, IOException {
		final int ARRAYSIZE = 500;
		
		WantToSee[] array = new WantToSee[ARRAYSIZE];
		
		WantToSee movie = new WantToSee("Nightmare On Elm Street");
		WantToSee movie1 = new WantToSee("The Kid", "2000");
		array[0] = movie;
		array[1]= movie1;
		System.out.println(array[0].toString());
		System.out.println("");
		System.out.println(array[1].toString());
		
		
		
		/*ignore the rest
		System.out.println("");
		
		String url = "http://www.omdbapi.com/?";
		String charset = "UTF-8";
		String title = "the kid";
		

		String query = String.format("t=%s",URLEncoder.encode(title, charset));
		    

		URLConnection connection = new URL(url + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();
		// Do some stuff with the data
		
		try (Scanner scanner = new Scanner(response)) {
			String responseBody = scanner.useDelimiter("\\A").next();
			scanner.close();
		    System.out.println(responseBody);
		}
		*/
	}

}
