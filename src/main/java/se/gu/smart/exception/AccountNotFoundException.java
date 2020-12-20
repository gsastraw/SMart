package se.gu.smart.exception;

import java.util.UUID;

public final class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(UUID userId) {
        super(String.format("User account with id '%s' not found", userId.toString()));
    }

    public AccountNotFoundException(String username) {
        super(String.format("User account with username '%s' not found", username));
    }
}
