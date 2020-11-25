package se.gu.smart.exception;

import java.util.UUID;

public final class UserAccountAlreadyExistsException extends RuntimeException {

    public UserAccountAlreadyExistsException(UUID userId) {
        super(String.format("User account with id '%s' already exists", userId.toString()));
    }

    public UserAccountAlreadyExistsException(String username) {
        super(String.format("User account with username '%s' already exists", username));
    }
}
