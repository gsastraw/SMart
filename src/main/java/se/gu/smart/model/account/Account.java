package se.gu.smart.model.account;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import se.gu.smart.permission.GeneralPermission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Account {

    private final UUID accountId;
    private final AccountType accountType;
    private final String username;
    private String displayName;
    private final List<GeneralPermission> permissions = new ArrayList<>();
    private String bio;

    public Account(AccountType accountType, String username, String displayName) {
        this(UUID.randomUUID(), accountType, username, displayName);
    }

    @JsonCreator
    public Account (
        @JsonProperty("accountId") UUID accountId,
        @JsonProperty("accountType") AccountType accountType,
        @JsonProperty("username") String username,
        @JsonProperty("displayName") String displayName
    ) {
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

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setDisplayName(String displayName) { // We don't have setters for those with the final attribute.
        this.displayName = displayName;
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
        return "User ID: " + getAccountId() + "\nDisplay Name: " + getDisplayName() + "\nUsername: " + getUsername() + "\nAccount type: " + getAccountType();
    }
}
