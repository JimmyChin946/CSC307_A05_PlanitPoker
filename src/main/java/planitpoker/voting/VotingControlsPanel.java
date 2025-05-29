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

    public VotingControlsPanel(VotingController votingController) {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.votingController = votingController;

        Repository.getInstance().addPropertyChangeListener("votingStarted", this);
        Repository.getInstance().addPropertyChangeListener("timeLeft", this);

        votingNotStartedUI();
    }

    private void votingNotStartedUI() {
        JButton startButton = new JButton("Start voting");
        startButton.addActionListener(e -> votingController.startVoting());
        startButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        add(startButton);
    }

    private void votingStartedUI() {
        // TODO: add buttons for when voting has been started
        Duration timeLeft = Repository.getInstance().getTimeLeft();
        if (timeLeft != null) {
            String timerString = String.format("%02d:%02d", timeLeft.toMinutesPart(), timeLeft.toSecondsPart());
            JLabel timer = new JLabel(timerString);
            timer.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            add(timer);
        }

        HashMap<User, Double> votes = Repository.getInstance().getVotes();
        for (User user : Repository.getInstance().getUsers()) {
            add(new JLabel(user.getName() + ": " + votes.get(user)));
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
