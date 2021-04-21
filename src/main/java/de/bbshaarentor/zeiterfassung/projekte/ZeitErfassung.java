package de.bbshaarentor.zeiterfassung.projekte;

import de.bbshaarentor.zeiterfassung.User;

public class ZeitErfassung {

    private final long id;
    private final String kommentar;
    private final long startZeit;
    private final long endZeit;
    private final User benutzer;

    public ZeitErfassung(long id, String Kommentar, long startZeit, long endZeit, User benutzer) {

        this.id = id;
        this.kommentar = Kommentar;
        this.startZeit = startZeit;
        this.endZeit = endZeit;
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

    public long getStartZeit() {
        return this.startZeit;
    }

    public long getEndZeit() {
        return this.endZeit;
    }
}
