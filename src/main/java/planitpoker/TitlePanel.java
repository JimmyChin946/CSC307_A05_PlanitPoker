package planitpoker;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import planitpoker.voting.VotingController;
import planitpoker.voting.VotingPanel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * shows the title of the current story that we are on
 *
 * @author Jude Shin 
 */

public class TitlePanel extends JPanel implements PropertyChangeListener {
	private Logger logger = LoggerFactory.getLogger(Repository.class);
	private JLabel currentStoryLabel; 

	public TitlePanel(Main main) {
		super();
		int currentStoryIndex = Repository.getInstance().getCurrentStoryIndex();
		currentStoryLabel = new JLabel("No Stories Available");

		refreshTitle(currentStoryIndex);
		add(currentStoryLabel);

		Repository.getInstance().addPropertyChangeListener("currentStoryIndex", this);
	}

	private void refreshTitle(int index) {
		ArrayList<Story> stories = Repository.getInstance().getStories();

		if (stories.isEmpty()) {
			currentStoryLabel.setText("No Stories Available");
		}
		else {
			Story currentStory = stories.get(index);
			currentStoryLabel.setText(currentStory.getTitle());
			logger.info("INDEX: " + index);
			logger.info("Stories: " + stories);
			logger.info("STORY TITLE: " + currentStory.getTitle());
		}

	
		revalidate();
		repaint();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("currentStoryIndex".equals(evt.getPropertyName())) {
			int newCurrentStoryIndex = (int) evt.getNewValue();
			refreshTitle(newCurrentStoryIndex);
		}
	}
}
