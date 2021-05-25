package de.bbshaarentor.zeiterfassung.projekte;

import de.bbshaarentor.zeiterfassung.User;

public class ZeitErfassung implements Comparable<ZeitErfassung> {

    private final long id;
    private final String kommentar;
    private final long startZeit;
    private final User benutzer;
    private final long logZeit;

    public ZeitErfassung(long id, String Kommentar, long logZeit, long startZeit, User benutzer) {

        this.id = id;
        this.kommentar = Kommentar;
        this.logZeit = logZeit;
        this.startZeit = startZeit;
        this.benutzer = benutzer;
    }

    public long getId() {
        return this.id;
    }

    public String getKommentar() {
        return this.kommentar;
    }

    public User getBenutzer() {
        return this.benutzer;
    }

    public long getLogZeit() {
        return this.logZeit;
    }

    public long getStartZeit() {
        return this.startZeit;
    }

    @Override
    public int compareTo(ZeitErfassung o) {
        return (int) ((int) o.getLogZeit() - this.logZeit);
    }
}
