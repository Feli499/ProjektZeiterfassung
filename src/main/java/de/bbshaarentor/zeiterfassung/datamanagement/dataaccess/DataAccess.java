package de.bbshaarentor.zeiterfassung.datamanagement.dataaccess;

import java.util.Collection;

import de.bbshaarentor.zeiterfassung.datamanagement.ProjektBereichDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektDaten;
import de.bbshaarentor.zeiterfassung.projekte.ZeitErfassung;

public interface DataAccess {

    Collection<ProjektDaten> loadProjektDaten();

    Collection<ProjektBereichDaten> loadProjektBereichDaten();

    Collection<ZeitErfassung> loadZeitErfassungen();

    void saveProjektDaten(Collection<ProjektDaten> projektDaten);

    void saveProjektBereichDaten(Collection<ProjektBereichDaten> projektBereichDaten);

    void saveZeitErfassung(Collection<ZeitErfassung> zeitErfassungen);

}
