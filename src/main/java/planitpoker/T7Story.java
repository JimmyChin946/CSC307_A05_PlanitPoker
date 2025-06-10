package planitpoker;

import java.io.Serializable;
import java.time.Duration;
import java.util.*;

/**
 * Stores some information about a story
 *
 * @author Kai Swangler
 */
public class T7Story implements Serializable {
	private String title;
	private boolean active;
	private HashMap<String, Double> votes;
	private Duration time;


	public T7Story(String title) {
		this.title = title;
		this.active = true;
		this.votes = new HashMap<>();
	}

	public double getEstimation() {
		double total = 0;

		for (Double vote : votes.values()) {
			total += vote;
		}

		return total / votes.size();
	}

	public String getTitle() { return title; }
	public Duration getTime() {return time;}
	public void setTime(Duration time) {this.time = time;}
	public HashMap<String, Double> getVotes() { return votes; }
	public void setVotes(HashMap<String, Double> votes) { this.votes = votes; }
	public boolean isActive() { return active; }
	public void setActive(boolean active) { this.active = active;}
}
