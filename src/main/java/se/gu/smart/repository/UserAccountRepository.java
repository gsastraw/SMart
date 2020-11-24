package se.gu.smart.repository;

import se.gu.smart.model.UserAccount;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

// Stores all our user accounts (CRUD = Create, Read, Update, Delete)
public class UserAccountRepository {

    private final Set<UserAccount> userAccounts = new HashSet<>();

    public UserAccount createUserAccount(String username, String displayName){
        UserAccount userAccount = new UserAccount(username, displayName);
        userAccounts.add(userAccount);
        return userAccount;    }

    public Optional<UserAccount> getUserAccount(UUID userId){
        return userAccounts.stream().filter(userAccount -> userAccount.getUserId().equals(userId)).findAny();
    }

    public boolean removeUserAccount(UUID userId){
        return userAccounts.removeIf(userAccount -> userAccount.getUserId().equals(userId)); // Returns true if successful at removing
    }

    public void updateUserAccount(UUID userId, String displayName){
        userAccounts.stream()
            .filter(userAccount -> userAccount.getUserId().equals(userId)) // filters it
            .findAny() // returns it
            .ifPresent(userAccount -> userAccount.setDisplayName(displayName)); // sets it
    }
}