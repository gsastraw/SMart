package se.gu.smart.ui.controller;

import static javafx.beans.binding.Bindings.createBooleanBinding;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.gu.smart.ui.util.FXMLUtil;

public final class LoginController  {

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
        var dashboardParent = FXMLUtil.loadFxml("dashboard");
        var dashboardScene = new Scene((Parent) dashboardParent);

        var window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.centerOnScreen();
        window.show();
    }
}
