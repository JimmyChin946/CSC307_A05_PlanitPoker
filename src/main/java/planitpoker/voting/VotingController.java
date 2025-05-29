package planitpoker.voting;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;
import planitpoker.Main;
import planitpoker.Repository;

/**
 * Controller for the VotingPanel 
 *
 * @author Nathan Lackie
 */
public class VotingController {
	private Main main;

	private Timer votingTimer;

	public VotingController(Main main) {
		this.main = main;
	}

	public void startVoting() {
		if (Repository.getInstance().getVotingStarted()) return;

		Repository.getInstance().setVotingStarted(true, false);

		Repository.getInstance().setTimeLeft(Duration.ofSeconds(30), false);
		// TODO: only do this if host
		votingTimer = new Timer();
		votingTimer.schedule(new CountDown(), 0, 1000);
	}

	public void vote(double score) {
		if (Repository.getInstance().getVotingStarted()) {
			Repository.getInstance().addVote(Repository.getInstance().getCurrentUser(), score, false);
		}
	}

	protected class CountDown extends TimerTask {
		@Override
		public void run() {
			Duration timeLeft = Repository.getInstance().getTimeLeft();
			if (timeLeft.isPositive()) {
				Repository.getInstance().setTimeLeft(timeLeft.minus(Duration.ofSeconds(1)), false);
			} else {
				Repository.getInstance().setTimeLeft(Duration.ZERO, false);
				Repository.getInstance().setVotingStarted(false, true);
				votingTimer.cancel();
			}
		}
	}
}
