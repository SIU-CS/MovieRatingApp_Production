
import java.util.Scanner;
import java.util.LinkedList;


public class Main {
	
	static LinkedList<MovieObjectBuilder> WantToSeeList = new LinkedList();
	static LinkedList<MovieObjectBuilder> NewReleases = new LinkedList();
	static LinkedList<MovieObjectBuilder> Recommendations = new LinkedList();
	static LinkedList<MovieObjectBuilder> HaveSeenList = new LinkedList();
	
	
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
					NewReleases.add(rearray[i]);
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
					for(int i=0; i < Recommendations.size(); i++){
					MovieObjectBuilder movie = Recommendations.get(i);
					System.out.println(movie.toString());
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
				String rating = scan.next();
				movie.setMyRating(rating);
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
				NewReleases.add(movie);
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

	
	
}
