package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import se.gu.smart.service.Services;
import se.gu.smart.service.UserAuthenticationService;

import static javafx.beans.binding.Bindings.createBooleanBinding;

public final class LoginController extends BaseUserController {

    private final UserAuthenticationService authService = Services.getUserAuthenticationService();

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button signInButton;

    @FXML
    public void initialize() {
        signInButton.disableProperty().bind(createBooleanBinding(
            () -> usernameField.getText().trim().isEmpty() || passwordField.getText().isEmpty(),
            usernameField.textProperty(),
            passwordField.textProperty())
        );
    }

    @FXML
    void logIn(MouseEvent event) {
        if (authService.authenticateUser(usernameField.getText(), passwordField.getText())) {
            redirect(event, "user_dashboard");
        } else {
            System.out.println("Username field: " + usernameField.getText());
            System.out.println("Password field: " + passwordField.getText());
        }
    }
}
