package de.bbshaarentor.zeiterfassung.datamanagement.dataaccess;

import java.util.Collection;
import java.util.HashSet;

import de.bbshaarentor.zeiterfassung.User;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektBereichDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektDaten;
import de.bbshaarentor.zeiterfassung.projekte.ZeitErfassung;

public class EmptyDataAccess implements DataAccess {

    @Override
    public Collection<ProjektDaten> loadProjektDaten() {
        return new HashSet<>();
    }

    @Override
    public Collection<ProjektBereichDaten> loadProjektBereichDaten() {
        return new HashSet<>();
    }

    @Override
    public Collection<ZeitErfassung> loadZeitErfassungen() {
        return new HashSet<>();
    }

    @Override
    public Collection<User> loadUsers() {
        return new HashSet<>();
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
