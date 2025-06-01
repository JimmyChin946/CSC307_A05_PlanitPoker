package planitpoker.mqtt;

/**
 * A datastructure to hold a "message"
 * Holds the name of the topic
 * and the content of the message
 * and the priority of it (QOS)
 *
 * @author Jude Shin
 *
 */
public class T7PublishItem {
	private final String subTopic;
	private final byte[] message; 
	private final int qos;

	public T7PublishItem(String subTopic, byte[] message, int qos) {
		this.subTopic = subTopic; 
		this.message = message;	
		this.qos = qos;
	}

	public String getSubTopic() { return subTopic; }
	public byte[] getMessage() { return message; }
	public int getQos() { return qos; }
}
