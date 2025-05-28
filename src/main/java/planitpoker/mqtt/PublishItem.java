package planitpoker.mqtt;

/**
 * A datastructure to hold a "message"
 * Holds the name of the topic
 * and the content of the message
 *
 * @author Jude Shin
 *
 */
public class PublishItem {
	private final String subTopic;
	private final byte[] message; 

	public PublishItem(String subTopic, byte[] message) {
		this.subTopic = subTopic; 
		this.message = message;	
	}

	public String getSubTopic() { return subTopic; }
	public byte[] getMessage() { return message; }
}
