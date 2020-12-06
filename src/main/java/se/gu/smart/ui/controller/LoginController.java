package se.gu.smart.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public final class LoginController  {
    @FXML
    private Button logInButton;

    @FXML
    void logIn(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }
    @FXML
    public void initialize() {
    }
}
