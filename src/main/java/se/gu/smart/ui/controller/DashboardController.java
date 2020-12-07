package se.gu.smart.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import se.gu.smart.ui.util.FXMLUtil;

public final class DashboardController {

    @FXML private Button viewProfileButton;

    public void viewProfile(ActionEvent event) {
        var viewProfileParent = FXMLUtil.loadFxml("viewProfile");
        var viewProfileScene = new Scene((Parent) viewProfileParent);

        var window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewProfileScene);
        window.show();
    }

    public void initialize() {

    }
}

