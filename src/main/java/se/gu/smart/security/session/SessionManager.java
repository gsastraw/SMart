package se.gu.smart.security.session;

import static java.util.Objects.requireNonNull;

import se.gu.smart.exception.UserAccountNotFoundException;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.AccountRepository;

import java.util.Optional;
import java.util.UUID;

public final class SessionManager {

    private static final SessionManager INSTANCE = new SessionManager();

    private final AccountRepository accountRepository = Repositories.getUserAccountRepository();

    private Session activeSession;

    protected SessionManager() {
    }

    public static SessionManager getInstance() {
        return INSTANCE;
    }

    public void setActiveSession(UUID userId) {
        requireNonNull(userId);

        if (activeSession != null) {
            if (activeSession.getUserId().equals(userId)) {
                return;
            }
        }

        final var optionalUserAccount = accountRepository.getUserAccount(userId);

        if (optionalUserAccount.isEmpty()) {
            throw new UserAccountNotFoundException(userId);
        }

        this.activeSession = new Session(userId);
    }

    public Optional<Session> getActiveSession() {
        return Optional.ofNullable(activeSession);
    }
}
