package de.bbshaarentor.zeiterfassung.datamanagement.dataaccess;

import java.io.File;
import java.util.Collection;

import de.bbshaarentor.zeiterfassung.User;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektBereichDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektDaten;
import de.bbshaarentor.zeiterfassung.projekte.ZeitErfassung;

public class FileDataAccess implements DataAccess {

    private final File directory;

    public FileDataAccess(File directory) {

        this.directory = directory;
        if (!directory.exists())
            directory.mkdirs();

    }

    @Override
    public Collection<ProjektDaten> loadProjektDaten() {
        return null;
    }

    @Override
    public Collection<ProjektBereichDaten> loadProjektBereichDaten() {
        return null;
    }

    @Override
    public Collection<ZeitErfassung> loadZeitErfassungen() {
        return null;
    }

    @Override
    public Collection<User> loadUsers() {
        return null;
    }

    @Override
    public void saveProjektDaten(Collection<ProjektDaten> projektDaten) {

    }

    @Override
    public void saveProjektBereichDaten(Collection<ProjektBereichDaten> projektBereichDaten) {

    }

    @Override
    public void saveZeitErfassung(Collection<ZeitErfassung> zeitErfassungen) {

    }

    @Override
    public void saveUser(Collection<User> users) {

    }
}
