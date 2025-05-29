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
	private static Repository instance;
	private User currentUser; // could be final?
	// private Room currentRoom = null; // TODO : put this in the singleton? 
	public enum Type { HOST, CLIENT }
	private Type type; // could be final?
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
	public Type getType() { return type; }
	public void setType(Type type) { this.type = type; }

	public String[] getVotingMethodNames() { return votingMethodNames; }
	public Double[][] getVotingMethodNumbers() { return votingMethodNumbers; }

	public void pushPublishQueue(PublishItem publishItem) { publishQueue.offer(publishItem); }
	public PublishItem popPublishQueue() {
		PublishItem publishItem = publishQueue.poll();
		return publishItem;
	}
	
	// ================================
}
