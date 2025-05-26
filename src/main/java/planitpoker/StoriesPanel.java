package planitpoker;

import javax.swing.*;
import java.awt.*;

/**
 * Creating a new room
 *
 * @author 
 */
public class StoriesPanel extends JPanel {
	public StoriesPanel(StoriesController storiesController) {
		setLayout(new GridLayout(4, 1));
		JLabel title = new JLabel("Add Stories");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title);
	}
}

