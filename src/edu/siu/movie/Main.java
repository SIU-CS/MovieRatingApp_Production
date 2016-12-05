package edu.siu.movie;

import java.util.ListIterator;
import java.util.Scanner;
import java.util.LinkedList;


public class Main {
	
	static LinkedList<MovieObjectBuilder> WantToSeeList = new LinkedList<MovieObjectBuilder>();
	static LinkedList<MovieObjectBuilder> NewReleasesList = new LinkedList<MovieObjectBuilder>();
	static LinkedList<MovieObjectBuilder> RecommendationList = new LinkedList<MovieObjectBuilder>();
	static LinkedList<MovieObjectBuilder> HaveSeenList = new LinkedList<MovieObjectBuilder>();
	
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to your movie tracker");
		MainMenu();
		
	}//end main
	
	
	
	
	public static void MainMenu(){
		while(true){
			System.out.println();
			System.out.println("MAIN MENU");
			System.out.println("Option 1: view your movie lists");
			System.out.println("Option 2: Add movies to your lists");
			System.out.println("Option 3: Exit");
			
			Scanner scan = new Scanner(System.in);
			String selection = scan.next();
			
			if(selection.equals("1")){
				ListMenu();
			}
			if(selection.equals("2")){
				AddMovies();
			}
			if(selection.equals("3")){
				scan.close();
				System.exit(0);
			}
			else{
				System.out.println("Invalid input");
			}
		
		
		}
	}
	
	
	
	
	
	
	
	public static void ListMenu(){
		while(true){
			System.out.println();
			System.out.println("LIST MENU");
			System.out.println("Option 1: view the movies you have seen");
			System.out.println("Option 2: view the movies you want to see");
			System.out.println("Option 3: view new releases");
			System.out.println("Option 4: View your recommendations");
			System.out.println("Option 5: Go back to main menu");
			
			Scanner scan = new Scanner(System.in);
			String selection = scan.next();
			
			if(selection.equals("1")){
				if(HaveSeenList.isEmpty()){
					System.out.println("The list is empty");
					MainMenu();
				}
				else{	
					for(int i=0; i < HaveSeenList.size(); i++){
					MovieObjectBuilder movie = HaveSeenList.get(i);
					System.out.println(movie.toString());
					System.out.println();
					}	
					MainMenu();
				}
			}
			
			if(selection.equals("2")){
				if(WantToSeeList.isEmpty()){
					System.out.println("The list is empty");
					MainMenu();
				}
				else{	
					for(int i=0; i < WantToSeeList.size(); i++){
					MovieObjectBuilder movie = WantToSeeList.get(i);
					System.out.println(movie.toString());
					System.out.println();
					}	
					MainMenu();
				}
			}
			
			if(selection.equals("3")){
				NewReleases relist = new NewReleases();
				MovieObjectBuilder[] rearray = relist.getNewReleaseList();
				for(int i=0; i<rearray.length; i++){
					NewReleasesList.add(rearray[i]);
					System.out.println(rearray[i].toString());
					System.out.println();
				}
				MainMenu();
			}
		
			if(selection.equals("4")){
				if(HaveSeenList.isEmpty()){
					System.out.println("You must add movies to your HAVE SEEN list so we can make recommendations");
					MainMenu();
				}
				else{	
					//This should be a method [FindHighestRated()]
					MovieObjectBuilder highestRated = HaveSeenList.get(0);
					for(int i = 0; i <= HaveSeenList.size()-1; i++){
						MovieObjectBuilder currentMovie = HaveSeenList.get(i);
						if(Integer.parseInt(currentMovie.getMyRating()) > Integer.parseInt(highestRated.getMyRating())){
							highestRated = currentMovie;
						}
					}//End
					Recommendations rec = new Recommendations(highestRated);
					MovieObjectBuilder[] recArray = rec.getRecommendationList();
					for(int i=0; i< recArray.length; i++){
						RecommendationList.add(recArray[i]);
						System.out.println(recArray[i].toString());
						System.out.println();
					}
					MainMenu();
				}
			}

			if(selection.equals("5")){ //Goes back to main menu
				MainMenu();
			}
			else{ //returns control to while loop in this method 
				System.out.println("Invalid input");
			}
		
			
		}
	}
	
	
	
	
	
	public static void AddMovies(){
		while(true){
			String title;
			String year;
			System.out.println();
			System.out.println("Add Movies Menu");
			System.out.println("Option 1: Add to the moives you have seen");
			System.out.println("Option 2: Add to the movies you want to see");
			System.out.println("Option 3: Add moives to new releases");
			System.out.println("Option 4: Go back to main menu");
			
			Scanner scan = new Scanner(System.in);
			String selection = scan.next();
			
			Scanner sc = new Scanner(System.in).useDelimiter("\\n");  // USE THIS SCANNER OBJECT IN YOUR CODE BELOW
			
			if(selection.equals("1")){
				System.out.println("Enter a moive title");
				title = sc.nextLine();
				MovieObjectBuilder movie = new MovieObjectBuilder(title);
				System.out.println("How would you rate this movie?  (1 to 5)");
				boolean tryAgain = true;
				while(tryAgain){
					String rating = scan.next();
					if(rating.equals("1")||rating.equals("2")||rating.equals("3")||rating.equals("4")||rating.equals("5")){
						movie.setMyRating(rating);
						tryAgain = false;
					}else{
						System.out.println("You must enter a number between 1 and 5");
					}
				}
				HaveSeenList.add(movie);
				MainMenu();
			}
			if(selection.equals("2")){
				System.out.println("Enter a moive title");
				title = sc.nextLine();
				MovieObjectBuilder movie = new MovieObjectBuilder(title);
				WantToSeeList.add(movie);
				MainMenu();
			}
			if(selection.equals("3")){
				System.out.println("Enter a moive title");
				title = sc.nextLine();
				MovieObjectBuilder movie = new MovieObjectBuilder(title);
				NewReleasesList.add(movie);
				MainMenu();
			}
			if(selection.equals("4")){ //Goes back to main menu
				MainMenu();  
			}
			else{ //returns control to while loop in this method 
				System.out.println("Invalid input");
			}
		}
	}
	
	private MovieObjectBuilder FindHighestRatedMovie(){
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

	
	
}
