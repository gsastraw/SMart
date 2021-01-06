package se.gu.smart.repository;

import static java.util.Objects.requireNonNull;

import se.gu.smart.model.account.Account;
import se.gu.smart.model.account.Account.AccountType;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

// Stores all our user accounts (CRUD = Create, Read, Update, Delete)
public class AccountRepository implements Repository<Account> {

    private final Set<Account> accounts = new HashSet<>();

    protected AccountRepository() {
    }

    public Account createAccount(AccountType accountType, String username, String displayName){
        final var account = new Account(accountType, username, displayName);
        accounts.add(account);
        return account;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public Optional<Account> getAccount(UUID accountId){
        return accounts.stream().filter(account -> account.getAccountId().equals(accountId)).findAny();
    }

    public Optional<Account> getAccountByUsername(String username) {
        return accounts.stream().filter(account -> account.getUsername().equals(username)).findAny();
    }

    public boolean removeAccount(UUID accountId){
        return accounts.removeIf(userAccount -> userAccount.getAccountId().equals(accountId)); // Returns true if successful at removing
    }
    public void updateAccount(UUID accountId, String displayName, String bio){
        accounts.stream()
            .filter(account -> account.getAccountId().equals(accountId)) // filters it
            .findAny() // returns it
            .ifPresent(account -> {
                if (displayName != null) {
                    account.setDisplayName(displayName);
                }

                if (bio != null) {
                    account.setBio(bio);
                }
            }); // sets it
    }

    public void load(Collection<Account> accounts) {
        requireNonNull(accounts);

        this.accounts.addAll(accounts);
    }
}
