package planitpoker;

import java.io.Serializable;

/**
 * Stores some information about a User
 *
 * @author Jude Shin 
 */
public class T7User implements Serializable {
	private String name; // this will be the unique id

	public T7User(String name) { this.name = name; }

	public String getName() { return name; }
}
