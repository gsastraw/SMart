package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public final class ViewProfileController extends BaseUserController {

    @FXML
    void redirectMessages(MouseEvent event) {
        redirect(event, "user_messages");
    }

    @FXML
    void redirectNewProject(MouseEvent event) {
        redirect(event, "user_new_project");
    }

    @FXML
    void redirectCalender(MouseEvent event) {
        redirect(event, "user_calender");
    }

    @FXML
    void redirectProjects(MouseEvent event) {
        redirect(event, "login");
    }

    @FXML
    void redirectDashboard(MouseEvent event) {
        redirect(event, "user_dashboard");
    }

    @FXML
    void redirectTimesheet(MouseEvent event) {
        redirect(event, "user_timesheet");
    }

    @FXML
    public void initialize() {
    }
}
