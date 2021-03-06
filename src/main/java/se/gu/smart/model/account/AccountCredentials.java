package se.gu.smart.model.account;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class AccountCredentials {

    private final UUID accountId; //obvs cant change user account
    private final String password; //passwords are changeable (didnt add password changes cause admin panel doesn't exist yet.)
    private final byte[] salt;

    @JsonCreator
    public AccountCredentials(
        @JsonProperty("accountId") UUID accountId,
        @JsonProperty("password") String password,
        @JsonProperty("salt") byte[] salt
    ) {
        this.accountId = requireNonNull(accountId);
        this.password = requireNonNull(password);
        this.salt = requireNonNull(salt);
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getPassword() {
        return password;
    }

    public byte[] getSalt() {
        return salt;
    }
}

