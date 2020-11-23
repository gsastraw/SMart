package se.gu.smart.security.encode;

public interface PasswordEncoder {

    String encodePassword(String password);
    
    static PasswordEncoder getDefaultEncoder() {
        return new Pbkdf2PasswordEncoder();
    }
}
