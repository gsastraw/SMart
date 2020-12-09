package se.gu.smart.service;

public final class Services {

    private static final UserAccountService USER_ACCOUNT_SERVICE = new UserAccountService();
    private static final UserAuthenticationService USER_AUTHENTICATION_SERVICE = new UserAuthenticationService();

    private Services() {
    }

    public static UserAccountService getUserAccountService() {
        return USER_ACCOUNT_SERVICE;
    }

    public static UserAuthenticationService getUserAuthenticationService() {
        return USER_AUTHENTICATION_SERVICE;
    }
}
