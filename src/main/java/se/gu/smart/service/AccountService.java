package se.gu.smart.service;

import static java.util.Objects.requireNonNull;

import se.gu.smart.exception.UserAccountAlreadyExistsException;
import se.gu.smart.model.account.Account;
import se.gu.smart.model.account.Account.AccountType;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.AccountCredentialsRepository;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.security.encode.PasswordEncoder;

public final class AccountService {

    private final AccountRepository accountRepository;
    private final AccountCredentialsRepository accountCredentialsRepository;

    AccountService() {
        this.accountRepository = Repositories.getUserAccountRepository();
        this.accountCredentialsRepository = Repositories.getUserAccountCredentialsRepository();
    }

    public Account createUser(String username, String password) {
        return createAccount(AccountType.USER, username, password);
    }

    public Account createAdministrator(String username, String password) {
        return createAccount(AccountType.ADMIN, username, password);
    }

    private Account createAccount(AccountType accountType, String username, String password) {
        requireNonNull(username);
        requireNonNull(password);

        final var optionalAccount = accountRepository.getAccountByUsername(username);

        if (optionalAccount.isPresent()) {
            throw new UserAccountAlreadyExistsException(username);
        }

        final var passwordEncoder = PasswordEncoder.getDefaultEncoder();
        final var encodedPassword = passwordEncoder.encodePassword(password);

        final var userAccount = accountRepository.createAccount(accountType, username, username); // display name is set as the username by default
        accountCredentialsRepository
                .createAccountCredentials(userAccount.getAccountId(), encodedPassword.getPassword(), encodedPassword.getSalt());

        return userAccount;
    }
}
