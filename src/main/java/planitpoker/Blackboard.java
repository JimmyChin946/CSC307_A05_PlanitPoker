package planitpoker;

import java.util.*;

/**
 * Data Repository for all the information that is being stored in our program
 *
 * @author Jude Shin 
 */
public class Blackboard {
	private static LinkedList<String> names = new LinkedList<>();
	private static LinkedList<String> stories = new LinkedList<>();
	private static Room currentRoom = new Room("TESTING ROOM", "Scrum");
	private static User currentUser = new User("John Doe", 1234);
	private static String[] modeOptions = {"Scrum", "Fibonacci", "Sequential", "Hours", "T-shirt", "Custom deck"};
	
	// testing code for export logic
	private static LinkedList<Vote> votes = new LinkedList<>(List.of(new Vote("Testing", "this is a testing vote that should be the default", 4.5)));
	
	public static void addName(String name) { names.add(name); }
	public static void addStory(String story) { stories.add(story); }
	public static void setCurrentRoom (Room room) { currentRoom = room; }
	public static void setCurrentUser (User user) { currentUser = user; }
	public static String[] getModeOptions() { return modeOptions; }
	public static LinkedList<Vote> getVotes() { return votes; }
	public static Room getCurrentRoom() { return currentRoom; }
	public static User getCurrentUser() { return currentUser; }
}
