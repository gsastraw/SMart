package se.gu.smart.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.account.Account;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.security.session.SessionManager;
import se.gu.smart.service.AccountService;
import se.gu.smart.service.Services;

import static javafx.beans.binding.Bindings.createBooleanBinding;

public class CreateUserController extends BaseAdminController {

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final AccountRepository accountRepository = Repositories.getUserAccountRepository();
    private Account user;


    @FXML
    private TextField usernameField;

    @FXML
    private TextField displaynameField;

    @FXML
    private TextField passwordFieldStars;

    @FXML
    private TextField passwordFieldText;

    @FXML
    private Button createUserButton;

    @FXML
    private CheckBox adminPrivilegesOption;

    @FXML
    private CheckBox showPasswordCheckbox;

    @FXML
    public void initialize() {
        createUserButton.disableProperty().bind(createBooleanBinding(
                () -> usernameField.getText().trim().isEmpty()
                        || (passwordFieldStars.getText().trim().isEmpty() && passwordFieldText.getText().trim().isEmpty())
                        || displaynameField.getText().trim().isEmpty(),
                usernameField.textProperty(),
                passwordFieldStars.textProperty(),
                passwordFieldText.textProperty(),
                displaynameField.textProperty()
        ));
        final var activeSession = sessionManager.getActiveSession();
        if (activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }
        user = accountRepository.getAccount(activeSession.get().getAccountId()).get();
        this.showPassword(null);
    }

    @FXML
    void showPassword(ActionEvent event) {
        if (showPasswordCheckbox.isSelected()) {
            passwordFieldText.setText(passwordFieldStars.getText());
            passwordFieldText.setVisible(true);
            passwordFieldStars.setVisible(false);
            return;
        }
        passwordFieldStars.setText(passwordFieldText.getText());
        passwordFieldStars.setVisible(true);
        passwordFieldText.setVisible(false);
    }

    @FXML
    void pressCreateUser(ActionEvent event) {

        if (adminPrivilegesOption.isSelected()) {
            AccountService accService = Services.getUserAccountService();
            Account account = accService.createAdministrator(usernameField.getText(), passwordFieldStars.getText());
            account.setDisplayName(displaynameField.getText());

            System.out.println("Admin Created.");
        } else {
            AccountService accService = Services.getUserAccountService();
            Account account = accService.createUser(usernameField.getText(), passwordFieldStars.getText());
            account.setDisplayName(displaynameField.getText());

            System.out.println("User Created.");
        }

        System.out.println("Username field: " + usernameField.getText());
        System.out.println("Password field: " + passwordFieldStars.getText());
        System.out.println("Displayname field: " + displaynameField.getText());
    }
}
