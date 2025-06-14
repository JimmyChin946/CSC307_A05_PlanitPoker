package planitpoker.voting;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Button that represents an individual card
 *
 * @author Nathan Lackie
 */
public class T7CardPanel extends JButton {
    private double value;

    public T7CardPanel(T7VotingController votingController, double value) {
        super();

        // Don't display decimal places unless non-integer
        if (value % 1 == 0) {
            setText(String.valueOf((int) value));
        } else {
            setText(String.valueOf(value));
        }

        this.value = value;

        addActionListener(e -> votingController.vote(this.value));

        setFont(new Font("Impact", Font.PLAIN, 20));
        setBackground(Color.WHITE);
        setOpaque(true);
        setBorder(new LineBorder(Color.BLUE, 5));
    }
}
