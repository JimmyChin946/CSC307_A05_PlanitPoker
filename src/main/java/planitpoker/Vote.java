package planitpoker;

import java.io.Serializable;
import java.util.*;

/**
 * A datastructure for a user and a double 
 *
 * @author Jude Shin 
 */
public class Vote implements Serializable {
	private final User user;
	private final Double score;

	public Vote(User user, Double score) {
		this.user = user;
		this.score = score;
	}

	public User getUser() { return user; }
	public Double getScore() { return score; }
}
