package se.gu.smart.service;

import static java.util.Objects.requireNonNull;

import se.gu.smart.exception.UserAccountCredentialsNotFoundException;
import se.gu.smart.exception.UserAccountNotFoundException;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.UserAccountCredentialsRepository;
import se.gu.smart.repository.UserAccountRepository;
import se.gu.smart.security.encode.PasswordEncoder;

public final class UserAuthenticationService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountCredentialsRepository userAccountCredentialsRepository;

    UserAuthenticationService() {
        this.userAccountRepository = Repositories.getUserAccountRepository();
        this.userAccountCredentialsRepository = Repositories.getUserAccountCredentialsRepository();
    }

    public boolean authenticateUser(String username, String password) {
        requireNonNull(username);
        requireNonNull(password);

        final var optionalAccount = userAccountRepository.getUserAccountByUsername(username);

        if (optionalAccount.isEmpty()) {
            throw new UserAccountNotFoundException(username);
        }

        final var userAccount = optionalAccount.get();

        final var optionalCredentials = userAccountCredentialsRepository.getUserAccountCredentials(userAccount.getUserId());

        if (optionalCredentials.isEmpty()) {
            throw new UserAccountCredentialsNotFoundException(userAccount.getUserId());
        }

        final var credentials = optionalCredentials.get();

        final var passwordEncoder = PasswordEncoder.getDefaultEncoder();
        final var encodedPassword = passwordEncoder.encodePassword(password, credentials.getSalt());

        return encodedPassword.getPassword().equals(credentials.getPassword());
    }
}
