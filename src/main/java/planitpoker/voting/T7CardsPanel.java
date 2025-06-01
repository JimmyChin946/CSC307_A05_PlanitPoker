package planitpoker.voting;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Panel that contains all the cards
 *
 * @author Nathan Lackie
 */
public class T7CardsPanel extends JPanel {
    public T7CardsPanel(T7VotingController votingController, ArrayList<Double> cards) {
        super();

        GridLayout layout = new GridLayout(2,3);
        layout.setHgap(10);
        layout.setVgap(10);
        setLayout(layout);

        for (double card : cards) {
            add(new T7CardPanel(votingController, card));
        }
    }
}
