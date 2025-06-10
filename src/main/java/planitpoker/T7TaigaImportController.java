package planitpoker;

import java.util.UUID;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import planitpoker.mqtt.T7Publisher;
import planitpoker.mqtt.T7Subscriber;
import org.json.JSONArray;
import org.json.JSONObject;

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
		logger.info("Getting JSON Array");
		// JSONArray jsonArray = TaigaStoryFetcher.getStories(username, password, projectId);
		JSONArray jsonArray = TaigaStoryFetcher.getStories("TestUser12345", "testtest", "nathan-lackie-csc-307-final-project");

		logger.info("Converting JSON Array to ArrayList of Story");
		ArrayList<T7Story> stories = parseJson(jsonArray);
		
		logger.info("Updating Repository with the new stories");
		updateStories(stories);
		
		switchGUI();
	}

	private ArrayList<T7Story> parseJson(JSONArray json) {
		return null;
	}

	private void updateStories(ArrayList<T7Story> stories) {
							
	}


	private void switchGUI() {
		main.setTitle("Room");
		T7RoomPanel roomPanel = new T7RoomPanel(main);
		main.setContentPane(roomPanel);
		main.revalidate();
		main.repaint();
	}
}
