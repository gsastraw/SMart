package se.gu.smart.model.account;

import static java.util.Objects.requireNonNull;

import se.gu.smart.permission.GeneralPermission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Account {

    // Potential: bio, work title, company, birthyear

    private final UUID accountId;
    private final AccountType accountType;
    private final String username; // final means we can not change it later
    private String displayName;
    private final List<GeneralPermission> permissions = new ArrayList<>();
    private String bio;
    private String location;

    public Account(AccountType accountType, String username, String displayName) {
        this(UUID.randomUUID(), accountType, username, displayName);
    }

    public Account(UUID accountId, AccountType accountType, String username, String displayName) {
        this.accountId = requireNonNull(accountId);
        this.accountType = requireNonNull(accountType);
        this.username = requireNonNull(username);
        this.displayName = requireNonNull(displayName);
    }

    public UUID getAccountId() {
        return accountId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

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

    public void addPermission(GeneralPermission permission) {
        permissions.add(permission);
    }

    public List<GeneralPermission> getPermissions() {
        return Collections.unmodifiableList(permissions);
    }

    public boolean hasPermission(GeneralPermission permission) {
        return permissions.contains(permission);
    }

    public enum AccountType {
        USER,
        ADMIN
    }
}

