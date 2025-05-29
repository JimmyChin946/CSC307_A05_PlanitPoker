package planitpoker;

import javax.swing.*;

/**
 * Main class that creates the JFrame
 * This initially kicks the user to a fresh login page
 *
 * @author
 */
public class Main extends JFrame {
	public Main() {
		// ----- Stories -----
		// showStoriesOverlay();

		// ----- Login -----
//		LoginController loginController = new LoginController(this);
//		LoginPanel loginPanel = new LoginPanel(loginController);
//		add(loginPanel);

		// // ----- CreateRoom -----
		 CreateRoomController createRoomController = new CreateRoomController(this);
		 CreateRoomPanel createRoomPanel = new CreateRoomPanel(createRoomController);
		 add(createRoomPanel);

		// // ----- Export -----
		// ExportController exportController = new ExportController(this);
		// ExportPanel exportPanel = new ExportPanel(exportController);
		// add(exportPanel);
	}

	public static void main(String[] args) {
		Main login = new Main();
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setSize(400, 400);
		login.setLocationRelativeTo(null);
		login.setVisible(true);

		// Main createRoomTestMain = new Main(2);
		// createRoomTestMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// createRoomTestMain.setSize(400, 400);
		// createRoomTestMain.setLocationRelativeTo(null);
		// createRoomTestMain.setVisible(true);

		// Main exportTestMain = new Main(3);
		// exportTestMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// exportTestMain.setSize(400, 400);
		// exportTestMain.setLocationRelativeTo(null);
		// exportTestMain.setVisible(true);
	}

	public void showStoriesOverlay() {
		JDialog overlay = new JDialog(this, "Create New Story", true);
		CreateStoryController controller = new CreateStoryController(this);
		CreateStoryPanel panel = new CreateStoryPanel(controller, overlay);
		overlay.setContentPane(panel);
		overlay.setSize(400, 400);
		overlay.setLocationRelativeTo(this);
		overlay.setVisible(true);
	}
}
