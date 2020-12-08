package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import static javafx.beans.binding.Bindings.createBooleanBinding;

public final class LoginController extends BaseUserController {

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
        redirect(event, "user_dashboard");
    }
}
