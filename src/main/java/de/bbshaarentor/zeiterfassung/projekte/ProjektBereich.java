package de.bbshaarentor.zeiterfassung.projekte;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ProjektBereich implements Comparable<ProjektBereich> {

    private final long id;
    private final String bezeichnung;
    private final Set<ZeitErfassung> zeitErfassungen;

    public ProjektBereich(long id, String bezeichnung, Collection<ZeitErfassung> zeitErfassungen) {

        this.id = id;
        this.bezeichnung = bezeichnung;
        this.zeitErfassungen = new TreeSet<>(zeitErfassungen);
    }

    public ProjektBereich(long id, String bezeichnung) {
        this(id, bezeichnung, new HashSet<>());
    }

    public long getId() {
        return this.id;
    }

    public String getBezeichnung() {
        return this.bezeichnung;
    }

    public Set<ZeitErfassung> getZeitErfassungen() {
        return new TreeSet<>(this.zeitErfassungen);
    }

    public void addZeitErfassung(ZeitErfassung zeitErfassung) {
        this.zeitErfassungen.add(zeitErfassung);
    }

    @Override
    public int compareTo(ProjektBereich o) {
        return Long.compare(this.getId(), o.getId());
    }
}
