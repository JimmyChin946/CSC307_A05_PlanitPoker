package planitpoker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.IOException;

/**
 * Controller for the Login Panel
 *
 * @author Jude Shin 
 */
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	private Main main;

	public LoginController(Main main) { this.main = main; }

	public void login(String name) {
		logger.info("loging in " + name + "...");
		User user = new User(name);
		Repository.getInstance().setCurrentUser(user);
		Repository.getInstance().addUser(user, false);
		switchGUI();
	}

	private void switchGUI() {
		main.setTitle("Dashboard");
		ChoiceController choiceController = new ChoiceController(main);
		ChoicePanel choicePanel = new ChoicePanel(choiceController);
		main.setContentPane(choicePanel);
		main.revalidate();
		main.repaint();
	}
}
