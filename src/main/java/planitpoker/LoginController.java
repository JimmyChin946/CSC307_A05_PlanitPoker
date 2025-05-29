package planitpoker;

import javax.swing.*;
import java.io.IOException;

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
		Repository.getInstance().setCurrentUser(user, false);
		Repository.getInstance().addUser(user, false);
		switchGUI();
	}

	public void switchGUI() {
		main.setTitle("Dashboard");
		ChoiceController choiceController = new ChoiceController(main);
		ChoicePanel choicePanel = new ChoicePanel(choiceController);
		main.setContentPane(choicePanel);
		main.revalidate();
		main.repaint();
	}
}
