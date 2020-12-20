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

    public AccountCredentials createAccountCredentials(UUID accountId, String password, byte[] salt){
        AccountCredentials credentials = new AccountCredentials(accountId, password, salt);
        accountCredentials.add(credentials);
        return credentials;
    }

    public Optional<AccountCredentials> getAccountCredentials(UUID accountId){
        return accountCredentials.stream().filter(accountCredentials -> accountCredentials.getAccountId().equals(accountId)).findAny();
    }

    public boolean removeAccountCredentials(UUID accountId){
        return accountCredentials.removeIf(accountCredentials -> accountCredentials.getAccountId().equals(accountId));
    }
}
