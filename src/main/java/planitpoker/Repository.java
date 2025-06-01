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
public class Repository extends PropertyChangeSupport {
	private Logger logger = LoggerFactory.getLogger(Repository.class);
	private static Repository instance;

	private User currentUser; 

	private ArrayList<User> users;
	private String currentRoomName;

	private boolean votingStarted;
	private Duration timeLeft;
	HashMap<User, Double> votes;
	private int currentStoryIndex;

	private ArrayList<Story> stories;

	public enum Type { HOST, CLIENT }
	private Type type; 

	private final String[] votingMethodNames = {"Sequential", "Fibonacci"};;
	private final Double[][] votingMethodNumbers = {{0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, {0.0, 0.5, 1.0, 2.0, 3.0, 5.0, 8.0}};
	private int votingMethodIndex;

	private Queue<PublishItem> publishQueue;
	
	private Repository() {
		super(new Object());
		currentUser = null;
		users = new ArrayList<User>();
		currentRoomName = null;
		votingStarted = false;
		votes = new HashMap<>();
		currentStoryIndex = 0;
		stories = new ArrayList<>();
		publishQueue = new LinkedBlockingQueue<>();
		votingMethodIndex = 0;
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
			firePropertyChange("currentUser", null, this.currentUser);
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
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
			firePropertyChange("type", null, this.type);
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
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
			firePropertyChange("users", null, this.users);
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
		}
	}
	public void addUser(ArrayList<User> users, boolean isSilent) { 
		try {
			this.users.addAll(users);
			if (!isSilent) { 
				PublishItem publishItem = new PublishItem("users", ByteConverter.toBytes(users), 0);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("users", null, this.users);
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
		}
	}
	public void addUser(User user, boolean isSilent) { 
		try {
			this.users.add(user);
			if (!isSilent) { 
				PublishItem publishItem = new PublishItem("users", ByteConverter.toBytes(users), 0); 
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("users", null, this.users);
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
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
			firePropertyChange("currentRoomName", null, this.currentRoomName);
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
		}
	}

	public Duration getTimeLeft() { return timeLeft; }
	public void setTimeLeft(Duration timeLeft, boolean isSilent) {
		try {
			this.timeLeft = timeLeft;
			if (!isSilent) { 
				PublishItem publishItem = new PublishItem("timeLeft", ByteConverter.toBytes(timeLeft), 0);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("timeLeft", null, this.timeLeft);
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
		}
	}

	public boolean getVotingStarted() { return votingStarted; }
	public void setVotingStarted(boolean votingStarted, boolean isSilent) {
		try {
			this.votingStarted = votingStarted;
			if (!isSilent) { 
				PublishItem publishItem = new PublishItem("votingStarted", ByteConverter.toBytes(votingStarted), 0);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("votingStarted", null, this.votingStarted);
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
		}
	}

	public HashMap<User, Double> getVotes() { return votes; }
	public void setVotes(HashMap<User, Double> votes, boolean isSilent) {
		try {
			this.votes = votes;	
			if (!isSilent) { 
				PublishItem publishItem = new PublishItem("votes", ByteConverter.toBytes(votes), 0);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("votes", null, this.votes);
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
		}
	}
	public void addVote(User user, double score, boolean isSilent) {
		try {
			this.votes.put(user, score);
			if (!isSilent) { 
				PublishItem publishItem = new PublishItem("votes", ByteConverter.toBytes(votes), 0);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("votes", null, this.votes);
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
		}
	}

	public int getCurrentStoryIndex() { return currentStoryIndex; }
	public void setCurrentStoryIndex(int storyIndex, boolean isSilent) {
		try {
			this.currentStoryIndex = storyIndex;
			if (!isSilent) { 
				PublishItem publishItem = new PublishItem("currentStoryIndex", ByteConverter.toBytes(currentStoryIndex), 0);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("currentStoryIndex", null, this.stories);
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
		}
	}

	public ArrayList<Story> getStories() { return stories; }
	public void setStories(ArrayList<Story> stories, boolean isSilent) { 
		try {
			this.stories = stories;
			if (!isSilent) { 
				PublishItem publishItem = new PublishItem("stories", ByteConverter.toBytes(stories), 0);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("stories", null, this.stories);
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
		}
	}
	public void addStory(Story story, boolean isSilent) { 
		try {
			this.stories.add(story);
			if (!isSilent) { 
				PublishItem publishItem = new PublishItem("stories", ByteConverter.toBytes(stories), 0);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("stories", null, this.stories);
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
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
				PublishItem publishItem = new PublishItem("votingMethodIndex", ByteConverter.toBytes(votingMethodIndex), 0);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("votingMethodIndex", null, this.votingMethodIndex);
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
		}
	}

	public void pushPublishQueue(PublishItem publishItem) { publishQueue.offer(publishItem); }
	public PublishItem popPublishQueue() {
		PublishItem publishItem = publishQueue.poll();
		return publishItem;
	}
	
	// ================================

	public void publishResults() { 
		try {
			PublishItem publishItem = new PublishItem("results", ByteConverter.toBytes(stories), 0);
			pushPublishQueue(publishItem); 
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
		}
	}

	public void publishInit() { 
		try {
			// TODO : publish everything relevant
			PublishItem publishItem1 = new PublishItem("users", ByteConverter.toBytes(users), 0);
			pushPublishQueue(publishItem1); 

			// PublishItem publishItem2 = new PublishItem("", ByteConverter.toBytes(), 0);
			// pushPublishQueue(publishItem2); 
		} catch (IOException e) {
			// System.out.println("Error in Repository: " + e);
			logger.error("Error in Repository: " + e);
		}
	}
}
