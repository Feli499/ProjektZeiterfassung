package de.bbshaarentor.zeiterfassung.datamanagement;

import java.util.List;

public class ProjektDaten {

    private final long id;
    private final String bezeichnung;
    private final List<Long> projektBereicheIds;

    public ProjektDaten(long id, String bezeichnung, List<Long> projektBereicheIds) {

        this.id = id;
        this.bezeichnung = bezeichnung;
        this.projektBereicheIds = projektBereicheIds;
    }

    public long getId() {
        return this.id;
    }

    public String getBezeichnung() {
        return this.bezeichnung;
    }

    public List<Long> getProjektBereicheIds() {
        return this.projektBereicheIds;
    }
}
