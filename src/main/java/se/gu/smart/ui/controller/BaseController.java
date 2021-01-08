package se.gu.smart.ui.controller;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.gu.smart.ui.util.FXMLUtil;

public class BaseController {

    protected void redirect(Event event, String fxmlFile) {
        var dashboardParent = FXMLUtil.loadFxml(fxmlFile);
        var dashboardScene = new Scene((Parent) dashboardParent);

        var window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.centerOnScreen();
        window.show();
    }
}
