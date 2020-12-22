package se.gu.smart.model.account;

import se.gu.smart.permission.GeneralPermission;

import java.time.LocalDate;
import java.util.*;

import static java.util.Objects.requireNonNull;

public class Account {

    // Potential: bio, work title, company, birthyear

    private final UUID accountId;
    private final AccountType accountType;
    private final String username; // final means we can not change it later
    private String displayName;
    private final List<GeneralPermission> permissions = new ArrayList<>();
    private String bio;
    private String location;
    private LocalDate birthdate;

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

    public LocalDate getBirthdate() { return birthdate; }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDisplayName(String displayName) { // We don't have setters for those with the final attribute.
        this.displayName = displayName;
    }

    public void setBirthdate(LocalDate displayName) {
        this.birthdate = birthdate;
    }

    public void addPermission(GeneralPermission... permissions) {
        if (permissions == null) return;

        Arrays.stream(permissions).filter(Objects::nonNull).forEach(this.permissions::add);
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

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountType=" + accountType +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", permissions=" + permissions +
                ", bio='" + bio + '\'' +
                ", location='" + location + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}

