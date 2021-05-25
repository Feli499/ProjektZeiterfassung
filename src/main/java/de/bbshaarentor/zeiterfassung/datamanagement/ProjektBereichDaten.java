package de.bbshaarentor.zeiterfassung.datamanagement;

import java.util.List;

public class ProjektBereichDaten implements Comparable<ProjektBereichDaten> {

    private final long id;
    private final String bezeichnung;
    private final List<Long> zeitErfassungenIds;

    public ProjektBereichDaten(long id, String bezeichnung, List<Long> zeitErfassungenIds) {

        this.id = id;
        this.bezeichnung = bezeichnung;
        this.zeitErfassungenIds = zeitErfassungenIds;
    }

    public long getId() {
        return this.id;
    }

    public String getBezeichnung() {
        return this.bezeichnung;
    }

    public List<Long> getZeitErfassungenIds() {
        return this.zeitErfassungenIds;
    }

    @Override
    public int compareTo(ProjektBereichDaten o) {
        return Long.compare(this.getId(), o.getId());
    }
}
