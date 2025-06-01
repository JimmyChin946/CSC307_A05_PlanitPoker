package planitpoker;

import java.util.*;
import java.util.Base64;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Controller for the Choice Panel 
 *
 * @author Jude Shin 
 */
public class ChoiceController{
	private Logger logger = LoggerFactory.getLogger(ChoiceController.class);
	private Main main;
	
	public ChoiceController(Main main) { this.main = main; }
	
	public void chooseJoinRoom() {
		main.setTitle("Join Room");
		Repository.getInstance().setType(Repository.Type.CLIENT, false);
		JoinRoomController joinRoomController = new JoinRoomController(main);
		JoinRoomPanel joinRoomPanel = new JoinRoomPanel(joinRoomController);
		main.setContentPane(joinRoomPanel);
		switchGUI();
	}

	public void chooseCreateRoom() {
		main.setTitle("Create Room");
		Repository.getInstance().setType(Repository.Type.HOST, false);
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

