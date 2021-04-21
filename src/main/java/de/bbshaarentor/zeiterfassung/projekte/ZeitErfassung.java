package de.bbshaarentor.zeiterfassung.projekte;

import de.bbshaarentor.zeiterfassung.User;

public class ZeitErfassung {

    private final long id;
    private final String kommentar;
    private final User benutzer;

    public ZeitErfassung(long id, String Kommentar, User benutzer){

        this.id = id;
        kommentar = Kommentar;
        this.benutzer = benutzer;
    }

    public long getId() {
        return id;
    }

    public String getKommentar() {
        return kommentar;
    }

    public User getBenutzer() {
        return benutzer;
    }
}
