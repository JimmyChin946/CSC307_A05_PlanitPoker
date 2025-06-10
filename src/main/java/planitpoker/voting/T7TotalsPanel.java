package planitpoker.voting;

import java.util.HashMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import planitpoker.T7Repository;
import planitpoker.T7Story;

/**
 * Bar chart showing the average vote of each user
 *
 * @author Nathan Lackie
 */
public class T7TotalsPanel extends ChartPanel {
    protected T7TotalsPanel(JFreeChart chart) {
        super(chart);
    }

    public static T7TotalsPanel createPanel() {
        DefaultCategoryDataset data = new DefaultCategoryDataset();

        HashMap<String, Double> userTotals = new HashMap<>();
        HashMap<String, Double> userAmounts = new HashMap<>();
        for (T7Story story : T7Repository.getInstance().getStories()) {
            HashMap<String, Double> votes = story.getVotes();
            for (String user : votes.keySet()) {
                userTotals.merge(user, votes.get(user), Double::sum);
                userAmounts.merge(user, 1.0, Double::sum);
            }
        }

        for (String user : userTotals.keySet()) {
            data.addValue(userTotals.get(user) / userAmounts.get(user), "Default", user);
        }

        JFreeChart chart = ChartFactory.createBarChart("Average User Votes", "User", "Average Vote", data);
        chart.removeLegend();
        return new T7TotalsPanel(chart);
    }
}
