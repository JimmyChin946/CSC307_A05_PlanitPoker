package planitpoker;

import java.util.*;

/**
 * Stores some information about a vote 
 *
 * @author Jude Shin 
 */
public class Vote {
	private String title;
	private String description;
	private Double score;

	public Vote (String title, String description, Double score) {
		this.title = title;
		this.description = description;
		this.score = score;
	}

	public String getTitle() { return title; }
	public String getDescription() { return description; }
	public Double getScore() { return score; }
}
