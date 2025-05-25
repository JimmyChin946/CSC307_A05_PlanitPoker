package planitpoker;

import javax.swing.*;
import java.awt.*;

/**
 * Exporting the voting data that is stored in the Blackboard 
 * into a CSV file or json 
 *
 * @author Jude Shin 
 */
public class ExportPanel extends JPanel {
	public ExportPanel (ExportNanny exportNanny) {
		setLayout(new GridLayout(4, 1));
		JLabel title = new JLabel("Export Votes");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title);

		JPanel box1 = new JPanel();
		box1.setLayout(new GridLayout(1, 2));
		JLabel modeLabel = new JLabel("Export Type: ");
		box1.add(modeLabel);
		String[] options = {"csv", "json"};
		JComboBox<String> comboBox = new JComboBox<>(options);
		box1.add(comboBox);
		add(box1);

		JPanel box2 = new JPanel();
		JButton exportButton = new JButton("Export");
		box2.add(exportButton);
		add(box2);

		exportButton.addActionListener(e -> exportNanny.export((String) comboBox.getSelectedItem()));
	}
}

