package planitpoker;

import java.util.UUID;
import planitpoker.mqtt.Publisher;
import planitpoker.mqtt.Subscriber;

/**
 * Controller for the CreateRoomPanel
 *
 * @author Jude Shin 
 */
public class JoinRoomController{
	
	private Main main;
	
	public JoinRoomController(Main main) {
		this.main = main;
	}
	
	public void joinRoom(String name) {
		System.out.println(" Joining a room..." + name);
		initMqtt(name);
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

