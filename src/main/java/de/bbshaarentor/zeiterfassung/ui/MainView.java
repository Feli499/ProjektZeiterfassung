package de.bbshaarentor.zeiterfassung.ui;

import javax.swing.*;

public class MainView implements ZeitErfassungsUIPanel {

    private JTree projektJTree;
    private JSplitPane splitPane;
    private JPanel mainPanel;

    @Override
    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    public void ladeFormInRechteSplitpane(ZeitErfassungsUIPanel zeitErfassungsUIPanel) {
        this.splitPane.setRightComponent(zeitErfassungsUIPanel.getMainPanel());
    }

    private void createUIComponents() {

        /*
         * TODO: Wir müssen hier unser eigenes TreeModel schreiben und reingeben damit wir im JTree
         * unsere Projekte / Zeiterfassungen repräsentieren können.
         */
        this.projektJTree = new JTree();
    }
}
