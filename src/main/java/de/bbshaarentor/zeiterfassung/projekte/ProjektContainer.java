package de.bbshaarentor.zeiterfassung.projekte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import de.bbshaarentor.zeiterfassung.User;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektBereichDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.ZeitErfassungsDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.dataaccess.DataAccess;

public class ProjektContainer {

    private final DataAccess dataAccess;
    private Set<Projekt> projekte;

    private final Set<Runnable> runnables = new HashSet<>();

    public ProjektContainer(DataAccess dataAccess) throws Exception {

        this.dataAccess = dataAccess;
        this.loadProjekte();
    }

    private void loadProjekte() throws Exception {

        Set<Projekt> projekte = new TreeSet<>();

        Collection<ProjektDaten> projektDatenCollection = this.dataAccess.loadProjektDaten();
        Collection<ProjektBereichDaten> projektBereichDatenCollection = this.dataAccess.loadProjektBereichDaten();
        Collection<ZeitErfassungsDaten> zeitErfassungCollection = this.dataAccess.loadZeitErfassungen();
        Collection<User> users = this.dataAccess.loadUsers();

        Map<Long, User> userMap = new HashMap<>();
        users.forEach(user -> userMap.put(user.getId(), user));

        Map<Long, ProjektBereichDaten> projektBereichDatenMap = new HashMap<>();
        projektBereichDatenCollection.forEach((projektBereichDaten -> projektBereichDatenMap.put(projektBereichDaten.getId(), projektBereichDaten)));

        Map<Long, ZeitErfassung> zeitErfassungMap = new HashMap<>();
        zeitErfassungCollection.forEach((zeitErfassung -> zeitErfassungMap.put(zeitErfassung.getId(), new ZeitErfassung(zeitErfassung.getId(), zeitErfassung.getKommentar(), zeitErfassung.getLogZeit(), zeitErfassung.getStartZeit(), userMap.get(zeitErfassung.getUserID())))));

        for (ProjektDaten projektDaten : projektDatenCollection) {

            Set<ProjektBereich> projektBereiche = new TreeSet<>();

            for (Long projektBereichID : projektDaten.getProjektBereicheIds()) {

                if (!projektBereichDatenMap.containsKey(projektBereichID)) {
                    throw new NullPointerException(String.format("Das Projekt '%s' (ID: %d) verweist auf einen Projektbereich mit der ID '%d', diese ist jedoch unbekannt.", projektDaten.getBezeichnung(), projektDaten.getId(), projektBereichID));
                }

                ProjektBereichDaten projektBereichDaten = projektBereichDatenMap.get(projektBereichID);

                Set<ZeitErfassung> zeitErfassungen = new TreeSet<>();

                for (Long zeitErfassungID : projektBereichDaten.getZeitErfassungenIds()) {

                    if (!zeitErfassungMap.containsKey(zeitErfassungID)) {
                        throw new NullPointerException(String.format("Der Projektbereich '%s' (ID: %d) vom Projekt '%s' (ID: %d) verweist auf eine ZeitErfassung mit der ID '%d', diese ist jedoch unbekannt.", projektBereichDaten.getBezeichnung(), projektBereichDaten.getId(),
                                projektDaten.getBezeichnung(), projektDaten.getId(), zeitErfassungID));
                    }

                    zeitErfassungen.add(zeitErfassungMap.get(zeitErfassungID));
                }

                projektBereiche.add(new ProjektBereich(projektBereichDaten.getId(), projektBereichDaten.getBezeichnung(), zeitErfassungen));
            }

            projekte.add(new Projekt(projektDaten.getId(), projektDaten.getBezeichnung(), projektBereiche));
        }

        this.projekte = projekte;
        this.runnables.forEach(x -> x.run());
    }

    public void registerNewRunnable(Runnable runnable) {
        this.runnables.add(runnable);
    }

    public Set<Projekt> getProjekte() {
        return new TreeSet<>(this.projekte);
    }

    public void addProjekt(String projektName) throws Exception {

        long id = this.ermittleFreieId();
        Set<ProjektDaten> projektList = new TreeSet<>();
        for (Projekt pro : this.projekte) {

            List<Long> ids = new ArrayList<>();
            pro.getProjektBereiche().forEach(projektBereich -> ids.add(projektBereich.getId()));
            projektList.add(new ProjektDaten(pro.getId(), pro.getBezeichnung(), ids));
        }

        projektList.add(new ProjektDaten(id, projektName, Collections.emptyList()));

        this.dataAccess.saveProjektDaten(projektList);
        this.loadProjekte();
    }

    public void addProjektBereich(Projekt projekt, String bereichName) throws Exception {

        long id = 0;
        Set<ProjektDaten> projektList = new TreeSet<>();
        Set<ProjektBereichDaten> projektBereiche = new TreeSet<>();
        for (Projekt pro : this.projekte) {
            for (ProjektBereich projektBereich : pro.getProjektBereiche()) {
                if (projektBereich.getId() >= id) {
                    id = projektBereich.getId() + 1;
                }

                List<Long> ids = new ArrayList<>();
                projektBereich.getZeitErfassungen().forEach(zeitErfassung -> ids.add(zeitErfassung.getId()));
                projektBereiche.add(new ProjektBereichDaten(projektBereich.getId(), projektBereich.getBezeichnung(), ids));
            }

            if (projekt.getId() != pro.getId()) {
                List<Long> ids = new ArrayList<>();
                pro.getProjektBereiche().forEach(projektBereich -> ids.add(projektBereich.getId()));
                projektList.add(new ProjektDaten(pro.getId(), pro.getBezeichnung(), ids));
            }
        }

        projektBereiche.add(new ProjektBereichDaten(id, bereichName, Collections.emptyList()));

        List<Long> ids = new ArrayList<>();
        projekt.getProjektBereiche().forEach(projektBereich -> ids.add(projektBereich.getId()));
        ids.add(id);
        projektList.add(new ProjektDaten(projekt.getId(), projekt.getBezeichnung(), ids));

        this.dataAccess.saveProjektBereichDaten(projektBereiche);
        this.dataAccess.saveProjektDaten(projektList);
        this.loadProjekte();
    }

    public void addZeitErfassung(ProjektBereich bereich, String kommentar, Long dauer, Long startZeit, User user) throws Exception {

        long id = 0;

        Set<ProjektBereichDaten> projektBereiche = new TreeSet<>();
        Set<ZeitErfassungsDaten> zeitErfassungsDaten = new TreeSet<>();

        for (Projekt pro : this.projekte) {
            for (ProjektBereich projektBereich : pro.getProjektBereiche()) {
                for (ZeitErfassung zeitErfassung : projektBereich.getZeitErfassungen()) {

                    if (zeitErfassung.getId() >= id) {
                        id = zeitErfassung.getId() + 1;
                    }

                    zeitErfassungsDaten.add(new ZeitErfassungsDaten(zeitErfassung.getId(), zeitErfassung.getKommentar(), zeitErfassung.getLogZeit(), zeitErfassung.getStartZeit(), zeitErfassung.getBenutzer().getId()));
                }

                if (projektBereich.getId() != bereich.getId()) {
                    List<Long> ids = new ArrayList<>();
                    projektBereich.getZeitErfassungen().forEach(zeitErfassung -> ids.add(zeitErfassung.getId()));
                    projektBereiche.add(new ProjektBereichDaten(pro.getId(), pro.getBezeichnung(), ids));
                }
            }
        }

        zeitErfassungsDaten.add(new ZeitErfassungsDaten(id, kommentar, dauer, startZeit, user.getId()));

        List<Long> ids = new ArrayList<>();
        bereich.getZeitErfassungen().forEach(projektBereich -> ids.add(projektBereich.getId()));
        ids.add(id);
        projektBereiche.add(new ProjektBereichDaten(bereich.getId(), bereich.getBezeichnung(), ids));

        this.dataAccess.saveZeitErfassung(zeitErfassungsDaten);
        this.dataAccess.saveProjektBereichDaten(projektBereiche);
        this.loadProjekte();
    }

    public boolean isIdVerfuegbar(long id) {

        for (Projekt projekt : this.projekte) {
            if (projekt.getId() == id) {
                return false;
            }
        }

        return true;
    }

    public Set<User> getBenutzer() throws Exception {
        return new HashSet<>(this.dataAccess.loadUsers());
    }

    public void createBenutzer(String benutzerName) throws Exception {

        Set<User> benutzer = this.getBenutzer();
        long id = 0;
        for (User user : benutzer) {
            if (user.getId() >= id) {
                id = user.getId() + 1;
            }
        }
        benutzer.add(new User(id, benutzerName));
        this.dataAccess.saveUser(benutzer);
    }

    private long ermittleFreieId() {

        long id = -1;

        for (Projekt x : this.projekte) {
            if (x.getId() > id) {
                id = x.getId();
            }
        }
        return id + 1;
    }
}
