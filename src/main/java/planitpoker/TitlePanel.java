package planitpoker;

import java.awt.*;
import javax.swing.*;
import planitpoker.voting.VotingController;
import planitpoker.voting.VotingPanel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/**
 * shows the title of the current story that we are on
 *
 * @author Jude Shin 
 */

public class TitlePanel extends JPanel implements PropertyChangeListener {
	private JLabel currentStoryTitle; 

	public TitlePanel(Main main) {
		super();
		int currentStoryIndex = Repository.getInstance().getCurrentStoryIndex();
		Story currentStory = Repository.getInstance().getStories().get(currentStoryIndex);
		currentStoryTitle = new JLabel(currentStory.getTitle());
		add(currentStoryTitle);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("currentStoryIndex".equals(evt.getPropertyName())) {
			int newCurrentStoryIndex = (int) evt.getNewValue();
			Story currentStory = Repository.getInstance().getStories().get(newCurrentStoryIndex);
			currentStoryTitle = new JLabel(currentStory.getTitle());
			revalidate();
			repaint();
		}
	}
}
