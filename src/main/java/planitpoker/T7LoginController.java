package planitpoker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller for the Login Panel
 *
 * @author Jude Shin 
 */
public class T7LoginController {
	private Logger logger = LoggerFactory.getLogger(T7LoginController.class);
	private T7Main main;

	public T7LoginController(T7Main main) { this.main = main; }

	public void login(String name) {
		logger.info("loging in " + name + "...");

		T7User user = new T7User(name);
		T7Repository.getInstance().setCurrentUser(user, false);
		switchGUI();
	}

	private void switchGUI() {
		main.setTitle("Dashboard");
		T7ChoiceController choiceController = new T7ChoiceController(main);
		T7ChoicePanel choicePanel = new T7ChoicePanel(choiceController, main);
		main.setContentPane(choicePanel);
		main.revalidate();
		main.repaint();
	}
}
