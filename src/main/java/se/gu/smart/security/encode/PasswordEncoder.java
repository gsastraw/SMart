package se.gu.smart.security.encode;

import static java.util.Objects.requireNonNull;

public interface PasswordEncoder {

    EncodeResult encodePassword(String password);

    EncodeResult encodePassword(String password, byte[] salt);
    
    static PasswordEncoder getDefaultEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    class EncodeResult {
        private final String password;
        private final byte[] salt;

        public EncodeResult(String password, byte[] salt) {
            this.password = requireNonNull(password);
            this.salt = salt;
        }

        public String getPassword() {
            return password;
        }

        public byte[] getSalt() {
            return salt;
        }
    }
}
