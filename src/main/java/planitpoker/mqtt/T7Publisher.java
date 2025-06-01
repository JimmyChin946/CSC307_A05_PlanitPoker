package planitpoker.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import planitpoker.*;

/**
 * Publishes data to the cloud with mqtt 
 * 
 * @author Jude Shin 
 * 
 */
public class T7Publisher implements Runnable {
	private Logger logger = LoggerFactory.getLogger(T7Publisher.class);

	private String broker;
	private String topic;
	private String id;

	public T7Publisher(String broker, String topic, String id) {
		this.broker = broker;
		this.topic = topic;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			MqttClient client = new MqttClient(broker, id, null);
			client.connect();
			logger.info("Connected to BROKER: " + broker);

			while (true) {
				T7PublishItem publishItem = T7Repository.getInstance().popPublishQueue();
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

