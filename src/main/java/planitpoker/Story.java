package planitpoker;

import java.util.*;

/**
 * Stores some information about a story
 *
 * @author Jude Shin 
 */
public class Story {
	private String name;
	private String description;
	private ArrayList<Double> votes;

	public Story (String name, String description) {
		this.name = name;
		this.description = description;
		this.votes = new ArrayList<>();
	}

	public String getName() { return name; }
	public String getDescription() { return description; }
	public ArrayList<Double> getVotes() { return votes; }
	public void addVote(Double vote) { votes.add(vote); }
}
