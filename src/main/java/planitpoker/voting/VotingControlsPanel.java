package planitpoker.voting;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Duration;
import java.util.HashMap;
import javax.swing.*;
import planitpoker.User;
import planitpoker.Repository;

public class VotingControlsPanel extends JPanel implements PropertyChangeListener {
    VotingController votingController;
    VotingModel votingModel = VotingModel.getInstance();
		Repository repository = Repository.getInstance();

    public VotingControlsPanel(VotingController votingController) {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.votingController = votingController;

        votingModel.addPropertyChangeListener("votingStarted", this);
        votingModel.addPropertyChangeListener("timeLeft", this);

        votingNotStartedUI();
    }

    private void votingNotStartedUI() {
        JButton startButton = new JButton("Start voting");
        startButton.addActionListener(e -> votingController.startVoting());
        add(startButton);
    }

    private void votingStartedUI() {
        // TODO: add buttons for when voting has been started
        Duration timeLeft = votingModel.getTimeLeft();
        if (timeLeft != null) {
            String timerString = String.format("%02d:%02d", timeLeft.toMinutesPart(), timeLeft.toSecondsPart());
            JLabel timer = new JLabel(timerString);
            add(timer);
        }

        HashMap<User, Double> votes = votingModel.getVotes();
        for (User user : repository.getUsers()) {
            add(new JLabel(user.getName() + ": " + votes.get(user)));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        removeAll();
        if (votingModel.isVotingStarted()) {;
            votingStartedUI();
        } else {
            votingNotStartedUI();
        }
        revalidate();
        repaint();
    }
}
