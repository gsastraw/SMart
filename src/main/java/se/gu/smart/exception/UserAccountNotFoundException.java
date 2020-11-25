package se.gu.smart.exception;

import java.util.UUID;

public final class UserAccountNotFoundException extends RuntimeException {

    public UserAccountNotFoundException(UUID userId) {
        super(String.format("User account with id '%s' not found", userId.toString()));
    }

    public UserAccountNotFoundException(String username) {
        super(String.format("User account with username '%s' not found", username));
    }
}
