package org.pong;

import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.*;
import java.io.IOException;


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
		
		// ByteConverter.fromBytes(...)
		// Repository.getInstance().setVaraible(...);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		System.out.println("Delivered complete: " + iMqttDeliveryToken.getMessageId());
	}
}

