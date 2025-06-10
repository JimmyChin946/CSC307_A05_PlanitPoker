package planitpoker;

import java.awt.*;
import javax.swing.*;
import planitpoker.voting.T7VotingController;
import planitpoker.voting.T7VotingPanel;

/**
 * Panel shown when a user in logged into a room
 * Contains the cards, stories, and a list of users connected to the room
 *
 * @author Nathan Lackie
 */
public class T7RoomPanel extends JPanel {
    public T7RoomPanel(T7Main main) {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        T7TitlePanel titlePanel = new T7TitlePanel(main);
        add(titlePanel);

        T7VotingController votingController = new T7VotingController();
        add(new T7VotingPanel(votingController));

        add(new T7StoriesPanel());
    }
}
