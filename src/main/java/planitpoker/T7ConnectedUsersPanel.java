package planitpoker;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Panel for displaying active users.
 *
 * @author Kai Swangler
 */
public class T7ConnectedUsersPanel extends JPanel implements PropertyChangeListener {
    private JPanel usersListPanel;

    public T7ConnectedUsersPanel() {
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Players", SwingConstants.CENTER);
        add(header, BorderLayout.NORTH);

        usersListPanel = new JPanel();
        usersListPanel.setLayout(new BoxLayout(usersListPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(usersListPanel), BorderLayout.CENTER);

        updateUserList(T7Repository.getInstance().getUsers());

        T7Repository.getInstance().addPropertyChangeListener("users", this);
    }

    private void updateUserList(List<T7User> users) {
        usersListPanel.removeAll();

        boolean votingStarted = T7Repository.getInstance().getVotingStarted();
        HashMap<String, Double> votes = T7Repository.getInstance().getVotes();
        for (T7User user : users) {
            Double vote = votes.get(user.getName());
            JLabel label;
            if (vote == null && votingStarted) {
                label = new JLabel(user.getName() + ": Not yet voted");
            } else if (vote == null) {
                label = new JLabel(user.getName());
            } else if (votingStarted) {
                label = new JLabel(user.getName() + ": Voted");
            } else {
                label = new JLabel(user.getName() + ": " + vote);
            }
            usersListPanel.add(label);
        }

        usersListPanel.revalidate();
        usersListPanel.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("users".equals(evt.getPropertyName())) {
            updateUserList((List<T7User>) evt.getNewValue());
        }
    }
}

