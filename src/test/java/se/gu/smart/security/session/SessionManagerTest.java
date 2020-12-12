package se.gu.smart.security.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.gu.smart.exception.UserAccountNotFoundException;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.UserAccountRepository;

import java.util.UUID;

final class SessionManagerTest {

    private SessionManager sessionManager;

    private final UserAccountRepository userAccountRepository = Repositories.getUserAccountRepository();

    @BeforeEach
    void setup() {
        this.sessionManager = new SessionManager();
    }

    @Test
    void setActiveSession() {
        final var userId = UUID.randomUUID();

        assertThrows(UserAccountNotFoundException.class, () -> sessionManager.setActiveSession(userId));
        assertFalse(sessionManager.getActiveSession().isPresent());

        final var userAccount = userAccountRepository.createUserAccount("username", "name");

        sessionManager.setActiveSession(userAccount.getUserId());

        assertTrue(sessionManager.getActiveSession().isPresent());
        assertEquals(userAccount.getUserId(), sessionManager.getActiveSession().get().getUserId());
    }

    @Test
    void getActiveUserSession() {
        assertFalse(sessionManager.getActiveSession().isPresent());
    }
}
