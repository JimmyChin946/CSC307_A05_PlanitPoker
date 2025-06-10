package planitpoker.voting;

import java.time.Duration;
import java.util.Timer;
import java.util.HashMap;
import java.util.TimerTask;
import planitpoker.T7Repository;
import planitpoker.T7Story;

/**
 * Controller for the VotingPanel 
 *
 * @author Nathan Lackie
 */
public class T7VotingController {
	private Timer votingTimer;

	public void startVoting() {
		if (T7Repository.getInstance().getVotingStarted()) return;
		if (T7Repository.getInstance().getStories().size()-1 <= T7Repository.getInstance().getCurrentStoryIndex()) return;

		T7Repository.getInstance().setVotes(new HashMap<>(), false);

		T7Repository.getInstance().setVotingStarted(true, false);

		T7Repository.getInstance().setTimeLeft(Duration.ZERO, false);

		votingTimer = new Timer();
		votingTimer.schedule(new CountDown(), 0, 1000);

		T7Repository.getInstance().incrementCurrentStoryIndex(false);
	}

	public void stopVoting() {
		if (!T7Repository.getInstance().getVotingStarted()) return;
		
		int i = T7Repository.getInstance().getCurrentStoryIndex();
		T7Story s = T7Repository.getInstance().getStories().get(i);
		s.setActive(false);
		s.setTime(T7Repository.getInstance().getTimeLeft());

		HashMap<String, Double> votes = T7Repository.getInstance().getVotes();
		s.setVotes(votes);

		T7Repository.getInstance().setVotingStarted(false, false);

		votingTimer.cancel();
	}

	public void vote(double score) {
		if (T7Repository.getInstance().getVotingStarted()) {
			T7Repository.getInstance().addVote(T7Repository.getInstance().getCurrentUser(), score, false);
		}
	}

	protected static class CountDown extends TimerTask {
		@Override
		public void run() {
			Duration timeLeft = T7Repository.getInstance().getTimeLeft();
			T7Repository.getInstance().setTimeLeft(timeLeft.plus(Duration.ofSeconds(1)), false);
		}
	}
}
