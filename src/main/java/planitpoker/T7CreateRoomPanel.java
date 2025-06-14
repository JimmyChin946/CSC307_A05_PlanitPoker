package planitpoker;

import javax.swing.*;
import java.awt.*;

/**
 * Creating a new room
 *
 * @author Jude Shin 
 */
public class T7CreateRoomPanel extends JPanel {
	public T7CreateRoomPanel(T7CreateRoomController createRoomController, T7Main main) {
		main.setSize(300, 150);

		setLayout(new GridLayout(4, 1));
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
		box2.setLayout(new GridLayout(1, 2));
		JLabel modeLabel = new JLabel("Mode:");
		box2.add(modeLabel);
		String[] options = T7Repository.getInstance().getVotingMethodNames();
		JComboBox<String> comboBox = new JComboBox<>(options);
		box2.add(comboBox);
		add(box2);

		JPanel box3 = new JPanel();
		JButton createButton = new JButton("Create");
		createButton.addActionListener(e -> createRoomController.createRoom(nameField.getText(), (int) comboBox.getSelectedIndex()));
		box3.add(createButton);
		add(box3);
	}
}

