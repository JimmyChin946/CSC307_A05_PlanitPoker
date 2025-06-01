package planitpoker.voting;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

public class CardsPanel extends JPanel {
    public CardsPanel(VotingController votingController, ArrayList<Integer> cards) {
        super();

        GridLayout layout = new GridLayout(2,3);
        layout.setHgap(10);
        layout.setVgap(10);
        setLayout(layout);

        for (int card : cards) {
            add(new CardPanel(votingController, card));
        }
    }
}
