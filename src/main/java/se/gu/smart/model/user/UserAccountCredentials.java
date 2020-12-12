package se.gu.smart.model.user;

import static java.util.Objects.requireNonNull;

import java.util.UUID;

public class UserAccountCredentials {

    private final UUID userId; //obvs cant change user account
    private final String password; //passwords are changeable (didnt add password changes cause admin panel doesn't exist yet.)
    private final byte[] salt;

    public UserAccountCredentials(UUID userId, String password, byte[] salt) {
        this.userId = requireNonNull(userId);
        this.password = requireNonNull(password);
        this.salt = requireNonNull(salt);
    }

    public UUID getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public byte[] getSalt() {
        return salt;
    }
}

