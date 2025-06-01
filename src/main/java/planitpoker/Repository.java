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
	HashMap<String, Double> votes;
	private int currentStoryIndex;

	private ArrayList<Story> stories;

	public enum Type { HOST, CLIENT }
	private Type type;

	private final String[] votingMethodNames = {"Sequential", "Fibonacci"};
	private final Double[][] votingMethodNumbers = {
		{0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0}, 
		{0.0, 1.0, 2.0, 3.0, 5.0, 8.0, 13.0, 21.0, 34.0, 55.0, 89.0}};
	private int votingMethodIndex;

	private Queue<PublishItem> publishQueue;
	
	private Repository() {
		super(new Object());
		currentUser = null;
		users = new ArrayList<User>();
		currentRoomName = null;
		votingStarted = false;
		votes = new HashMap<>();
		currentStoryIndex = -1;
		stories = new ArrayList<>();
		publishQueue = new LinkedBlockingQueue<>();
		votingMethodIndex = 0;
	}

	public static Repository getInstance() {
		if (instance == null) { instance = new Repository(); }
		return instance;
	}
	
	public User getCurrentUser() { return currentUser; }
	public void setCurrentUser(User currentUser, boolean isSilent) {
		try {
			this.currentUser = currentUser;
			if (!isSilent) {
				PublishItem publishItem = new PublishItem("join", ByteConverter.toBytes(currentUser), 2);
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

	public ArrayList<User> getUsers() { return users; }
	public void setUsers(ArrayList<User> users, boolean isSilent) { 
		try {
			this.users = users;
			if (!isSilent) { 
				PublishItem publishItem = new PublishItem("users", ByteConverter.toBytes(users), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("users", null, this.users);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}
	public void addUser(User user, boolean isSilent) { 
		try {
			this.users.add(user);
			if (!isSilent) {
				PublishItem publishItem = new PublishItem("users", ByteConverter.toBytes(users), 2);
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
				PublishItem publishItem = new PublishItem("timeLeft", ByteConverter.toBytes(timeLeft), 0);
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
				PublishItem publishItem = new PublishItem("votingStarted", ByteConverter.toBytes(votingStarted), 2);
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
				PublishItem publishItem = new PublishItem("votes", ByteConverter.toBytes(votes), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("votes", null, this.votes);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}
	public void addVote(User user, double score, boolean isSilent) {
		try {
			Vote vote = new Vote(user, score);

			this.votes.put(user.getName(), score);

			if (!isSilent) { 
				PublishItem publishItem = new PublishItem("vote", ByteConverter.toBytes(vote), 2);
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
				PublishItem publishItem = new PublishItem("currentStoryIndex", ByteConverter.toBytes(currentStoryIndex), 2);
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
				PublishItem publishItem = new PublishItem("currentStoryIndex", ByteConverter.toBytes(currentStoryIndex), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("currentStoryIndex", null, this.currentStoryIndex);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}

	public ArrayList<Story> getStories() { return stories; }
	public void setStories(ArrayList<Story> stories, boolean isSilent) { 
		try {
			this.stories = stories;
			if (!isSilent) { 
				PublishItem publishItem = new PublishItem("stories", ByteConverter.toBytes(stories), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("stories", null, this.stories);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}
	public void addStory(Story story, boolean isSilent) { 
		try {
			this.stories.add(story);
			if (!isSilent) { 
				PublishItem publishItem = new PublishItem("stories", ByteConverter.toBytes(stories), 2);
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
				PublishItem publishItem = new PublishItem("votingMethodIndex", ByteConverter.toBytes(votingMethodIndex), 2);
				pushPublishQueue(publishItem); 
			}
			firePropertyChange("votingMethodIndex", null, this.votingMethodIndex);
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}

	public void pushPublishQueue(PublishItem publishItem) { publishQueue.offer(publishItem); }
	public PublishItem popPublishQueue() {
		PublishItem publishItem = publishQueue.poll();
		return publishItem;
	}
	
	// ================================

	public void publishInit() { 
		try {
			pushPublishQueue(new PublishItem("users", ByteConverter.toBytes(users), 2));
			pushPublishQueue(new PublishItem("timeLeft", ByteConverter.toBytes(timeLeft), 0));
			pushPublishQueue(new PublishItem("votingStarted", ByteConverter.toBytes(votingStarted), 2));
			pushPublishQueue(new PublishItem("votes", ByteConverter.toBytes(votes), 2));
			pushPublishQueue(new PublishItem("currentStoryIndex", ByteConverter.toBytes(currentStoryIndex), 2));
			pushPublishQueue(new PublishItem("stories", ByteConverter.toBytes(stories), 2));
			pushPublishQueue(new PublishItem("votingMethodIndex", ByteConverter.toBytes(votingMethodIndex), 2));
		} catch (IOException e) {
			logger.error("Error in Repository: " + e);
		}
	}
}
