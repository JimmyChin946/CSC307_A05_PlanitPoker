package planitpoker;

import java.util.*;
import java.util.concurrent.*;
import java.beans.*;
import java.io.*;
import java.time.Duration;
import planitpoker.mqtt.*;
import org.slf4j.*;

/**
 * Singleton Data Repository for all the information that is being stored in our program
 *
 * @author Jude Shin 
 */
public class T7Repository extends PropertyChangeSupport {
	private Logger logger = LoggerFactory.getLogger(T7Repository.class);
	private static T7Repository instance;

	private T7User currentUser;

	private ArrayList<T7User> users;
	private String currentRoomName;

	private boolean votingStarted;
	private Duration timeLeft;
	HashMap<String, Double> votes;
	private int currentStoryIndex;

	private ArrayList<T7Story> stories;

	public enum Type { HOST, CLIENT }
	private Type type;

	private final String[] votingMethodNames = {"Sequential", "Fibonacci"};
	private final Double[][] votingMethodNumbers = {
		{0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0}, 
		{0.0, 1.0, 2.0, 3.0, 5.0, 8.0, 13.0, 21.0, 34.0, 55.0, 89.0}};
	private int votingMethodIndex;

	private Queue<T7PublishItem> publishQueue;
	
	private T7Repository() {
		super(new Object());
		currentUser = null;
		users = new ArrayList<>();
		currentRoomName = null;
		votingStarted = false;
		votes = new HashMap<>();
		currentStoryIndex = -1;
		stories = new ArrayList<>();
		publishQueue = new LinkedBlockingQueue<>();
		votingMethodIndex = 0;
	}

	public static T7Repository getInstance() {
		if (instance == null) { instance = new T7Repository(); }
		return instance;
	}
	
	public T7User getCurrentUser() { return currentUser; }
	public void setCurrentUser(T7User currentUser, boolean isSilent) {
		try {
			this.currentUser = currentUser;
			if (!isSilent) {
				T7PublishItem publishItem = new T7PublishItem("join", T7ByteConverter.toBytes(currentUser), 2);
				pushPublishQueue(publishItem);
			}
			firePropertyChange("currentUser", null, this.currentUser);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}

	public Type getType() { return type; }
	public void setType(Type type) {
		this.type = type;
		firePropertyChange("type", null, this.type);
	}

	public ArrayList<T7User> getUsers() { return users; }
	public void setUsers(ArrayList<T7User> users, boolean isSilent) {
		try {
			this.users = users;
			if (!isSilent) { 
				T7PublishItem publishItem = new T7PublishItem("users", T7ByteConverter.toBytes(users), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("users", null, this.users);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}
	public void addUser(T7User user, boolean isSilent) {
		try {
			this.users.add(user);
			if (!isSilent) {
				T7PublishItem publishItem = new T7PublishItem("users", T7ByteConverter.toBytes(users), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("users", null, this.users);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}

	public String getCurrentRoomName() { return currentRoomName; }
	public void setCurrentRoomName(String roomName) {
		this.currentRoomName = roomName;
		firePropertyChange("currentRoomName", null, this.currentRoomName);
	}

	public Duration getTimeLeft() { return timeLeft; }
	public void setTimeLeft(Duration timeLeft, boolean isSilent) {
		try {
			this.timeLeft = timeLeft;
			if (!isSilent) { 
				T7PublishItem publishItem = new T7PublishItem("timeLeft", T7ByteConverter.toBytes(timeLeft), 0);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("timeLeft", null, this.timeLeft);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}

	public boolean getVotingStarted() { return votingStarted; }
	public void setVotingStarted(boolean votingStarted, boolean isSilent) {
		try {
			this.votingStarted = votingStarted;
			if (!isSilent) { 
				T7PublishItem publishItem = new T7PublishItem("votingStarted", T7ByteConverter.toBytes(votingStarted), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("votingStarted", null, this.votingStarted);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}

	public HashMap<String, Double> getVotes() { return votes; }
	public void setVotes(HashMap<String, Double> votes, boolean isSilent) {
		try {
			this.votes = votes;	
			if (!isSilent) { 
				T7PublishItem publishItem = new T7PublishItem("votes", T7ByteConverter.toBytes(votes), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("votes", null, this.votes);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}
	public void addVote(T7User user, double score, boolean isSilent) {
		try {
			T7Vote vote = new T7Vote(user, score);

			this.votes.put(user.getName(), score);

			if (!isSilent) { 
				T7PublishItem publishItem = new T7PublishItem("vote", T7ByteConverter.toBytes(vote), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("vote", null, vote);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}

	public int getCurrentStoryIndex() { return currentStoryIndex; }
	public void setCurrentStoryIndex(int storyIndex, boolean isSilent) {
		try {
			this.currentStoryIndex = storyIndex;
			if (!isSilent) { 
				T7PublishItem publishItem = new T7PublishItem("currentStoryIndex", T7ByteConverter.toBytes(currentStoryIndex), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("currentStoryIndex", null, this.currentStoryIndex);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}
	public void incrementCurrentStoryIndex(boolean isSilent) {
		try {
			this.currentStoryIndex = currentStoryIndex + 1;
			if (!isSilent) { 
				T7PublishItem publishItem = new T7PublishItem("currentStoryIndex", T7ByteConverter.toBytes(currentStoryIndex), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("currentStoryIndex", null, this.currentStoryIndex);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}

	public ArrayList<T7Story> getStories() { return stories; }
	public void setStories(ArrayList<T7Story> stories, boolean isSilent) {
		try {
			this.stories = stories;
			if (!isSilent) { 
				T7PublishItem publishItem = new T7PublishItem("stories", T7ByteConverter.toBytes(stories), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("stories", null, this.stories);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}
	public void addStory(T7Story story, boolean isSilent) {
		try {
			this.stories.add(story);
			if (!isSilent) { 
				T7PublishItem publishItem = new T7PublishItem("stories", T7ByteConverter.toBytes(stories), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("stories", null, this.stories);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}

	public String[] getVotingMethodNames() { return votingMethodNames; }
	public Double[][] getVotingMethodNumbers() { return votingMethodNumbers; }
	public int getVotingMethodIndex() { return votingMethodIndex; }
	public void setVotingMethodIndex(int votingMethodIndex, boolean isSilent) {
		try {
			this.votingMethodIndex = votingMethodIndex;
			if (!isSilent) { 
				T7PublishItem publishItem = new T7PublishItem("votingMethodIndex", T7ByteConverter.toBytes(votingMethodIndex), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("votingMethodIndex", null, this.votingMethodIndex);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}

	public void pushPublishQueue(T7PublishItem publishItem) { publishQueue.offer(publishItem); }
	public T7PublishItem popPublishQueue() {
		T7PublishItem publishItem = publishQueue.poll();
		return publishItem;
	}
	
	// ================================

	public void publishInit() { 
		try {
			pushPublishQueue(new T7PublishItem("users", T7ByteConverter.toBytes(users), 2));
			pushPublishQueue(new T7PublishItem("timeLeft", T7ByteConverter.toBytes(timeLeft), 0));
			pushPublishQueue(new T7PublishItem("votingStarted", T7ByteConverter.toBytes(votingStarted), 2));
			pushPublishQueue(new T7PublishItem("votes", T7ByteConverter.toBytes(votes), 2));
			pushPublishQueue(new T7PublishItem("currentStoryIndex", T7ByteConverter.toBytes(currentStoryIndex), 2));
			pushPublishQueue(new T7PublishItem("stories", T7ByteConverter.toBytes(stories), 2));
			pushPublishQueue(new T7PublishItem("votingMethodIndex", T7ByteConverter.toBytes(votingMethodIndex), 2));
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}
}
