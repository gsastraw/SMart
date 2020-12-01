package se.gu.smart.model;

import se.gu.smart.permission.GeneralPermission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UserAccount {

    // Potential: bio, work title, company, birthyear

    private final UUID userId;
    private final String username; // final means we can not change it later
    private String displayName;
    private final List<GeneralPermission> permissions = new ArrayList<>();
    private String bio;
    private String location;

    public UserAccount(String username, String displayName) {
        this(UUID.randomUUID(), username, displayName);
    }

    public UserAccount(UUID userId, String username, String displayName) {
        this.userId = userId;
        this.username = username;
        this.displayName = displayName;
        this.bio = bio;
        this.location = location;
    }

    public UUID getUserId() { return userId; }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() { return displayName; }

    public String getBio() { return bio; }

    public String getLocation() {
        return location;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDisplayName(String displayName) { // We don't have setters for those with the final attribute.
        this.displayName = displayName;
    }

    public void setPermissions(GeneralPermission permission) {
        permissions.add(permission);
    }

    public List<GeneralPermission> getPermissions() {
        return Collections.unmodifiableList(permissions);
    }

    public boolean hasPermission(GeneralPermission permission) {
        return permissions.contains(permission);
    }
}

