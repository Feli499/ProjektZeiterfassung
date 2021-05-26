package de.bbshaarentor.zeiterfassung.datamanagement.dataaccess;

import java.util.Collection;

import de.bbshaarentor.zeiterfassung.User;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektBereichDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.ZeitErfassungsDaten;

public interface DataAccess {

    Collection<ProjektDaten> loadProjektDaten() throws Exception;

    Collection<ProjektBereichDaten> loadProjektBereichDaten() throws Exception;

    Collection<ZeitErfassungsDaten> loadZeitErfassungen() throws Exception;

    Collection<User> loadUsers() throws Exception;

    void saveProjektDaten(Collection<ProjektDaten> projektDaten) throws Exception;

    void saveProjektBereichDaten(Collection<ProjektBereichDaten> projektBereichDaten) throws Exception;

    void saveZeitErfassung(Collection<ZeitErfassungsDaten> zeitErfassungen) throws Exception;

    void saveUser(Collection<User> users) throws Exception;

}
