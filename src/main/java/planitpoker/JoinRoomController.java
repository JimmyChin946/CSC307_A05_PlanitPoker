package planitpoker;

/**
 * Controller for the CreateRoomPanel
 *
 * @author Jude Shin 
 */
public class JoinRoomController{
	
	private Main main;
	
	public JoinRoomController(Main main) {
		this.main = main;
	}
	
	public void joinRoom(String name) {
		System.out.println(" Joining a room..." + name);
		switchGUI();
	}
	
	private void switchGUI() {
		main.setTitle("Room");

		RoomPanel roomPanel = new RoomPanel(main);

		main.setContentPane(roomPanel);
		main.revalidate();
		main.repaint();
	}
}

