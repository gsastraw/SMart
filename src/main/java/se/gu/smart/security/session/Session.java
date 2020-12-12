package se.gu.smart.security.session;

import static java.util.Objects.requireNonNull;

import java.util.UUID;

public final class Session {

    private final UUID accountId;

    public Session(UUID accountId) {
        this.accountId = requireNonNull(accountId);
    }

    public UUID getAccountId() {
        return accountId;
    }
}
