package se.gu.smart.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

final class SessionManagerTest {

    private SessionManager sessionManager;

    @BeforeEach
    void setup() {
        this.sessionManager = new SessionManager();
    }

    @Test
    void setActiveSession() {
        final var userId = UUID.randomUUID();

        sessionManager.setActiveSession(userId);

        assertTrue(sessionManager.getActiveSession().isPresent());
        assertEquals(userId, sessionManager.getActiveSession().get().getUserId());
    }

    @Test
    void getActiveUserSession() {
        assertFalse(sessionManager.getActiveSession().isPresent());
    }
}
