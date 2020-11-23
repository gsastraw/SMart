package se.gu.smart.session;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.UUID;

public final class SessionManager {

    private Session activeSession;

    public void setActiveSession(UUID userId) {
        requireNonNull(userId);

        if (activeSession != null) {
            if (activeSession.getUserId().equals(userId)) {
                return;
            }
        }

        this.activeSession = new Session(userId);
    }

    public Optional<Session> getActiveSession() {
        return Optional.ofNullable(activeSession);
    }
}
