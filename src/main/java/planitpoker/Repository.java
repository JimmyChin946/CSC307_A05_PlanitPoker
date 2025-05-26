package planitpoker;

import java.util.*;
import java.util.concurrent.*;
import java.beans.*;

/**
 * Singleton Data Repository for all the information that is being stored in our program
 *
 * @author Jude Shin 
 */
public class Repository extends PropertyChangeSupport{
	private static Repository instance;

	private Queue<PublishItem> publishQueue;

	private Repository() {
		super(new Object());
		publishQueue = new LinkedBlockingQueue<>();
	};

	public static Repository getInstance() {
		if (instance == null) { instance = new Repository(); }
		return instance;
	}
	
	// users
	private User currentUser = null; 
	private Room currentRoom = null; 
	
	// testing code for export logic
	private static ArrayList<Vote> votes = new ArrayList<>(List.of(new Vote("Testing", "this is a testing vote that should be the default", 4.5)));

	// gettters and setters
	public void pushPublishQueue(PublishItem publishItem) { publishQueue.offer(publishItem); }
	public PublishItem popPublishQueue() {
		PublishItem publishItem = publishQueue.poll();
		return publishItem;
	}

	public User getCurrentUser() { return currentUser; }
	public void setCurrentUser (User user) { currentUser = user; }

	public Room getCurrentRoom() { return currentRoom; }
	public void setCurrentRoom (Room room) { currentRoom = room; }
}
