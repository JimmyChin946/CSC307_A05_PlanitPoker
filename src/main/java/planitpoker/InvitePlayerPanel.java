package planitpoker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

public class InvitePlayerPanel extends JPanel {
    private JTextField roomNameField;
    private JButton copyButton;

    public InvitePlayerPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Invite a teammate:");
        roomNameField = new JTextField(Repository.getInstance().getCurrentRoomName());
        roomNameField.setEditable(false);
        copyButton = new JButton("Copy");

        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String roomName = roomNameField.getText();
                StringSelection selection = new StringSelection(roomName);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, null);
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(roomNameField, BorderLayout.CENTER);
        inputPanel.add(copyButton, BorderLayout.EAST);

        add(label);
        add(inputPanel);
    }
}
