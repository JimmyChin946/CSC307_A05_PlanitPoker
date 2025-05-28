package planitpoker;

import java.util.*;
import java.util.concurrent.*;
import java.beans.*;

import planitpoker.mqtt.PublishItem;

/**
 * Singleton Data Repository for all the information that is being stored in our program
 *
 * @author Jude Shin 
 */
public class Repository extends PropertyChangeSupport{
	// var def
	private static Repository instance;
	private User currentUser = null; 
	// private Room currentRoom = null; // TODO : put the singleton in this singleton?
	private final String[] votingMethodNames = {"Sequential", "Fibonacci"};;
	private final Double[][] votingMethodNumbers = {{0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, {0.0, 0.5, 1.0, 2.0, 3.0, 5.0, 8.0}};;
	private Queue<PublishItem> publishQueue;
	
	// singleton
	private Repository() {
		super(new Object());
		publishQueue = new LinkedBlockingQueue<>();
	};

	public static Repository getInstance() {
		if (instance == null) { instance = new Repository(); }
		return instance;
	}
	
	
	// gettters and setters
	public User getCurrentUser() { return currentUser; }
	public void setCurrentUser (User user) { currentUser = user; }
	// public Room getCurrentRoom() { return currentRoom; }
	// public void setCurrentRoom (Room room) { currentRoom = room; }

	public String[] getVotingMethodNames() { return votingMethodNames; }
	public Double[][] getVotingMethodNumbers() { return votingMethodNumbers; }

	public void pushPublishQueue(PublishItem publishItem) { publishQueue.offer(publishItem); }
	public PublishItem popPublishQueue() {
		PublishItem publishItem = publishQueue.poll();
		return publishItem;
	}
	
	// ================================
}
