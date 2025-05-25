package planitpoker;

import java.util.*;
import java.beans.*;

/**
 * Singleton Data Repository for all the information that is being stored in our program
 *
 * @author Jude Shin 
 */
public class Repository extends PropertyChangeSupport{
	private static Repository instance;

	private Repository() {
		super(new Object());
	};

	public static Repository getInstance() {
		if (instance == null) { instance = new Repository(); }
		return instance;
	}
	
	// users
	private static Room currentRoom = new Room("TESTING ROOM", "Scrum");
	private static User currentUser = new User("John Doe");
	
	// stories
	private static ArrayList<String> names = new ArrayList<>();
	private static ArrayList<String> stories = new ArrayList<>();
	private static String[] modeOptions = {"Scrum", "Fibonacci", "Sequential", "Hours", "T-shirt", "Custom deck"};
	
	// testing code for export logic
	private static ArrayList<Vote> votes = new ArrayList<>(List.of(new Vote("Testing", "this is a testing vote that should be the default", 4.5)));

	// gettters and setters
	public void addName(String name) { names.add(name); }
	public void addStory(String story) { stories.add(story); }
	public void setCurrentRoom (Room room) { currentRoom = room; }
	public void setCurrentUser (User user) { currentUser = user; }
	public String[] getModeOptions() { return modeOptions; }
	public ArrayList<Vote> getVotes() { return votes; }
	public Room getCurrentRoom() { return currentRoom; }
	public User getCurrentUser() { return currentUser; }
}
