package planitpoker;

import java.util.*;

/**
 * Singleton Data Repository for all the information that is being stored in our program
 *
 * @author Jude Shin 
 */
public class Repository {
	private static Repository instance;
	private static void Repository() {};
	public Repository getInstance() {
		if (instance == null) { instance = new Repository(); }
		return instance;
	}
	
	// users
	private static Room currentRoom = new Room("TESTING ROOM", "Scrum");
	private static User currentUser = new User("John Doe", 1234);
	
	// stories
	private static ArrayList<String> names = new ArrayList<>();
	private static ArrayList<String> stories = new ArrayList<>();
	private static String[] modeOptions = {"Scrum", "Fibonacci", "Sequential", "Hours", "T-shirt", "Custom deck"};
	
	// testing code for export logic
	private static ArrayList<Vote> votes = new ArrayList<>(List.of(new Vote("Testing", "this is a testing vote that should be the default", 4.5)));

	// gettters and setters
	public static void addName(String name) { names.add(name); }
	public static void addStory(String story) { stories.add(story); }
	public static void setCurrentRoom (Room room) { currentRoom = room; }
	public static void setCurrentUser (User user) { currentUser = user; }
	public static String[] getModeOptions() { return modeOptions; }
	public static ArrayList<Vote> getVotes() { return votes; }
	public static Room getCurrentRoom() { return currentRoom; }
	public static User getCurrentUser() { return currentUser; }
}
