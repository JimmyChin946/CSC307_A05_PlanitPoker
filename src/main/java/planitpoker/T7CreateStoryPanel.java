package planitpoker;

import javax.swing.*;
import java.awt.*;

/**
 * Panel shown when a user is adding stories.
 *
 * @author Kai Swangler
 */
public class T7CreateStoryPanel extends JPanel {
    public T7CreateStoryPanel(T7CreateStoryController createStoryController, JDialog dialog) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Create New Story", SwingConstants.CENTER);

        JTextArea storyTextArea = new JTextArea("Put your stories here. Each line contains a new story");
        JScrollPane storyScrollPane = new JScrollPane(storyTextArea);

        JPanel buttonPanel = new JPanel(new GridLayout(2,2));
        JButton saveAddNewButton = new JButton("Save & Add New");
        JButton saveCloseButton = new JButton("Save & Close");
        JButton importButton = new JButton("Import");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(saveAddNewButton);
        buttonPanel.add(saveCloseButton);
        buttonPanel.add(importButton);
        buttonPanel.add(cancelButton);

        add(titleLabel, BorderLayout.NORTH);
        add(storyScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        saveAddNewButton.addActionListener(e -> {
            createStoryController.saveAddNew(storyTextArea.getText());
            storyTextArea.setText("Put your stories text here. Each line contains new story.");
        });
        saveCloseButton.addActionListener(e -> {
            createStoryController.saveClose(storyTextArea.getText());
            dialog.dispose();
        });
        cancelButton.addActionListener(e -> {
            dialog.dispose();
        });

        importButton.addActionListener(e -> {
            createStoryController.importFromFile(this);
        });

    }
}
