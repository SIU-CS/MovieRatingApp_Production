package edu.siu.movie;

public class NewReleases{

	private final String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=4e7c712ecea3f30f533b2d29bedb2018&language=en-US";
	private MovieObjectBuilder[] NewReleaseList;
	
	
	public NewReleases(){
		SecondAPIclass ReleaseList = new SecondAPIclass(URL);
	    this.NewReleaseList = ReleaseList.getMovieArray();
	}
	
	
	public MovieObjectBuilder[] getNewReleaseList(){
		return NewReleaseList;
	}


}
