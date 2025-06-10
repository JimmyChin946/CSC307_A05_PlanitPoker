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
public class T7CreateRoomController {
	private Logger logger = LoggerFactory.getLogger(T7CreateRoomController.class);
	private T7Main main;
	
	public T7CreateRoomController(T7Main main) { this.main = main; }
	
	public void createRoom(String name, int selectedIndex) {
		logger.info("Creating room..." + name + ", mode: " + selectedIndex);
		initMqtt(name);
		T7Repository.getInstance().setCurrentRoomName(name);

		T7Repository.getInstance().setVotingMethodIndex(selectedIndex, false);

		switchGUI();
	}

	private void initMqtt(String roomName) {
		UUID id = UUID.randomUUID();

		T7Publisher publisher = new T7Publisher("tcp://test.mosquitto.org:1883", "csc-307/planit-poker/" + roomName, id + "-Publisher");
		new Thread(publisher).start();

		new T7Subscriber("tcp://test.mosquitto.org:1883", "csc-307/planit-poker/" + roomName + "/+", id + "-Subscriber");
	}
	
	private void switchGUI() {
		main.setTitle("Import from Taiga");
		T7TaigaImportController taigaImportController = new T7TaigaImportController(main);
		T7TaigaImportPanel taigaImportPanel = new T7TaigaImportPanel(taigaImportController, main);
		main.setContentPane(taigaImportPanel);
		main.revalidate();
		main.repaint();
	}
}

