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

        InvitePlayerPanel invitePlayerPanel = new InvitePlayerPanel();
        add(invitePlayerPanel);

        CreateStoryController createStoryController = new CreateStoryController(main);
        add(new StoriesPanel(createStoryController));
    }
}
