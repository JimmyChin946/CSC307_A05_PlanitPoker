package planitpoker;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.*;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Controller for the ExportPanel
 * when exporting files or the data as csv
 *
 * @author Jude Shin 
 */
public class ExportController {

	private Main main;

	public ExportController(Main main) {
		this.main = main;
	}

	public void export(String type) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save As");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(type.toUpperCase() + " Files", type.toLowerCase());
		fileChooser.setFileFilter(fileFilter);

		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			ArrayList<Vote> votes = null; //Repository.getInstance().getVotes();

			try (FileWriter writer = new FileWriter(selectedFile)) {

				if (type.equalsIgnoreCase("csv")) {
					writer.write("Title,Description,Score\n");
					for (Vote v : votes) {
						String line = String.format("\"%s\",\"%s\",%f\n",
								escapeCsv(v.getTitle()),
								escapeCsv(v.getDescription()),
								v.getScore());
						writer.write(line);
					}

				} else if (type.equalsIgnoreCase("json")) {
					StringBuilder jsonBuilder = new StringBuilder();
					jsonBuilder.append("[\n");
					for (int i = 0; i < votes.size(); i++) {
						Vote v = votes.get(i);
						jsonBuilder.append("  {\n");
						jsonBuilder.append("    \"title\": ").append(toJsonString(v.getTitle())).append(",\n");
						jsonBuilder.append("    \"description\": ").append(toJsonString(v.getDescription())).append(",\n");
						jsonBuilder.append("    \"score\": ").append(v.getScore()).append("\n");
						jsonBuilder.append("  }");
						if (i < votes.size() - 1) {
							jsonBuilder.append(",");
						}
						jsonBuilder.append("\n");
					}
					jsonBuilder.append("]");
					writer.write(jsonBuilder.toString());

				} else {
					JOptionPane.showMessageDialog(null, "Unsupported file type: " + type);
					return;
				}

				JOptionPane.showMessageDialog(null, "Export successful!");
				switchGUI();

			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Export failed: " + e.getMessage());
			}
		}
	}

	private String escapeCsv(String input) {
		return input.replace("\"", "\"\"");
	}

	private String toJsonString(String input) {
		if (input == null) return "null";
		return "\"" + input.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
	}

	
	private void switchGUI() {
		// these will be implemented by someone else
		// main.setTitle("");
		// for now, I commented them out so that they don't break the program
		// StoriesNanny createRoomNanny = new StoriesNanny(main);
		// StoriesPanel createRoomPanel = new StoriesPanel(createRoomNanny);
		// main.setContentPane(createRoomPanel);
		// main.revalidate();
		// main.repaint();
	}
}

