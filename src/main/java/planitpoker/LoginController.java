package planitpoker;

import javax.swing.*;

/**
 * Controller for the Login Panel
 *
 * @author Jude Shin 
 */
public class LoginController {

	private Main main;

	public LoginController(Main main) {
		this.main = main;
	}

	public void login(String name) {
		System.out.println("loging in " + name + "...");
		User user = new User(name);
		Repository.getInstance().setCurrentUser(user);
		switchGUI();
	}

	public void switchGUI() {
		main.setTitle("Room");
		// this should kick the user to a dashboard page, which gives the option to join a room, or create a room
		ChoiceController choiceController = new ChoiceController(main);
		ChoicePanel choicePanel = new ChoicePanel(choiceController);
		main.setContentPane(choicePanel);

		main.revalidate();
		main.repaint();
	}
}
