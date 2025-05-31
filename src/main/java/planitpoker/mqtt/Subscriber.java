package planitpoker.mqtt;

import java.util.*;
import org.eclipse.paho.client.mqttv3.*;
import java.io.IOException;
import java.time.Duration;
import planitpoker.*;

/**
 * Subscribes for the cloud based on a topic
 *
 * @author Jude Shin
 * 
 */
public class Subscriber implements MqttCallback {
	public Subscriber(String broker, String topic, String id) {
		try {
			MqttClient client = new MqttClient(broker, id);
			client.setCallback(this);
			client.connect();
			System.out.println("Connected to BROKER: " + broker);
			client.subscribe(topic);
			System.out.println("Subscribed to TOPIC: " + topic);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void connectionLost(Throwable throwable) {
		System.out.println("Connection lost: " + throwable.getMessage());
	}

	@Override
	public void messageArrived(String s, MqttMessage mqttMessage) throws IOException, ClassNotFoundException {
		byte[] bytes = mqttMessage.getPayload();
		String[] topics = s.split("/");
		String subTopic = topics[topics.length - 1];
		
		if (Repository.getInstance().getType() == Repository.Type.HOST) {
			switch (subTopic) {
				// votes on the currentStoryIndex 
				case "votes":
					HashMap<User, Double> votes = ByteConverter.fromBytes(bytes, HashMap.class);
					Repository.getInstance().setVotes(votes, true);
					break;
			}
		}
		else {
			switch (subTopic) {
				// stories
				case "stories":
					ArrayList<Story> stories = ByteConverter.fromBytes(bytes, ArrayList.class);
					Repository.getInstance().setStories(stories, true);
					break;
				// timeLeft 
				case "timeLeft":
					Duration timeLeft = ByteConverter.fromBytes(bytes, Duration.class);
					Repository.getInstance().setTimeLeft(timeLeft, true);
					break;
				case "currentStoryIndex":
					int currentStoryIndex = ByteConverter.fromBytes(bytes, Integer.class);
					Repository.getInstance().setCurrentStoryIndex(currentStoryIndex, true);
					break;
			}
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		System.out.println("Delivered complete: " + iMqttDeliveryToken.getMessageId());
	}
}

