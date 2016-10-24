
import java.util.Scanner;
import java.util.LinkedList;


public class Main {
	
	static LinkedList<WantToSee> WantToSeeList = new LinkedList();
	//CREATE have seen linkedlist HERE
	//CREATE new release linkedlist HERE
	
	
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
			System.out.println("Option 4: Go back to main menu");
			
			Scanner scan = new Scanner(System.in);
			String selection = scan.next();
			
			if(selection.equals("1")){
				//display movies in HAVE SEEN list
				
				MainMenu();
			}
			if(selection.equals("2")){
				if(WantToSeeList.isEmpty()){
					System.out.println("The list is empty");
					MainMenu();
				}
				else{	
					for(int i=0; i < WantToSeeList.size(); i++){
					WantToSee movie = WantToSeeList.get(i);
					System.out.println(movie.toString());
					System.out.println();
					}	
					MainMenu();
				}
			}
			if(selection.equals("3")){
				//display movies in NEW RELEASES list
				
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
	
	
	
	
	
	
	
	public static void AddMovies(){
		while(true){
			String title;
			String year;
			System.out.println();
			System.out.println("LIST MENU");
			System.out.println("Option 1: Add to the moives you have seen");
			System.out.println("Option 2: Add to the movies you want to see");
			System.out.println("Option 3: Add moives to new releases");
			System.out.println("Option 4: Go back to main menu");
			
			Scanner scan = new Scanner(System.in);
			String selection = scan.next();
			
			Scanner sc = new Scanner(System.in).useDelimiter("\\n");  // USE THIS SCANNER OBJECT IN YOUR CODE BELOW
			
			if(selection.equals("1")){
				//add movies to HAVE SEEN list
				
				MainMenu();
			}
			if(selection.equals("2")){
				System.out.println("Enter a moive title");
				title = sc.nextLine();
				WantToSee movie = new WantToSee(title);
				WantToSeeList.add(movie);
				MainMenu();
			}
			if(selection.equals("3")){
				//add movies to NEW RELEASES list
				
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
