package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public final class UserDashboardController extends BaseUserController {

    @FXML
    void redirectAdminDashboard(MouseEvent event) {
        redirect(event, "admin_dashboard");
    }
}