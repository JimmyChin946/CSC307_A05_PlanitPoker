package planitpoker.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import planitpoker.*;

/**
 * Publishes data to the cloud with mqtt 
 * 
 * @author Jude Shin 
 * 
 */
public class Publisher implements Runnable {
	private Logger logger = LoggerFactory.getLogger(Publisher.class);

	private String broker;
	private String topic;
	private String id;

	public Publisher(String broker, String topic, String id) {
		this.broker = broker;
		this.topic = topic;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			MqttClient client = new MqttClient(broker, id);
			client.connect();
			// System.out.println("Connected to BROKER: " + broker);
			logger.info("Connected to BROKER: " + broker);

			while (true) {
				PublishItem publishItem = Repository.getInstance().popPublishQueue();
				if (publishItem == null) { continue; }

				byte[] content = publishItem.getMessage();
				String subTopic = publishItem.getSubTopic();
				int qos = publishItem.getQos();

				String fullTopic = topic + "/" + subTopic;
				MqttMessage message = new MqttMessage(content);
				message.setQos(qos); 

				if (client.isConnected()) {
					client.publish(fullTopic, message);
				}
			}
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}

