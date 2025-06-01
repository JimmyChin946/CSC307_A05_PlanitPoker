package planitpoker;


/**
 * Controller for the Choice Panel 
 *
 * @author Jude Shin 
 */
public class T7ChoiceController {
	private T7Main main;
	
	public T7ChoiceController(T7Main main) { this.main = main; }
	
	public void chooseJoinRoom() {
		main.setTitle("Join Room");
		T7Repository.getInstance().setType(T7Repository.Type.CLIENT);
		T7JoinRoomController joinRoomController = new T7JoinRoomController(main);
		T7JoinRoomPanel joinRoomPanel = new T7JoinRoomPanel(joinRoomController);
		main.setContentPane(joinRoomPanel);
		switchGUI();
	}

	public void chooseCreateRoom() {
		main.setTitle("Create Room");
		T7Repository.getInstance().setType(T7Repository.Type.HOST);
		T7CreateRoomController createRoomController = new T7CreateRoomController(main);
		T7CreateRoomPanel createRoomPanel = new T7CreateRoomPanel(createRoomController);
		main.setContentPane(createRoomPanel);
		switchGUI();
	}
	
	private void switchGUI() {
		main.revalidate();
		main.repaint();
	}
}

