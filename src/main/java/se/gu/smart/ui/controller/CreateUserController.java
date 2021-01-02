package se.gu.smart.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
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
    private DatePicker birthdayField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button createUserButton;

    @FXML
    private RadioButton adminPrivilegesOption;

    @FXML
    public void initialize() {
        createUserButton.disableProperty().bind(createBooleanBinding(
                () -> usernameField.getText().trim().isEmpty()
                        || passwordField.getText().trim().isEmpty()
                        || birthdayField.getValue()==null
                        || displaynameField.getText().trim().isEmpty(),
                usernameField.textProperty(),
                passwordField.textProperty(),
                displaynameField.textProperty(),
                birthdayField.valueProperty()
        ));
        final var activeSession = sessionManager.getActiveSession();
        if (activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }
        user = accountRepository.getAccount(activeSession.get().getAccountId()).get();
    }

    @FXML
    void pressCreateUser(ActionEvent event) {

        if (adminPrivilegesOption.isSelected()) {
            AccountService accService = Services.getUserAccountService();
            Account account = accService.createAdministrator(usernameField.getText(), passwordField.getText());
            account.setDisplayName(displaynameField.getText());
            account.setBirthdate(birthdayField.getValue());

            System.out.println("Admin Created.");
        } else {
            AccountService accService = Services.getUserAccountService();
            Account account = accService.createUser(usernameField.getText(), passwordField.getText());
            account.setDisplayName(displaynameField.getText());
            account.setBirthdate(birthdayField.getValue());

            System.out.println("User Created.");
        }

        System.out.println("Username field: " + usernameField.getText());
        System.out.println("Password field: " + passwordField.getText());
        System.out.println("Displayname field: " + displaynameField.getText());
    }
}
