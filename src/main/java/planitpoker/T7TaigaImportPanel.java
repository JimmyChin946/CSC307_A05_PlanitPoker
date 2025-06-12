package planitpoker;

import javax.swing.*;
import java.awt.*;

/**
 * importing stories based on a taiga project 
 *
 * @author Jude Shin 
 */
public class T7TaigaImportPanel extends JPanel {
	public T7TaigaImportPanel(T7TaigaImportController taigaImportController, T7Main main) {
		main.setSize(300, 200);

		setLayout(new GridLayout(5, 1));
		JLabel title = new JLabel("Create new Room");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title);

		JPanel box1 = new JPanel();
		box1.setLayout(new GridLayout(1, 2));
		JLabel usernameLabel= new JLabel("Username: ");
		JTextField usernameField = new JTextField("TestUser12345");
		box1.add(usernameLabel);
		box1.add(usernameField);
		add(box1);

		JPanel box2 = new JPanel();
		box2.setLayout(new GridLayout(1, 2));
		JLabel passwordLabel = new JLabel("Password: ");
		JTextField passwordField = new JTextField("testtest");
		box2.add(passwordLabel);
		box2.add(passwordField);
		add(box2);

		JPanel box3 = new JPanel();
		box3.setLayout(new GridLayout(1, 2));
		JLabel projectIdLabel = new JLabel("Project Id: ");
		JTextField projectIdField = new JTextField("jude_shin-final-307-test");
		box3.add(projectIdLabel);
		box3.add(projectIdField);
		add(box3);

		JPanel box4 = new JPanel();
		JButton importButton = new JButton("Import");
		importButton.addActionListener(e -> taigaImportController.importStories(usernameField.getText(), passwordField.getText(), projectIdField.getText()));
		box4.add(importButton);
		add(box4);
	}
}

