package de.bbshaarentor.zeiterfassung;

import javax.swing.*;

import de.bbshaarentor.zeiterfassung.ui.MainView;
import de.bbshaarentor.zeiterfassung.ui.ZeitErfassenForm;

public class ProjektZeiterfassungMain {

    /**
     * Hier startet die Anwendung
     */
    public static void main(String[] args) {

        JFrame jFrame = new JFrame("Zeit Erfassung");
        MainView mainView = new MainView();
        jFrame.setContentPane(mainView.getMainPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        mainView.ladeFormInRechteSplitpane(new ZeitErfassenForm());
    }
}
