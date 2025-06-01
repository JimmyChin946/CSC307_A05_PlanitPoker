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

        HashMap<Double, Integer> voteCounts = new HashMap<>();

        for (String user : votes.keySet()) {
            Double vote = votes.get(user);
            voteCounts.merge(vote, 1, Integer::sum);
        }

        Set<Double> distinctVotes = voteCounts.keySet();
        double totalVoteSum = votes.size();
        int prevTotal = 0;
        int currTotal = 0;
        for (Double vote : distinctVotes) {
            currTotal += voteCounts.get(vote);

            g.setColor(Color.getHSBColor(prevTotal / (float) totalVoteSum, 1, 0.8f));
            ((Graphics2D) g).setStroke(new BasicStroke(10));

            int startAngle = (int) (prevTotal * 360.0 / totalVoteSum);
            int endAngle = (int) (currTotal * 360.0 / totalVoteSum);
            g.drawArc(
                50, 50,
                size - 100, size - 100,
                startAngle, endAngle - startAngle
            );
            prevTotal = currTotal;
        }
    }
}
