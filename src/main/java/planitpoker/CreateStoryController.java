package planitpoker;


import javax.swing.*;

public class CreateStoryController {
    private Repository repository = Repository.getInstance();
    private Main main;

    public CreateStoryController(Main main) {
        this.main = main;
    }

    public void saveAddNew(String text) {
        Story story = new Story(text);
        repository.addStory(story, false);
    }

    public void saveClose(String text) {
        Story story = new Story(text);
        repository.addStory(story, false);
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
