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
	private VotingModel votingModel = VotingModel.getInstance();
	private Repository repository = Repository.getInstance();

	private Timer votingTimer = new Timer();

	public VotingController(Main main) {
		this.main = main;
	}

	public void startVoting() {
		if (votingModel.isVotingStarted()) return;

		votingModel.setVotingStarted(true, false);

		votingModel.setTimeLeft(Duration.ofSeconds(30), false);
		// TODO: only do this if host
		votingTimer.schedule(new CountDown(), 0, 1000);
	}

	public void vote(double score) {
		if (votingModel.isVotingStarted()) {
			this.votingModel.addVote(repository.getCurrentUser(), score, false);
		}
	}

	protected class CountDown extends TimerTask {
		@Override
		public void run() {
			Duration timeLeft = votingModel.getTimeLeft();
			if (timeLeft.isPositive()) {
				votingModel.setTimeLeft(timeLeft.minus(Duration.ofSeconds(1)), false);
			} else {
				votingModel.setTimeLeft(Duration.ZERO, false);
				votingModel.setVotingStarted(false, true);
				votingTimer.cancel();
			}
		}
	}
}
