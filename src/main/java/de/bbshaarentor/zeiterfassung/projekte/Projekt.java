package de.bbshaarentor.zeiterfassung.projekte;

import java.util.HashSet;
import java.util.Set;

public class Projekt {

    private final long id;
    private final String bezeichnung;
    private final Set<ProjektBereich> projektBereiche;

    public Projekt(long id, String bezeichnung, Set<ProjektBereich> projektBereiche) {

        this.id = id;
        this.bezeichnung = bezeichnung;
        this.projektBereiche = projektBereiche;
    }

    public Projekt(long id, String bezeichnung) {
        this(id, bezeichnung, new HashSet<>());
    }

    public long getId() {
        return this.id;
    }

    public String getBezeichnung() {
        return this.bezeichnung;
    }

    public Set<ProjektBereich> getProjektBereiche() {
        return new HashSet<>(this.projektBereiche);
    }
}
