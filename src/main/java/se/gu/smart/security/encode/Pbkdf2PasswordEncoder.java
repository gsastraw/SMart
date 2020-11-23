package se.gu.smart.security.encode;

import static java.util.Objects.requireNonNull;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class Pbkdf2PasswordEncoder implements PasswordEncoder {

    private static final int DEFAULT_ENCODE_ITERATIONS = 65536;
    private static final int DEFAULT_ENCODE_KEY_LENGTH = 128;

    @Override
    public String encodePassword(String password) {
        return new String(encodePassword0(requireNonNull(password)));
    }

    private byte[] encodePassword0(String password) {
        final var spec = new PBEKeySpec(password.toCharArray(), generateSalt(), DEFAULT_ENCODE_ITERATIONS, DEFAULT_ENCODE_KEY_LENGTH);

        try {
            final var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    private byte[] generateSalt() {
        final var random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }
}
