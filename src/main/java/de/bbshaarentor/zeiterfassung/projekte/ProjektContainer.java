package de.bbshaarentor.zeiterfassung.projekte;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import de.bbshaarentor.zeiterfassung.datamanagement.ProjektBereichDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.dataaccess.DataAccess;

public class ProjektContainer {

    private final DataAccess dataAccess;
    private Set<Projekt> projekte;

    public ProjektContainer(DataAccess dataAccess) {

        this.dataAccess = dataAccess;
        this.loadProjekte();
    }

    private void loadProjekte() {

        Set<Projekt> projekte = new TreeSet<>();

        Collection<ProjektDaten> projektDatenCollection = this.dataAccess.loadProjektDaten();
        Collection<ProjektBereichDaten> projektBereichDatenCollection = this.dataAccess.loadProjektBereichDaten();
        Collection<ZeitErfassung> zeitErfassungCollection = this.dataAccess.loadZeitErfassungen();

        Map<Long, ProjektBereichDaten> projektBereichDatenMap = new HashMap<>();
        projektBereichDatenCollection.forEach((projektBereichDaten -> projektBereichDatenMap.put(projektBereichDaten.getId(), projektBereichDaten)));

        Map<Long, ZeitErfassung> zeitErfassungMap = new HashMap<>();
        zeitErfassungCollection.forEach((zeitErfassung -> zeitErfassungMap.put(zeitErfassung.getId(), zeitErfassung)));

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

                projektBereiche.add(new ProjektBereich(projektBereichDaten.getId(), projektDaten.getBezeichnung(), zeitErfassungen));
            }

            projekte.add(new Projekt(projektDaten.getId(), projektDaten.getBezeichnung(), projektBereiche));
        }

        this.projekte = projekte;
    }

    public Set<Projekt> getProjekte() {
        return new TreeSet<>(this.projekte);
    }

    public void addProjekt(Projekt projekt) {

        if (!this.isIdVerfuegbar(projekt.getId())) {
            throw new IllegalArgumentException(String.format("Die ID '%d' ist bereits vergeben.", projekt.getId()));
        }

        this.projekte.add(projekt);
    }

    public Projekt createAndAddProjekt(String bezeichnung) {

        Projekt projekt = new Projekt(this.ermittleFreieId(), bezeichnung);
        this.addProjekt(projekt);
        return projekt;
    }

    public boolean isIdVerfuegbar(long id) {

        for (Projekt projekt : this.projekte) {
            if (projekt.getId() == id) {
                return false;
            }
        }

        return true;
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
