package planitpoker;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		JSONArray jsonArray = T7TaigaStoryFetcher.getStories(username, password, projectId);

		logger.info("Converting JSON Array to ArrayList of Story");
		ArrayList<T7Story> stories = parseJson(jsonArray);
		
		logger.info("Updating Repository with the new stories");
		T7Repository.getInstance().setStories(stories, false);
		
		switchGUI();
	}

	private ArrayList<T7Story> parseJson(JSONArray jsonArray) {
		ArrayList<T7Story> stories = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String title = jsonObject.getString("subject");

			T7Story story = new T7Story(title);
			stories.add(story);
		}

		return stories;
	}

	private void switchGUI() {
		main.setTitle("Room");
		T7RoomPanel roomPanel = new T7RoomPanel(main);
		main.setContentPane(roomPanel);
		main.revalidate();
		main.repaint();
	}
}
