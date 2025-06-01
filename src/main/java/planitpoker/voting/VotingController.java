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
		if (Repository.getInstance().getStories().size() <= Repository.getInstance().getCurrentStoryIndex()) return;

		Repository.getInstance().setVotingStarted(true, false);

		Repository.getInstance().setTimeLeft(Duration.ZERO, false);

		votingTimer = new Timer();
		votingTimer.schedule(new CountDown(), 0, 1000);

		Repository.getInstance().incrementCurrentStoryIndex(false);
	}

	public void stopVoting() {
		if (!Repository.getInstance().getVotingStarted()) return;

		Repository.getInstance().setVotingStarted(false, false);

		votingTimer.cancel();

		// TODO: calculate results and publish story
	}

	public void vote(double score) {
		if (Repository.getInstance().getVotingStarted()) {
			Repository.getInstance().addVote(Repository.getInstance().getCurrentUser(), score, false);
		}
	}

	protected static class CountDown extends TimerTask {
		@Override
		public void run() {
			Duration timeLeft = Repository.getInstance().getTimeLeft();
			Repository.getInstance().setTimeLeft(timeLeft.plus(Duration.ofSeconds(1)), false);
		}
	}
}
