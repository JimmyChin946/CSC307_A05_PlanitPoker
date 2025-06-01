package planitpoker;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import planitpoker.mqtt.T7Publisher;
import planitpoker.mqtt.T7Subscriber;

/**
 * Controller for the CreateRoomPanel
 *
 * @author Jude Shin 
 */
public class T7JoinRoomController {
	private Logger logger = LoggerFactory.getLogger(T7JoinRoomController.class);

	private T7Main main;
	
	public T7JoinRoomController(T7Main main) { this.main = main; }
	
	public void joinRoom(String name) {
		logger.info("Joining a room..." + name);
		initMqtt(name);
		T7Repository.getInstance().setCurrentRoomName(name);
		switchGUI();
	}

	private void initMqtt(String roomName) {
		UUID id = UUID.randomUUID();

		T7Publisher publisher = new T7Publisher("tcp://test.mosquitto.org:1883", "csc-307/planit-poker/" + roomName, id + "-Publisher");
		new Thread(publisher).start();

		new T7Subscriber("tcp://test.mosquitto.org:1883", "csc-307/planit-poker/" + roomName + "/+", id + "-Subscriber");
	}
	
	private void switchGUI() {
		main.setTitle("Room");

		T7RoomPanel roomPanel = new T7RoomPanel(main);

		main.setContentPane(roomPanel);
		main.revalidate();
		main.repaint();
	}
}

