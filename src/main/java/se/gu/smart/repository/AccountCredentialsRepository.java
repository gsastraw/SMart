package se.gu.smart.repository;

import se.gu.smart.model.account.AccountCredentials;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class AccountCredentialsRepository {

    private final Set<AccountCredentials> accountCredentials = new HashSet<>();

    protected AccountCredentialsRepository() {
    }

    public AccountCredentials createUserAccountCredentials(UUID userId, String password, byte[] salt){
        AccountCredentials credentials = new AccountCredentials(userId, password, salt);
        accountCredentials.add(credentials);
        return credentials;
    }

    public Optional<AccountCredentials> getUserAccountCredentials(UUID userId){
        return accountCredentials.stream().filter(userAccount -> userAccount.getAccountId().equals(userId)).findAny();
    }

    public boolean removeUserAccountCredentials(UUID userId){
        return accountCredentials.removeIf(userAccount -> userAccount.getAccountId().equals(userId));
    }
}
