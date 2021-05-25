package de.bbshaarentor.zeiterfassung;

import javax.swing.*;

import de.bbshaarentor.zeiterfassung.datamanagement.dataaccess.DataAccess;
import de.bbshaarentor.zeiterfassung.datamanagement.dataaccess.EmptyDataAccess;
import de.bbshaarentor.zeiterfassung.projekte.ProjektContainer;
import de.bbshaarentor.zeiterfassung.ui.MainView;
import de.bbshaarentor.zeiterfassung.ui.ZeitErfassenForm;

public class ProjektZeiterfassungMain {

    /**
     * Hier startet die Anwendung
     */
    public static void main(String[] args) {

        DataAccess dataAccess = new EmptyDataAccess();
        ProjektContainer projektContainer = new ProjektContainer(dataAccess);

        JFrame jFrame = new JFrame("Zeit Erfassung");
        MainView mainView = new MainView(projektContainer);
        jFrame.setContentPane(mainView.getMainPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        mainView.ladeFormInRechteSplitpane(new ZeitErfassenForm());
    }
}
