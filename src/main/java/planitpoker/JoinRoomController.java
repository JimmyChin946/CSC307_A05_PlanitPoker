package planitpoker;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import planitpoker.mqtt.Publisher;
import planitpoker.mqtt.Subscriber;

/**
 * Controller for the CreateRoomPanel
 *
 * @author Jude Shin 
 */
public class JoinRoomController {
	private Logger logger = LoggerFactory.getLogger(JoinRoomController.class);

	private Main main;
	
	public JoinRoomController(Main main) { this.main = main; }
	
	public void joinRoom(String name) {
		logger.info("Joining a room..." + name);
		initMqtt(name);
		Repository.getInstance().setCurrentRoomName(name, false);
		switchGUI();
	}

	private void initMqtt(String roomName) {
		UUID id = UUID.randomUUID();

		Publisher publisher = new Publisher("tcp://test.mosquitto.org:1883", "csc-307/planit-poker/" + roomName, id + "-Publisher");
		new Thread(publisher).start();

		new Subscriber("tcp://test.mosquitto.org:1883", "csc-307/planit-poker/" + roomName + "/+", id + "-Subscriber");
	}
	
	private void switchGUI() {
		main.setTitle("Room");

		RoomPanel roomPanel = new RoomPanel(main);

		main.setContentPane(roomPanel);
		main.revalidate();
		main.repaint();
	}
}

