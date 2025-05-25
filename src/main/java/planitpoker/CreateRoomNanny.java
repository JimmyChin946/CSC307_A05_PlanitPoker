package planitpoker;

import java.util.*;
import java.util.Base64;
import java.util.Random;

/**
 * Controller for the CreateRoomPanel
 *
 * @author Jude Shin 
 */
public class CreateRoomController {
	
	private Main main;
	
	public CreateRoomController(Main main) {
		this.main = main;
	}
	
	public void createRoom(String name, String selectedItem) {
		System.out.println(" Creating room..." + name + ", mode: " + selectedItem);
		Room room = new Room(name, selectedItem);
		Blackboard.setCurrentRoom(room); // TODO
		switchGUI();
	}
	
	private void switchGUI() {
		main.setTitle("Stories");
		// these will be implemented by someone else
		// for now, I commented them out so that they don't break the program
		// StoriesNanny createRoomNanny = new StoriesNanny(main);
		// StoriesPanel createRoomPanel = new StoriesPanel(createRoomNanny);
		// main.setContentPane(createRoomPanel);
		main.revalidate();
		main.repaint();
	}
}

