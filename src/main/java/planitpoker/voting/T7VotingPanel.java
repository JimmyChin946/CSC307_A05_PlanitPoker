package planitpoker.voting;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import planitpoker.T7Repository;

/**
 * Where people will vote
 * this includes both the host and the clients
 *
 * @author Nathan Lackie
 */
public class T7VotingPanel extends JPanel implements PropertyChangeListener {
	T7VotingController votingController;

	public T7VotingPanel(T7VotingController votingController) {
		super();

		this.votingController = votingController;

		setLayout(new GridLayout(1, 2));
		setPreferredSize(new Dimension(0, 300));

		T7Repository.getInstance().addPropertyChangeListener("votingStarted", this);

		drawUI();
	}

	private void drawUI() {
		removeAll();

		if (T7Repository.getInstance().getVotingStarted()) {
			// add(new CardsPanel(votingController, new ArrayList<>(List.of(1, 2, 3, 4))));
			int votingMethodIndex = T7Repository.getInstance().getVotingMethodIndex();
			Double[][] votingMethodNumbers = T7Repository.getInstance().getVotingMethodNumbers();
			Double[] votingMethod = votingMethodNumbers[votingMethodIndex];

			add(new T7CardsPanel(votingController, new ArrayList<>(Arrays.asList(votingMethod))));
		} else {
			add(T7ResultsPanel.createPanel());
			add(T7TotalsPanel.createPanel());
		}

		T7VotingControlsPanel votingControls = new T7VotingControlsPanel(votingController);
		add(votingControls);

		revalidate();
		repaint();
	}

	@Override
	public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
		drawUI();
	}
}
