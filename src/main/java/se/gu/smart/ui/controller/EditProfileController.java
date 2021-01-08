package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.account.Account;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.security.session.SessionManager;

import java.util.Optional;
import java.util.UUID;

public final class EditProfileController extends BaseUserController {

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final AccountRepository accountRepository = Repositories.getAccountRepository();

    @FXML
    private Optional<Account> loggedUser;
    @FXML
    private Text usernameText;
    @FXML
    private TextField changeName;
    @FXML
    private Text ageText;
    @FXML
    private Text assignedIssuesText;
    @FXML
    private TextArea changeAboutMeTextArea;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;

    @FXML
    void redirectBackButton(MouseEvent event) {
        redirect(event, "user_view_profile");
    }

    @FXML
    void redirectSaveButton(MouseEvent event) {
        userData(loggedUser.get().getAccountId());
        redirect(event, "user_view_profile");
    }

    @FXML
    public void initialize() {
        super.initialize();
        final var activeSession = sessionManager.getActiveSession();
        if (activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }

        this.loggedUser = accountRepository.getAccount(activeSession.get().getAccountId());
        viewUser();
    }

    public void userData(UUID accountId) {
        if (!changeName.getText().isEmpty() || !changeAboutMeTextArea.getText().isEmpty()) {
            accountRepository.updateAccount(accountId, changeName.getText(), changeAboutMeTextArea.getText());
        }
    }
    @FXML
    void viewUser(){
        usernameText.setText(String.valueOf(loggedUser.get().getUsername()));
    }
}
