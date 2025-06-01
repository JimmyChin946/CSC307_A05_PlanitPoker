package planitpoker.voting;

import java.awt.*;
import java.util.HashMap;
import java.util.Set;
import javax.swing.*;
import planitpoker.Repository;
import planitpoker.User;

/**
 * Panel for displaying the voting results for a story
 *
 * @author Nathan Lackie
 */
public class ResultsPanel extends JPanel {
    public ResultsPanel() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = Math.min(getWidth(), getHeight());

        HashMap<String, Double> votes = Repository.getInstance().getVotes();

        if (votes.isEmpty()) return;

        HashMap<Double, Integer> voteCounts = new HashMap<>();

        double voteSum = 0;
        for (String user : votes.keySet()) {
            Double vote = votes.get(user);
            voteCounts.merge(vote, 1, Integer::sum);
            voteSum += vote;
        }

        Set<Double> distinctVotes = voteCounts.keySet();
        double numVotes = votes.size();
        int prevTotal = 0;
        int currTotal = 0;
        for (Double vote : distinctVotes) {
            currTotal += voteCounts.get(vote);

            g.setColor(Color.getHSBColor(prevTotal / (float) numVotes, 1, 0.8f));
            ((Graphics2D) g).setStroke(new BasicStroke(10));

            int startAngle = (int) (prevTotal * 360.0 / numVotes);
            int endAngle = (int) (currTotal * 360.0 / numVotes);
            g.drawArc(
                50, 50,
                size - 100, size - 100,
                startAngle, endAngle - startAngle
            );
            prevTotal = currTotal;
        }
        g.setColor(Color.black);
        setFont(new Font("Impact", Font.PLAIN, 20));
        g.drawString("Avg: " + (voteSum / numVotes), (size - 50) / 2, size / 2);
    }
}
