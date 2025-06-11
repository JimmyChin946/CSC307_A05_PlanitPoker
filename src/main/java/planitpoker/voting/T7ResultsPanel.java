package planitpoker.voting;

import java.util.HashMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import planitpoker.T7Repository;

/**
 * Pie chart showing the breakdown of total votes
 *
 * @author Nathan Lackie
 */
public class T7ResultsPanel extends ChartPanel {
    protected T7ResultsPanel(JFreeChart chart) {
        super(chart);
    }

    public static T7ResultsPanel createPanel() {
        HashMap<String, Double> votes = T7Repository.getInstance().getVotes();

        HashMap<Double, Integer> voteCounts = new HashMap<>();

        double voteTotal = 0;
        for (String user : votes.keySet()) {
            Double vote = votes.get(user);
            voteCounts.merge(vote, 1, Integer::sum);
            voteTotal += vote;
        }
        double voteAverage = voteTotal / votes.size();


        DefaultPieDataset<Double> data = new DefaultPieDataset<>();
        for (Double vote : voteCounts.keySet()) {
            data.setValue(vote, voteCounts.get(vote) );
        }

        JFreeChart chart = ChartFactory.createPieChart("Vote Distribution", data, true, true, false);
        ((PiePlot<Double>) chart.getPlot()).setLabelGenerator(new StandardPieSectionLabelGenerator("{1} ({2})"));
        chart.addSubtitle(new TextTitle("Average: " + String.format("%.2f", voteAverage)));

        return new T7ResultsPanel(chart);
    }
}
