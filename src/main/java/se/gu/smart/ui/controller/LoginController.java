package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.gu.smart.ui.util.FXMLUtil;

public final class LoginController  {

    @FXML
    void logIn(MouseEvent event) {
        var dashboardParent = FXMLUtil.loadFxml("dashboard");
        var dashboardScene = new Scene((Parent) dashboardParent);

        var window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    @FXML
    public void initialize() {
    }
}
