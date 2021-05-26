package de.bbshaarentor.zeiterfassung.datamanagement.dataaccess;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import de.bbshaarentor.zeiterfassung.User;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektBereichDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.ZeitErfassungsDaten;

public class EmptyDataAccess implements DataAccess {

    @Override
    public Collection<ProjektDaten> loadProjektDaten() {

        Set<ProjektDaten> projektDaten = new TreeSet<>();

        projektDaten.add(new ProjektDaten(1, "Zeiterfassungs Projekt", Arrays.asList(1L, 2L)));
        projektDaten.add(new ProjektDaten(2, "Was anderes", Arrays.asList(3L)));

        return projektDaten;
    }

    @Override
    public Collection<ProjektBereichDaten> loadProjektBereichDaten() {

        Set<ProjektBereichDaten> projektBereichDaten = new TreeSet<>();
        projektBereichDaten.add(new ProjektBereichDaten(1, "Konzeption", Arrays.asList(1L, 2L, 4L)));
        projektBereichDaten.add(new ProjektBereichDaten(2, "Entwicklung", Arrays.asList(3L, 5L, 8L)));
        projektBereichDaten.add(new ProjektBereichDaten(3, "Entwicklung", Arrays.asList(6L, 7L, 9L, 10L)));

        return projektBereichDaten;
    }

    @Override
    public Collection<ZeitErfassungsDaten> loadZeitErfassungen() {

        Set<ZeitErfassungsDaten> zeitErfassungen = new TreeSet<>();
        zeitErfassungen.add(new ZeitErfassungsDaten(1, "1", 1000 * 60 * 30, System.currentTimeMillis(), 1L));
        zeitErfassungen.add(new ZeitErfassungsDaten(2, "2", 1000 * 60 * 10, System.currentTimeMillis(), 1L));
        zeitErfassungen.add(new ZeitErfassungsDaten(3, "3", 1000 * 60 * 40, System.currentTimeMillis(), 1L));
        zeitErfassungen.add(new ZeitErfassungsDaten(4, "4", 1000 * 60 * 15, System.currentTimeMillis(), 1L));
        zeitErfassungen.add(new ZeitErfassungsDaten(5, "5", 1000 * 60 * 1, System.currentTimeMillis(), 1L));
        zeitErfassungen.add(new ZeitErfassungsDaten(6, "6", 1000 * 60 * 3, System.currentTimeMillis(), 1L));
        zeitErfassungen.add(new ZeitErfassungsDaten(7, "7", 1000 * 60 * 70, System.currentTimeMillis(), 1L));
        zeitErfassungen.add(new ZeitErfassungsDaten(8, "8", 1000 * 60 * 45, System.currentTimeMillis(), 1L));
        zeitErfassungen.add(new ZeitErfassungsDaten(9, "9", 1000 * 60 * 32, System.currentTimeMillis(), 1L));
        zeitErfassungen.add(new ZeitErfassungsDaten(10, "10", 1000 * 60 * 75, System.currentTimeMillis(), 1L));

        return zeitErfassungen;
    }

    @Override
    public List<User> loadUsers() {
        return Arrays.asList(new User(1L, "Benutzer1"));
    }

    @Override
    public void saveProjektDaten(Collection<ProjektDaten> projektDaten) {

    }

    @Override
    public void saveProjektBereichDaten(Collection<ProjektBereichDaten> projektBereichDaten) {

    }

    @Override
    public void saveZeitErfassung(Collection<ZeitErfassungsDaten> zeitErfassungen) {

    }

    @Override
    public void saveUser(Collection<User> users) {
    }
}
