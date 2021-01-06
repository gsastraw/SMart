package se.gu.smart.repository;

public final class Repositories {
    
    private static final AccountRepository USER_ACCOUNT_REPOSITORY = new AccountRepository();
    private static final AccountCredentialsRepository USER_ACCOUNT_CREDENTIALS_REPOSITORY = new AccountCredentialsRepository();
    private static final TicketRepository TICKET_REPOSITORY = new TicketRepository();
    private static final ProjectRepository PROJECT_REPOSITORY = new ProjectRepository();
    private static SelectedUser SELECTED_USER = new SelectedUser();
    private static SelectedProject SELECTED_PROJECT = new SelectedProject();

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

    public static ProjectRepository getProjectRepository() {
        return PROJECT_REPOSITORY;
    }

    public static SelectedUser getSelectedUser(){
        return SELECTED_USER;
    }

    public static SelectedProject getSelectedProject(){
        return SELECTED_PROJECT;
    }
}
