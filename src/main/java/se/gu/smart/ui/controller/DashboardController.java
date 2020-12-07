package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.gu.smart.ui.util.FXMLUtil;

public final class DashboardController {

    @FXML
    private Button messagesButton;

    @FXML
    void redirectMessages(MouseEvent event) {
        var dashboardParent = FXMLUtil.loadFxml("messages");
        var dashboardScene = new Scene((Parent) dashboardParent);

        var window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.centerOnScreen();
        window.show();
    }

    public void initialize() {

    }
}

