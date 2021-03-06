package se.gu.smart.security.encode;

import static java.util.Objects.requireNonNull;

import se.gu.smart.exception.PasswordEncoderException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class Pbkdf2PasswordEncoder implements PasswordEncoder {

    private static final int DEFAULT_ENCODE_ITERATIONS = 65536;
    private static final int DEFAULT_ENCODE_KEY_LENGTH = 128;

    @Override
    public EncodeResult encodePassword(String password) {
        return encodePassword(password, generateSalt());
    }

    @Override
    public EncodeResult encodePassword(String password, byte[] salt) {
        requireNonNull(password);

        final var spec = new PBEKeySpec(password.toCharArray(), salt, DEFAULT_ENCODE_ITERATIONS, DEFAULT_ENCODE_KEY_LENGTH);

        try {
            final var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            final var encoded = factory.generateSecret(spec).getEncoded();

            return new EncodeResult(new String(encoded), salt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new PasswordEncoderException();
        }
    }

    private byte[] generateSalt() {
        final var random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }
}
