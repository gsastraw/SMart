package se.gu.smart.model;

import java.util.UUID;

public class UserAccount {

    // Potential: bio, work title, company, birthyear

    private final UUID userId;
    private final String username; // final means we can not change it later
    private String displayName;

    public UserAccount(String username, String displayName) {
        this(UUID.randomUUID(), username, displayName);
    }

    public UserAccount(UUID userId, String username, String displayName) {
        this.userId = userId;
        this.username = username;
        this.displayName = displayName;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) { // We don't have setters for those with the final attribute.
        this.displayName = displayName;
    }
}
