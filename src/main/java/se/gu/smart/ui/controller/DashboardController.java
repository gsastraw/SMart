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

public final class DashboardController {

        @FXML private Button viewProfileButton;

        public void viewProfile(ActionEvent event) throws IOException {
            Parent viewProfileParent = FXMLLoader.load(getClass().getResource("fxml/viewProfile.fxml"));
            Scene viewProfileScene = new Scene(viewProfileParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(viewProfileScene);
            window.show();
        }
        public void initialize() {
        }
    }

