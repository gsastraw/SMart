package se.gu.smart.repository;

public final class Repositories {
    
    private static final UserAccountRepository USER_ACCOUNT_REPOSITORY = new UserAccountRepository();
    private static final UserAccountCredentialsRepository USER_ACCOUNT_CREDENTIALS_REPOSITORY = new UserAccountCredentialsRepository();
    private static final TicketRepository TICKET_REPOSITORY = new TicketRepository();
    
    private Repositories() {
    }
    
    public static UserAccountRepository getUserAccountRepository() {
        return USER_ACCOUNT_REPOSITORY;
    }
    
    public static UserAccountCredentialsRepository getUserAccountCredentialsRepository() {
        return USER_ACCOUNT_CREDENTIALS_REPOSITORY;
    }

    public static TicketRepository getTicketRepository() {
        return TICKET_REPOSITORY;
    }
}
