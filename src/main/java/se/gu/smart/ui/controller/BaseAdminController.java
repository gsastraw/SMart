package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class BaseAdminController extends BaseController{

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
