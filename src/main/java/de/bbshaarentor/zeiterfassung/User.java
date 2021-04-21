package de.bbshaarentor.zeiterfassung;

public class User {

    private final long userId;
    private final String name;

    public User(long userId, String name) {

        this.userId = userId;
        this.name = name;
    }

    public long getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }
}
