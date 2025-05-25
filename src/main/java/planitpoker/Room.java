package planitpoker;

import java.util.*;

/**
 * Stores some information about a Room 
 *
 * @author Jude Shin 
 */
public class Room {
	private String name;
	private String mode;
	private int id; // may not need this
	private ArrayList<User> users;

	public Room (String name, String mode) {
		this.name = name;
		this.mode = mode;
		this.users = new ArrayList<>();
	}

	public String getName() { return name; }
	public int getId() { return id; }
	public String getMode() { return mode; }
	public ArrayList<User> getUsers() { return users; }

	public void addUser(User user) { users.add(user); }
}
