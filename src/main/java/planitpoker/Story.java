package planitpoker;

import java.io.Serializable;
import java.util.*;

/**
 * Stores some information about a story
 *
 * @author Jude Shin 
 */
public class Story implements Serializable {
	private String title;
	private boolean active;
	private ArrayList<Double> votes;


	public Story (String title) {
		this.title = title;
		this.active = true;
		this.votes = new ArrayList<>();
	}

	public String getTitle() { return title; }
	public boolean isActive() { return active; }
	public void setActive(boolean active) { this.active = active;}
	public ArrayList<Double> getVotes() { return votes; }
	public void addVote(Double vote) { votes.add(vote); }
}
