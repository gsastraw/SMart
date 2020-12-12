package se.gu.smart.exception;

import java.util.UUID;

public final class AccountCredentialsNotFoundException extends RuntimeException {

    public AccountCredentialsNotFoundException(UUID userId) {
        super(String.format("Credentials for user account with id '%s' not found", userId.toString()));
    }

}
