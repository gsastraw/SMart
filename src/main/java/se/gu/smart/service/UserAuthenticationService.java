package se.gu.smart.service;

import static java.util.Objects.requireNonNull;

import se.gu.smart.exception.AccountCredentialsNotFoundException;
import se.gu.smart.exception.AccountNotFoundException;
import se.gu.smart.exception.InvalidUsernamePasswordException;
import se.gu.smart.model.account.Account;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.AccountCredentialsRepository;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.security.encode.PasswordEncoder;

public final class UserAuthenticationService {

    private final AccountRepository accountRepository;
    private final AccountCredentialsRepository accountCredentialsRepository;

    UserAuthenticationService() {
        this.accountRepository = Repositories.getAccountRepository();
        this.accountCredentialsRepository = Repositories.getAccountCredentialsRepository();
    }

    public Account authenticateUser(String username, String password) {
        requireNonNull(username);
        requireNonNull(password);

        final var optionalAccount = accountRepository.getAccountByUsername(username);

        if (optionalAccount.isEmpty()) {
            throw new AccountNotFoundException(username);
        }

        final var account = optionalAccount.get();

        final var optionalCredentials = accountCredentialsRepository.getAccountCredentials(account.getAccountId());

        if (optionalCredentials.isEmpty()) {
            throw new AccountCredentialsNotFoundException(account.getAccountId());
        }

        final var credentials = optionalCredentials.get();

        final var passwordEncoder = PasswordEncoder.getDefaultEncoder();
        final var encodedPassword = passwordEncoder.encodePassword(password, credentials.getSalt());

        if (!encodedPassword.getPassword().equals(credentials.getPassword())) {
            throw new InvalidUsernamePasswordException();
        }

        return account;
    }
}
