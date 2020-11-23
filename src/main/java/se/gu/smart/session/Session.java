package se.gu.smart.session;

import static java.util.Objects.requireNonNull;

import java.util.UUID;

public final class Session {

    private final UUID userId;

    public Session(UUID userId) {
        this.userId = requireNonNull(userId);
    }

    public UUID getUserId() {
        return userId;
    }
}
