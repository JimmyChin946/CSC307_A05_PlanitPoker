package planitpoker.voting;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Where people will vote
 * this includes both the host and the clients
 *
 * @author Nathan Lackie
 */
public class VotingPanel extends JPanel {
	CardsPanel cards;

	public VotingPanel(VotingController votingController) {
		super();

		cards = new CardsPanel(votingController, new ArrayList<>(List.of(1, 2, 3, 4)));
		add(cards);

		VotingControlsPanel votingControls = new VotingControlsPanel(votingController);
		add(votingControls);
	}
}
