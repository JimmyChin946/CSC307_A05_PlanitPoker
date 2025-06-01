package planitpoker;

import java.util.*;
import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * shows the title of the current story that we are on
 *
 * @author Jude Shin 
 */

public class T7TitlePanel extends JPanel implements PropertyChangeListener {
	private Logger logger = LoggerFactory.getLogger(T7Repository.class);
	private JLabel currentStoryLabel; 

	public T7TitlePanel(T7Main main) {
		super();
		int currentStoryIndex = T7Repository.getInstance().getCurrentStoryIndex();
		currentStoryLabel = new JLabel("No Stories Available");

		refreshTitle(currentStoryIndex);
		add(currentStoryLabel);

		T7Repository.getInstance().addPropertyChangeListener("currentStoryIndex", this);
	}

	private void refreshTitle(int index) {
		ArrayList<T7Story> stories = T7Repository.getInstance().getStories();
		int currentStoryIndex = T7Repository.getInstance().getCurrentStoryIndex();

		if (stories.isEmpty() || (currentStoryIndex == -1)) {
			currentStoryLabel.setText("No Stories Available");
		}
		else {
			T7Story currentStory = stories.get(index);
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
