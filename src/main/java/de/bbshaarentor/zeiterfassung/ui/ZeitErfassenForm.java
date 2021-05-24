package de.bbshaarentor.zeiterfassung.ui;

import javax.swing.*;

public class ZeitErfassenForm implements ZeitErfassungsUIPanel {
    private JTextArea kommentarTextArea;
    private JButton speichernButton;
    private JComboBox benutzerComboBox;
    private JPanel mainPanel;

    @Override
    public JPanel getMainPanel() {
        return this.mainPanel;
    }
}
