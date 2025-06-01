package planitpoker;

import javax.swing.*;
import java.awt.*;

/**
 * Login Panel  
 * The user will try to log into an exsisting room
 *
 * @author Jude Shin 
 */
public class T7LoginPanel extends JPanel{
	public T7LoginPanel(T7LoginController loginController) {
		JLabel titleLabel = new JLabel("PlanItPoker");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JTextField nameField = new JTextField("Enter your name");
		JButton enterButton = new JButton("Login!");

		setLayout(new GridLayout(8, 1));
		add(titleLabel);
		add(nameField);
		add(enterButton);

		enterButton.addActionListener(e -> loginController.login(nameField.getText()));
	}
}
