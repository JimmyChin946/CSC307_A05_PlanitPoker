package planitpoker;


import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Controller for the CreateStoryPanel.
 *
 * @author Kai Swangler
 */
public class CreateStoryController {
    private Main main;

    public CreateStoryController(Main main) {
        this.main = main;
    }

    public void saveAddNew(String text) {
        String[] lines = text.split("\\r?\\n");
        int count = 0;

        for (String line : lines) {
            line = line.trim();
            if (!line.isEmpty()) {
                Story story = new Story(line);
                Repository.getInstance().addStory(story, false);
                count++;
            }
        }

        if (count > 0) {
            Repository.getInstance().setCurrentStoryIndex(
                    Repository.getInstance().getCurrentStoryIndex(), false
            );
        }
    }

    public void saveClose(String text) {
        saveAddNew(text);
    }

    public void importFromFile(Component parentComponent) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(parentComponent);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                int count = 0;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        saveAddNew(line);
                        count++;
                    }
                }
                JOptionPane.showMessageDialog(parentComponent, count + " stories imported successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parentComponent, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void createNewStoryDialog() {
        JFrame parentFrame = main;
        if (parentFrame != null) {
            JDialog dialog = new JDialog(parentFrame, "Create New Story", true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setSize(400, 400);
            dialog.setLocationRelativeTo(parentFrame);

            CreateStoryPanel panel = new CreateStoryPanel(this, dialog);
            dialog.setContentPane(panel);
            dialog.setVisible(true);
        }
    }
}
