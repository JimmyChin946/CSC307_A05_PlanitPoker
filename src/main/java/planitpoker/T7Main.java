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
		T7LoginController loginController = new T7LoginController(this);
		T7LoginPanel loginPanel = new T7LoginPanel(loginController, this);
		add(loginPanel);
	}

	public static void main(String[] args) {
		T7Main main = new T7Main();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setLocationRelativeTo(null);
		main.setVisible(true);
	}
}
