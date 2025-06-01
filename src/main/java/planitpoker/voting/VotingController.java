package planitpoker.voting;

import java.time.Duration;
import java.util.Timer;
import java.util.HashMap;
import java.util.TimerTask;
import planitpoker.Main;
import planitpoker.Repository;
import planitpoker.User;
import planitpoker.Story;

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
		if (Repository.getInstance().getStories().size()-1 <= Repository.getInstance().getCurrentStoryIndex()) return;

		Repository.getInstance().setVotingStarted(true, false);

		Repository.getInstance().setTimeLeft(Duration.ZERO, false);

		votingTimer = new Timer();
		votingTimer.schedule(new CountDown(), 0, 1000);

		Repository.getInstance().incrementCurrentStoryIndex(false);
	}

	public void stopVoting() {
		if (!Repository.getInstance().getVotingStarted()) return;
		
		int i = Repository.getInstance().getCurrentStoryIndex();
		Story s = Repository.getInstance().getStories().get(i);
		s.setActive(false);
		s.setTime(Repository.getInstance().getTimeLeft());

		HashMap<User, Double> votes = Repository.getInstance().getVotes();
		double voteSum = 0;
		for (User user : votes.keySet()) {
			Double vote = votes.get(user);
			voteSum += vote;
		}
		double average = voteSum / votes.size();
		s.setEstimation(average);

		Repository.getInstance().setVotingStarted(false, false);

		votingTimer.cancel();
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
