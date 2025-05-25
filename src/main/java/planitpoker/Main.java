package planitpoker;

import javax.swing.*;

/**
 * Main class that creates the JFrame
 * This initially kicks the user to a fresh login page
 *
 * @author Jude Shin 
 */
public class Main extends JFrame {
	public Main(int launchNumber) {
		switch (launchNumber) {
			case 1:
				// ----- LoginNanny -----
				LoginNanny loginNanny = new LoginNanny(this);
				LoginPanel loginPanel = new LoginPanel(loginNanny);
				add(loginPanel);
				break;

			case 2:
				// ----- CreateRoom -----
				CreateRoomNanny createRoomNanny = new CreateRoomNanny(this);
				CreateRoomPanel createRoomPanel = new CreateRoomPanel(createRoomNanny);
				add(createRoomPanel);
				break;

			case 3:
				// ----- Export -----
				ExportNanny exportNanny  = new ExportNanny(this);
				ExportPanel exportPanel = new ExportPanel(exportNanny);
				add(exportPanel);
				break;
		}
	}

	public static void main(String[] args) {
		// in prod, you would only have one main panel
		// this is just for ease of demonstration 
		Main loginTestMain = new Main(1);
		loginTestMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginTestMain.setSize(400, 400);
		loginTestMain.setLocationRelativeTo(null);
		loginTestMain.setVisible(true);

		Main createRoomTestMain = new Main(2);
		createRoomTestMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createRoomTestMain.setSize(400, 400);
		createRoomTestMain.setLocationRelativeTo(null);
		createRoomTestMain.setVisible(true);

		Main exportTestMain = new Main(3);
		exportTestMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		exportTestMain.setSize(400, 400);
		exportTestMain.setLocationRelativeTo(null);
		exportTestMain.setVisible(true);
	}
}
