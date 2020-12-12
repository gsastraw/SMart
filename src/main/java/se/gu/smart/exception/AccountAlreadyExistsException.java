package se.gu.smart.exception;

import java.util.UUID;

public final class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException(UUID userId) {
        super(String.format("User account with id '%s' already exists", userId.toString()));
    }

    public AccountAlreadyExistsException(String username) {
        super(String.format("User account with username '%s' already exists", username));
    }
}
