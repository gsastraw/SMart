package se.gu.smart.repository;

import se.gu.smart.model.account.Account;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

// Stores all our user accounts (CRUD = Create, Read, Update, Delete)
public class AccountRepository {

    private final Set<Account> accounts = new HashSet<>();

    protected AccountRepository() {
    }

    public Account createUserAccount(String username, String displayName){
        Account account = Account.createUserAccount(username, displayName);
        accounts.add(account);
        return account;
    }

    public Optional<Account> getUserAccount(UUID userId){
        return accounts.stream().filter(userAccount -> userAccount.getAccountId().equals(userId)).findAny();
    }

    public Optional<Account> getUserAccountByUsername(String username) {
        return accounts.stream().filter(userAccount -> userAccount.getUsername().equals(username)).findAny();
    }

    public boolean removeUserAccount(UUID userId){
        return accounts.removeIf(userAccount -> userAccount.getAccountId().equals(userId)); // Returns true if successful at removing
    }

    public void updateUserAccount(UUID userId, String displayName){
        accounts.stream()
            .filter(userAccount -> userAccount.getAccountId().equals(userId)) // filters it
            .findAny() // returns it
            .ifPresent(userAccount -> userAccount.setDisplayName(displayName)); // sets it
    }
}
