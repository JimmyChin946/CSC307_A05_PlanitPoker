package planitpoker;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import planitpoker.mqtt.Publisher;
import planitpoker.mqtt.Subscriber;
import java.util.ArrayList;

/**
 * Controller for the CreateRoomPanel
 *
 * @author Jude Shin 
 */
public class CreateRoomController {
	private Logger logger = LoggerFactory.getLogger(CreateRoomController.class);
	private Main main;
	
	public CreateRoomController(Main main) { this.main = main; }
	
	public void createRoom(String name, int selectedIndex) {
		logger.info("Creating room..." + name + ", mode: " + selectedIndex);
		initMqtt(name);
		Repository.getInstance().setCurrentRoomName(name, false);

		Repository.getInstance().setVotingMethodIndex(selectedIndex, false);

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

