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

public class BaseAdminController extends BaseController{


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
    void redirectResetSystem(MouseEvent event) {
        redirect(event, "admin_reset_system");
    }

    @FXML
    void redirectReports(MouseEvent event) {
        redirect(event, "admin_tickets");
    }

    @FXML
    void redirectManageUsers(MouseEvent event) {
        redirect(event, "admin_manage_users");
    }

    @FXML
    void redirectCreateUser(MouseEvent event) {
        redirect(event, "admin_create_user");
    }

    @FXML
    void redirectAdministration(MouseEvent event) {
        redirect(event, "admin_administration");
    }

    @FXML
    public void initialize() {
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
