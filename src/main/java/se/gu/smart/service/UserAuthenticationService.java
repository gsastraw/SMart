package se.gu.smart.service;

import static java.util.Objects.requireNonNull;

import se.gu.smart.exception.UserAccountCredentialsNotFoundException;
import se.gu.smart.exception.UserAccountNotFoundException;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.AccountCredentialsRepository;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.security.encode.PasswordEncoder;

public final class UserAuthenticationService {

    private final AccountRepository accountRepository;
    private final AccountCredentialsRepository accountCredentialsRepository;

    UserAuthenticationService() {
        this.accountRepository = Repositories.getUserAccountRepository();
        this.accountCredentialsRepository = Repositories.getUserAccountCredentialsRepository();
    }

    public boolean authenticateUser(String username, String password) {
        requireNonNull(username);
        requireNonNull(password);

        final var optionalAccount = accountRepository.getUserAccountByUsername(username);

        if (optionalAccount.isEmpty()) {
            throw new UserAccountNotFoundException(username);
        }

        final var userAccount = optionalAccount.get();

        final var optionalCredentials = accountCredentialsRepository.getUserAccountCredentials(userAccount.getAccountId());

        if (optionalCredentials.isEmpty()) {
            throw new UserAccountCredentialsNotFoundException(userAccount.getAccountId());
        }

        final var credentials = optionalCredentials.get();

        final var passwordEncoder = PasswordEncoder.getDefaultEncoder();
        final var encodedPassword = passwordEncoder.encodePassword(password, credentials.getSalt());

        return encodedPassword.getPassword().equals(credentials.getPassword());
    }
}
