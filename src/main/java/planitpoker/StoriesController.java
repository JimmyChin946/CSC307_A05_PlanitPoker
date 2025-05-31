package planitpoker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Base64;
import java.util.Random;

/**
 * Controller for the StoriesPanel
 * This is Host spesific
 *
 * @author Jude Shin 
 */
public class StoriesController {
	private Logger logger = LoggerFactory.getLogger(StoriesController.class);

	private Main main;
	
	public StoriesController(Main main) { this.main = main; }
	
	public void addStory() {
		// TODO
		// System.out.println("adding a new story");
		logger.info("adding a new story");
	}

	public void deleteStory() {
		// TODO
		// System.out.println("deleting a story");
		logger.info("deleting a story");
	}

	public void importStories() {
		// TODO
		// System.out.println("importing stories from csv");
		logger.info("importing stories from csv");
	}

	public void startVoting() {
		main.setTitle("Stories");
		// TODO
		// StoriesNanny createRoomNanny = new StoriesNanny(main);
		// StoriesPanel createRoomPanel = new StoriesPanel(createRoomNanny);
		// main.setContentPane(createRoomPanel);
		main.revalidate();
		main.repaint();
	}
}

