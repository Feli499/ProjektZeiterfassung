package de.bbshaarentor.zeiterfassung.datamanagement.dataaccess;

import java.util.Collection;
import java.util.Collections;

import de.bbshaarentor.zeiterfassung.datamanagement.ProjektBereichDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektDaten;
import de.bbshaarentor.zeiterfassung.projekte.ZeitErfassung;

public class EmptyDataAccess implements DataAccess {

    @Override
    public Collection<ProjektDaten> loadProjektDaten() {
        return Collections.emptySet();
    }

    @Override
    public Collection<ProjektBereichDaten> loadProjektBereichDaten() {
        return Collections.emptySet();
    }

    @Override
    public Collection<ZeitErfassung> loadZeitErfassungen() {
        return Collections.emptySet();
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
}
