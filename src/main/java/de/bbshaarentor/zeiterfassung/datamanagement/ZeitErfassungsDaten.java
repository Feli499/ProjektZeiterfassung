package de.bbshaarentor.zeiterfassung.datamanagement;

public class ZeitErfassungsDaten implements Comparable<ZeitErfassungsDaten> {

    private final long id;
    private final String kommentar;
    private final long logZeit;
    private final long startZeit;
    private final long userID;

    public ZeitErfassungsDaten(long id, String kommentar, long logZeit, long startZeit, long userID) {

        this.id = id;
        this.kommentar = kommentar;
        this.logZeit = logZeit;
        this.startZeit = startZeit;
        this.userID = userID;
    }

    public long getId() {
        return this.id;
    }

    public String getKommentar() {
        return this.kommentar;
    }

    public long getLogZeit() {
        return this.logZeit;
    }

    public long getStartZeit() {
        return this.startZeit;
    }

    public long getUserID() {
        return this.userID;
    }

    @Override
    public int compareTo(ZeitErfassungsDaten o) {
        return Long.compare(o.getId(), this.getId());
    }
}
