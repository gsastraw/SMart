package se.gu.smart.model;

public class UserAccountAuthentication {
    private final UserAccount userAccount; //obvs cant change user account
    private final String password; //passwords are changeable (didnt add password changes cause admin panel doesn't exist yet.)

    public UserAccountAuthentication(UserAccount userAccount, String password) {
        this.userAccount = userAccount;
        this.password = password;
    }

    public UserAccount getUserAccount(){return userAccount;}

    public String getPassword(){return password;}
}

