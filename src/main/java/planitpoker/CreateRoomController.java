package planitpoker;

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
		// TODO when you create a room, you should be be a host in mqtt
		// generate a string that will be the TOPIC
		// all other clients will be voting on this TOPIC
		// people will share that TOPIC code manually
		System.out.println(" Creating room..." + name + ", mode: " + selectedItem);
		// Room room = new Room(name, selectedItem);
		// Repository.getInstance().setCurrentRoom(room);
		switchGUI();
	}
	
	private void switchGUI() {
		main.setTitle("Create Room");
		RoomPanel roomPanel = new RoomPanel(main);
		main.setContentPane(roomPanel);
		main.revalidate();
		main.repaint();
	}
}

