package se.gu.smart.service;

import static java.util.Objects.requireNonNull;

import se.gu.smart.exception.UserAccountAlreadyExistsException;
import se.gu.smart.model.UserAccount;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.UserAccountCredentialsRepository;
import se.gu.smart.repository.UserAccountRepository;
import se.gu.smart.security.encode.PasswordEncoder;

public final class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountCredentialsRepository userAccountCredentialsRepository;

    public UserAccountService() {
        this.userAccountRepository = Repositories.getUserAccountRepository();
        this.userAccountCredentialsRepository = Repositories.getUserAccountCredentialsRepository();
    }

    public UserAccount createUser(String username, String password) {
        requireNonNull(username);
        requireNonNull(password);

        final var optionalAccount = userAccountRepository.getUserAccountByUsername(username);

        if (optionalAccount.isPresent()) {
            throw new UserAccountAlreadyExistsException(username);
        }

        final var passwordEncoder = PasswordEncoder.getDefaultEncoder();
        final var encodedPassword = passwordEncoder.encodePassword(password);

        final var userAccount = userAccountRepository.createUserAccount(username, username); // display name is set as the username by default
        userAccountCredentialsRepository.createUserAccountCredentials(userAccount.getUserId(), encodedPassword.getPassword(), encodedPassword.getSalt());

        return userAccount;
    }
}
