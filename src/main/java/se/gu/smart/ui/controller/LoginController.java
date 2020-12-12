package se.gu.smart.ui.controller;

import static javafx.beans.binding.Bindings.createBooleanBinding;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.exception.AccountNotFoundException;
import se.gu.smart.exception.InvalidUsernamePasswordException;
import se.gu.smart.security.session.SessionManager;
import se.gu.smart.service.Services;
import se.gu.smart.service.UserAuthenticationService;

public final class LoginController extends BaseController {

    private final UserAuthenticationService authService = Services.getUserAuthenticationService();
    private final SessionManager sessionManager = SessionManager.getInstance();

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button signInButton;
    @FXML
    private Text promptErrorText; //invisible in scenebuilder

    @FXML
    public void initialize() {
        signInButton.disableProperty().bind(createBooleanBinding(
            () -> usernameField.getText().trim().isEmpty() || passwordField.getText().isEmpty(),
            usernameField.textProperty(),
            passwordField.textProperty())
        );
    }

    @FXML
    void onEnterPressed(ActionEvent event) {
        if (usernameField.getText().trim().isEmpty() || passwordField.getText().isEmpty()) {
            return;
        }

        logIn(event);
    }

    @FXML
    void onSignInClicked(MouseEvent event) {
        logIn(event);
    }

    private void logIn(Event event) {
        try {
            var account = authService.authenticateUser(usernameField.getText(), passwordField.getText());
            sessionManager.setActiveSession(account.getAccountId());

            switch (account.getAccountType()) {
                case USER -> redirect(event, "user_dashboard");
                case ADMIN -> redirect(event, "admin_dashboard");
            }
        } catch (AccountNotFoundException | InvalidUsernamePasswordException e) {
            passwordField.clear();
            promptErrorText.setVisible(true);
        }
    }
}
