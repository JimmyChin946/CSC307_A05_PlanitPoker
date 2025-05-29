package planitpoker;

import javax.swing.*;
import java.awt.*;

/**
 * Joining an exsisting room 
 *
 * @author Jude Shin 
 */
public class JoinRoomPanel extends JPanel {
	public JoinRoomPanel(JoinRoomController joinRoomController) {
		setLayout(new GridLayout(3, 1));
		JLabel title = new JLabel("Create new Room");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title);

		JPanel box1 = new JPanel();
		box1.setLayout(new GridLayout(1, 2));
		JLabel nameLabel = new JLabel("Name:");
		JTextField nameField = new JTextField("CSC307");
		box1.add(nameLabel);
		box1.add(nameField);
		add(box1);

		JPanel box2 = new JPanel();
		JButton joinButton = new JButton("Join");
		joinButton.addActionListener(e -> joinRoomController.createRoom(nameField.getText()));
		box2.add(joinButton);
		add(box2);
	}
}

