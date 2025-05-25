package planitpoker;

import javax.swing.*;

/**
 * Controller for the Login Panel
 *
 * @author Jude Shin 
 */
public class LoginController {

	private Main main;

	public LoginController(Main main) {
		this.main = main;
	}

	public void enterRoom(String name) {
		System.out.println(name + " entering a room ...");
		Room currentRoom = Blackboard.getCurrentRoom(); // TODO
		User user = Blackboard.getCurrentUser(); // TODO
		currentRoom.addUser(user);
		switchGUI();
	}

	public void switchGUI() {
		main.setTitle("Room");
		// this should kick the user to the panel to vote (the session/ room)
		// the example code below kicks the user to the Create Room panel
		// CreateRoomNanny createRoomNanny = new CreateRoomNanny(main);
		// CreateRoomPanel createRoomPanel = new CreateRoomPanel(createRoomNanny);
		// main.setContentPane(createRoomPanel);
		main.revalidate();
		main.repaint();
	}
}
