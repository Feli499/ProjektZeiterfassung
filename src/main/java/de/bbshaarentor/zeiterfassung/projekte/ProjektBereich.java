package de.bbshaarentor.zeiterfassung.projekte;

import java.util.HashSet;
import java.util.Set;

public class ProjektBereich {

    private final long id;
    private final String bezeichnung;
    private final Set<ZeitErfassung> zeitErfassungen;

    public ProjektBereich(long id, String bezeichnung, Set<ZeitErfassung> zeitErfassungen) {

        this.id = id;
        this.bezeichnung = bezeichnung;
        this.zeitErfassungen = zeitErfassungen;
    }

    public ProjektBereich(long id, String bezeichnung) {
        this(id, bezeichnung, new HashSet<>());
    }

    public long getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public Set<ZeitErfassung> getZeitErfassungen() {
        return new HashSet<>(zeitErfassungen);
    }
}
