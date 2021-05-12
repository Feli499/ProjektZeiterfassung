package de.bbshaarentor.zeiterfassung.projekte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ProjektBereich {

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
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public Set<ZeitErfassung> getZeitErfassungen() {
        return new TreeSet<>(zeitErfassungen);
    }

    public void addZeitErfassung(ZeitErfassung zeitErfassung){
        zeitErfassungen.add(zeitErfassung);
    }
}
