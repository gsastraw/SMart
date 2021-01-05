package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.account.Account;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.security.session.SessionManager;

import java.util.Optional;

public final class ViewProfileController extends BaseUserController {

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final AccountRepository accountRepository = Repositories.getUserAccountRepository();

    @FXML
    private Optional<Account> loggedUser;
    @FXML
    private Text usernameText;
    @FXML
    private Text displayText;
    @FXML
    private Text ageText;
    @FXML
    private Text assignedIssuesText;
    @FXML
    private TextArea aboutMeTextArea;
    @FXML
    private Button editButton;
    @FXML
    void redirectEdit(MouseEvent event) {
        redirect(event, "user_edit_profile");
    }

    @FXML
    public void initialize() {
        super.initialize();

        final var activeSession = sessionManager.getActiveSession();

        if (activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }

        this.loggedUser = accountRepository.getAccount(activeSession.get().getAccountId());

        loadUserData();
    }

    @FXML
    void loadUserData() {
        usernameText.setText(String.valueOf(loggedUser.get().getUsername()));
        displayText.setText(String.valueOf(loggedUser.get().getDisplayName()));
        aboutMeTextArea.setText(String.valueOf(loggedUser.get().getBio()));
    }

    @FXML
    void setUserData() {

    }


}
