package planitpoker;

import javax.swing.*;

/**
 * Main class that creates the JFrame
 * This initially kicks the user to a fresh login page
 *
 * @author Kai Swangler
 */
public class T7Main extends JFrame {
	public T7Main() {
		// ----- Login -----
		T7LoginController loginController = new T7LoginController(this);
		T7LoginPanel loginPanel = new T7LoginPanel(loginController);
		add(loginPanel);
	}

	public static void main(String[] args) {
		T7Main login = new T7Main();
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setSize(800, 800);
		login.setLocationRelativeTo(null);
		login.setVisible(true);
	}
}
