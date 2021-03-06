package se.gu.smart.security.session;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.UUID;

final class SessionTest {

    @Test
    void userIdNull() {
        assertThrows(NullPointerException.class, () -> new Session(null));
    }

    @Test
    void constructor() {
        assertDoesNotThrow(() -> new Session(UUID.randomUUID()));
    }
}
