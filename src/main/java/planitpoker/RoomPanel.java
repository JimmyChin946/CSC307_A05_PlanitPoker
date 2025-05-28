package planitpoker;

import java.awt.*;
import javax.swing.*;
import planitpoker.voting.VotingController;
import planitpoker.voting.VotingPanel;

/**
 * Panel shown when a user in logged into a room
 * Contains the cards, stories, and a list of users connected to the room
 *
 * @author Nathan Lackie
 */
public class RoomPanel extends JPanel {
    public RoomPanel(Main main) {
        super();

        setLayout(new GridLayout(2, 1));

        VotingController votingController = new VotingController(main);
        add(new VotingPanel(votingController));

        StoriesController storiesController = new StoriesController(main);
        add(new StoriesPanel(storiesController));
    }
}
