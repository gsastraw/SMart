package se.gu.smart.repository;

public final class Repositories {
    
    private static final AccountRepository USER_ACCOUNT_REPOSITORY = new AccountRepository();
    private static final AccountCredentialsRepository USER_ACCOUNT_CREDENTIALS_REPOSITORY = new AccountCredentialsRepository();
    private static final TicketRepository TICKET_REPOSITORY = new TicketRepository();
    
    private Repositories() {
    }
    
    public static AccountRepository getUserAccountRepository() {
        return USER_ACCOUNT_REPOSITORY;
    }
    
    public static AccountCredentialsRepository getUserAccountCredentialsRepository() {
        return USER_ACCOUNT_CREDENTIALS_REPOSITORY;
    }

    public static TicketRepository getTicketRepository() {
        return TICKET_REPOSITORY;
    }
}
