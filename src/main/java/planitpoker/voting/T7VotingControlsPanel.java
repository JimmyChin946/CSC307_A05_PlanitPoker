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
public class T7VotingControlsPanel extends JPanel implements PropertyChangeListener {
    private T7VotingController votingController;
    private T7Repository repository = T7Repository.getInstance();

    public T7VotingControlsPanel(T7VotingController votingController) {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.votingController = votingController;

        T7Repository.getInstance().addPropertyChangeListener("votingStarted", this);
        T7Repository.getInstance().addPropertyChangeListener("timeLeft", this);
        T7Repository.getInstance().addPropertyChangeListener("stories", this);

        drawUI();
    }

    private void drawUI() {
        if (T7Repository.getInstance().getVotingStarted()) {;
            votingStartedUI();
        } else {
            votingNotStartedUI();
        }

        T7InvitePlayerPanel invitePlayerPanel = new T7InvitePlayerPanel();
        add(invitePlayerPanel);

        T7ConnectedUsersPanel connectedUsersPanel = new T7ConnectedUsersPanel();
        add(connectedUsersPanel);
    }

    private void votingNotStartedUI() {
        if (repository.getType() == T7Repository.Type.HOST) {
            JButton startButton = new JButton("Start voting");
            startButton.addActionListener(e -> votingController.startVoting());
            startButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            add(startButton);
        }
    }

    private void votingStartedUI() {
        if (repository.getType() == T7Repository.Type.HOST) {
            JButton stopButton = new JButton("Finish voting");
            stopButton.addActionListener(e -> votingController.stopVoting());
            stopButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            add(stopButton);
        }

        Duration timeLeft = T7Repository.getInstance().getTimeLeft();
        if (timeLeft != null) {
            String timerString = String.format("%02d:%02d", timeLeft.toMinutesPart(), timeLeft.toSecondsPart());
            JLabel timer = new JLabel(timerString);
            timer.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            add(timer);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        removeAll();

        drawUI();

        revalidate();
        repaint();
    }
}
