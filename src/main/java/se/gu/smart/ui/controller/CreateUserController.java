package se.gu.smart.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import se.gu.smart.service.Services;
import se.gu.smart.service.UserAccountService;

public class CreateUserController extends BaseAdminController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField displaynameField;

    @FXML
    private DatePicker birthdayField;

    @FXML
    private TextField passwordField;

    @FXML
    void pressCreateUser(ActionEvent event) {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            usernameField.setPromptText("You must fill in everything!");
            passwordField.setText("");
        } else {
            UserAccountService accService = Services.getUserAccountService();
            accService.createUser(usernameField.getText(), passwordField.getText());

            System.out.println("Username field: " + usernameField.getText());
            System.out.println("Password field: " + passwordField.getText());
        }
    }
}
