package planitpoker;

import java.util.UUID;
import javax.swing.*;
import planitpoker.mqtt.Publisher;

/**
 * Main class that creates the JFrame
 * This initially kicks the user to a fresh login page
 *
 * @author
 */
public class Main extends JFrame {
	public Main() {
		// ----- Login -----
		LoginController loginController = new LoginController(this);
		LoginPanel loginPanel = new LoginPanel(loginController);
		add(loginPanel);
	}

	public static void main(String[] args) {
		Main login = new Main();
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setSize(800, 400);
		login.setLocationRelativeTo(null);
		login.setVisible(true);
	}

	public void showStoriesOverlay() {
		JDialog overlay = new JDialog(this, "Create New Story", true);
		CreateStoryController controller = new CreateStoryController(this);
		CreateStoryPanel panel = new CreateStoryPanel(controller, overlay);
		overlay.setContentPane(panel);
		overlay.setSize(800, 400);
		overlay.setLocationRelativeTo(this);
		overlay.setVisible(true);
	}
}
