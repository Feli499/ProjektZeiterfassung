package de.bbshaarentor.zeiterfassung.projekte;

import java.util.HashSet;
import java.util.Set;

public class ProjektContainer {

    private final Set<Projekt> projekte;

    public ProjektContainer() {
        this.projekte = new HashSet<>();
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
