package planitpoker;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import planitpoker.mqtt.T7Publisher;
import planitpoker.mqtt.T7Subscriber;

/**
 * Controller for the TaigaImportPanel 
 *
 * @author Jude Shin 
 */
public class T7TaigaImportController {
	private Logger logger = LoggerFactory.getLogger(T7CreateRoomController.class);
	private T7Main main;
	
	public T7TaigaImportController(T7Main main) { this.main = main; }

	public void importStories(String username, String password, String projectId) {
		
		switchGUI();
	}

	private void switchGUI() {
		main.setTitle("Room");
		T7RoomPanel roomPanel = new T7RoomPanel(main);
		main.setContentPane(roomPanel);
		main.revalidate();
		main.repaint();
	}
}

