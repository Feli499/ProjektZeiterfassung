package de.bbshaarentor.zeiterfassung;

import java.io.File;

import javax.swing.*;

import de.bbshaarentor.zeiterfassung.datamanagement.dataaccess.DataAccess;
import de.bbshaarentor.zeiterfassung.datamanagement.dataaccess.FileDataAccess;
import de.bbshaarentor.zeiterfassung.projekte.ProjektContainer;
import de.bbshaarentor.zeiterfassung.ui.FehlerDialog;
import de.bbshaarentor.zeiterfassung.ui.MainView;
import de.bbshaarentor.zeiterfassung.ui.ZeitErfassenForm;

public class ProjektZeiterfassungMain {

    /**
     * Hier startet die Anwendung
     */
    public static void main(String[] args) {

        try {

            DataAccess dataAccess = new FileDataAccess(new File("daten"));
            ProjektContainer projektContainer = new ProjektContainer(dataAccess);

            JFrame jFrame = new JFrame("Zeit Erfassung");
            MainView mainView = new MainView(projektContainer);
            jFrame.setContentPane(mainView.getMainPanel());
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.pack();
            jFrame.setVisible(true);

            mainView.ladeFormInRechteSplitpane(new ZeitErfassenForm());

        } catch (Exception e) {
            showFehlerDialog(e);
        }
    }

    public static void showFehlerDialog(Exception e) {

        FehlerDialog jDialog = new FehlerDialog();
        jDialog.setTitle("Fehler:");
        String stacktrace = e.getLocalizedMessage() + "\n";
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            stacktrace += stackTraceElement.toString() + "\n";
        }
        jDialog.setFehlerText(stacktrace);
        jDialog.setSize(420, 180);
        jDialog.setVisible(true);
    }
}
