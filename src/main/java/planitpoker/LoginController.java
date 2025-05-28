package planitpoker;

import javax.swing.*;

/**
 * Controller for the Login Panel
 *
 * @author Jude Shin 
 */
public class LoginController {

	private Main main;
	private RoomModel roomModel = RoomModel.getInstance();

	public LoginController(Main main) {
		this.main = main;
	}

	public void login(String name) {
		System.out.println("loging in " + name + "...");
		User user = new User(name);
		Repository.getInstance().setCurrentUser(user);
		roomModel.addUser(user, false);
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
