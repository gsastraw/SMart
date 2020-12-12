package se.gu.smart.repository;

import se.gu.smart.model.user.UserAccountCredentials;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class UserAccountCredentialsRepository {

    private final Set<UserAccountCredentials> userAccountCredentials = new HashSet<>();

    protected UserAccountCredentialsRepository() {
    }

    public UserAccountCredentials createUserAccountCredentials(UUID userId, String password, byte[] salt){
        UserAccountCredentials credentials = new UserAccountCredentials(userId, password, salt);
        userAccountCredentials.add(credentials);
        return credentials;
    }

    public Optional<UserAccountCredentials> getUserAccountCredentials(UUID userId){
        return userAccountCredentials.stream().filter(userAccount -> userAccount.getUserId().equals(userId)).findAny();
    }

    public boolean removeUserAccountCredentials(UUID userId){
        return userAccountCredentials.removeIf(userAccount -> userAccount.getUserId().equals(userId));
    }
}
