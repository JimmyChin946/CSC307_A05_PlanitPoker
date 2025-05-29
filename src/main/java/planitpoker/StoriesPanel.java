package planitpoker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel shown when a user is adding a story.
 *
 * @author Kai Swangler
 */
public class StoriesPanel extends JPanel {
	private JPanel storyDisplayPanel;
	private Repository repository = Repository.getInstance();

	public StoriesPanel(CreateStoryController createStoryController) {
		setLayout(new BorderLayout());

		JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
		JButton activeStories = new JButton("Active Stories");
		JButton completedStories = new JButton("Completed Stories");
		JButton allStories = new JButton("All Stories");
		JButton newStory = new JButton("+ New");

		buttonPanel.add(activeStories);
		buttonPanel.add(completedStories);
		buttonPanel.add(allStories);
		buttonPanel.add(newStory);

		add(buttonPanel, BorderLayout.NORTH);

		storyDisplayPanel = new JPanel();
		storyDisplayPanel.setLayout(new BoxLayout(storyDisplayPanel, BoxLayout.Y_AXIS));
		add(new JScrollPane(storyDisplayPanel), BorderLayout.CENTER);

		activeStories.addActionListener(e -> showActiveStories());
		completedStories.addActionListener(e -> showCompletedStories());
		allStories.addActionListener(e -> showAllStories());
		newStory.addActionListener(e -> createStoryController.createNewStoryDialog());
	}

	private void showActiveStories() {
		storyDisplayPanel.removeAll();
		for (Story story : repository.getStories()) {
			if (story.isActive()) {
				storyDisplayPanel.add(createStoryCard(story));
			}
		}
		storyDisplayPanel.revalidate();
		storyDisplayPanel.repaint();
	}

	private void showCompletedStories() {
		storyDisplayPanel.removeAll();
		for (Story story : repository.getStories()) {
			if (!story.isActive()) {
				storyDisplayPanel.add(createStoryCard(story));
			}
		}
		storyDisplayPanel.revalidate();
		storyDisplayPanel.repaint();
	}

	private void showAllStories() {
		storyDisplayPanel.removeAll();
		for (Story story : repository.getStories()) {
			storyDisplayPanel.add(createStoryCard(story));
		}
		storyDisplayPanel.revalidate();
		storyDisplayPanel.repaint();
	}



	private JPanel createStoryCard(Story story) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(120, 60));
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel.setBackground(Color.WHITE);
		panel.add(new JLabel(story.getTitle()));
		return panel;
	}
}
