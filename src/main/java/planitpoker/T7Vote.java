package planitpoker;

import java.io.Serializable;

/**
 * A datastructure for a user and a double 
 *
 * @author Jude Shin 
 */
public class T7Vote implements Serializable {
	private final T7User user;
	private final Double score;

	public T7Vote(T7User user, Double score) {
		this.user = user;
		this.score = score;
	}

	public T7User getUser() { return user; }
	public Double getScore() { return score; }
}
