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
	private Repository repository = Repository.getInstance();
	
	public ChoiceController(Main main) { this.main = main; }
	
	public void chooseJoinRoom() {
		main.setTitle("Join Room");
		repository.setType(Repository.Type.CLIENT, false);
		// System.out.println("JOIN ROOM NOT IMPLEMENTED YET");
		JoinRoomController joinRoomController = new JoinRoomController(main);
		JoinRoomPanel joinRoomPanel = new JoinRoomPanel(joinRoomController);
		main.setContentPane(joinRoomPanel);
		switchGUI();
	}

	public void chooseCreateRoom() {
		main.setTitle("Create Room");
		repository.setType(Repository.Type.HOST, false);
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

