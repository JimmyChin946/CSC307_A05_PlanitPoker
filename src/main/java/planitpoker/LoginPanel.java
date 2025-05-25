package planitpoker;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Login Panel  
 * The user will try to log into an exsisting room
 *
 * @author Jude Shin 
 */
public class LoginPanel extends JPanel{
	public LoginPanel(LoginController joinRoomController) {
		JLabel titleLabel = new JLabel("Lets Start!");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel subtitleLabel = new JLabel("Join the room:");
		subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		Room room = Blackboard.getCurrentRoom(); // TODO
		JLabel roomName = new JLabel("Room Name: " + room.getName());
		roomName.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel roomMode = new JLabel("Mode: " + room.getMode());
		roomMode.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel roomUsers = new JLabel("Users: " + room.getUsers().toString());
		roomUsers.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField nameField = new JTextField("Enter your name");
		JButton enterButton = new JButton("Enter");

		setLayout(new GridLayout(8, 1));
		add(titleLabel);
		add(subtitleLabel);
		add(nameField);
		add(enterButton);
		add(roomName);
		add(roomMode);
		add(roomUsers);

		enterButton.addActionListener(e -> joinRoomController.enterRoom(nameField.getText()));
	}
}
