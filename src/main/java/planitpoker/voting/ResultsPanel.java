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
    Repository repository = Repository.getInstance();

    public ResultsPanel() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        HashMap<User, Double> votes = repository.getVotes();
        HashMap<Double, Integer> voteCounts = new HashMap<>();

        double totalVoteSum = 0;
        for (User user : votes.keySet()) {
            Double vote = votes.get(user);
            voteCounts.merge(vote, 1, Integer::sum);
            totalVoteSum += vote;
        }

        Set<Double> distinctVotes = voteCounts.keySet();
        int prevTotal = 0;
        int currTotal = 0;
        for (Double vote : distinctVotes) {
            currTotal += voteCounts.get(vote);
            g.drawArc(
                width / 2, height / 2,
                width, height,
                (int) (prevTotal * 360.0 / totalVoteSum), (int) (currTotal * 360.0 / totalVoteSum)
            );
            prevTotal = currTotal;
        }
    }
}
