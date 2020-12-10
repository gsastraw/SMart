package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.gu.smart.ui.util.FXMLUtil;

public class BaseAdminController {

    protected void redirect(MouseEvent event, String fxmlFile) {
        var dashboardParent = FXMLUtil.loadFxml(fxmlFile);
        var dashboardScene = new Scene((Parent) dashboardParent);

        var window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void redirectResetSystem(MouseEvent event) {
        redirect(event, "admin_reset_system");
    }

    @FXML
    void redirectReports(MouseEvent event) {
        redirect(event, "admin_reports");
    }

    @FXML
    void redirectManageUsers(MouseEvent event) {
        redirect(event, "admin_manage_users");
    }

    @FXML
    void redirectCreateUser(MouseEvent event) {
        redirect(event, "admin_create_user");
    }

    @FXML
    void redirectAdministration(MouseEvent event) {
        redirect(event, "admin_administration");
    }

    @FXML
    void redirectAdminDashboard(MouseEvent event) {
        redirect(event, "admin_dashboard");
    }

    @FXML
    void redirectUserDashboard(MouseEvent event) {
        redirect(event, "user_dashboard");
    }

    @FXML
    public void initialize() {

    }
}
