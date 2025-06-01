package planitpoker.voting;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Duration;
import java.util.HashMap;
import javax.swing.*;
import planitpoker.*;

/**
 * The voting controls sidebar
 * Includes the timer and buttons to control the current story
 *
 * @author Nathan Lackie
 */
public class VotingControlsPanel extends JPanel implements PropertyChangeListener {
    private VotingController votingController;
    private Repository repository = Repository.getInstance();

    public VotingControlsPanel(VotingController votingController) {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.votingController = votingController;

        Repository.getInstance().addPropertyChangeListener("votingStarted", this);
        Repository.getInstance().addPropertyChangeListener("timeLeft", this);

        votingNotStartedUI();
				InvitePlayerPanel invitePlayerPanel = new InvitePlayerPanel();
				add(invitePlayerPanel);

    }

    private void votingNotStartedUI() {
        if (repository.getType() == Repository.Type.HOST) {
            JButton startButton = new JButton("Start voting");
            startButton.addActionListener(e -> votingController.startVoting());
            startButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            add(startButton);
        }
    }

    private void votingStartedUI() {
        if (repository.getType() == Repository.Type.HOST) {
            JButton stopButton = new JButton("Finish voting");
            stopButton.addActionListener(e -> votingController.stopVoting());
            stopButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            add(stopButton);
        }

        Duration timeLeft = Repository.getInstance().getTimeLeft();
        if (timeLeft != null) {
            String timerString = String.format("%02d:%02d", timeLeft.toMinutesPart(), timeLeft.toSecondsPart());
            JLabel timer = new JLabel(timerString);
            timer.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            add(timer);
        }

        HashMap<String, Double> votes = Repository.getInstance().getVotes();
        for (User user : Repository.getInstance().getUsers()) {
            add(new JLabel(user.getName() + ": " + votes.get(user.getName())));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        removeAll();
        if (Repository.getInstance().getVotingStarted()) {;
            votingStartedUI();
        } else {
            votingNotStartedUI();
        }
        revalidate();
        repaint();
    }
}
