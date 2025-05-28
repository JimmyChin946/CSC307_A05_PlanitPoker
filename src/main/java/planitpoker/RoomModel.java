package planitpoker;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 *
 * @author Nathan Lackie
 */
public class RoomModel extends PropertyChangeSupport {
    private static final RoomModel instance = new RoomModel();
    private ArrayList<User> users = new ArrayList<>();
    private Story activeStory;

    private RoomModel() {
        super(new Object());
    }

    public static RoomModel getInstance() {
        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user, boolean silent) {
        users.add(user);
        firePropertyChange("users", null, users);
        if (!silent) {
            // TODO: add to publish queue
        }
    }

    public void setUsers(ArrayList<User> users, boolean silent) {
        this.users = users;
        firePropertyChange("users", null, this.users);
        if (!silent) {
            // TODO: add to publish queue
        }
    }

    public Story getActiveStory() {
        return activeStory;
    }

    public void setActiveStory(Story activeStory, boolean silent) {
        this.activeStory = activeStory;
        firePropertyChange("activeStory", null, activeStory);
        if (!silent) {
            // TODO: add to publish queue
        }
    }
}
