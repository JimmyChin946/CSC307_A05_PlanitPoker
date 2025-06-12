package planitpoker.mqtt;

import java.util.*;
import org.eclipse.paho.client.mqttv3.*;
import java.io.IOException;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import planitpoker.*;

/**
 * Subscribes for the cloud based on a topic
 *
 * @author Jude Shin
 *
 */
public class T7Subscriber implements MqttCallback {
	private Logger logger = LoggerFactory.getLogger(T7Subscriber.class);

	public T7Subscriber(String broker, String topic, String id) {
		try {
			MqttClient client = new MqttClient(broker, id, null);
			client.setCallback(this);
			client.connect();
			logger.info("Connected to BROKER: " + broker);
			client.subscribe(topic);
			logger.info("Subscribed to TOPIC: " + topic);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void connectionLost(Throwable throwable) {
		logger.info("Connection lost: " + throwable.getMessage());
	}

	@Override
	public void messageArrived(String s, MqttMessage mqttMessage) throws IOException, ClassNotFoundException {
		logger.info("Message Arrived: " + s + ": " + new String(mqttMessage.getPayload()));

		byte[] bytes = mqttMessage.getPayload();
		String[] topics = s.split("/");
		String subTopic = topics[topics.length - 1];

		if (T7Repository.getInstance().getType() == T7Repository.Type.HOST) {
			switch (subTopic) {
				// votes on the currentStoryIndex 
				case "votes":
					HashMap<String, Double> votes = T7ByteConverter.fromBytes(bytes, HashMap.class);
					T7Repository.getInstance().setVotes(votes, true);
					break;
				case "vote":
					T7Vote vote = T7ByteConverter.fromBytes(bytes, T7Vote.class);
					T7Repository.getInstance().addVote(vote.getUser(), vote.getScore(), true);
					break;
				case "join": // could be changed to user
					T7User user = T7ByteConverter.fromBytes(bytes, T7User.class);
					T7Repository.getInstance().addUser(user, true);
					T7Repository.getInstance().publishInit();
					break;
			}
		}
		else {
			switch (subTopic) {
				// votingMethodIndex
				case "votingMethodIndex":
					int votingMethodIndex = T7ByteConverter.fromBytes(bytes, Integer.class);
					T7Repository.getInstance().setVotingMethodIndex(votingMethodIndex, true);
					break;
				// stories
				case "stories":
					ArrayList<T7Story> stories = T7ByteConverter.fromBytes(bytes, ArrayList.class);
					T7Repository.getInstance().setStories(stories, true);
					break;
				// timeLeft 
				case "timeLeft":
					Duration timeLeft = T7ByteConverter.fromBytes(bytes, Duration.class);
					T7Repository.getInstance().setTimeLeft(timeLeft, true);
					break;
				// currentStoryIndex
				case "currentStoryIndex":
					int currentStoryIndex = T7ByteConverter.fromBytes(bytes, Integer.class);
					T7Repository.getInstance().setCurrentStoryIndex(currentStoryIndex, true);
					break;
				case "votingStarted":
					boolean votingStarted = T7ByteConverter.fromBytes(bytes, Boolean.class);
					T7Repository.getInstance().setVotingStarted(votingStarted, true);
					break;
				case "vote":
					T7Vote vote = T7ByteConverter.fromBytes(bytes, T7Vote.class);
					T7Repository.getInstance().addVote(vote.getUser(), vote.getScore(), true);
					break;
				case "users":
					ArrayList<T7User> users = T7ByteConverter.fromBytes(bytes, ArrayList.class);
					T7Repository.getInstance().setUsers(users, true);
					break;
			}
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		logger.info("Delivered complete: " + iMqttDeliveryToken.getMessageId());
	}
}

