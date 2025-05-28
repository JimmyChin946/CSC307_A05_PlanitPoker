package planitpoker.voting;

import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.util.HashMap;
import planitpoker.User;

public class VotingModel extends PropertyChangeSupport {
    private static final VotingModel instance = new VotingModel();

    private boolean votingStarted = false;
    private Duration timeLeft;
    HashMap<User, Double> votes = new HashMap<>();

    private VotingModel() {
        super(new Object());
    }

    public static VotingModel getInstance() {
        return instance;
    }

    public Duration getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Duration timeLeft, boolean silent) {
        this.timeLeft = timeLeft;
        firePropertyChange("timeLeft", null, this.timeLeft);
        if (!silent) {
            // TODO: add to publish queue
            // Note: we should probably make this MQTT QOS level zero or one, since it updates pretty frequently
        }
    }

    public boolean isVotingStarted() {
        return votingStarted;
    }

    public void setVotingStarted(boolean votingStarted, boolean silent) {
        this.votingStarted = votingStarted;
        firePropertyChange("votingStarted", null, this.votingStarted);
        if (!silent) {
            // TODO: add to publish queue
        }
    }

    public HashMap<User, Double> getVotes() {
        return votes;
    }

    public void addVote(User user, double score, boolean silent) {
        this.votes.put(user, score);
        firePropertyChange("votes", null, votes);
        if (!silent) {
            // TODO: add to publish queue
        }
    }

}
