package planitpoker.voting;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import planitpoker.Repository;
import java.util.stream.*;

/**
 * Where people will vote
 * this includes both the host and the clients
 *
 * @author Nathan Lackie
 */
public class VotingPanel extends JPanel implements PropertyChangeListener {
	VotingController votingController;

	public VotingPanel(VotingController votingController) {
		super();

		this.votingController = votingController;

		setLayout(new GridLayout(1, 2));

		Repository.getInstance().addPropertyChangeListener("votingStarted", this);

		drawUI();
	}

	private void drawUI() {
		removeAll();

		if (Repository.getInstance().getVotingStarted()) {
			// add(new CardsPanel(votingController, new ArrayList<>(List.of(1, 2, 3, 4))));
			int votingMethodIndex = Repository.getInstance().getVotingMethodIndex();
			Double[][] votingMethodNumbers = Repository.getInstance().getVotingMethodNumbers();
			Double[] votingMethod = votingMethodNumbers[votingMethodIndex];

			add(new CardsPanel(votingController, new ArrayList<Double>(Arrays.asList(votingMethod))));
		} else {
			add(new ResultsPanel());
		}

		VotingControlsPanel votingControls = new VotingControlsPanel(votingController);
		add(votingControls);

		revalidate();
		repaint();
	}

	@Override
	public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
		drawUI();
	}
}
