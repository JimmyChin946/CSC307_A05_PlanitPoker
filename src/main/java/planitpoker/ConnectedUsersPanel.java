package planitpoker;

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
public class ConnectedUsersPanel extends JPanel implements PropertyChangeListener {
    private JPanel usersListPanel;

    public ConnectedUsersPanel() {
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Players", SwingConstants.CENTER);
        add(header, BorderLayout.NORTH);

        usersListPanel = new JPanel();
        usersListPanel.setLayout(new BoxLayout(usersListPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(usersListPanel), BorderLayout.CENTER);

        updateUserList(Repository.getInstance().getUsers());

        Repository.getInstance().addPropertyChangeListener("users", this);
    }

    private void updateUserList(List<User> users) {
        usersListPanel.removeAll();
        for (User user : users) {
            JLabel label = new JLabel(user.getName());
            usersListPanel.add(label);
        }
        usersListPanel.revalidate();
        usersListPanel.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("users".equals(evt.getPropertyName())) {
            updateUserList((List<User>) evt.getNewValue());
        }
    }
}

