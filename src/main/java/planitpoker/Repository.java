package planitpoker;

import java.util.*;
import java.util.concurrent.*;
import java.beans.*;
import java.io.*;

import planitpoker.mqtt.*;

/**
 * Singleton Data Repository for all the information that is being stored in our program
 *
 * @author Jude Shin 

/**
 * Singleton Data Repository for all the information that is being stored in our program
 *
 * @author Jude Shin 
 */
public class Repository extends PropertyChangeSupport{
	private static Repository instance;

	private User currentUser; 

	private ArrayList<User> users;
	private Story activeStory;
	private String currentRoomName;

	private ArrayList<Story> stories = new ArrayList<>();

	public ArrayList<Story> getStories() {
		return stories;
	}

	public enum Type { HOST, CLIENT }
	private Type type; 

	private final String[] votingMethodNames = {"Sequential", "Fibonacci"};;
	private final Double[][] votingMethodNumbers = {{0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, {0.0, 0.5, 1.0, 2.0, 3.0, 5.0, 8.0}};

	private Queue<PublishItem> publishQueue;
	
	private Repository() {
		super(new Object());
		currentUser = null;
		users = new ArrayList<User>();
		activeStory = null;
		currentRoomName = null;
		publishQueue = new LinkedBlockingQueue<>();
	};

	public void addStory(Story story) {
		stories.add(story);
		firePropertyChange("stories", null, stories);
	}

	public static Repository getInstance() {
		if (instance == null) { instance = new Repository(); }
		return instance;
	}
	
	public User getCurrentUser() { return currentUser; }
	public void setCurrentUser (User currentUser, boolean isSilent) {
		try {
			this.currentUser = currentUser; 
			if (!isSilent) { 
				PublishItem publishItem = new PublishItem("currentUser", ByteConverter.toBytes(currentUser), 0);
				pushPublishQueue(publishItem); 
			}
		} catch (IOException e) {
			System.out.println("Error in Repository: " + e);
		}
	}

	public Type getType() { return type; }
	public void setType(Type type, boolean isSilent) {
		try {
		this.type = type; 
		if (!isSilent) { 
			PublishItem publishItem = new PublishItem("type", ByteConverter.toBytes(currentUser), 0);
			pushPublishQueue(publishItem); 
		}
		} catch (IOException e) {
			System.out.println("Error in Repository: " + e);
		}
	}

	public ArrayList<User> getUsers() { return users; }
	public void setUsers(ArrayList<User> users, boolean isSilent) { 
		try {
		this.users = users;
		if (!isSilent) { 
			PublishItem publishItem = new PublishItem("users", ByteConverter.toBytes(users), 0);
			pushPublishQueue(publishItem); 
		}
		} catch (IOException e) {
			System.out.println("Error in Repository: " + e);
		}
	}
	public void addUser(ArrayList<User> users, boolean isSilent) { 
		try {
		this.users.addAll(users);
		if (!isSilent) { 
			PublishItem publishItem = new PublishItem("users", ByteConverter.toBytes(users), 0);
			pushPublishQueue(publishItem); 
		}
		} catch (IOException e) {
			System.out.println("Error in Repository: " + e);
		}
	}
	public void addUser(User user, boolean isSilent) { 
		try {
		this.users.add(user);
		if (!isSilent) { 
			PublishItem publishItem = new PublishItem("users", ByteConverter.toBytes(users), 0); 
			pushPublishQueue(publishItem); 
		}
		} catch (IOException e) {
			System.out.println("Error in Repository: " + e);
		}
	}
	
	public Story getActiveStory() { return activeStory; }
	public void setActiveStory(Story story, boolean isSilent) { 
		try {
		this.activeStory = story;
		if (!isSilent) { 
			PublishItem publishItem = new PublishItem("story", ByteConverter.toBytes(activeStory), 0);
			pushPublishQueue(publishItem); 
		}
		} catch (IOException e) {
			System.out.println("Error in Repository: " + e);
		}
	}

	public String getCurrentRoomName() { return currentRoomName; }
	public void setCurrentRoomName(String roomName, boolean isSilent) {
		try {
		this.currentRoomName = roomName;
		if (!isSilent) { 
			PublishItem publishItem = new PublishItem("currentRoomName", ByteConverter.toBytes(currentRoomName), 0);
			pushPublishQueue(publishItem); 
		}
		} catch (IOException e) {
			System.out.println("Error in Repository: " + e);
		}
	}


	public String[] getVotingMethodNames() { return votingMethodNames; }
	public Double[][] getVotingMethodNumbers() { return votingMethodNumbers; }

	public void pushPublishQueue(PublishItem publishItem) { publishQueue.offer(publishItem); }
	public PublishItem popPublishQueue() {
		PublishItem publishItem = publishQueue.poll();
		return publishItem;
	}
	
	// ================================
}
