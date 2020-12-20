package se.gu.smart.service;

public final class Services {

    private static final AccountService USER_ACCOUNT_SERVICE = new AccountService();
    private static final UserAuthenticationService USER_AUTHENTICATION_SERVICE = new UserAuthenticationService();

    private Services() {
    }

    public static AccountService getUserAccountService() {
        return USER_ACCOUNT_SERVICE;
    }

    public static UserAuthenticationService getUserAuthenticationService() {
        return USER_AUTHENTICATION_SERVICE;
    }
}
