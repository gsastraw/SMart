package se.gu.smart.security.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.gu.smart.exception.AccountNotFoundException;
import se.gu.smart.service.AccountService;
import se.gu.smart.service.Services;

import java.util.UUID;

final class SessionManagerTest {

    private SessionManager sessionManager;

    private final AccountService accountService = Services.getUserAccountService();

    @BeforeEach
    void setup() {
        this.sessionManager = new SessionManager();
    }

    @Test
    void setActiveSession() {
        final var userId = UUID.randomUUID();

        assertThrows(AccountNotFoundException.class, () -> sessionManager.setActiveSession(userId));
        assertFalse(sessionManager.getActiveSession().isPresent());

        final var userAccount = accountService.createUser("username", "name");

        sessionManager.setActiveSession(userAccount.getAccountId());

        assertTrue(sessionManager.getActiveSession().isPresent());
        assertEquals(userAccount.getAccountId(), sessionManager.getActiveSession().get().getAccountId());
    }

    @Test
    void getActiveUserSession() {
        assertFalse(sessionManager.getActiveSession().isPresent());
    }
}
