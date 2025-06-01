package planitpoker;

import javax.swing.*;
import java.awt.*;

/**
 * Modal to make a choice between joining a room, or creating a new one 
 *
 * @author Jude Shin 
 */
public class T7ChoicePanel extends JPanel {
	
	public T7ChoicePanel(T7ChoiceController choiceController) {
		setLayout(new GridLayout(4, 1));

		JLabel title = new JLabel("Make a Choice:");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title);

		JPanel box1 = new JPanel();
		JButton chooseJoinRoom = new JButton("Join Exsisting Room");
		chooseJoinRoom.addActionListener(e -> choiceController.chooseJoinRoom());
		box1.add(chooseJoinRoom);
		add(box1);

		JPanel box2 = new JPanel();
		JButton chooseCreateRoom = new JButton("Create New Room");
		chooseCreateRoom.addActionListener(e -> choiceController.chooseCreateRoom());
		box2.add(chooseCreateRoom);
		add(box2);
	}
}

