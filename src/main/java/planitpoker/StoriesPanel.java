package planitpoker;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;

/**
 * Panel shown when a user is adding a story.
 *
 * @author Kai Swangler
 */
public class StoriesPanel extends JPanel implements PropertyChangeListener {
	private enum ActivePanel {
		ACTIVE_STORIES,
		COMPLETED_STORIES,
		ALL_STORIES,
	}

	private JPanel storyDisplayPanel;
	private ActivePanel activePanel = ActivePanel.ACTIVE_STORIES;

	public StoriesPanel(CreateStoryController createStoryController) {
		setLayout(new BorderLayout());

		JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
		JButton activeStories = new JButton("Active Stories");
		JButton completedStories = new JButton("Completed Stories");
		JButton allStories = new JButton("All Stories");

		buttonPanel.add(activeStories);
		buttonPanel.add(completedStories);
		buttonPanel.add(allStories);

		if (Repository.getInstance().getType() == Repository.Type.HOST) {
			JButton newStory = new JButton("+ New");
			buttonPanel.add(newStory);
			newStory.addActionListener(e -> createStoryController.createNewStoryDialog());
		}

		add(buttonPanel, BorderLayout.NORTH);

		storyDisplayPanel = new JPanel();
		storyDisplayPanel.setLayout(new BoxLayout(storyDisplayPanel, BoxLayout.Y_AXIS));
		add(new JScrollPane(storyDisplayPanel), BorderLayout.CENTER);

		activeStories.addActionListener(e -> switchPanel(ActivePanel.ACTIVE_STORIES));
		completedStories.addActionListener(e -> switchPanel(ActivePanel.COMPLETED_STORIES));
		allStories.addActionListener(e -> switchPanel(ActivePanel.ALL_STORIES));

		Repository.getInstance().addPropertyChangeListener("stories", this);

		drawPanel();
	}

	private void switchPanel(ActivePanel panel) {
		activePanel = panel;
		drawPanel();
	}

	private void drawPanel() {
		switch (activePanel) {
            case ACTIVE_STORIES -> {
				showActiveStories();
            }
            case COMPLETED_STORIES -> {
				showCompletedStories();
            }
            case ALL_STORIES -> {
				showAllStories();
            }
        }
	}

	private void showActiveStories() {
		storyDisplayPanel.removeAll();
		storyDisplayPanel.add(createHeaderPanel());
		for (Story story : Repository.getInstance().getStories()) {
			if (story.isActive()) {
				storyDisplayPanel.add(displayStory(story));
			}
		}
		storyDisplayPanel.revalidate();
		storyDisplayPanel.repaint();
	}

	private void showCompletedStories() {
		storyDisplayPanel.removeAll();
		storyDisplayPanel.add(createDetailedHeaderPanel());
		for (Story story : Repository.getInstance().getStories()) {
			if (!story.isActive()) {
				storyDisplayPanel.add(displayDetailedStory(story));
			}
		}
		storyDisplayPanel.revalidate();
		storyDisplayPanel.repaint();
	}

	private void showAllStories() {
		storyDisplayPanel.removeAll();
		storyDisplayPanel.add(createDetailedHeaderPanel()); // Add column labels
		for (Story story : Repository.getInstance().getStories()) {
			storyDisplayPanel.add(displayDetailedStory(story));
		}
		storyDisplayPanel.revalidate();
		storyDisplayPanel.repaint();
	}

	private JPanel createDetailedHeaderPanel() {
		JPanel header = new JPanel(new GridLayout(1, 3));
		header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		header.setBackground(Color.LIGHT_GRAY);

		JLabel titleHeader = new JLabel("Title", SwingConstants.CENTER);
		JLabel estimationHeader = new JLabel("Estimation", SwingConstants.CENTER);
		JLabel timeHeader = new JLabel("Time", SwingConstants.CENTER);

		header.add(titleHeader);
		header.add(estimationHeader);
		header.add(timeHeader);

		return header;
	}

	private JPanel createHeaderPanel() {
		JPanel header = new JPanel(new GridLayout(1, 1));
		header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		header.setBackground(Color.LIGHT_GRAY);

		JLabel titleHeader = new JLabel("Title", SwingConstants.CENTER);

		header.add(titleHeader);

		return header;
	}

	private JPanel displayDetailedStory(Story story) {
		JPanel panel = new JPanel(new GridLayout(1, 3));
		panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel.setBackground(Color.WHITE);

		JLabel titleLabel = new JLabel(story.getTitle(), SwingConstants.CENTER);
		panel.add(titleLabel);

		if (story.isActive()) {
			JLabel estimationLabel = new JLabel("-", SwingConstants.CENTER);
			JLabel timeLabel = new JLabel("-", SwingConstants.CENTER);
			panel.add(estimationLabel);
			panel.add(timeLabel);
			return panel;
		}

		JLabel estimationLabel = new JLabel("" + story.getEstimation(), SwingConstants.CENTER);
		JLabel timeLabel = new JLabel("" + story.getTime(), SwingConstants.CENTER);

		panel.add(estimationLabel);
		panel.add(timeLabel);

		return panel;
	}


	private JPanel displayStory(Story story) {
		JPanel panel = new JPanel(new GridLayout(1, 1));
		panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel.setBackground(Color.WHITE);

		JLabel titleLabel = new JLabel(story.getTitle(), SwingConstants.CENTER);

		panel.add(titleLabel);

		return panel;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		drawPanel();
	}
}
