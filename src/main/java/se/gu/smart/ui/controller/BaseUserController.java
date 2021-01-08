package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.account.Account;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.security.session.SessionManager;

import java.util.Optional;

public class BaseUserController extends BaseController {

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final AccountRepository accountRepository = Repositories.getAccountRepository();

    @FXML
    private Text topbarDisplaynameText;

    @FXML
    private Text sidebarUsernameText;

    @FXML
    private Optional<Account> loggedUserBase;

    @FXML
    void logOut(MouseEvent event) {
        sessionManager.removeActiveSession();
        redirect(event, "login.fxml");
    }

    @FXML
    void redirectReports(MouseEvent event) {
        redirect(event, "user_create_ticket");
    }

    @FXML
    void redirectViewProfile(MouseEvent event) {
        redirect(event, "user_view_profile");
    }

    @FXML
    void redirectDashboard(MouseEvent event) {
        redirect(event, "user_dashboard");
    }

    @FXML
    void redirectTimesheet(MouseEvent event) {
        redirect(event, "user_timesheet");
    }

    @FXML
    public void initialize() {
        randomizeQuotes();
        final var activeSession = sessionManager.getActiveSession();

        if (activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }

        this.loggedUserBase = accountRepository.getAccount(activeSession.get().getAccountId());
        loadTextDisplay();
    }
    @FXML
    void loadTextDisplay() {
        topbarDisplaynameText.setText(String.valueOf(loggedUserBase.get().getDisplayName()));
        sidebarUsernameText.setText(String.valueOf(loggedUserBase.get().getUsername()));
    }
}
