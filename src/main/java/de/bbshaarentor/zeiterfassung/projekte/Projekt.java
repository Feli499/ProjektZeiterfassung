package de.bbshaarentor.zeiterfassung.projekte;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Projekt implements Comparable<Projekt> {

    private final long id;
    private final String bezeichnung;
    private final Set<ProjektBereich> projektBereiche;

    public Projekt(long id, String bezeichnung, Collection<ProjektBereich> projektBereiche) {

        this.id = id;
        this.bezeichnung = bezeichnung;
        this.projektBereiche = new HashSet<>(projektBereiche);
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

    @Override
    public int compareTo(Projekt o) {
        return Long.compare(this.getId(), o.getId());
    }
}
