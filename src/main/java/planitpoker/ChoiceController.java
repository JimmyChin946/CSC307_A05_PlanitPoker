package planitpoker;

import java.util.*;
import java.util.Base64;
import java.util.Random;

/**
 * Controller for the Choice Panel 
 *
 * @author Jude Shin 
 */
public class ChoiceController{
	private Main main;
	
	public ChoiceController(Main main) { this.main = main; }
	
	public void chooseJoinRoom() {
		main.setTitle("Join Room");
		// System.out.println("JOIN ROOM NOT IMPLEMENTED YET");
		JoinRoomController joinRoomController = new JoinRoomController(main);
		JoinRoomPanel joinRoomPanel = new JoinRoomPanel(joinRoomController);
		main.setContentPane(joinRoomPanel);
		switchGUI();
	}

	public void chooseCreateRoom() {
		main.setTitle("Create Room");
		CreateRoomController createRoomController = new CreateRoomController(main);
		CreateRoomPanel createRoomPanel = new CreateRoomPanel(createRoomController);
		main.setContentPane(createRoomPanel);
		switchGUI();
	}
	
	private void switchGUI() {
		main.revalidate();
		main.repaint();
	}
}

